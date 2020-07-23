/*
 * 
 */
package br.com.sicoob.cca.replicacao.persistencia.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ContaCapitalReplicacaoDaoFactoryTest {

	@Test
	public void testGetInstance() {
		assertNotNull(ContaCapitalReplicacaoDaoFactory.getInstance());
	}
	
	@Test
	public void testCriarFechamentoContaCapitalDao() {
		assertNotNull(ContaCapitalReplicacaoDaoFactory.getInstance().criarReplicacaoContaCapitalDao());
	}
	
}
