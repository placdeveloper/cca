package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.SituacaoCadastroPropostaServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.SituacaoCadastroPropostaServicoRemote;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroDaoFactory;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.SituacaoCadastroPropostaDao;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;

/**
 *  @author Marco.Nascimento
 */
@Stateless
@Local (SituacaoCadastroPropostaServicoLocal.class)
@Remote(SituacaoCadastroPropostaServicoRemote.class) 
public class SituacaoCadastroPropostaServicoEJB extends ContaCapitalCadastroCrudServicoEJB<SituacaoCadastroProposta> implements SituacaoCadastroPropostaServicoLocal, SituacaoCadastroPropostaServicoRemote {
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalCadastroDaoFactory.class)
	private SituacaoCadastroPropostaDao situacaoCadastroPropostaDao;

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalCadastroCrudDaoIF<SituacaoCadastroProposta> getDAO() {
		return this.situacaoCadastroPropostaDao;
	}	
}