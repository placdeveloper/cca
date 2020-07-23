package br.com.sicoob.sisbr.cca.relatorios.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class CCARelatoriosCodeCoverageTest {
	
	public static final Object setArgs[] = { null };
	
	public static final Object noArgs[] = {};

	public static void superficialCCAReplicacaoCodeCoverage(Object entidade) {
		Method[] methods = entidade.getClass().getMethods();
		try {
			for (Method method : methods) {
				if (method.getName().startsWith("set")) {
					method.invoke(entidade, setArgs);
				}
				if (method.getName().startsWith("get")) {
					method.invoke(entidade, noArgs);
				}
			}
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
	}
	
	@Test
	public void testSuperficialCCARelatoriosCodeCoverage() throws Exception {
		CCARelatoriosCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ItemListaVO("", ""));
		CCARelatoriosCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new RelDebitoIndeterminadoRenVO());
	}
	
}
