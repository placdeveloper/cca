/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ConfiguracaoCapitalDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ContaCapitalDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.DocumentoCapitalDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.PropostaSubscricaoDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.SituacaoCadastroPropostaDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ValorConfiguracaoCapitalDao;

/**
 * Test DAOs do cadastro de conta capital
 * @author Marco.Nascimento
 */
public class ContaCapitalCadastroDaoFactoryTest {

	/**
	 * O método Test get instance.
	 */
	@Test
	public void testGetInstance() {
		ContaCapitalCadastroDaoFactory factory = ContaCapitalCadastroDaoFactory.getInstance();
		ContaCapitalCadastroDaoFactory factory2 = ContaCapitalCadastroDaoFactory.getInstance();
		
		assertNotNull(factory);
		assertNotNull(factory2);
		assertEquals(factory, factory2);
	}

	/**
	 * O método Test criar configuracao capital dao.
	 */
	@Test
	public void testCriarConfiguracaoCapitalDao(){
		ConfiguracaoCapitalDao dao = ContaCapitalCadastroDaoFactory.getInstance().criarConfiguracaoCapitalDao();
		assertNotNull(dao);
	}
	
	/**
	 * O método Test criar valor configuracao capital dao.
	 */
	@Test
	public void testCriarValorConfiguracaoCapitalDao(){
		ValorConfiguracaoCapitalDao dao = ContaCapitalCadastroDaoFactory.getInstance().criarValorConfiguracaoCapitalDao();
		assertNotNull(dao);
	}
	
	/**
	 * O método Test criar situacao cadastro proposta dao.
	 */
	@Test
	public void testCriarSituacaoCadastroPropostaDao(){
		SituacaoCadastroPropostaDao dao = ContaCapitalCadastroDaoFactory.getInstance().criarSituacaoCadastroPropostaDao();
		assertNotNull(dao);
	}
	
	/**
	 * O método Test criar proposta subscricao dao.
	 */
	@Test
	public void testCriarPropostaSubscricaoDao(){
		PropostaSubscricaoDao dao = ContaCapitalCadastroDaoFactory.getInstance().criarPropostaSubscricaoDao();
		assertNotNull(dao);
	}
	
	/**
	 * O método Test criar conta capital dao.
	 */
	@Test
	public void testCriarContaCapitalDao(){
		ContaCapitalDao dao = ContaCapitalCadastroDaoFactory.getInstance().criarContaCapitalDao();
		assertNotNull(dao);
	}
	
	/**
	 * O método Test criar documento capital dao.
	 */
	@Test
	public void testCriarDocumentoCapitalDao(){
		DocumentoCapitalDao dao = ContaCapitalCadastroDaoFactory.getInstance().criarDocumentoCapitalDao();
		assertNotNull(dao);
	}
	
	@Test
	public void testCriarAgrupadorConfiguracaoCapitalDao(){
		assertNotNull(ContaCapitalCadastroDaoFactory.getInstance().criarAgrupadorConfiguracaoCapitalDao());
	}
	
}