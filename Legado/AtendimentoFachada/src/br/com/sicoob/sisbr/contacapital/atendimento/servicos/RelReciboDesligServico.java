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
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.DadosRelReciboDesligVO;

@RemoteService
public class RelReciboDesligServico extends AtendimentoFachada {

	RelContaCapitalDelegate relCcaDelegate = AtendimentoFabricaDelegates
			.getInstance().criarRelContaCapitalDelegate();
	
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");

	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		return retorno;
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		Integer matricula = (Integer) dto.getDados().get("numMatric");
		List<DadosRelReciboDesligVO> dados = relCcaDelegate.gerarRelReciboDeslig(matricula.longValue());
		Map<String, Object> parametros = new HashMap<String, Object>();
		setarParametros(parametros, matricula.longValue());
		gerarRelatorio(dados, parametros);
		return new RetornoDTO();
	}

	private void setarParametros(Map<String, Object> parametros, Long matricula)
			throws BancoobException {
		String sNomeCoop = RecuperadorCooperativa.getInstance()
				.obterSiglaCooperativa(obterCooperativa());
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("NUM_COOPERATIVA", new DecimalFormat("0000")
				.format(Integer.valueOf(obterCooperativa())));
		parametros.put("DESC_SIGLA_COOP", sNomeCoop);
		parametros.put("COD_RELATORIO", "CCA-043");
	}

	private void gerarRelatorio(List<DadosRelReciboDesligVO> dados,
			Map<String, Object> parametros) throws RelatorioException {

		RelatorioAtendimentoCCA<DadosRelReciboDesligVO> relatorio = new RelatorioAtendimentoCCA<DadosRelReciboDesligVO>(
				dados, "CCARelReciboDeslig.jasper", parametros);

		ContextoHttp.getInstance().adicionarContexto(
				"CCARelatorioReciboDeslig", relatorio.gerarSincronamente());
	}

	
}
