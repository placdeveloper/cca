package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelFichaMatriculaDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ContaCapitalLegadoDao;

/**
 * EJB contendo servicos relacionados a ContaCapitalLegado.
 */
@Stateless
@Local (ContaCapitalLegadoServicoLocal.class)
@Remote(ContaCapitalLegadoServicoRemote.class)
public class ContaCapitalLegadoServicoEJB extends ContaCapitalIntegracaoLegadoCrudServicoEJB<ContaCapitalLegado>
		implements ContaCapitalLegadoServicoLocal,
		ContaCapitalLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private ContaCapitalLegadoDao contaCapitalLegadoDao;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb.ContaCapitalIntegracaoLegadoCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalIntegracaoLegadoCrudDaoIF<ContaCapitalLegado> getDAO() {
		return contaCapitalLegadoDao;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#obterContaCapital(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public List<ContaCapitalLegado> obterContaCapital(Integer numCliente) throws BancoobException {
		
		List<ContaCapitalLegado> lstContaCapitalLegado = null;
		try{
			ConsultaDto<ContaCapitalLegado> criterios = new ConsultaDto<ContaCapitalLegado>();
			ContaCapitalLegado filtro = new ContaCapitalLegado();
			filtro.setNumCliente(numCliente);		
			criterios.setFiltro(filtro);
			lstContaCapitalLegado = contaCapitalLegadoDao.listar(criterios);
		}catch (BancoobException e) {		
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_001",e);
		}
		return lstContaCapitalLegado; 		
		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#obterUltimaMatricula()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public Integer obterUltimaMatricula() throws BancoobException {
		Integer saida = 0;
		
		try{
			Integer numMatricula = contaCapitalLegadoDao.obterUltimaMatricula();
			if (numMatricula != null){
				saida = numMatricula; 
			}						
		}catch (BancoobException e) {		
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG003",e);
		}		
		
		return saida;				
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#verificarClienteCadastrado(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public Boolean verificarClienteCadastrado(Integer numCliente) throws BancoobException {
		Boolean saida = false; 
		try{
			ConsultaDto<ContaCapitalLegado> criterios = new ConsultaDto<ContaCapitalLegado>();
			ContaCapitalLegado filtro = new ContaCapitalLegado();
			filtro.setNumCliente(numCliente);		
			criterios.setFiltro(filtro);
			List<ContaCapitalLegado> lstContaCapitalLegado = contaCapitalLegadoDao.listar(criterios);
			
			if (!lstContaCapitalLegado.isEmpty()){
				saida = true;
			}
			
		}catch (BancoobException e) {		
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG011",e);
		}
		return saida; 		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#listarRelFichaAdmissao(java.lang.Integer, java.lang.Integer)
	 */
	public List<DadosRelFichaMatriculaDTO> listarRelFichaAdmissao(Integer idPessoa, Integer iAplicCoopDif) throws BancoobException {
		return contaCapitalLegadoDao.listarRelFichaAdmissao(idPessoa, iAplicCoopDif);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#listarRelFicha(java.lang.Integer, java.lang.Integer)
	 */
	public List<DadosRelFichaMatriculaDTO> listarRelFicha(Integer matricula, Integer iAplicCoopDif) throws BancoobException {
		return contaCapitalLegadoDao.listarRelFicha(matricula, iAplicCoopDif);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#listarRelExtrato(br.com.sicoob.sisbr.cca.legado.negocio.dto.RelExtratoLegadoDTO)
	 */
	public List<DadosRelExtratoLegadoDTO> listarRelExtrato(RelExtratoLegadoDTO relExtratoDTO) throws BancoobException {		
		return contaCapitalLegadoDao.listarRelExtrato(relExtratoDTO);		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#verificarContaCapitalCadastrada(java.lang.Integer, java.lang.Integer)
	 */
	public Boolean verificarContaCapitalCadastrada(Integer numCoop, Integer numMatricula) throws BancoobException {
		return contaCapitalLegadoDao.verificarContaCapitalCadastrada(numCoop, numMatricula);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#excluir(Integer, Integer)
	 */
	public void excluir(Integer numMatricula, Integer idInstituicao) throws BancoobException {
		Integer numCoop = instituicaoIntegracaoServico.obterNumeroCooperativa(idInstituicao);
		contaCapitalLegadoDao.excluir(numMatricula, numCoop);
	}
	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#obterContaCapitalCooperativaCliente(Integer, Integer, Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public List<ContaCapitalLegado> obterContaCapitalCooperativaCliente(Integer numCooperativa,Integer numCliente,Integer situacao) throws BancoobException {
		
		List<ContaCapitalLegado> lstContaCapitalLegado = null;
		try{
			lstContaCapitalLegado = contaCapitalLegadoDao.obterContaCapitalCooperativaCliente(numCooperativa, numCliente,situacao);
		}catch (BancoobException e) {		
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_001",e);
		}
		return lstContaCapitalLegado; 		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#obterNovaContaCapital(java.lang.Integer)
	 */
	public Integer obterNovaContaCapital(Integer idInstituicao) throws BancoobException {
		return contaCapitalLegadoDao.obterNovaContaCapital(idInstituicao);
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#excluirDebIndeterminadoEmLote(java.util.List)
	 */
	public void excluirDebIndeterminadoEmLote(List<Integer> numMatriculas) throws BancoobException {
		contaCapitalLegadoDao.excluirDebIndeterminadoEmLote(numMatriculas);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#atualizarDebIndeterminadoEmLote(java.util.List)
	 */
	public void atualizarDebIndeterminadoEmLote(List<ContaCapitalLegado> lst) throws BancoobException {
		contaCapitalLegadoDao.atualizarDebIndeterminadoEmLote(lst);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico#alterarDebIndeterminadoEmLote(java.util.List, java.lang.Integer, java.math.BigDecimal)
	 */
	public void alterarDebIndeterminadoEmLote(List<ContaCapitalLegado> lst, Integer tipoAlteracao,
			BigDecimal percentual) throws BancoobException {
		contaCapitalLegadoDao.alterarDebIndeterminadoEmLote(lst, tipoAlteracao, percentual);
	}
}