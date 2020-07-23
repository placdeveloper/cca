package br.com.sicoob.cca.relatorios.negocio.servicos.locator;

import org.junit.Assert;
import org.junit.Test;

public class ContaCapitalRelatoriosServiceLocatorTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(ContaCapitalRelatoriosServiceLocator.getInstance());
	}
	
}
