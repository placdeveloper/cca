/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * @author Marco.Nascimento
 */
public class ContaCapitalIntegracaoLegadoFabricaDelegatesTest {
	
	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * O método Test get instance.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance());
	}
	
	/**
	 * O método Test criar conta capital legado delegate.
	 */
	@Test
	public void testCriarContaCapitalLegadoDelegate() {
		ContaCapitalLegadoDelegate delegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarContaCapitalLegadoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar hist conta capital legado delegate.
	 */
	@Test
	public void testCriarHistContaCapitalLegadoDelegate() {
		HistContaCapitalLegadoDelegate delegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarHistContaCapitalDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar lancamentos c capital legado delegate.
	 */
	@Test
	public void testCriarLancamentosCCapitalLegadoDelegate() {
		LancamentosCCapitalLegadoDelegate delegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarLancamentosCCapitalLegadoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar parcelamento cca legado delegate.
	 */
	@Test
	public void testCriarParcelamentoCCALegadoDelegate() {
		ParcelamentoCCALegadoDelegate delegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarParcelamentoCCALegadoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar capa lote capital legado delegate.
	 */
	@Test
	public void testCriarCapaLoteCapitalLegadoDelegate() {
		CapaLoteCapitalLegadoDelegate delegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarCapaLoteCapitalLegadoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar trabalha legado delegate.
	 */
	@Test
	public void testCriarTrabalhaLegadoDelegate() {
		TrabalhaLegadoDelegate delegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarTrabalhaLegadoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar valor cotas legado delegate.
	 */
	@Test
	public void testCriarValorCotasLegadoDelegate() {
		ValorCotasLegadoDelegate delegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarValorCotasLegadoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar conciliacao contabil legado delegate.
	 */
	@Test
	public void testCriarConciliacaoContabilLegadoDelegate() {
		ConciliacaoContabilLegadoDelegate delegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarConciliacaoContabilLegadoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar informacao acumulada legado delegate.
	 */
	@Test
	public void testCriarInformacaoAcumuladaLegadoDelegate() {
		InformacaoAcumuladaLegadoDelegate delegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarInformacaoAcumuladaLegadoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar operacao financeira legado delegate.
	 */
	@Test
	public void testCriarOperacaoFinanceiraLegadoDelegate() {
		OperacaoFinanceiraLegadoDelegate delegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarOperacaoFinanceiraLegadoDelegate();
		assertNotNull(delegate);
	}
}