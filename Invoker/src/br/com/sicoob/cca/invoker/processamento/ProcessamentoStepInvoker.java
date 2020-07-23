package br.com.sicoob.cca.invoker.processamento;
import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.invoker.ContaCapitalInvoker;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * Testes de steps
 * @author Nairon.Silva
 */
public class ProcessamentoStepInvoker extends ContaCapitalInvoker {

	@Override
	protected void executar() throws BancoobException {
		StepServico step = criarServico(StepServico.class);
		ContextoExecucao contexto = new ContextoExecucao();
		Parametro parametroDinamico = new Parametro(null, "3008", TipoParametro.TEXTO);
		contexto.setParametroDinamico(parametroDinamico);
		step.executar(contexto);
	}
	
	public static void main(String[] args) {
		new ProcessamentoStepInvoker().invoke("cca_processamento/ConciliacaoContabilStepServicoRemote");
		new ProcessamentoStepInvoker().invoke("cca_processamento/PercentualSingularStepServicoRemote"); 
		new ProcessamentoStepInvoker().invoke("cca_processamento/LancamentoContabilSingularStepServicoRemote");
		new ProcessamentoStepInvoker().invoke("cca_processamento/GestaoEmpresarialStepRemote"); 
		new ProcessamentoStepInvoker().invoke("cca_processamento/PLDContaCapitalStepRemote"); 
		new ProcessamentoStepInvoker().invoke("cca_processamento/ExpurgoReplicacaoContaCapitalStepRemote"); 
	}

}
