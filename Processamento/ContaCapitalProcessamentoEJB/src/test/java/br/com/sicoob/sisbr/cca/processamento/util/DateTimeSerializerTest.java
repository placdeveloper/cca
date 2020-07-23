package br.com.sicoob.sisbr.cca.processamento.util;

import org.junit.Assert;
import org.junit.Test;

import br.com.sicoob.tipos.DateTime;

public class DateTimeSerializerTest {

	private DateTimeSerializer serializer = new DateTimeSerializer();
	
	@Test
	public void serializeTest() {
		Assert.assertNotNull(serializer.serialize(new DateTime(), null, null));
	}
	
}
