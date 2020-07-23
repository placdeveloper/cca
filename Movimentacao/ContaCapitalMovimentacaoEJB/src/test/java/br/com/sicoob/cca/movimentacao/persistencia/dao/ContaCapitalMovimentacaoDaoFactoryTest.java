/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistParticipacaoCentralBancoobDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParticipacaoCentralBancoobDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParticipacaoIndiretaBancoobDao;

/**
 * A Classe ContaCapitalMovimentacaoDaoFactoryTest.
 */
public class ContaCapitalMovimentacaoDaoFactoryTest {

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
		assertNotNull(ContaCapitalMovimentacaoDaoFactory.getInstance());
	}
	
	@Test
	public void testCriarBloqueioContaCapitalDao() {
		assertNotNull(ContaCapitalMovimentacaoDaoFactory.getInstance().criarBloqueioContaCapitalDao());
	}
	
	@Test
	public void testCriarDebitoIndeterminadoDao() {
		assertNotNull(ContaCapitalMovimentacaoDaoFactory.getInstance().criarDebitoIndeterminadoDao());
	}
	
	@Test
	public void testCriarGestaoEmpresarialDao() {
		assertNotNull(ContaCapitalMovimentacaoDaoFactory.getInstance().criarGestaoEmpresarialDao());
	}
	
	@Test
	public void testCriarHistBloqueioContaCapitalDao() {
		assertNotNull(ContaCapitalMovimentacaoDaoFactory.getInstance().criarHistBloqueioContaCapitalDao());
	}
	
	@Test
	public void testCriarHistoricoLancamentoContaCapitalDao() {
		assertNotNull(ContaCapitalMovimentacaoDaoFactory.getInstance().criarHistoricoLancamentoContaCapitalDao());
	}
	
	@Test
	public void testCriarLancamentoContaCapitalDao() {
		assertNotNull(ContaCapitalMovimentacaoDaoFactory.getInstance().criarLancamentoContaCapitalDao());
	}
	
	@Test
	public void testCriarParcelamentoContaCapitalDao() {
		assertNotNull(ContaCapitalMovimentacaoDaoFactory.getInstance().criarParcelamentoContaCapitalDao());
	}
	
	@Test
	public void testCriarHistParticipacaoCentralBancoobDao() {
		HistParticipacaoCentralBancoobDao dao = ContaCapitalMovimentacaoDaoFactory.getInstance().criarHistParticipacaoCentralBancoobDao();
		assertNotNull(dao);
	}
	
	@Test
	public void testCriarParticipacaoCentralBancoobDao() {
		ParticipacaoCentralBancoobDao dao = ContaCapitalMovimentacaoDaoFactory.getInstance().criarParticipacaoCentralBancoobDao();
		assertNotNull(dao);
	}
	
	@Test
	public void testCriarParticipacaoIndiretaBancoobDao() {
		ParticipacaoIndiretaBancoobDao dao = ContaCapitalMovimentacaoDaoFactory.getInstance().criarParticipacaoIndiretaBancoobDao();
		assertNotNull(dao);
	}
	
}
