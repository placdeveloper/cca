package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ValorCotaLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ValorCotasLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ValorCotasLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ValorCotasLegadoDao;

/**
 * EJB contendo servicos relacionados a ValorCotasLegado.
 */
@Stateless
@Local (ValorCotasLegadoServicoLocal.class)
@Remote(ValorCotasLegadoServicoRemote.class)
public class ValorCotasLegadoServicoEJB extends ContaCapitalIntegracaoLegadoCrudServicoEJB<ValorCotaLegado> implements ValorCotasLegadoServicoLocal, ValorCotasLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;

	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private ValorCotasLegadoDao valorCotasLegadoDao;	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ValorCotasLegadoServico#obterNumeroMinimoCota()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public Integer obterNumeroMinimoCota() throws BancoobException {
		return valorCotasLegadoDao.obterNumeroMinimoCota();
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ValorCotasLegadoServico#obterValorCota()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public BigDecimal obterValorCota() throws BancoobException {
		return valorCotasLegadoDao.obterValorCota();
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb.ContaCapitalIntegracaoLegadoCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalIntegracaoLegadoCrudDaoIF<ValorCotaLegado> getDAO() {
		return this.valorCotasLegadoDao;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ValorCotasLegadoServico#obterValorCotaVigente(java.lang.Integer)
	 */
	public ValorCotaLegado obterValorCotaVigente(Integer numCoop) throws BancoobException {
		return this.valorCotasLegadoDao.obterValorCotaVigente(numCoop);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ValorCotasLegadoServico#obterValorSalarioBase(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public BigDecimal obterValorSalarioBase(Integer numCoop) throws BancoobException {
		return this.valorCotasLegadoDao.obterValorSalarioBase(numCoop);
	}
}