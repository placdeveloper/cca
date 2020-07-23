package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.GestaoEmpresarialServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.processamento.negocio.excecao.ProcessamentoGestaoEmpresarialException;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Remote(JobServico.class)
public class GestaoEmpresarialJob extends ContaCapitalProcessamentoJob {
	
	private static final ISicoobLogger LOG = getLogger(GestaoEmpresarialJob.class);
	
	private static final String STEP_GESTAOEMPRESARIAL = "cca_processamento/GestaoEmpresarialStepRemote";
	private static final String MSG_FINALIZAR_JOB = "Não há novos lançamentos.";
	
	@EJB
	private GestaoEmpresarialServicoLocal geServico;

	/**
	 * @see br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb.ContaCapitalProcessamentoJob#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public VerificacaoDependencias verificarDependencias(ContextoExecucao ctx) {
		VerificacaoDependencias retorno = null;
		try {
			Integer numCoop = getNumCoop(ctx);
			if(geServico.iniciarProcessamento(numCoop) || geServico.isPrimeiraCarga(numCoop)) {
				retorno = sucesso();
			} else {
				retorno = finalizarJob(MSG_FINALIZAR_JOB);
			}
		} catch (ProcessamentoGestaoEmpresarialException e) {
			LOG.erro(e, e.getMessage());
			retorno = erro(e.getMessage());
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			retorno = erro(e.getMessage());
		}
		
		return retorno;
	}

	/**
	 * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public List<Step> obterSteps(ContextoExecucao contexto) {
		List<Step> steps = new ArrayList<Step>();
		steps.add(ejb(STEP_GESTAOEMPRESARIAL).realizandoEsforcoDe(VALOR_ESFORCO_JOB_1));
		return steps;
	}
	
}