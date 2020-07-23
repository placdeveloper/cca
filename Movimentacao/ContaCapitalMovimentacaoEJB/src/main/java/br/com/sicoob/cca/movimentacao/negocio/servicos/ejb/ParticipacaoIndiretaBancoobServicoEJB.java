package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoob;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParticipacaoIndiretaBancoobServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParticipacaoIndiretaBancoobServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParticipacaoIndiretaBancoobDao;

/**
 * EJB contendo servicos relacionados a ParticipacaoIndiretaBancoob.
 */
@Stateless
@Local (ParticipacaoIndiretaBancoobServicoLocal.class)
@Remote(ParticipacaoIndiretaBancoobServicoRemote.class) 
public class ParticipacaoIndiretaBancoobServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<ParticipacaoIndiretaBancoob> implements ParticipacaoIndiretaBancoobServicoLocal, ParticipacaoIndiretaBancoobServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private ParticipacaoIndiretaBancoobDao participacaoIndiretaBancoobDao;	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb.ProcessamentoCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<ParticipacaoIndiretaBancoob> getDAO() {
		return participacaoIndiretaBancoobDao;
	}


}
