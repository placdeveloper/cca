package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.servico.JobServico;
/**
 * @author antonio.genaro
 */
@Stateless
@Remote(JobServico.class)
public class FechBaixarParcelaViaCCOJob extends ContaCapitalProcessamentoJob {

	private static final String STEP_BAIXAR_PARCELA_SQL = "cca_processamento/FechBaixarParcelaViaCCOSQLStepRemote";
	private static final String STEP_BAIXAR_PARCELA_DB2 = "cca_processamento/FechBaixarParcelaViaCCODB2StepRemote";

	/**
	 * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public List<Step> obterSteps(ContextoExecucao contexto) {
		
		List<Step> steps = new ArrayList<Step>();
		steps.add(ejb(STEP_BAIXAR_PARCELA_SQL).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		steps.add(ejb(STEP_BAIXAR_PARCELA_DB2).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		return steps;
	}

}
