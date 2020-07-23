package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.GestaoEmpresarialServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.processamento.negocio.excecao.ProcessamentoGestaoEmpresarialException;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Remote(StepServico.class)
public class GestaoEmpresarialStep extends ContaCapitalProcessamentoStep {
	
	private static final ISicoobLogger LOG = getLogger(GestaoEmpresarialStep.class);
	
	@EJB
	private GestaoEmpresarialServicoLocal geServico;

	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			Integer numCoop = getNumCoop(ctx);
			geServico.realizarCarga(numCoop);
			return sucesso();
		} catch (ProcessamentoGestaoEmpresarialException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}
	}
	
}