/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.CapaLoteCapitalLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ClienteCooperativaLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ConciliacaoContabilLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ContaCapitalLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.HistContaCapitalLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.InformacaoAcumuladaLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.LancamentosCCapitalLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.OperacaoFinanceiraLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TrabalhaLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ValorCotasLegadoDao;

/**
 * @author Marco.Nascimento
 */
public class ContaCapitalIntegracaoLegadoDaoFactoryTest {

	/**
	 * O m�todo Test get instance.
	 */
	@Test
	public void testGetInstance() {
		ContaCapitalIntegracaoLegadoDaoFactory factory = ContaCapitalIntegracaoLegadoDaoFactory.getInstance();
		ContaCapitalIntegracaoLegadoDaoFactory factory2 = ContaCapitalIntegracaoLegadoDaoFactory.getInstance();
		
		assertNotNull(factory);
		assertNotNull(factory2);
		assertEquals(factory, factory2);
	}

	/**
	 * O m�todo Test criar conta capital legado dao.
	 */
	@Test
	public void testCriarContaCapitalLegadoDao() {
		ContaCapitalLegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarContaCapitalLegadoDao();
		assertNotNull(dao);
	}

	/**
	 * O m�todo Test criar hist conta capital legado dao.
	 */
	@Test
	public void testCriarHistContaCapitalLegadoDao() {
		HistContaCapitalLegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarHistContaCapitalLegadoDao();
		assertNotNull(dao);
	}

	/**
	 * O m�todo Test criar lancamentos c capital legado dao.
	 */
	@Test
	public void testCriarLancamentosCCapitalLegadoDao() {
		LancamentosCCapitalLegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarLancamentosCCapitalLegadoDao();
		assertNotNull(dao);
	}
	
	/**
	 * O m�todo Test criar capa lote capital legado dao.
	 */
	@Test
	public void testCriarCapaLoteCapitalLegadoDao() {
		CapaLoteCapitalLegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarCapaLoteCapitalLegadoDao();
		assertNotNull(dao);
	}
	
	/**
	 * O m�todo Test criar parcelamento cca legado dao.
	 */
	@Test
	public void testCriarParcelamentoCCALegadoDao() {
		ParcelamentoCCALegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarParcelamentoCCALegadoDao();
		assertNotNull(dao);
	}
	
	/**
	 * O m�todo Test criar trabalha legado dao.
	 */
	@Test
	public void testCriarTrabalhaLegadoDao() {
		TrabalhaLegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarTrabalhaLegadoDao();
		assertNotNull(dao);
	}
	
	/**
	 * O m�todo Test criar valor cotas legado dao.
	 */
	@Test
	public void testCriarValorCotasLegadoDao() {
		ValorCotasLegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarValorCotasLegadoDao();
		assertNotNull(dao);
	}

	/**
	 * O m�todo Test criar conciliacao contabil legado dao.
	 */
	@Test
	public void testCriarConciliacaoContabilLegadoDao() {
		ConciliacaoContabilLegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarConciliacaoContabilLegadoDao();
		assertNotNull(dao);
	}
	
	/**
	 * O m�todo Test criar informacao acumulada legado dao.
	 */
	@Test
	public void testCriarInformacaoAcumuladaLegadoDao() {
		InformacaoAcumuladaLegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarInformacaoAcumuladaLegadoDao();
		assertNotNull(dao);
	}
	
	/**
	 * O m�todo Test criar operacao financeira legado dao.
	 */
	@Test
	public void testCriarOperacaoFinanceiraLegadoDao() {
		OperacaoFinanceiraLegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarOperacaoFinanceiraLegadoDao();
		assertNotNull(dao);
	}
	
	/**
	 * O m�todo Test criar cliente cooperativa legado dao.
	 */
	@Test
	public void testCriarClienteCooperativaLegadoDao() {
		ClienteCooperativaLegadoDao dao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarClienteCooperativaLegadoDao();
		assertNotNull(dao);
	}	
}