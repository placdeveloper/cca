package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.HistContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.HistContaCapitalLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.HistContaCapitalLegadoDao;

/**
 * EJB contendo servicos relacionados a HistContaCapitalLegado.
 */
@Stateless
@Local (HistContaCapitalLegadoServicoLocal.class)
@Remote(HistContaCapitalLegadoServicoRemote.class)
public class HistContaCapitalLegadoServicoEJB extends ContaCapitalIntegracaoLegadoCrudServicoEJB<HistContaCapitalLegado> 
	implements HistContaCapitalLegadoServicoLocal, HistContaCapitalLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private HistContaCapitalLegadoDao histContaCapitalLegadoDao;
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb.ContaCapitalIntegracaoLegadoCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalIntegracaoLegadoCrudDaoIF<HistContaCapitalLegado> getDAO() {
		// TODO Auto-generated method stub
		return histContaCapitalLegadoDao;
	}
	
	/**
	 * {@link HistContaCapitalLegadoServico#qtdInativacaoCCA(Integer)}
	 */
	public Integer qtdInativacaoCCA(Integer numMatricula) throws BancoobException {
		return histContaCapitalLegadoDao.qtdInativacaoCCA(numMatricula);
	}
}