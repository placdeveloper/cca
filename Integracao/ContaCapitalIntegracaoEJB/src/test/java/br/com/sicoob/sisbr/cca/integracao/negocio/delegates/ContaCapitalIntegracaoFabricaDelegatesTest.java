/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * @author Marco.Nascimento
 */
public class ContaCapitalIntegracaoFabricaDelegatesTest {
	
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
		assertNotNull(ContaCapitalIntegracaoFabricaDelegates.getInstance());
	}
	
	/**
	 * O método Test criar instituicao integracao delegate.
	 */
	@Test
	public void testCriarInstituicaoIntegracaoDelegate() {
		InstituicaoIntegracaoDelegate delegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar capes integracao delegate.
	 */
	@Test
	public void testCriarCapesIntegracaoDelegate() {
		CapesIntegracaoDelegate delegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCapesIntegracaoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test criar gen int integracao delegate.
	 */
	@Test
	public void testCriarGenIntIntegracaoDelegate() {
		GenIntIntegracaoDelegate delegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarGenIntIntegracaoDelegate();
		assertNotNull(delegate);
	}

	/**
	 * O método Test conta corrente integracao delegate.
	 */
	@Test
	public void testContaCorrenteIntegracaoDelegate() {
		ContaCorrenteIntegracaoDelegate delegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarContaCorrenteIntegracaoDelegate();
		assertNotNull(delegate);
	}
	
	/**
	 * O método Test contabilidade integracao delegate.
	 */
	@Test
	public void testContabilidadeIntegracaoDelegate() {
		ContabilidadeIntegracaoDelegate delegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarContabilidadeIntegracaoDelegate();
		assertNotNull(delegate);
	}
	
	@Test
	public void testCriarCaptacaoRemuneradaIntegracaoDelegate() {
		assertNotNull(ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCaptacaoRemuneradaIntegracaoDelegate());
	}	
	
	@Test
	public void testCriarDocumentoIntegracaoDelegate() {
		assertNotNull(ContaCapitalIntegracaoFabricaDelegates.getInstance().criarDocumentoIntegracaoDelegate());
	}	
	
	@Test
	public void testCriarGftIntegracaoDelegate() {
		assertNotNull(ContaCapitalIntegracaoFabricaDelegates.getInstance().criarGftIntegracaoDelegate());
	}
	
	@Test
	public void testCriarLocalizacaoIntegracaoDelegate() {
		assertNotNull(ContaCapitalIntegracaoFabricaDelegates.getInstance().criarLocalizacaoIntegracaoDelegate());
	}	
	
}