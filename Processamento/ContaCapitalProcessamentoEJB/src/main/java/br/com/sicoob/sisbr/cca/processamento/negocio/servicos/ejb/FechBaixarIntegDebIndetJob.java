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
public class FechBaixarIntegDebIndetJob extends ContaCapitalProcessamentoJob {
	private static final String STEP_BAIXAR_INTEG_DEB_INDET_SQL = "cca_processamento/FechBaixarIntegDebIndetSQLStepRemote";
	private static final String STEP_BAIXAR_INTEG_DEB_INDET_DB2 = "cca_processamento/FechBaixarIntegDebIndetDB2StepRemote";

	/**
	 * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public List<Step> obterSteps(ContextoExecucao contexto) {
		
		List<Step> steps = new ArrayList<Step>();
		steps.add(ejb(STEP_BAIXAR_INTEG_DEB_INDET_SQL).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		steps.add(ejb(STEP_BAIXAR_INTEG_DEB_INDET_DB2).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		return steps;
	}


}
