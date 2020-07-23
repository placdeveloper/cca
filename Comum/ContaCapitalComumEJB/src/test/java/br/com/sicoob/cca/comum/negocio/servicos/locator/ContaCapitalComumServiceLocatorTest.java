package br.com.sicoob.cca.comum.negocio.servicos.locator;

import org.junit.Assert;
import org.junit.Test;

public class ContaCapitalComumServiceLocatorTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(ContaCapitalComumServiceLocator.getInstance());
	}
	
}
