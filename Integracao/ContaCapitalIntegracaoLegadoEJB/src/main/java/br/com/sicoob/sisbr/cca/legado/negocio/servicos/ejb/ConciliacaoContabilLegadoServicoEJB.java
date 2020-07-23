package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConciliacaoContabilLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ConciliacaoContabilLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ConciliacaoContabilLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ConciliacaoContabilLegadoDao;

/**
 * EJB contendo servicos relacionados a ConciliacaoContabilLegado.
 */
@Stateless
@Local (ConciliacaoContabilLegadoServicoLocal.class)
@Remote(ConciliacaoContabilLegadoServicoRemote.class)
public class ConciliacaoContabilLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements ConciliacaoContabilLegadoServicoLocal, ConciliacaoContabilLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private ConciliacaoContabilLegadoDao conciliacaoContabilLegadoDao;

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ConciliacaoContabilLegadoServico#obterListaDadosConciliacaoContabil(java.lang.Integer, br.com.bancoob.persistencia.types.DateTimeDB)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ConciliacaoContabilLegadoDTO> obterListaDadosConciliacaoContabil(Integer numCooperativa, DateTimeDB dataLote) throws BancoobException {
 		return conciliacaoContabilLegadoDao.obterListaDadosConciliacaoContabil(numCooperativa, dataLote);
	}
	
	/**
	 * {@link ConciliacaoContabilLegadoServicoEJB#atualizarConciliacaoContabil(Integer, ConciliacaoContabilLegadoDTO)}
	 * @param numCooperativa
	 * @param dto
	 * @throws BancoobException
	 */
	public void atualizarConciliacaoContabil(Integer numCooperativa, ConciliacaoContabilLegadoDTO dto) throws BancoobException {
 		conciliacaoContabilLegadoDao.atualizarConciliacaoContabil(numCooperativa, dto);
	}	
}