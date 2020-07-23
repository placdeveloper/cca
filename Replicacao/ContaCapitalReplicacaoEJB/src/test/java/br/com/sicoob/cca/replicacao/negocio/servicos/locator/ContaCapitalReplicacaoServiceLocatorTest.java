package br.com.sicoob.cca.replicacao.negocio.servicos.locator;

import org.junit.Assert;
import org.junit.Test;

public class ContaCapitalReplicacaoServiceLocatorTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(ContaCapitalReplicacaoServiceLocator.getInstance());
	}
	
}
