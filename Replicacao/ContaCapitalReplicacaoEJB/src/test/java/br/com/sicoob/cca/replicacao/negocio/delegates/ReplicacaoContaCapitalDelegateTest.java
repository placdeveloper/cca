package br.com.sicoob.cca.replicacao.negocio.delegates;

import org.junit.Assert;
import org.junit.Test;

public class ReplicacaoContaCapitalDelegateTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(ReplicacaoContaCapitalDelegate.getInstance());
	}
	
}
