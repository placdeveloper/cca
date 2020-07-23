package br.com.sicoob.sisbr.cca.legado.negocio.constantes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

public class ReplicacaoLegadoConstantesTest {

	@Test
	public void testConstrutorPrivado() throws Exception {
		Constructor<ReplicacaoLegadoConstantes> constructor = ReplicacaoLegadoConstantes.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void constantesNaoNulasTest() throws IllegalArgumentException, IllegalAccessException {
		Field[] declaredFields = ReplicacaoLegadoConstantes.class.getDeclaredFields();
		for (Field field : declaredFields) {
		    if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
		        Assert.assertNotNull(field.get(null));
		    }
		}
	}
	
}
