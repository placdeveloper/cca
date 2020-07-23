package br.com.sicoob.cca.comum.util;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class JsonCapitalTest {

	private JsonCapital jsonCapital = new JsonCapital();
	
	@Test
	public void criarJsonCapital() throws BancoobException {
		Assert.assertEquals("true", jsonCapital.obterJSon(Boolean.TRUE));
		Assert.assertEquals(Boolean.TRUE, jsonCapital.converterJSon("1", Boolean.class));
	}
	
}
