/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.util;

import java.lang.reflect.Type;

import br.com.sicoob.tipos.DateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * DateTimeSerializer
 */
public class DateTimeSerializer implements JsonSerializer<DateTime> {

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public JsonElement serialize(DateTime arg0, Type arg1,JsonSerializationContext arg2) {
        return new JsonPrimitive(datetimeToJson(arg0));
	}

    /**
     * datetimeToJson
     * @param dt
     * @return
     */
    public static String datetimeToJson(DateTime dt) {
        if (dt == null){
        	return null;
        }

        return dt.toString();
    }	 

}
