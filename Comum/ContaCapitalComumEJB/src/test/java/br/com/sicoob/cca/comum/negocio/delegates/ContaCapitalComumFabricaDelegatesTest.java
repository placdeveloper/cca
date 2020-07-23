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
	 * O m�todo Sets the up.
	 *
	 * @throws Exception lan�a a exce��o Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * O m�todo Test get instance.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(ContaCapitalComumFabricaDelegates.getInstance());
	}
	
	/**
	 * O m�todo Test criar fechamento conta capital delegate.
	 */
	@Test
	public void testCriarFechamentoContaCapitalDelegate() {
		assertNotNull(ContaCapitalComumFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate());
	}
	
	/**
	 * O m�todo Test criar pesquisa conta capital delegate.
	 */
	@Test
	public void testCriarPesquisaContaCapitalDelegate() {
		assertNotNull(ContaCapitalComumFabricaDelegates.getInstance().criarPesquisaContaCapitalDelegate());
	}
}
