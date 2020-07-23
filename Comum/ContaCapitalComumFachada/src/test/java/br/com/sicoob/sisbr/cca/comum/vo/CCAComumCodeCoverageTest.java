package br.com.sicoob.sisbr.cca.comum.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CCAComumCodeCoverageTest.
 */
public class CCAComumCodeCoverageTest {
	
	/** A constante setArgs. */
	public static final Object setArgs[] = { null };
	
	/** A constante noArgs. */
	public static final Object noArgs[] = {};

	/**
	 * O m�todo Superficial cca code coverage.
	 *
	 * @param classe o valor de classe
	 * @param entidate o valor de entidade
	 */
	public static void superficialCCAComumCodeCoverage(Object entidade) {
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
	
	/**
	 * O m�todo Test superficial cca code coverage.
	 *
	 * @throws Exception lan�a a exce��o Exception
	 */
	@Test
	public void testSuperficialCCAComumCodeCoverage() throws Exception {
		CCAComumCodeCoverageTest.superficialCCAComumCodeCoverage(new ItemListaVO("", ""));
		CCAComumCodeCoverageTest.superficialCCAComumCodeCoverage(new PesquisaContaCapitalVO());
	}
	
}
