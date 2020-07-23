package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.processamento.util.ProcessamentoUtil;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * @author marco.nascimento@sicoob.com.br
 * @since 18/06/2014
 */
@Stateless
@Remote(JobServico.class)
public class LancamentoContabilSingularJobServicoEJB extends ContaCapitalProcessamentoJob {
	
	private static final ISicoobLogger LOG = getLogger(LancamentoContabilSingularJobServicoEJB.class);
	
	private static final String SERVICO_LANCAMENTO_SINGULAR_STEP = "cca_processamento/LancamentoContabilSingularStepServicoRemote";
	
	/**
	 * @see ISicoobContextualJob#obterSteps(ContextoExecucaoJob)
	 */
	public List<Step> obterSteps(ContextoExecucao ctx) {
		List<Step> steps = new ArrayList<Step>();
		steps.addAll(criarStep(ctx));
		return steps;
	}
	
	/**
	 * Cria StepMetadata
	 * @param ctx
	 * @return
	 */
	private List<Step> criarStep(ContextoExecucao ctx) {
		LOG.info("Criando Step");
		List<Step> steps = new ArrayList<Step>();
		try {
			Integer numCoop = getNumCoop(ctx);
			if(ProcessamentoUtil.isSingular(ProcessamentoUtil.getIdInstituicao(numCoop))) {
				steps.add(ejb(SERVICO_LANCAMENTO_SINGULAR_STEP).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
				LOG.info("Step criado, numCoop:" + numCoop);
			}
		} catch (NumberFormatException e) {
			LOG.erro(e, "CCA." + e.getMessage());
		} catch (BancoobException e) {
			LOG.erro(e, "CCA." + e.getMessage());
		}
		return steps;
	}
	
}