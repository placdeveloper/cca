package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.SaldoContaCapitalServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * @author rodrigo.melchior
 */
@Stateless
@Remote(StepServico.class)
public class SaldoContaCapitalStep extends ContaCapitalProcessamentoStep{
	
	private static final ISicoobLogger LOG = getLogger(SaldoContaCapitalStep.class);
	
	@EJB
	private SaldoContaCapitalServicoLocal saldoContaCapitalServicoLocal;
	
	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			saldoContaCapitalServicoLocal.realizarCargaSWS(getNumCoop(ctx));
			return sucesso();
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}
	}
}	