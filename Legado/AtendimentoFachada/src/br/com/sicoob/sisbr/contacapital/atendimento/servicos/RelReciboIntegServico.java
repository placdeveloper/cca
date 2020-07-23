package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.negocio.excecao.RelatorioException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.retaguarda.comum.RecuperadorCooperativa;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelParcelamentoCCADelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.DadosRelParcelamentoCCAVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelatorioParcelamentoCCAVO;

@RemoteService
public class RelReciboIntegServico extends AtendimentoFachada {
	
	RelParcelamentoCCADelegate relParDelegate = AtendimentoFabricaDelegates.getInstance().criarRelParcelamentoCCADelegate();
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		String[] minMax = obterMatricMinimaMaxima();
		
		retorno.getDados().put("pacs", recuperarListaPACs());
		retorno.getDados().put("min", minMax[0]);
		retorno.getDados().put("max", minMax[1]);		
		return retorno;	
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		final RelatorioParcelamentoCCAVO vo = (RelatorioParcelamentoCCAVO) dto.getDados()
				.get("filtro");
		relParDelegate.validarCamposReciboInteg(vo);
		List<DadosRelParcelamentoCCAVO> dados = relParDelegate.gerarRelReciboInteg(vo);
		Map<String, Object> parametros = new HashMap<String, Object>();
		setarParametros(parametros, vo);
		gerarRelatorio(dados, parametros);
		return new RetornoDTO();
	}

	private void setarParametros(Map<String, Object> parametros,
			RelatorioParcelamentoCCAVO vo) throws BancoobException {
		String sNomeCoop = RecuperadorCooperativa.getInstance()
				.obterSiglaCooperativa(obterCooperativa());
		String nomePac = "";
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("NUM_COOPERATIVA", new DecimalFormat("0000").format(Integer.valueOf(obterCooperativa())));
		parametros.put("DESC_SIGLA_COOP", sNomeCoop);
		parametros.put("TXT_PAC", nomePac);
		parametros.put("COD_RELATORIO", "CCA-009");
	}

	private void gerarRelatorio(List<DadosRelParcelamentoCCAVO> dados,
			Map<String, Object> parametros) throws RelatorioException {

		RelatorioAtendimentoCCA<DadosRelParcelamentoCCAVO> relatorio = new RelatorioAtendimentoCCA<DadosRelParcelamentoCCAVO>(
				dados, "CCARelReciboInteg.jasper", parametros);

		ContextoHttp.getInstance()
				.adicionarContexto("CCARelatorioReciboInteg",
						relatorio.gerarSincronamente());
	}



}
