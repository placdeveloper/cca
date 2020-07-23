package br.com.sicoob.cca.comum.util;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

/**
 * BooleanDeserializer
 */
public class BooleanDeserializer implements JsonDeserializer<Boolean>  {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public Boolean deserialize(JsonElement arg0, Type arg1,JsonDeserializationContext arg2) {
		return (arg0.toString().equals("1") ? true : false);
    }

}
