package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.servico.JobServico;

/**
* @author Ricardo.Barcante
*/
@Stateless
@Remote(JobServico.class)
public class FechRelatoriosContaCapitalJob extends ContaCapitalProcessamentoJob {

	private static final String STEP_RELATORIO_SALDO_CONTA_CAPITAL = "cca_processamento/FechRelSaldoContaCapitalStepRemote";
	private static final String STEP_RELATORIO_POSICAO_DIARIA_CARTEIRA= "cca_processamento/FechRelPosicaoDiariaCarteiraStepRemote";
	private static final String STEP_RELATORIO_LANCAMENTO_CONTABIL = "cca_processamento/FechRelLancContabilStepRemote";
	private static final String STEP_RELATORIO_RESUMO_LANCAMENTOS= "cca_processamento/FechRelResumoLancamentosStepRemote";
	private static final String STEP_RELATORIO_LANCAMENTOS_SINTETICO = "cca_processamento/FechRelLancamentosSinteticoStepRemote";
	private static final String STEP_RELATORIO_LANCAMENTOS_ANALITICO = "cca_processamento/FechRelLancamentosAnaliticoStepRemote";
	private static final String STEP_RELATORIO_CONCILIACAO_CONTABIL = "cca_processamento/FechRelConciliacaoContabilStepRemote";

	/**
	 * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public List<Step> obterSteps(ContextoExecucao contexto) {
		
		List<Step> steps = new ArrayList<Step>();
		steps.add(ejb(STEP_RELATORIO_SALDO_CONTA_CAPITAL).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		steps.add(ejb(STEP_RELATORIO_POSICAO_DIARIA_CARTEIRA).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		steps.add(ejb(STEP_RELATORIO_LANCAMENTO_CONTABIL).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		steps.add(ejb(STEP_RELATORIO_RESUMO_LANCAMENTOS).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		steps.add(ejb(STEP_RELATORIO_LANCAMENTOS_SINTETICO).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		steps.add(ejb(STEP_RELATORIO_LANCAMENTOS_ANALITICO).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		steps.add(ejb(STEP_RELATORIO_CONCILIACAO_CONTABIL).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		
		return steps;
	}
}