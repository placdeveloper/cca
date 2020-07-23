package br.com.sicoob.sisbr.cca.relatorios.servicos;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelBloqueioDelegate;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;

/**
 * Servico de fachada para gerar os relatorios de bloqueio
 * @author Nairon.Silva
 */
@RemoteService
public class RelBloqueioServico extends RelatoriosContaCapital {

	private RelBloqueioDelegate relBloqueioDelegate = 
			ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelBloqueioDelegate();
	
	/**
	 * Emite o relatorio de historico de bloqueio
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirRelatorioHistoricoBloqueio(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		BloqueioContaCapitalDTO filtro = new BloqueioContaCapitalDTO();
		filtro.setIdBloqueio(Integer.valueOf(reqDTO.getDados().get("idBloqueio").toString()));
		filtro.setIdInstituicao(Integer.valueOf(reqDTO.getDados().get("idInstituicao").toString()));
		ContextoHttp.getInstance().adicionarContexto("RelHistoricoBloqueio", relBloqueioDelegate.gerarRelatorioHistoricoBloqueio(filtro));
		return retorno;
	}
	
	/**
	 * Emite o relatorio de bloqueios
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirRelatorioBloqueios(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		String tipoPesquisa = reqDTO.getDados().get("tipoPesquisa").toString();
		BloqueioContaCapitalDTO filtro = montarDTOConsulta(reqDTO);
		ContextoHttp.getInstance().adicionarContexto("RelBloqueios", relBloqueioDelegate.gerarRelatorioBloqueios(tipoPesquisa, filtro));
		return retorno;
	}

	private BloqueioContaCapitalDTO montarDTOConsulta(RequisicaoReqDTO reqDTO) {
		Map<String, Object> dados = reqDTO.getDados();
		BloqueioContaCapitalDTO dto = new BloqueioContaCapitalDTO();
		dto.setIdInstituicao(Integer.valueOf(dados.get("idInstituicao").toString()));
		dto.setNomePessoa(dados.get("nomePessoa") == null ? null : dados.get("nomePessoa").toString());
		dto.setCpfCnpj(dados.get("cpfCnpj") == null ? null : dados.get("cpfCnpj").toString());
		dto.setCodTipoBloqueio(dados.get("codTipoBloqueio") == null ? null : Integer.valueOf(dados.get("codTipoBloqueio").toString()));
		dto.setNomeTipoBloqueio(dados.get("nomeTipoBloqueio") == null ? null : dados.get("nomeTipoBloqueio").toString());
		dto.setNumContaCapital(dados.get("numContaCapital") == null ? null : Integer.valueOf(dados.get("numContaCapital").toString()));
		dto.setNumProtocolo(dados.get("numProtocolo") == null ? null : dados.get("numProtocolo").toString());
		dto.setCodSituacaoBloqueio(dados.get("codSituacaoBloqueio") == null ? null : Integer.valueOf(dados.get("codSituacaoBloqueio").toString()));
		return dto;
	}
	
}
