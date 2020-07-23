package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapital;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.HistBloqueioContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.HistBloqueioContaCapitalServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistBloqueioContaCapitalDao;

/**
 * EJB com os servicos de HistBloqueioCapital
 */
@Stateless
@Local (HistBloqueioContaCapitalServicoLocal.class)
@Remote(HistBloqueioContaCapitalServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class HistBloqueioContaCapitalServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<HistBloqueioCapital> implements HistBloqueioContaCapitalServicoLocal, HistBloqueioContaCapitalServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private HistBloqueioContaCapitalDao histBloqueioContaCapitalDao;
	
	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<HistBloqueioCapital> getDAO() {
		return histBloqueioContaCapitalDao;
	}

}
