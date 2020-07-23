/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * A Classe ContaCapitalRelatoriosDaoFactoryTest.
 */
public class ContaCapitalRelatoriosDaoFactoryTest {

	/**
	 * O m�todo Sets the up.
	 *
	 * @throws Exception
	 *             lan�a a exce��o Exception
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
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance());
	}

	/**
	 * O m�todo Test criar rel participacao indireta singular dao.
	 */
	@Test
	public void testCriarRelParticipacaoIndiretaSingularDao() {
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance().criarRelParticipacaoIndiretaSingularDao());
	}

	@Test
	public void testCriarRelAprovacaoQuadroPendenciaDao() {
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance().criarRelAprovacaoQuadroPendenciaDao());
	}

	@Test
	public void testCriarRelDesligamentoAssociadoDao() {
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance().criarRelDesligamentoAssociadoDao());
	}

	@Test
	public void testCriarRelImpedimentosDesligamentoDao() {
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance().criarRelImpedimentosDesligamentoDao());
	}

	@Test
	public void testCriarRelSaldoAtualDao() {
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance().criarRelSaldoAtualDao());
	}

	@Test
	public void testCriarRelPosicaoDiariaCarteiraDao() {
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance().criarFechRelPosicaoDiariaCarteiraDao());
	}

	@Test
	public void testCriarRelParcelamentoContaCapitalDao() {
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance().criarRelParcelamentoContaCapitalDao());
	}

	@Test
	public void testCriarRelSituacaoMatriculaCCARenDao() {
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance().criarRelSituacaoMatriculaCCARenDao());
	}

	@Test
	public void testCriarRelSituacaoPeriodoCCARenDao() {
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance().criarRelSituacaoPeriodoCCARenDao());
	}

	@Test
	public void testCriarRelLancamentosDao() {
		assertNotNull(ContaCapitalRelatoriosDaoFactory.getInstance().criarRelLancamentosDao());
	}
}
