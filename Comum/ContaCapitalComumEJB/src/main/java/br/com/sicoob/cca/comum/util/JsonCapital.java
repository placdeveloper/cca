/*
 * 
 */
package br.com.sicoob.cca.comum.util;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.tipos.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * JsonCapital
 */
public final class JsonCapital {

	private final Gson conversor = criarJsonCapital();
	
	/**
	 * Construtor
	 */
	public JsonCapital() {
	}

	private Gson criarJsonCapital() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(DateTime.class, new DateTimeSerializer());
		builder.registerTypeAdapter(DateTime.class, new DateTimeDeserializer());
		builder.registerTypeAdapter(Boolean.class, new BooleanDeserializer());
		return builder.create();
	}
	
	/**
	 * obterJSon
	 * @param objeto
	 * @return
	 * @throws BancoobException
	 */
	public String obterJSon(Object objeto) throws BancoobException {
		try {
			return conversor.toJson(objeto);	
		} catch (JsonSyntaxException e) {
			throw new BancoobException(e.getMessage(), e);
		}		
	}
	
	/**
	 * converterJSon
	 * @param json
	 * @param clazz
	 * @return
	 * @throws BancoobException
	 */
	public <T> T converterJSon(String json, Class<T> clazz) throws BancoobException {
		try {
			return conversor.fromJson(json, clazz);	
		} catch (JsonSyntaxException e) {
			throw new BancoobException(e.getMessage(), e);
		}		
	}	
	
}
