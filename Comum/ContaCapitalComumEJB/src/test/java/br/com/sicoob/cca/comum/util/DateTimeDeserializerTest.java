package br.com.sicoob.cca.comum.util;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonPrimitive;

import br.com.bancoob.excecao.BancoobRuntimeException;

public class DateTimeDeserializerTest {

	private DateTimeDeserializer deserializer = new DateTimeDeserializer(); 
	
	@Test
	public void deserializeTest() {
		Assert.assertNotNull(deserializer.deserialize(new JsonPrimitive("20000101"), null, null));
		Assert.assertNotNull(deserializer.deserialize(new JsonPrimitive("2000-01-01"), null, null));
		Assert.assertNotNull(deserializer.deserialize(new JsonPrimitive("2000-01-01 00:00:00.000"), null, null));
	}
	
	@Test(expected=BancoobRuntimeException.class)
	public void deserializeExceptionTest() {
		deserializer.deserialize(new JsonPrimitive("2000-01-01-00:00:00.000"), null, null);
	}
	
}
