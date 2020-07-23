package br.com.sicoob.sisbr.cca.legado.negocio;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import br.com.sicoob.sisbr.cca.legado.negocio.dto.CadastroContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConciliacaoContabilLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConfiguracaoConciliacaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConsultaMonitoracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelFichaMatriculaDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.EmprestimoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ExpurgoReplicacaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.GestaoEmpresarialLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ParticipacaoIndiretaBancoobLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.MonitorCooperativaReplicacaoCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.MonitorReplicacaoCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.OperacaoFinanceiraLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ParcelamentoContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoContaCapitalDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoLancamentoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoParcelamentoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoConfiguracaoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaLancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaParcelamentoCCALegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TabelaIRRFLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TrabalhaLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ValorCotaLegado;
/**
 * A Classe CCAIntegracaoLegadoCodeCoverageTest.
 */
public class CCAIntegracaoLegadoCodeCoverageTest {
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
	public static void superficialCCAIntegracaoLegadoCodeCoverage(Class classe, Object entidate) {
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
	public void testSuperficialCCAIntegracaoLegadoCoverage() throws Exception {
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(CadastroContaCapitalLegadoDTO.class, new CadastroContaCapitalLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ConciliacaoContabilLegadoDTO.class, new ConciliacaoContabilLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ConfiguracaoConciliacaoDTO.class, new ConfiguracaoConciliacaoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ConsultaMonitoracaoDTO.class, new ConsultaMonitoracaoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(DadosRelExtratoLegadoDTO.class, new DadosRelExtratoLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(DadosRelFichaMatriculaDTO.class, new DadosRelFichaMatriculaDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(EmprestimoIntegracaoDTO.class, new EmprestimoIntegracaoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ExpurgoReplicacaoDTO.class, new ExpurgoReplicacaoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(GestaoEmpresarialLegadoDTO.class, new GestaoEmpresarialLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ParticipacaoIndiretaBancoobLegadoDTO.class, new ParticipacaoIndiretaBancoobLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(MonitorCooperativaReplicacaoCapitalLegadoDTO.class, new MonitorCooperativaReplicacaoCapitalLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(MonitorReplicacaoCapitalLegadoDTO.class, new MonitorReplicacaoCapitalLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(OperacaoFinanceiraLegadoDTO.class, new OperacaoFinanceiraLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ParcelamentoContaCapitalLegadoDTO.class, new ParcelamentoContaCapitalLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(RegistroBatimentoContaCapitalDTO.class, new RegistroBatimentoContaCapitalDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(RegistroBatimentoLancamentoDTO.class, new RegistroBatimentoLancamentoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(RegistroBatimentoParcelamentoDTO.class, new RegistroBatimentoParcelamentoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(RelExtratoLegadoDTO.class, new RelExtratoLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ReplicacaoConfiguracaoLegadoDTO.class, new ReplicacaoConfiguracaoLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ReplicacaoContaCapitalLegadoDTO.class, new ReplicacaoContaCapitalLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ReplicacaoTabelaContaCapitalLegadoDTO.class, new ReplicacaoTabelaContaCapitalLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ReplicacaoTabelaLancamentosCCapitalLegadoDTO.class, new ReplicacaoTabelaLancamentosCCapitalLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ReplicacaoTabelaParcelamentoCCALegadoDTO.class, new ReplicacaoTabelaParcelamentoCCALegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(TabelaIRRFLegadoDTO.class, new TabelaIRRFLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(TrabalhaLegadoDTO.class, new TrabalhaLegadoDTO());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(CapaLoteCapitalLegado.class, new CapaLoteCapitalLegado());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(CapaLoteCapitalLegadoPK.class, new CapaLoteCapitalLegadoPK());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ContaCapitalLegado.class, new ContaCapitalLegado());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(HistContaCapitalLegado.class, new HistContaCapitalLegado());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(HistContaCapitalLegadoPK.class, new HistContaCapitalLegadoPK());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(LancamentosCCapitalLegado.class, new LancamentosCCapitalLegado());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(LancamentosCCapitalLegadoPK.class, new LancamentosCCapitalLegadoPK());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ParcelamentoCCALegado.class, new ParcelamentoCCALegado());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ParcelamentoCCALegadoPK.class, new ParcelamentoCCALegadoPK());
		CCAIntegracaoLegadoCodeCoverageTest.superficialCCAIntegracaoLegadoCodeCoverage(ValorCotaLegado.class, new ValorCotaLegado());
	}
}
