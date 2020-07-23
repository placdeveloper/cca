package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.locator;

import org.junit.Assert;
import org.junit.Test;

public class ProcessamentoServiceLocatorTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(ProcessamentoServiceLocator.getInstance());
	}
	
}
