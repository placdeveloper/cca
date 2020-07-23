/*
 * 
 */
package br.com.sicoob.cca.comum.persistencia.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * A Classe ContaCapitalComumDaoFactoryTest.
 */
public class ContaCapitalComumDaoFactoryTest {

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
		assertNotNull(ContaCapitalComumDaoFactory.getInstance());
	}
	
	/**
	 * O m�todo Test criar fechamento conta capital dao.
	 */
	@Test
	public void testCriarFechamentoContaCapitalDao() {
		assertNotNull(ContaCapitalComumDaoFactory.getInstance().criarFechamentoContaCapitalDao());
	}
	
	/**
	 * O m�todo Test criar pesquisa conta capital dao.
	 */
	@Test
	public void testCriarPesquisaContaCapitalDao() {
		assertNotNull(ContaCapitalComumDaoFactory.getInstance().criarPesquisaContaCapitalDao());
	}
}
