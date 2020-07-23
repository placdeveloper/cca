package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * @author rodrigo.melchior
 */
@Stateless
@Remote(JobServico.class)
public class SaldoContaCapitalJob extends ContaCapitalProcessamentoJob{
	
	private static final String STEP_SALDOCONTACAPITAL = "cca_processamento/SaldoContaCapitalStepRemote";
	
	/**
	 * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public List<Step> obterSteps(ContextoExecucao contexto) {
		List<Step> steps = new ArrayList<Step>();
		steps.add(ejb(STEP_SALDOCONTACAPITAL).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		return steps;
	}
	
}
