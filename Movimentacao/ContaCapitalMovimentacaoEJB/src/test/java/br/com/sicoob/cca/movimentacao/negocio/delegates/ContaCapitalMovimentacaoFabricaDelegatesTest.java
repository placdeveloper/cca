/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * A Classe ContaCapitalMovimentacaoFabricaDelegatesTest.
 */
public class ContaCapitalMovimentacaoFabricaDelegatesTest {

	
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
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance());
	}
	
	@Test
	public void testCriarBloqueioContaCapitalDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarBloqueioContaCapitalDelegate());
	}
	
	@Test
	public void testCriarDebitoIndeterminadoDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarDebitoIndeterminadoDelegate());
	}
	
	@Test
	public void testCriarDesligarContaCapitalDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarDesligarContaCapitalDelegate());
	}
	
	@Test
	public void testCriarDevolucaoContaCapitalDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarDevolucaoContaCapitalDelegate());
	}
	
	@Test
	public void testCriarGestaoEmpresarialDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarGestaoEmpresarialDelegate());
	}
	
	@Test
	public void testCriarLancamentoContaCapitalDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarLancamentoContaCapitalDelegate());
	}
	
	@Test
	public void testCriarLancamentoIntegralizacaoExternaDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarLancamentoIntegralizacaoExternaDelegate());
	}
	
	@Test
	public void testCriarParcelamentoContaCapitalDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarParcelamentoContaCapitalDelegate());
	}
	
	@Test
	public void testCriarParcelamentoContaCapitalExternoDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarParcelamentoContaCapitalExternoDelegate());
	}
	
	@Test
	public void testCriarSubscricaoContaCapitalDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarSubscricaoContaCapitalDelegate());
	}
	
	@Test
	public void testCriarTransferenciaContaCapitalDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarTransferenciaContaCapitalDelegate());
	}
	
	@Test
	public void testCriarHistParticipacaoCentralBancoobDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates .getInstance().criarHistParticipacaoCentralBancoobDelegate());
	}
	
	@Test
	public void testCriarParticipacaoCentralBancoobDelegate() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates .getInstance().criarParticipacaoCentralBancoobDelegate());
	}
	
	@Test
	public void testCriarParticipacaoIndiretaBancoobDao() {
		assertNotNull(ContaCapitalMovimentacaoFabricaDelegates .getInstance().criarParticipacaoIndiretaBancoobDelegate());
	}
	
}
