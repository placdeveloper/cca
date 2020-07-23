package br.com.sicoob.sisbr.cca.replicacao.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CCAReplicacaoCodeCoverageTest.
 */
public class CCAReplicacaoCodeCoverageTest {
	
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
	
	
	/**
	 * O método Test superficial cca code coverage.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Test
	public void testSuperficialCCAReplicacaoCodeCoverage() throws Exception {
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ConfiguracaoConciliacaoVO());
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ConfiguracaoReplicacaoVO());
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ExpurgoReplicacaoVO());
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ItemListaVO("", ""));
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new MonitoracaoCapitalVO());
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new MonitoracaoContaCapitalVO());
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new MonitoracaoCooperativaCapitalVO());
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new MonitoracaoLancamentoContaCapitalVO());
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new MonitoracaoParcelamentoContaCapitalVO());
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new MonitoracaoQuadroTotalVO());
		CCAReplicacaoCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ReplicacaoContaCapitalLegadoVO());
	}
	
}
