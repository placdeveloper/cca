package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.entidades.negocio.entidades.HistParticipacaoCentralBancoob;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.HistParticipacaoCentralBancoobServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.HistParticipacaoCentralBancoobServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistParticipacaoCentralBancoobDao;

/**
 * EJB contendo servicos relacionados a HistParticipacaoCentralBancoob.
 */
@Stateless
@Local (HistParticipacaoCentralBancoobServicoLocal.class)
@Remote(HistParticipacaoCentralBancoobServicoRemote.class) 
public class HistParticipacaoCentralBancoobServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<HistParticipacaoCentralBancoob> implements HistParticipacaoCentralBancoobServicoLocal, HistParticipacaoCentralBancoobServicoRemote {
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private HistParticipacaoCentralBancoobDao histParticipacaoCentralBancoobDao;	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb.ProcessamentoCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<HistParticipacaoCentralBancoob> getDAO() {
		return histParticipacaoCentralBancoobDao;
	}
}