/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.delegates;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


/**
 * A Classe ContaCapitalComumFabricaDelegatesTest.
 */
public class ContaCapitalComumFabricaDelegatesTest {
	
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
		assertNotNull(ContaCapitalComumFabricaDelegates.getInstance());
	}
	
	/**
	 * O método Test criar fechamento conta capital delegate.
	 */
	@Test
	public void testCriarFechamentoContaCapitalDelegate() {
		assertNotNull(ContaCapitalComumFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate());
	}
	
	/**
	 * O método Test criar pesquisa conta capital delegate.
	 */
	@Test
	public void testCriarPesquisaContaCapitalDelegate() {
		assertNotNull(ContaCapitalComumFabricaDelegates.getInstance().criarPesquisaContaCapitalDelegate());
	}
}
