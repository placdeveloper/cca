package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;

/**
 * Classe abstrata para os jobs.
 * @author Nairon.Silva
 */
public abstract class ContaCapitalProcessamentoJob extends JobSicoobServico {

	protected static final Double VALOR_ESFORCO_JOB_1 = 1.0;
	
	/**
	 * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public VerificacaoDependencias verificarDependencias(ContextoExecucao ctx) {
		return sucesso();
	}
	
	/**
	 * Retorna o numero da cooperativa no parametro dinamico.
	 * @param ctx
	 * @return
	 */
	protected Integer getNumCoop(ContextoExecucao ctx) {
		return Integer.valueOf(ctx.getParametroDinamico().getValor().toString());
	}
	
	/**
	 * Retorna o logger
	 * @return
	 */
	protected static ISicoobLogger getLogger(Class<?> clazz) {
		return SicoobLoggerPadrao.getInstance(clazz);
	}

}
