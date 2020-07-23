package br.com.sicoob.sisbr.cca.processamento.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.tipos.DateTime;

/**
 * DateTimeDeserializer
 */
public class DateTimeDeserializer implements JsonDeserializer<DateTime> {

	private static final int TAM_DATA_8 = 8;
	private static final int TAM_DATA_10 = 10;
	
	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public DateTime deserialize(JsonElement arg0, Type arg1,JsonDeserializationContext arg2) {

		String data = arg0.getAsJsonPrimitive().getAsString();		
		
		if (data == null){
			return null;
		}		
		
		return stringToDate(data,getFormatador(data));
	}

	/**
	 * stringToDate
	 * @param data
	 * @param formato
	 * @return
	 */
	public static DateTime stringToDate(String data, String formato) {
				
		try {
			DateFormat df = new SimpleDateFormat(formato);
			return new DateTime(df.parse(data).getTime());
		} catch (ParseException e) {
			throw new BancoobRuntimeException(e);
		}
	}		

	private String getFormatador(String data){
		String saida;
		switch (data.length()) {
		case TAM_DATA_8:
			saida =  "yyyyMMdd";
			break;
		case TAM_DATA_10:
			saida =  "yyyy-MM-dd";
			break;
		default:
			saida =  "yyyy-MM-dd HH:mm:ss.SSS";
			break;
		}
		return saida;
	}
	
}
