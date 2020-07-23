package br.com.sicoob.cca.invoker.processamento;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.invoker.ContaCapitalInvoker;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * Testes de jobs 
 * @author Nairon.Silva
 */
public class ProcessamentoJobInvoker extends ContaCapitalInvoker {

	private static final Logger LOG = Logger.getLogger(ProcessamentoJobInvoker.class.getName());
	
	@Override
	protected void executar() throws BancoobException {
		JobServico job = criarServico(JobServico.class);
		ContextoExecucao contexto = new ContextoExecucao();
		Parametro parametroDinamico = new Parametro(null, "3008", TipoParametro.TEXTO);
		contexto.setParametroDinamico(parametroDinamico);
		
		List<Step> steps = job.obterSteps(contexto);
		LOG.info("Criou "+steps.size()+" steps");
		
	}
	
	public static void main(String[] args) {
		new ProcessamentoJobInvoker().invoke("cca_processamento/ConciliacaoContabilJobServicoRemote");
		new ProcessamentoJobInvoker().invoke("cca_processamento/PercentualSingularJobServicoRemote");
		new ProcessamentoJobInvoker().invoke("cca_processamento/LancamentoContabilSingularJobServicoRemote");
		new ProcessamentoJobInvoker().invoke("cca_processamento/ParticipacaoIndiretaCentralizadorJobServicoRemote");
		new ProcessamentoJobInvoker().invoke("cca_processamento/GestaoEmpresarialJobRemote");
		new ProcessamentoJobInvoker().invoke("cca_processamento/PLDContaCapitalJobRemote");
		new ProcessamentoJobInvoker().invoke("cca_processamento/ExpurgoReplicacaoContaCapitalJobRemote");
	}

}
