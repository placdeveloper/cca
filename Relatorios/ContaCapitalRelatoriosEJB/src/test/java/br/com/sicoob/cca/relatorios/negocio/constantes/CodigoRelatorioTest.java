package br.com.sicoob.cca.relatorios.negocio.constantes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

public class CodigoRelatorioTest {

	@Test
	public void testConstrutorPrivado() throws Exception {
		Constructor<CodigoRelatorio> constructor = CodigoRelatorio.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void constantesNaoNulasTest() throws IllegalArgumentException, IllegalAccessException {
		Field[] declaredFields = CodigoRelatorio.class.getDeclaredFields();
		for (Field field : declaredFields) {
		    if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
		        Assert.assertNotNull(field.get(null));
		    }
		}
	}
	
}
