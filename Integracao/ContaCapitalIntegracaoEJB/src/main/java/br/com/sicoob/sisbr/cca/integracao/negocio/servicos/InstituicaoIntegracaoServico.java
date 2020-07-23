package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TipoGrauCooperativaDTO;

/**
 * A Interface InstituicaoIntegracaoServico.
 */
public interface InstituicaoIntegracaoServico extends ContaCapitalIntegracaoServico {

	/**
	 * Obtem o numero da cooperativa a partir do numero da instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException;	


	/**
	 * Obtem o grau da cooperativa a partir do numero da instituicao 			
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	TipoGrauCooperativaDTO obterTipoGrauCooperativa(Integer idInstituicao) throws BancoobException;		
	
	/**
	 * Obtem codigo instituição a partir do número da cooperativa
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	Integer obterIdInstituicao(Integer numCoop) throws BancoobException;
	/**
	 * Obtem o tipo da cooperativa a partir do id da instituição
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Integer obterTipoCooperativa(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Lista instituições centrais
	 * @return
	 * @throws BancoobException
	 */
	List<ItemListaIntegracaoDTO> listarCentral() throws BancoobException;
	
	/**
	 * Lista instituições centrais e confederacao
	 * @return
	 * @throws BancoobException
	 */
	List<ItemListaIntegracaoDTO> listarCentralEConfederacao() throws BancoobException;
	
	/**
	 * Lista instituições centrais instituicao
	 * @return
	 * @throws BancoobException
	 */
	List<ItemListaIntegracaoDTO> listarCentralInstituicao() throws BancoobException;
	
	/**
	 * Lista instituicoes singulares pela central
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<ItemListaIntegracaoDTO> listarSingulares(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Lista instituicoes singulares pela central instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<ItemListaIntegracaoDTO> listarSingularesInstituicao(Integer idInstituicao) throws BancoobException;

	/**
	 * Verifica se a cooperativa esta ativa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Boolean isInstituicaoAtiva(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Consultar central cooperativa.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return CentralCooperativaDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	CentralCooperativaDTO consultarCentralCooperativa(Integer idInstituicao) throws BancoobException;

	/**
	 * Consultar id institucao centralpor singular.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer consultarIdInstitucaoCentralporSingular(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter instituicao integracao.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return InstituicaoIntegracaoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	InstituicaoIntegracaoDTO obterInstituicaoIntegracao(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter instituicao integracao.
	 *
	 * @param numCoop o valor do num da cooperativa
	 * @return InstituicaoIntegracaoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	InstituicaoIntegracaoDTO obterInstituicaoIntegracaoPorNumCoop(Integer numCoop) throws BancoobException;
	
	/**
	 * Obtem informacoes da instituicao em questao
	 * @param idInstituicaoSCI
	 * @return
	 * @throws BancoobException
	 */
	InstituicaoIntegracaoDTO obterInformacoesInstituicaoSCI(Integer idInstituicaoSCI) throws BancoobException;

	/**
	 * Obtem informacoes da instituicao e pac em questao
	 * @param idInstituicao
	 * @param pac
	 * @return
	 * @throws BancoobException
	 */
	InstituicaoIntegracaoDTO obterInformacoesInstituicaoSCI(Integer idInstituicao, String pac) throws BancoobException;	
}
