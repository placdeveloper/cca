/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.delegates;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * @author Marco.Nascimento
 */
public class ContaCapitalDelegateTest  {
	
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
	 * O método Test criar conta capital delegate.
	 */
	@Test
	public void testCriarContaCapitalDelegate() {
		assertNotNull(APIContaCapitalFabricaDelegates.getInstance().criarContaCapitalDelegate());
	}
}