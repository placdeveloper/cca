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
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.excecao.AtendimentoCadastroException;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosRelDestinacaoJurosCCAProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelDestinacaoJurosCCAVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelDestinacaoJurosCCAServico extends AtendimentoFachada {
	
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");


	private RelDestinacaoJurosDelegate relDestinacaoJurosDelegate = AtendimentoFabricaDelegates
			.getInstance().criarRelDestinacaoJurosDelegate();
	private DestinoProvisaoDelegate destinoProvisaoDelegate = AtendimentoFabricaDelegates
			.getInstance().criarDestinoProvisaoDelegate();

	private static final String KEY_LISTA_DATAS_DESTINACAO = "keyListaDatasDestinacao";
	private static final String KEY_DATA_ATUAL_MOVIMENTO = "keyDataAtualMovimento";
	private static final String KEY_LISTA_PACS = "keyListaPacs";
	private static final String KEY_MATRICULA_MAX_MIN = "keyMatriculaMaxMin";
	private static final String KEY_PARAMETROS_TELA = "keyParametrosTela";
	private static final String NOME_RELATORIO_SESSAO = "CCARelDestinacaoJuros";
	private static final String NOME_RELATORIO_ARQUIVO = "CCARelDestinacaoJuros.jasper";

	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {

		RetornoDTO retorno = new RetornoDTO();

		// @TODO Recuperar data de destinação
		retorno.getDados().put(KEY_LISTA_DATAS_DESTINACAO,
				obterListaDataDestinacao());
		retorno.getDados().put(KEY_DATA_ATUAL_MOVIMENTO,
				obterDataAtualMovimento());
		retorno.getDados().put(KEY_LISTA_PACS, obterListaPACs());
		retorno.getDados()
				.put(KEY_MATRICULA_MAX_MIN, obterMatricMinimaMaxima());

		return retorno;
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {

		List<DadosRelDestinacaoJurosCCAProxy> dadosRelatorio;
		Map<String, Object> parametros;
		RelatorioAtendimentoCCA<DadosRelDestinacaoJurosCCAProxy> relatorio;

		RelDestinacaoJurosCCAVO parametrosTela = (RelDestinacaoJurosCCAVO) dto
				.getDados().get(KEY_PARAMETROS_TELA);

		parametros = new HashMap<String, Object>();
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("DADOS_COOPERATIVA", descricaoCooperativa());
		parametros.put("DATA_ATUAL_PRODUTO", obterDataAtualProduto());
		parametros.put("QUEBRA_POR_PAC", parametrosTela.getQuebraPorPac());
		parametros.put("DESC_PERIODO_MATRICULA", obterDescricaoPeriodoMatricula(parametrosTela));
		parametros.put("DESCRICAO_ORDENACAO", obterDescricaoOrdenacao(parametrosTela));
		parametros.put("NUM_PAC_SELECIONADO", parametrosTela.getNumPac().toString());

		dadosRelatorio = relDestinacaoJurosDelegate.obterRelDestJurosCCA(parametrosTela);

		relatorio = new RelatorioAtendimentoCCA<DadosRelDestinacaoJurosCCAProxy>(
				dadosRelatorio, NOME_RELATORIO_ARQUIVO, parametros);

		ContextoHttp.getInstance().adicionarContexto(NOME_RELATORIO_SESSAO,
				relatorio.gerarSincronamente());

		return new RetornoDTO();
	}

	private String descricaoCooperativa() throws BancoobException {
		StringBuilder sb = new StringBuilder();

		sb.append(obterCooperativaFmt());
		sb.append(" - ");
		sb.append(obterSiglaCooperativa());

		return sb.toString();
	}

	private List<DateTime> obterListaDataDestinacao()
			throws AtendimentoCadastroException {
		List<Date> listaDatas = destinoProvisaoDelegate.obterDataDestinacao();

		return converterListaDataParaDateTime(listaDatas);
	}
	
	private String obterDescricaoPeriodoMatricula(RelDestinacaoJurosCCAVO parametrosTela) throws BancoobException{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Matrículas: ");
		sb.append(parametrosTela.getNumMatriculaInicial());
		sb.append(" a ");
		sb.append(parametrosTela.getNumMatriculaFinal());
		sb.append("    Período: ");
		sb.append(DataUtil.converterDateToString(parametrosTela.getDataInicial()));
		sb.append(" a ");
		sb.append(DataUtil.converterDateToString(parametrosTela.getDataFinal()));
		
		return sb.toString();
	}
	
	private String obterDescricaoOrdenacao(RelDestinacaoJurosCCAVO parametrosTela) throws BancoobException{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Data Destinação: ");
		
		if(parametrosTela.getDataDestinacao() != null){
			sb.append(DataUtil.converterDateToString(parametrosTela.getDataDestinacao()));			
		}else{
			sb.append("TODAS");						
		}
		
		sb.append("  Ordenação: ");
		
		switch (parametrosTela.getOrdenacao()) {
		case 1:
			sb.append("MATRICULA");
			break;
		case 2:
			sb.append("NOME CLIENTE");
			break;
		case 3:
			sb.append("DATA DESTINAÇÃO");
			break;
		}
		
		return sb.toString();
	}	
		
}
