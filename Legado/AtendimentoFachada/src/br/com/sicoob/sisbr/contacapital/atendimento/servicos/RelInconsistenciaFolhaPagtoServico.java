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
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelInconsistenciaFolhaPagtoDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosRetornoArquivoProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.DadosRelInconsistenciaFolhaPagtoVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelInconsistenciaFolhaPagtoServico extends AtendimentoFachada {

	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");	

	private RelInconsistenciaFolhaPagtoDelegate relInconsistenciaFolhaPagtoDelegate = AtendimentoFabricaDelegates
			.getInstance().criarRelInconsistenciaFolhaPagtoDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		return null;
	}
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		Integer numPessoa = (Integer) dto.getDados().get("numPessoa");
		DateTime dataMesAno = (DateTime) dto.getDados().get("dataMesAno");
		Integer sequencialArquivo = (Integer) dto.getDados().get("sequencialArquivo");
		Integer tipo = (Integer) dto.getDados().get("tipo");
		Integer ordenacao = (Integer) dto.getDados().get("ordenacao");
		
		Long numPessoaLng = null;
		if(numPessoa != null){
			numPessoaLng = numPessoa.longValue();
		}
		
		Date mesAnoRef = null;
		if(dataMesAno != null){
			mesAnoRef = new Date(dataMesAno.getTime());
		}

		Long sequencialArquivoLng = null;
		if(sequencialArquivo != null){
			sequencialArquivoLng = sequencialArquivo.longValue();
		}
		
		Integer numAnoMesInicial = null;
		try{
			numAnoMesInicial = DataUtil.converterDateToIntMesAno(mesAnoRef);
		}catch (Exception e) {
		}
		
		List<DadosRelInconsistenciaFolhaPagtoVO> lista = relInconsistenciaFolhaPagtoDelegate
				.obterDadosRelatorio(numPessoaLng, numAnoMesInicial,
						sequencialArquivoLng, tipo, ordenacao);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		String siglaCooperativa = obterSiglaCooperativa();
		parametros.put("NUM_COOPERATIVA", obterCooperativaFmt());
		parametros.put("DESC_SIGLA_COOP", siglaCooperativa);
		parametros.put("DATA_PROCESSAMENTO", DataUtil.formataData(new Date(
				obterDataAtualProduto().getTime()), "dd/MM/yyyy"));
		
		parametros.put("TXT_COMPLEMENTO",complemento(tipo,ordenacao));

		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");  
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		
		RelatorioAtendimentoCCA<DadosRelInconsistenciaFolhaPagtoVO> relatorio = new RelatorioAtendimentoCCA<DadosRelInconsistenciaFolhaPagtoVO>(
				lista, "CCARelInconsistCapConsig.jasper", parametros);
		
		Object conteudo = relatorio.gerarSincronamente();
		ContextoHttp.getInstance().adicionarContexto("CCARelInconsistCapConsig", conteudo);
		
		
		return null;
	}

	private String complemento(Integer tipo, Integer ordenacao) {
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo erro: ");
		switch (tipo) {
		case -1:
			sb.append("TODOS");
			break;
		case 1:
			sb.append("GRAVE");
			break;
		case 2:
			sb.append("ALERTA");
			break;
		default:
			break;
		}
		sb.append(" ").append("Ordenação: ");
		
		switch (ordenacao) {
		case 1:
			sb.append("CÓDIGO");
			break;
		case 2:
			sb.append("MATRÍCULA");
			break;
		case 3:
			sb.append("NOME");
			break;
		default:
			break;
		}
		
		return sb.toString();
	}

	public RetornoDTO obterArquivos(RequisicaoReqDTO dto)
			throws BancoobException {
		Integer numPessoaJuridica = (Integer) dto.getDados().get("numPessoaJuridica");
		DateTime mesAnoReferencia = (DateTime) dto.getDados().get("mesAnoReferencia");
		
		Long numPessoa = null;
		if(numPessoaJuridica != null){
			numPessoa = numPessoaJuridica.longValue();
		}
		
		Date mesAnoRef = null;
		if(mesAnoReferencia != null){
			mesAnoRef = new Date(mesAnoReferencia.getTime());
		}
		
		Integer numAnoMesInicial = DataUtil.converterDateToIntMesAno(mesAnoRef);
		
		List<DadosRetornoArquivoProxy> listaArquivo = relInconsistenciaFolhaPagtoDelegate.obterDadosArquivos(numPessoa, numAnoMesInicial);
		RetornoDTO retornoDTO = new RetornoDTO();
		retornoDTO.getDados().put("listaArquivo", listaArquivo);
		return retornoDTO;
	}

	
}
