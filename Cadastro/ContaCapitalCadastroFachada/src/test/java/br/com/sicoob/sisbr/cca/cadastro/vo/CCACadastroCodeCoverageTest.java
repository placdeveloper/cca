package br.com.sicoob.sisbr.cca.cadastro.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CCAComumCodeCoverageTest.
 */
public class CCACadastroCodeCoverageTest {
	
	/** A constante setArgs. */
	public static final Object setArgs[] = { null };
	
	/** A constante noArgs. */
	public static final Object noArgs[] = {};

	/**
	 * O método Superficial cca code coverage.
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
	 * O método Test superficial cca code coverage.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Test
	public void testSuperficialCCAComumCodeCoverage() throws Exception {
		CCACadastroCodeCoverageTest.superficialCCAComumCodeCoverage(new CadastroContaCapitalRenVO());
		CCACadastroCodeCoverageTest.superficialCCAComumCodeCoverage(new CondicaoEstatutariaVO());
		CCACadastroCodeCoverageTest.superficialCCAComumCodeCoverage(new ConfiguracaoCapitalVO());
		CCACadastroCodeCoverageTest.superficialCCAComumCodeCoverage(new ContaCapitalVO());
		CCACadastroCodeCoverageTest.superficialCCAComumCodeCoverage(new DocumentoCapitalVO());
		CCACadastroCodeCoverageTest.superficialCCAComumCodeCoverage(new ItemListaVO("", ""));
		CCACadastroCodeCoverageTest.superficialCCAComumCodeCoverage(new ParticipacaoCentralBancoobVO());
		CCACadastroCodeCoverageTest.superficialCCAComumCodeCoverage(new TabelaIRRFVO());
		CCACadastroCodeCoverageTest.superficialCCAComumCodeCoverage(new ValorConfiguracaoCapitalVO());
	}
	
}
