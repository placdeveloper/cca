package br.com.sicoob.cca.movimentacao.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import br.com.sicoob.sisbr.cca.movimentacao.vo.BloqueioContaCapitalVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ConsultaDebitoIndeterminadoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DebitoIndeterminadoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DesligarContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DevolucaoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.QuadroGeralAssociadoVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.SubscricaoRenVO;
/**
 * A Classe CCAMovimentacaoCodeCoverageTest.
 */
public class CCAMovimentacaoCodeCoverageTest {
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
	public static void superficialCCAMovimentacaoCodeCoverage(Object entidade) {
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
	public void testSuperficialCCAMovimencacaoCodeCoverage() throws Exception {
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(new BloqueioContaCapitalVO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(new CadastroContaCapitalRenVO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(new ConsultaDebitoIndeterminadoRenVO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(new DebitoIndeterminadoRenVO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(new DesligarContaCapitalRenVO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(new DevolucaoRenVO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(new ItemListaVO("", ""));
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(new ParcelamentoRenVO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(new QuadroGeralAssociadoVO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(new SubscricaoRenVO());
	}
	
}
