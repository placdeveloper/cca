package br.com.sicoob.sisbr.cca.api.negocio.servicos.locator;

import org.junit.Assert;
import org.junit.Test;

public class APIContaCapitalServiceLocatorTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(APIContaCapitalServiceLocator.getInstance());
	}
	
}
