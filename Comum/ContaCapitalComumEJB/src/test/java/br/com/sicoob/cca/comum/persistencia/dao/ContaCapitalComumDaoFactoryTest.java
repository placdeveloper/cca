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
		assertNotNull(ContaCapitalComumDaoFactory.getInstance());
	}
	
	/**
	 * O método Test criar fechamento conta capital dao.
	 */
	@Test
	public void testCriarFechamentoContaCapitalDao() {
		assertNotNull(ContaCapitalComumDaoFactory.getInstance().criarFechamentoContaCapitalDao());
	}
	
	/**
	 * O método Test criar pesquisa conta capital dao.
	 */
	@Test
	public void testCriarPesquisaContaCapitalDao() {
		assertNotNull(ContaCapitalComumDaoFactory.getInstance().criarPesquisaContaCapitalDao());
	}
}
