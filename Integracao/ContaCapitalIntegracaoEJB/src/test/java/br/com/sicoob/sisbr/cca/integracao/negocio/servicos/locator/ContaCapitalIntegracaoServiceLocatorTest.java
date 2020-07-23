package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator;

import org.junit.Assert;
import org.junit.Test;

public class ContaCapitalIntegracaoServiceLocatorTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(ContaCapitalIntegracaoServiceLocator.getInstance());
	}
	
}
