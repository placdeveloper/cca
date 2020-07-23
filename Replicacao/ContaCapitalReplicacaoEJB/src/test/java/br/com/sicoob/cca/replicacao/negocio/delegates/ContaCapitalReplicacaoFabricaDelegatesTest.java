/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.delegates;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ContaCapitalReplicacaoFabricaDelegatesTest {
	
	@Test
	public void testGetInstance() {
		assertNotNull(ContaCapitalReplicacaoFabricaDelegates.getInstance());
	}
	
	@Test
	public void testCriarEmailReplicacaoDelegate() {
		assertNotNull(ContaCapitalReplicacaoFabricaDelegates.getInstance().criarEmailReplicacaoDelegate());
	}
	
	@Test
	public void testCriarReplicacaoContaCapitalDelegate() {
		assertNotNull(ContaCapitalReplicacaoFabricaDelegates.getInstance().criarReplicacaoContaCapitalDelegate());
	}
}
