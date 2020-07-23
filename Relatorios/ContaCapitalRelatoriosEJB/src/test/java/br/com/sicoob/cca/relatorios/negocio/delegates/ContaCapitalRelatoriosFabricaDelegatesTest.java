/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


/**
 * A Classe ContaCapitalRelatoriosFabricaDelegatesTest.
 */
public class ContaCapitalRelatoriosFabricaDelegatesTest {

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
	 * O m�todo Test conta capital relatorios fabrica delegates get instance.
	 */
	@Test
	public void testContaCapitalRelatoriosFabricaDelegatesGetInstance() {
		assertNotNull(ContaCapitalRelatoriosFabricaDelegates.getInstance());
	}
	
	/**
	 * O m�todo Test criar rel conta capital delegate.
	 */
	@Test
	public void testCriarRelContaCapitalDelegate() {
		assertNotNull(ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelContaCapitalDelegate());
	}
	
	/**
	 * O m�todo Test criar rel participacao indireta singular delegate.
	 */
	@Test
	public void testCriarRelParticipacaoIndiretaSingularDelegate(){
		assertNotNull(ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelParticipacaoIndiretaSingularDelegate());
	}
	
	/**
	 * O m�todo Test criar rel ficha proposta matricula delegate.
	 */
	@Test
	public void testCriarRelFichaPropostaMatriculaDelegate() {
		assertNotNull(ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelFichaPropostaMatriculaDelegate());
	}	
}