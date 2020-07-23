package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechCalculoProvisaoJurosServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechCalculoProvisaoJurosServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechCalculoProvisaoJurosDao;

/**
* @author Antonio.Genaro
*/
@Stateless
@Local(FechCalculoProvisaoJurosServicoLocal.class)
@Remote(FechCalculoProvisaoJurosServicoRemote.class)
public class FechCalculoProvisaoJurosServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements FechCalculoProvisaoJurosServicoLocal, FechCalculoProvisaoJurosServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private FechCalculoProvisaoJurosDao fechCalculoProvisaoJurosDao;

	public void rodar(Integer numCoop) throws BancoobException {
		SicoobLoggerPadrao.getInstance(FechCalculoProvisaoJurosServicoEJB.class).info("------ gerarCalculoProvisaoJuros FechCalculoProvisaoJurosServicoEJB.rodar ------ ");
		fechCalculoProvisaoJurosDao.gerarCalculoProvisaoJuros(numCoop);
	}	
		
}