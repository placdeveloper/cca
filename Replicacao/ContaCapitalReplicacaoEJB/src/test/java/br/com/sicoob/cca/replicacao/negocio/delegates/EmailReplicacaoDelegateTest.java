package br.com.sicoob.cca.replicacao.negocio.delegates;

import org.junit.Assert;
import org.junit.Test;

public class EmailReplicacaoDelegateTest {

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(EmailReplicacaoDelegate.getInstance());
	}
	
}
