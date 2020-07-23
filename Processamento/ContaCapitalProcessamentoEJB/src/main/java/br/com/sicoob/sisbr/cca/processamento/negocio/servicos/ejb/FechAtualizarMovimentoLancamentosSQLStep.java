package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.LancamentosCCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.processamento.constantes.EnumFechamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;
/**
 * @author antonio.genaro
 */
@Stateless
@Remote(StepServico.class)
public class FechAtualizarMovimentoLancamentosSQLStep extends ContaCapitalProcessamentoStep {

	private static final ISicoobLogger LOG = getLogger(FechAtualizarMovimentoLancamentosSQLStep.class);
	
	@EJB
	private LancamentosCCapitalLegadoServicoLocal lancamentosCCapitalLegadoServicoLocal;
	
	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			rodarFechamentoSQL(lancamentosCCapitalLegadoServicoLocal, getNumCoop(ctx), EnumFechamento.ID_PROCESSO_ATUALIZA_MOV_LANC_SQL.getIntValue());
			return sucesso();
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}	
	}
}
