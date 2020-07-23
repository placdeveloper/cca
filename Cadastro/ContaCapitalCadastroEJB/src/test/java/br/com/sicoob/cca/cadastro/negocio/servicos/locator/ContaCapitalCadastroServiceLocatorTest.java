package br.com.sicoob.cca.cadastro.negocio.servicos.locator;

import org.junit.Assert;
import org.junit.Test;

public class ContaCapitalCadastroServiceLocatorTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(ContaCapitalCadastroServiceLocator.getInstance());
	}
	
}
