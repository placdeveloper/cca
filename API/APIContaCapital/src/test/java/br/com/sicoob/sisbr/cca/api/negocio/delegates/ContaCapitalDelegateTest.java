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
	 * O m�todo Sets the up.
	 *
	 * @throws Exception lan�a a exce��o Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * O m�todo Test criar conta capital delegate.
	 */
	@Test
	public void testCriarContaCapitalDelegate() {
		assertNotNull(APIContaCapitalFabricaDelegates.getInstance().criarContaCapitalDelegate());
	}
}