package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.entidades.negocio.entidades.OrigemBloqueioCapital;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.OrigemBloqueioCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.OrigemBloqueioCapitalServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.OrigemBloqueioCapitalDao;

/**
 * Servico OrigemBloqueioCapitalServicoEJB
 */
@Stateless
@Local (OrigemBloqueioCapitalServicoLocal.class)
@Remote(OrigemBloqueioCapitalServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrigemBloqueioCapitalServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<OrigemBloqueioCapital> implements OrigemBloqueioCapitalServicoLocal, OrigemBloqueioCapitalServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private OrigemBloqueioCapitalDao origemBloqueioCapitalDao;
	
	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<OrigemBloqueioCapital> getDAO() {
		return origemBloqueioCapitalDao;
	}

}
