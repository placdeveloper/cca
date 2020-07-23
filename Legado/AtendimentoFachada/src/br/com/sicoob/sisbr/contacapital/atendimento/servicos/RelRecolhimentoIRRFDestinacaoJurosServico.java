package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.DestinoProvisaoDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelDestinacaoJurosDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosRelRecolhimentoIRRFDesJurosProxy;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelRecolhimentoIRRFDestinacaoJurosServico extends
		AtendimentoFachada {
	
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");

	private DestinoProvisaoDelegate destinoProvisaoDelegate = AtendimentoFabricaDelegates
			.getInstance().criarDestinoProvisaoDelegate();
	private RelDestinacaoJurosDelegate relDestinacaoJurosDelegate = AtendimentoFabricaDelegates
			.getInstance().criarRelDestinacaoJurosDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		String[] matriculas = obterMatricMinimaMaxima();
		retorno.getDados().put("min", matriculas[0]);
		retorno.getDados().put("max", matriculas[1]);
		retorno.getDados().put("listaPacs", obterListaPACs());
		retorno.getDados().put("dataAtualMovimento",obterDataAtualMovimento());

		List<Date> listaDataDestinacaoAux = destinoProvisaoDelegate.obterDataDestinacao();
		List<DateTime> listaDataDestinacao = converterListaDataParaDateTime(listaDataDestinacaoAux);
		retorno.getDados().put("listaDataDestinacao", listaDataDestinacao);
		
		return retorno;
	}


	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		
		List<DadosRelRecolhimentoIRRFDesJurosProxy> dadosRelatorio = null;
		Map<String, Object> parametros = null;
		RelatorioAtendimentoCCA<DadosRelRecolhimentoIRRFDesJurosProxy> relatorio = null;

		String matriculaInicial = (String) dto.getDados().get("matriculaInicial");
		String matriculaFinal = (String) dto.getDados().get("matriculaFinal");
		Integer pac = (Integer) dto.getDados().get("pac");
		
		String dataDestinacaoAux = (String) dto.getDados().get("dataDestinacao");
		Date dataDestinacao = null;
		if(dataDestinacaoAux != null && !"TODAS".equals(dataDestinacaoAux)){
			dataDestinacao = br.com.bancoob.util.DataUtil.converterStringToDate(dataDestinacaoAux, "dd/MM/yyyy");
		}
		
		DateTime dataInicialAux = (DateTime) dto.getDados().get("dataInicial");
		Date dataInicial = null;
		if(dataInicialAux != null){
			dataInicial = new Date(dataInicialAux.getTime());
		}
		
		DateTime dataFinalAux = (DateTime) dto.getDados().get("dataFinal");
		Date dataFinal = null;
		if(dataFinalAux != null){
			dataFinal = new Date(dataFinalAux.getTime());
		}
		
		String ordenacao = (String) dto.getDados().get("ordenacao");
		Boolean quebraPorPac = (Boolean) dto.getDados().get("quebraPorPac");

		parametros = new HashMap<String, Object>();
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("DADOS_COOPERATIVA", descricaoCooperativa());
		parametros.put("DATA_ATUAL_PRODUTO", obterDataAtualProduto());
		parametros.put("QUEBRA_POR_PAC", quebraPorPac);
		parametros.put("DESC_PERIODO_MATRICULA", obterDescricaoPeriodoMatricula(matriculaInicial,matriculaFinal,dataInicial,dataFinal));
		parametros.put("DESCRICAO_ORDENACAO", obterDescricaoOrdenacao(dataDestinacaoAux,Integer.valueOf(ordenacao)));
		parametros.put("NUM_PAC", pac);
		
		dadosRelatorio = relDestinacaoJurosDelegate
				.obterDadosRelRecolhimentoIRRFDestinacaoJuros(Long.valueOf(matriculaInicial),
						Long.valueOf(matriculaFinal), dataInicial, dataFinal, dataDestinacao,
						Integer.valueOf(ordenacao), pac, quebraPorPac);

		relatorio = new RelatorioAtendimentoCCA<DadosRelRecolhimentoIRRFDesJurosProxy>(
				dadosRelatorio, "CCARelRecolhimentoIRRFDestinacaoJuros.jasper", parametros);

		ContextoHttp.getInstance().adicionarContexto("CCARelatorioRecolhimentoIRRFDestJuros",
				relatorio.gerarSincronamente());
		
		return null;
	}
	
	private String obterDescricaoPeriodoMatricula(String matriculaInicial,
			String matriculaFinal, Date dataInicial, Date dataFinal)
			throws BancoobException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Matrículas: ");
		sb.append(matriculaInicial);
		sb.append(" a ");
		sb.append(matriculaFinal);
		sb.append("    Período: ");
		sb.append(DataUtil.converterDateToString(dataInicial));
		sb.append(" a ");
		sb.append(DataUtil.converterDateToString(dataFinal));
		
		return sb.toString();
	}
	
	private String obterDescricaoOrdenacao(String dataDestinacao, int ordenacao){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Data Destinação: ");
		
		if(dataDestinacao != null)
			sb.append(dataDestinacao);
		
		sb.append("  Ordenação: ");
		
		switch (ordenacao) {
		case 1:
			sb.append("MATRICULA");
			break;
		case 2:
			sb.append("CLIENTE");
			break;
		case 3:
			sb.append("NOME");
			break;
		}
		
		return sb.toString();
	}
	
	private String descricaoCooperativa() throws BancoobException {
		StringBuilder sb = new StringBuilder();

		sb.append(obterCooperativaFmt());
		sb.append(" - ");
		sb.append(obterSiglaCooperativa());

		return sb.toString();
	}

	
	
}
