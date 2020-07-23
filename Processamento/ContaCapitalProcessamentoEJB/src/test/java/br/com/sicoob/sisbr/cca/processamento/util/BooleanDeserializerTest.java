package br.com.sicoob.sisbr.cca.processamento.util;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonPrimitive;

public class BooleanDeserializerTest {

	private BooleanDeserializer booleanDeserializer = new BooleanDeserializer();
	
	@Test
	public void deserializeTest() {
		Assert.assertTrue(booleanDeserializer.deserialize(new JsonPrimitive(1), null, null)); 
		Assert.assertFalse(booleanDeserializer.deserialize(new JsonPrimitive(0), null, null)); 
	}
	
}
