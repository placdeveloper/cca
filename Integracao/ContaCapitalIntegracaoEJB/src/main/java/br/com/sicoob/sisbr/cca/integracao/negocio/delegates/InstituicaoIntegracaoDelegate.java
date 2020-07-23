/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TipoGrauCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator.ContaCapitalIntegracaoServiceLocator;

/**
 * A Classe InstituicaoIntegracaoDelegate.
 */
public class InstituicaoIntegracaoDelegate extends ContaCapitalIntegracaoDelegate<InstituicaoIntegracaoServico> {


	/**
	 * Recupera a unica instancia de InstituicaoIntegracaoDelegate.
	 *
	 * @return uma instancia de InstituicaoIntegracaoDelegate
	 */
	public static InstituicaoIntegracaoDelegate getInstance() {
		return new InstituicaoIntegracaoDelegate();
	}		
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected InstituicaoIntegracaoServico localizarServico() {
		return (InstituicaoIntegracaoServico) ContaCapitalIntegracaoServiceLocator.getInstance().localizarInstituicaoIntegracaoServico();
	}

	/**
	 * Obtém o número da cooperativa a partir do número da instituição
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException{
		return getServico().obterNumeroCooperativa(idInstituicao);
	}
	
	
	/**
	 * Obtem o grau da cooperativa a partir do numero da instituicao 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public TipoGrauCooperativaDTO obterTipoGrauCooperativa(Integer idInstituicao) throws BancoobException{
		return getServico().obterTipoGrauCooperativa(idInstituicao);
	}
	
	/**
	 * Verifica se a instituicao esta ativa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Boolean isInstituicaoAtiva(Integer idInstituicao) throws BancoobException {
		return getServico().isInstituicaoAtiva(idInstituicao);
	}
	
	
	/**
	 * Obtem o id da instituição a partir do número da coperativa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterIdInstituicao(Integer numCoop) throws BancoobException {
		return getServico().obterIdInstituicao(numCoop);
	}
	/**
	 * Obtem o tipo da cooperativa a partir do id da instituição
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterTipoCooperativa(Integer idInstituicao) throws BancoobException {
		return getServico().obterTipoCooperativa(idInstituicao);
	}
	
	/**
	 * Lista instituições centrais
	 * @return
	 * @throws BancoobException
	 */
	public List<ItemListaIntegracaoDTO> listarCentral() throws BancoobException {
		return getServico().listarCentral();
	}
	
	/**
	 * Lista instituições centrais
	 * @return
	 * @throws BancoobException
	 */
	public List<ItemListaIntegracaoDTO> listarCentralEConfederacao() throws BancoobException {
		return getServico().listarCentralEConfederacao();
	}
	
	/**
	 * Lista instituições centrais instituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<ItemListaIntegracaoDTO> listarCentralInstituicao() throws BancoobException {
		return getServico().listarCentralInstituicao();
	}
	
	/**
	 * {@link InstituicaoIntegracaoServico#listarSingulares(Integer)}
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<ItemListaIntegracaoDTO> listarSingulares(Integer idInstituicao) throws BancoobException {
		return getServico().listarSingulares(idInstituicao);
	}
	
	/**
	 * listar Singulares Instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<ItemListaIntegracaoDTO> listarSingularesInstituicao(Integer idInstituicao) throws BancoobException {
		return getServico().listarSingularesInstituicao(idInstituicao);
	}

	/**
	 * Consultar central cooperativa.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return CentralCooperativaDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public CentralCooperativaDTO consultarCentralCooperativa(Integer idInstituicao) throws BancoobException {
		return getServico().consultarCentralCooperativa(idInstituicao);
	}
	
	/**
	 * Consultar id institucao centralpor singular.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer consultarIdInstitucaoCentralporSingular(Integer idInstituicao) throws BancoobException{
		return getServico().consultarIdInstitucaoCentralporSingular(idInstituicao);
	}
	/**
	 * Obtem a instituição a partir do id da instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public InstituicaoIntegracaoDTO obterInstituicaoIntegracao(Integer idInstituicao) throws BancoobException {
		return getServico().obterInstituicaoIntegracao(idInstituicao);
	}
	
	/**
	 * {@link InstituicaoIntegracaoServico#obterInformacoesInstituicaoSCI(Integer)}
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public InstituicaoIntegracaoDTO obterInformacoesInstituicaoSCI(Integer idInstituicaoSCI) throws BancoobException {
		return getServico().obterInformacoesInstituicaoSCI(idInstituicaoSCI);
	}

	/**
	 * Obtem InstituicaoIntegracaoDTO pela instituicao e pac
	 * @param idInstituicao
	 * @param pac
	 * @return
	 * @throws BancoobException
	 */
	public InstituicaoIntegracaoDTO obterInformacoesInstituicaoSCI(Integer idInstituicao, String pac) throws BancoobException {
		return getServico().obterInformacoesInstituicaoSCI(idInstituicao, pac);
	}
}
