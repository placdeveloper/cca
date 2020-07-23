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
public class APIContaCapitalFabricaDelegatesTest  {

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
		assertNotNull(APIContaCapitalFabricaDelegates.getInstance());
	}
	
	/**
	 * O método Test criar conta capital delegate.
	 */
	@Test
	public void testCriarContaCapitalDelegate() {
		ContaCapitalDelegate delegate = APIContaCapitalFabricaDelegates.getInstance().criarContaCapitalDelegate();
		assertNotNull(delegate);
	}
	
	@Test
	public void testCriarCadastroContaCapitalDelegate() {
		assertNotNull(APIContaCapitalFabricaDelegates.getInstance().criarCadastroContaCapitalDelegate());
	}
	
	@Test
	public void testCriarDebIndeterminadoContaCapitalDelegate() {
		assertNotNull(APIContaCapitalFabricaDelegates.getInstance().criarDebIndeterminadoContaCapitalDelegate());
	}
	
	@Test
	public void testCriarIntegralizacaoCapitalDelegate() {
		assertNotNull(APIContaCapitalFabricaDelegates.getInstance().criarIntegralizacaoCapitalDelegate());
	}
	
}