package br.com.sicoob.cca.frontoffice.negocio.delegates;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ContaCapitalFrontofficeFabricaDelegatesTest {

	@Test
	public void testGetInstance() {
		assertNotNull(ContaCapitalFrontofficeFabricaDelegates.getInstance());
	}
	
	@Test
	public void testCriarIntegralizacaoCapitalDelegate() {
		assertNotNull(ContaCapitalFrontofficeFabricaDelegates.getInstance().criarIntegralizacaoCapitalDelegate());
	}
	
}
