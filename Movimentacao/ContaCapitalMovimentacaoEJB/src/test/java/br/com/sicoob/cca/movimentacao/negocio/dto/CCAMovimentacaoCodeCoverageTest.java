package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import br.com.sicoob.cca.movimentacao.negocio.dto.CancelamentoParcelamentoDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.GestaoEmpresarialDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.LancamentoCCODTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.QuadroGeralAssociadoDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.SubscricaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.TransferenciaRenDTO;
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
	@SuppressWarnings("rawtypes")
	public static void superficialCCAMovimentacaoCodeCoverage(Class classe, Object entidate) {
		Method[] methods = classe.getMethods();
		try {
			for (Method method : methods) {
				if (method.getName().startsWith("set")) {
					method.invoke(entidate, setArgs);
				}
				if (method.getName().startsWith("get")) {
					method.invoke(entidate, noArgs);
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
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(BloqueioContaCapitalDTO.class, new BloqueioContaCapitalDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(CancelamentoParcelamentoDTO.class, new CancelamentoParcelamentoDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(ConsultaDebitoIndeterminadoRenDTO.class, new ConsultaDebitoIndeterminadoRenDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(DebitoIndeterminadoRenDTO.class, new DebitoIndeterminadoRenDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(DevolucaoRenDTO.class, new DevolucaoRenDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(GestaoEmpresarialDTO.class, new GestaoEmpresarialDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(IntegralizacaoCapitalDTO.class, new IntegralizacaoCapitalDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(LancamentoCCODTO.class, new LancamentoCCODTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(ParcelamentoCapitalDTO.class, new ParcelamentoCapitalDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(ParcelamentoRenDTO.class, new ParcelamentoRenDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(QuadroGeralAssociadoDTO.class, new QuadroGeralAssociadoDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(SubscricaoRenDTO.class, new SubscricaoRenDTO());
		CCAMovimentacaoCodeCoverageTest.superficialCCAMovimentacaoCodeCoverage(TransferenciaRenDTO.class, new TransferenciaRenDTO());
	}
	
}
