package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.IntegralizacaoOutrosBancosServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * @author rodrigo.melchior
 */
@Stateless
@Remote(StepServico.class)
public class IntegralizacaoOutrosBancosStep extends ContaCapitalProcessamentoStep{
	
	private static final ISicoobLogger LOG = getLogger(IntegralizacaoOutrosBancosStep.class);
	
	@EJB
	private IntegralizacaoOutrosBancosServicoLocal integralizacaoOutrosBancosServicoLocal;

	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao contexto) {
		try {
			integralizacaoOutrosBancosServicoLocal.IntegralizacaoOutrosBancosSWS(getNumCoop(contexto));
			return sucesso();
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}
	}
}
