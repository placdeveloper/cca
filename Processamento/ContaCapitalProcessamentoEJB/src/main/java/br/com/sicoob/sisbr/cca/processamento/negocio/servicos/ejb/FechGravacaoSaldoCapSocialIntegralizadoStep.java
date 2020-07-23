package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechGravacaoSaldoCapSocialIntegralizadoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.processamento.constantes.EnumFechamento;
import br.com.sicoob.sisbr.cca.processamento.negocio.excecao.ProcessamentoGestaoEmpresarialException;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
* @author Ricardo.Barcante
*/
@Stateless
@Remote(StepServico.class)
public class FechGravacaoSaldoCapSocialIntegralizadoStep extends ContaCapitalProcessamentoStep {
	
	private static final ISicoobLogger LOG = getLogger(FechGravacaoSaldoCapSocialIntegralizadoStep.class);
	
	@EJB
	private FechGravacaoSaldoCapSocialIntegralizadoLegadoServicoLocal servico;

	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			rodarFechamento(servico, getNumCoop(ctx), EnumFechamento.ID_PROCESSO_GRAVA_SALDO_CAPITAL_SOCIAL_INT.getIntValue());
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