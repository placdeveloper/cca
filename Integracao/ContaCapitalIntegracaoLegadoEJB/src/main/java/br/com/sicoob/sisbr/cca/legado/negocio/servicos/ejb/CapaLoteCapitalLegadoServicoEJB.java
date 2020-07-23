package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.CapaLoteCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.CapaLoteCapitalLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.CapaLoteCapitalLegadoDao;

/**
 * EJB contendo servicos relacionados a CapaLoteCapitalLegado.
 */
@Stateless
@Local (CapaLoteCapitalLegadoServicoLocal.class)
@Remote(CapaLoteCapitalLegadoServicoRemote.class)
public class CapaLoteCapitalLegadoServicoEJB extends
		ContaCapitalIntegracaoLegadoCrudServicoEJB<CapaLoteCapitalLegado> implements
		CapaLoteCapitalLegadoServicoLocal, CapaLoteCapitalLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private CapaLoteCapitalLegadoDao capaLoteCapitalLegadoDao;	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb.ContaCapitalIntegracaoLegadoCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalIntegracaoLegadoCrudDaoIF<CapaLoteCapitalLegado> getDAO() {
		// TODO Auto-generated method stub
		return capaLoteCapitalLegadoDao;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.CapaLoteCapitalLegadoServico#atualizarCapaLote(java.lang.Integer, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer)
	 */
	public void atualizarCapaLote(Integer numCooperativa, DateTimeDB dataLote, Integer numLote) throws BancoobException {
		capaLoteCapitalLegadoDao.atualizarCapaLote(numCooperativa, dataLote, numLote);
	}
	
}
