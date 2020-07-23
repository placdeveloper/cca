package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechMecanismoContabilLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechMecanismoContabilLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechMecanismoContabilLegadoDao;

/**
 * @author ricardo.barcante
 */
@Stateless
@Local(FechMecanismoContabilLegadoServicoLocal.class)
@Remote(FechMecanismoContabilLegadoServicoRemote.class)
public class FechMecanismoContabilLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements FechMecanismoContabilLegadoServicoLocal, FechMecanismoContabilLegadoServicoRemote{

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private FechMecanismoContabilLegadoDao fechamentoMecanismoContabilLegadoDao;
	
	/**
	 * @throws BancoobException 
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.FechMecanismoContabilLegadoServico#rodar	(java.lang.Integer)
	 */
	public void rodar(Integer numCoop) throws BancoobException {
		fechamentoMecanismoContabilLegadoDao.fechamentoMecanismoContabil(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, numCoop);
	}
}
