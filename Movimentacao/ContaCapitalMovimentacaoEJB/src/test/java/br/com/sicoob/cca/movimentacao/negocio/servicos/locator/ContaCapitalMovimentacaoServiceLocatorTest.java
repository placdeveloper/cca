package br.com.sicoob.cca.movimentacao.negocio.servicos.locator;

import org.junit.Assert;
import org.junit.Test;

public class ContaCapitalMovimentacaoServiceLocatorTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(ContaCapitalMovimentacaoServiceLocator.getInstance());
	}
	
}
