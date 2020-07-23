package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechDestinacaoJurosLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechDestinacaoJurosLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechDestinacaoJurosLegadoDao;

/**
* @author Antonio.Genaro
*/
@Stateless
@Local(FechDestinacaoJurosLegadoServicoLocal.class)
@Remote(FechDestinacaoJurosLegadoServicoRemote.class)
public class FechDestinacaoJurosLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements FechDestinacaoJurosLegadoServicoLocal, FechDestinacaoJurosLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private FechDestinacaoJurosLegadoDao fechDestinacaoJurosLegadoDao;
	
	public void gerarLancamentoProvisaoJuros(Integer numCoop, String idUsuario) throws BancoobException {
		fechDestinacaoJurosLegadoDao.gerarLancamentoProvisaoJuros(numCoop, idUsuario);
	}	
	
	public void gerarLancamentoDestinacaoJuros(Integer numCoop, String idUsuario) throws BancoobException {
		fechDestinacaoJurosLegadoDao.gerarLancamentoDestinacaoJuros(numCoop, idUsuario);
	}	
	
}