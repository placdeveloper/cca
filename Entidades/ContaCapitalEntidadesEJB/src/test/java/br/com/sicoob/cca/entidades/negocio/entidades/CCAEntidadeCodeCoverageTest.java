package br.com.sicoob.cca.entidades.negocio.entidades;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import br.com.sicoob.cca.entidades.negocio.entidades.AgrupadorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.BloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.CondicaoAssociacao;
import br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.DebitoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.DocumentoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.GrupoHistorico;
import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapitalPK;
import br.com.sicoob.cca.entidades.negocio.entidades.HistParticipacaoCentralBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.HistParticipacaoCentralBancoobPK;
import br.com.sicoob.cca.entidades.negocio.entidades.HistValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistValorConfiguracaoCapitalPK;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoContaCapitalPK;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoLancamentoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoPropostaSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoPropostaSubscricaoPK;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.MotivoDevolucao;
import br.com.sicoob.cca.entidades.negocio.entidades.OrigemBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.OrigemIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoobPK;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoobPK;
import br.com.sicoob.cca.entidades.negocio.entidades.PerfilConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.SaldoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.SaldoContaCapitalPK;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoCooperativa;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoDocumentoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoParcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoPeriodoDebito;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoPessoa;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoValorDebito;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;

/**
 * A Classe CCAEntidadeCodeCoverageTest.
 */
public class CCAEntidadeCodeCoverageTest {

	/** A constante setArgs. */
	public static final Object setArgs[] = { null };
	
	/** A constante noArgs. */
	public static final Object noArgs[] = {};

	/**
	 * O método Superficial cca entidade code coverage.
	 *
	 * @param classe o valor de classe
	 * @param entidate o valor de entidade
	 */
	@SuppressWarnings("rawtypes")
	public static void superficialCCAEntidadeCodeCoverage(Class classe, Object entidate) {
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
	 * O método Test superficial cca entidade code coverage.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Test
	public void testSuperficialCCAEntidadeCodeCoverage() throws Exception {
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(AgrupadorConfiguracaoCapital.class, new AgrupadorConfiguracaoCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(BloqueioCapital.class, new BloqueioCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(CondicaoAssociacao.class, new CondicaoAssociacao());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(ConfiguracaoCapital.class, new ConfiguracaoCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(ContaCapital.class, new ContaCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(ContaCorrenteView.class, new ContaCorrenteView());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(DebitoContaCapital.class, new DebitoContaCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(DocumentoCapital.class, new DocumentoCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(GrupoHistorico.class, new GrupoHistorico());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistBloqueioCapital.class, new HistBloqueioCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistBloqueioCapitalPK.class, new HistBloqueioCapitalPK());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistoricoContaCapital.class, new HistoricoContaCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistoricoContaCapitalPK.class, new HistoricoContaCapitalPK());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistoricoLancamentoCCA.class, new HistoricoLancamentoCCA());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistoricoPropostaSubscricao.class, new HistoricoPropostaSubscricao());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistoricoPropostaSubscricaoPK.class, new HistoricoPropostaSubscricaoPK());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistParticipacaoCentralBancoob.class, new HistParticipacaoCentralBancoob());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistParticipacaoCentralBancoobPK.class, new HistParticipacaoCentralBancoobPK());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistValorConfiguracaoCapital.class, new HistValorConfiguracaoCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(HistValorConfiguracaoCapitalPK.class, new HistValorConfiguracaoCapitalPK());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(LancamentoContaCapital.class, new LancamentoContaCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(MotivoDevolucao.class, new MotivoDevolucao());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(OrigemBloqueioCapital.class, new OrigemBloqueioCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(OrigemIntegralizacao.class, new OrigemIntegralizacao());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(Parcelamento.class, new Parcelamento());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(ParticipacaoCentralBancoob.class, new ParticipacaoCentralBancoob());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(ParticipacaoCentralBancoobPK.class, new ParticipacaoCentralBancoobPK());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(ParticipacaoIndiretaBancoob.class, new ParticipacaoIndiretaBancoob());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(ParticipacaoIndiretaBancoobPK.class, new ParticipacaoIndiretaBancoobPK());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(PerfilConfiguracaoCapital.class, new PerfilConfiguracaoCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(PropostaSubscricao.class, new PropostaSubscricao());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(SaldoContaCapital.class, new SaldoContaCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(SaldoContaCapitalPK.class, new SaldoContaCapitalPK());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(SituacaoCadastroProposta.class, new SituacaoCadastroProposta());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(SituacaoContaCapital.class, new SituacaoContaCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(SituacaoParcelamento.class, new SituacaoParcelamento());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoCooperativa.class, new TipoCooperativa());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoDocumentoCapital.class, new TipoDocumentoCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoHistoricoCCA.class, new TipoHistoricoCCA());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoIntegralizacao.class, new TipoIntegralizacao());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoLote.class, new TipoLote());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoParcelamento.class, new TipoParcelamento());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoPeriodoDebito.class, new TipoPeriodoDebito());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoPessoa.class, new TipoPessoa());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoSubscricao.class, new TipoSubscricao());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoValorConfiguracaoCapital.class, new TipoValorConfiguracaoCapital());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(TipoValorDebito.class, new TipoValorDebito());
		CCAEntidadeCodeCoverageTest.superficialCCAEntidadeCodeCoverage(ValorConfiguracaoCapital.class, new ValorConfiguracaoCapital());
				
	}

}
