package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.AgrupadorConfiguracaoCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.AgrupadorConfiguracaoCapitalServicoRemote;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroDaoFactory;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.AgrupadorConfiguracaoCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.AgrupadorConfiguracaoCapital;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Local(AgrupadorConfiguracaoCapitalServicoLocal.class)
@Remote(AgrupadorConfiguracaoCapitalServicoRemote.class)
public class AgrupadorConfiguracaoCapitalServicoEJB extends ContaCapitalCadastroCrudServicoEJB<AgrupadorConfiguracaoCapital> implements AgrupadorConfiguracaoCapitalServicoLocal, AgrupadorConfiguracaoCapitalServicoRemote {
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalCadastroDaoFactory.class)
	private AgrupadorConfiguracaoCapitalDao agrupadorConfiguracaoCapitalDao;

	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalCadastroCrudDaoIF<AgrupadorConfiguracaoCapital> getDAO() {
		return this.agrupadorConfiguracaoCapitalDao;
	}	
}