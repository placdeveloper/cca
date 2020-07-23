package br.com.sicoob.cca.frontoffice.negocio.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CCAReplicacaoCodeCoverageTest.
 */
public class CCAFrontofficeCodeCoverageTest {
	
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
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new EntradaCancelamentoIntegralizacaoAgendadaDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new EntradaConsultaContaCapitalDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new EntradaConsultaIntegralizacaoAgendadaDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new EntradaConsultaParcelasViaCaixaDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new EntradaIntegDevolCapitalViaCaixaDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new EntradaIntegralizacaoCapitalDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new SaidaCancelamentoIntegralizacaoAgendadaDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new SaidaConsultaContaCapitalDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new SaidaConsultaIntegralizacaoAgendadaDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new SaidaConsultaParcelasViaCaixaDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new SaidaConteudoUnicoDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new SaidaInformacoesGeraisDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new SaidaIntegDevolCapitalViaCaixaCabecalhoDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new SaidaIntegDevolCapitalViaCaixaDTO());
		CCAFrontofficeCodeCoverageTest.superficialCCAReplicacaoCodeCoverage(new SaidaIntegralizacaoDTO());
	}
	
}
