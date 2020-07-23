package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ClienteCooperativaLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ClienteCooperativaLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ClienteCooperativaLegadoDao;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Local(ClienteCooperativaLegadoServicoLocal.class)
@Remote(ClienteCooperativaLegadoServicoRemote.class)
public class ClienteCooperativaLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements ClienteCooperativaLegadoServicoLocal, ClienteCooperativaLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private ClienteCooperativaLegadoDao clienteCooperativaLegadoDao;

	/**
	 * {@link ClienteCooperativaLegadoServico#consultarClienteCooperativa(Integer, Integer)
	 */
	public Integer consultarClienteCooperativa(Integer numCoop, Integer numPac) throws BancoobException {
		if(numPac == null) {
			numPac = ContaCapitalConstantes.NUM_PAC_DEFAULT;
		}
		return this.clienteCooperativaLegadoDao.consultarClienteCooperativa(numCoop, numPac);
	}
}