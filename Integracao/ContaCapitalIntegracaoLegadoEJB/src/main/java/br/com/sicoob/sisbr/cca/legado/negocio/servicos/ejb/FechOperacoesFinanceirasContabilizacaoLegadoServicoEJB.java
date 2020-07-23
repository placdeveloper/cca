package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechOperacoesFinanceirasContabilizacaoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechOperacoesFinanceirasContabilizacaoLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechOperacoesFinanceirasContabilizacaoLegadoDao;

/**
* @author Ricardo.Barcante
*/
@Stateless
@Local(FechOperacoesFinanceirasContabilizacaoLegadoServicoLocal.class)
@Remote(FechOperacoesFinanceirasContabilizacaoLegadoServicoRemote.class)
public class FechOperacoesFinanceirasContabilizacaoLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements FechOperacoesFinanceirasContabilizacaoLegadoServicoLocal, FechOperacoesFinanceirasContabilizacaoLegadoServicoRemote{

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private FechOperacoesFinanceirasContabilizacaoLegadoDao fechamentoOperacoesFinanceirasContabilizacaoLegadoDao;
	
	@EJB
	private FechamentoContaCapitalServicoLocal fechamentoContaCapitalServicoLocal;
	
	public void rodar(Integer numCoop) throws BancoobException {
		String idUsuario = fechamentoContaCapitalServicoLocal.buscarIdUsuarioFechamento(numCoop);
		fechamentoOperacoesFinanceirasContabilizacaoLegadoDao.fechamentoOperacoesFinanceirasContabilizacao(numCoop, idUsuario);		
	}

}
