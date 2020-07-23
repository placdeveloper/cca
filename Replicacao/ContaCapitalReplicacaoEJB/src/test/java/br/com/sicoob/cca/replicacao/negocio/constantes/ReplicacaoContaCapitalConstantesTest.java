package br.com.sicoob.cca.replicacao.negocio.constantes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

public class ReplicacaoContaCapitalConstantesTest {

	@Test
	public void testConstrutorPrivado() throws Exception {
		Constructor<ReplicacaoContaCapitalConstantes> constructor = ReplicacaoContaCapitalConstantes.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void constantesNaoNulasTest() throws IllegalArgumentException, IllegalAccessException {
		Field[] declaredFields = ReplicacaoContaCapitalConstantes.class.getDeclaredFields();
		for (Field field : declaredFields) {
		    if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
		        Assert.assertNotNull(field.get(null));
		    }
		}
	}
	
}
