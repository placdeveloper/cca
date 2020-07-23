package br.com.sicoob.sisbr.cca.movimentacao.servicos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.entidades.negocio.entidades.OrigemBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrigemBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoBloqueio;
import br.com.sicoob.cca.movimentacao.negocio.delegates.BloqueioContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.OrigemBloqueioCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.movimentacao.MovimentacaoContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.conversor.ConversorBloqueioContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.enums.EnumTipoPesquisaBloqueio;
import br.com.sicoob.sisbr.cca.movimentacao.vo.BloqueioContaCapitalVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;

/**
 * Servico fachada para funcionalidade de bloqueio de saldo de capital
 * @author Nairon.Silva
 */
@RemoteService
public class BloqueioContaCapitalServico extends MovimentacaoContaCapital {

	private BloqueioContaCapitalDelegate bloqueioContaCapitalDelegate = 
			ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarBloqueioContaCapitalDelegate();
	
	private ConversorBloqueioContaCapital conversor = new ConversorBloqueioContaCapital();
	
	/**
	 * Obtem as definicoes iniciais para tela de consulta
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();				
		
		retornoDTO.getDados().put("cboTipoPesquisa", criarCboTipoPesquisa());
		retornoDTO.getDados().put("cboSituacaoBloqueio", criarCboSituacaoBloqueio());
		retornoDTO.getDados().put("cboTipoBloqueio", criarCboTipoBloqueio());
		
		return retornoDTO;
	}

	private List<ItemListaVO> criarCboTipoPesquisa() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		for (EnumTipoPesquisaBloqueio tipoPesquisa : EnumTipoPesquisaBloqueio.values()) {
			lista.add(new ItemListaVO(tipoPesquisa.getCod(), tipoPesquisa.getLabel()));
		}
		return lista;
	}
	
	private List<ItemListaVO> criarCboSituacaoBloqueio() {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		lista.add(new ItemListaVO("", "TODAS"));
		for (EnumSituacaoBloqueio situacao : EnumSituacaoBloqueio.values()) {
			lista.add(new ItemListaVO(situacao.getCodigo().toString(), situacao.getDescricao()));
		}
		return lista;
	}
	
	private List<ItemListaVO> criarCboTipoBloqueio() throws BancoobException {
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>();
		OrigemBloqueioCapitalDelegate origemBloqueioCapitalDelegate = 
				ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarOrigemBloqueioCapitalDelegate();
		List<OrigemBloqueioCapital> origens = origemBloqueioCapitalDelegate.listar();
		for (OrigemBloqueioCapital origem : origens) {
			lista.add(new ItemListaVO(origem.getId().toString(), origem.getNomeOrigemBloqueio()));
		}
		return lista;
	}
	
	/**
	 * Realiza a consulta
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		BloqueioContaCapitalVO vo = (BloqueioContaCapitalVO) reqDTO.getDados().get("vo");
		BloqueioContaCapitalDTO dto = conversor.converterVOParaDTO(vo);
		List<BloqueioContaCapitalDTO> lista = bloqueioContaCapitalDelegate.consultarBloqueios(dto);
		if(lista != null && !lista.isEmpty()) {
			retornoDTO.getDados().put("registros", conversor.converterListaDTOParaVO(lista));
		}
		return retornoDTO;
	}
	
	/**
	 * Obtem as informacoes para a tela de inclusao de bloqueio
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterInformacoesBloqueio(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		Integer idContaCapital = (Integer) reqDTO.getDados().get("idContaCapital");
		retornoDTO.getDados().put("valorIntegralizado", calcularValorIntegralizado(idContaCapital));
		retornoDTO.getDados().put("valorBloqueado", calcularValorBloqueado(idContaCapital));
		retornoDTO.getDados().put("cboTipoBloqueio", Arrays.asList(
				new ItemListaVO(EnumOrigemBloqueioCapital.JUDICIAL.getCodigo().toString(), EnumOrigemBloqueioCapital.JUDICIAL.getDescricao())));
		return retornoDTO;
	}
	
	/**
	 * Realiza a inclusao do bloqueio
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO incluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		BloqueioContaCapitalVO vo = (BloqueioContaCapitalVO) reqDTO.getDados().get("vo");
		bloqueioContaCapitalDelegate.incluir(conversor.converterVOParaDTO(vo));
		retornoDTO.getDados().put("msg", "Dados gravados com sucesso.");
		return retornoDTO;
	}
	
	/**
	 * Obtem as informacoes para a tela de desbloqueio de capital
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterInformacoesDesbloqueio(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		InstituicaoIntegracaoDelegate instituicaoIntegracaoDelegate = 
				ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
		Integer idInstituicao = (Integer) reqDTO.getDados().get("idInstituicao");
		CentralCooperativaDTO central = instituicaoIntegracaoDelegate.consultarCentralCooperativa(idInstituicao);
		InstituicaoIntegracaoDTO instituicao = instituicaoIntegracaoDelegate.obterInstituicaoIntegracao(idInstituicao);
		retornoDTO.getDados().put("central", central == null ? "" : (central.getNumCentral() + " - " + central.getDescCentral()));
		retornoDTO.getDados().put("instituicao", instituicao.getNumero() + " - " + instituicao.getSiglaInstituicao()); 
		return retornoDTO;
	}
	
	/**
	 * Realiza o desbloqueio de capital
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO desbloquear(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		BloqueioContaCapitalVO vo = (BloqueioContaCapitalVO) reqDTO.getDados().get("vo");
		bloqueioContaCapitalDelegate.desbloquear(conversor.converterVOParaDTO(vo));
		retornoDTO.getDados().put("msg", "Dados gravados com sucesso.");
		return retornoDTO;
	}

}
