package br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator;

import org.junit.Assert;
import org.junit.Test;

public class ContaCapitalIntegracaoLegadoServiceLocatorTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(ContaCapitalIntegracaoLegadoServiceLocator.getInstance());
	}
	
}
