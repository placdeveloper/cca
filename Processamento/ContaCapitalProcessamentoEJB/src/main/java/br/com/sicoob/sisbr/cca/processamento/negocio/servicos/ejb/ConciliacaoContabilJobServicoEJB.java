package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * @author marco.nascimento@sicoob.com.br
 * @since 04/02/2014
 */
@Stateless
@Remote(JobServico.class)
public class ConciliacaoContabilJobServicoEJB extends ContaCapitalProcessamentoJob {
	
	private static final String SERVICO_STEP = "cca_processamento/ConciliacaoContabilStepServicoRemote";

	/**
	 * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public List<Step> obterSteps(ContextoExecucao ctx) {
		List<Step> steps = new ArrayList<Step>();
		steps.add(ejb(SERVICO_STEP).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		return steps;
	}

}