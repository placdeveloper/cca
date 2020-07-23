/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * A Classe RelContaCapitalDelegateTest.
 */
public class RelContaCapitalDelegateTest {

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
	 * O m�todo Test rel conta capital delegate get instance.
	 */
	@Test
	public void testRelContaCapitalDelegateGetInstance() {
		assertNotNull(RelContaCapitalDelegate.getInstance());
	}
}
