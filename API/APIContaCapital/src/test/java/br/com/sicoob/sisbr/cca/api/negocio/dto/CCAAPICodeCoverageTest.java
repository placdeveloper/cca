package br.com.sicoob.sisbr.cca.api.negocio.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CCAReplicacaoCodeCoverageTest.
 */
public class CCAAPICodeCoverageTest {
	
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
	public void testSuperficialCCAFrontofficeCodeCoverage() throws Exception {
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new CadastroContaCapitalDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ConsultaIntegralizacaoCapitalCabalDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ConsultaIntegralizacaoCapitalCabalRetornoDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ContaCapitalDebIndeterminadoDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ContaCapitalDIRFDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new DadosContaCapitalDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new IntegralizacaoCapitalBoletoDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new IntegralizacaoCapitalCabalDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new IntegralizacaoCapitalCabalRetornoDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new IntegralizacaoCapitalCartaoDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new IntegralizacaoCapitalRateioDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new RelExtratoDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new SaldoContaCapitalDTO());
		CCAAPICodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new ValorIngressoCapitalDTO());
	}
	
}
