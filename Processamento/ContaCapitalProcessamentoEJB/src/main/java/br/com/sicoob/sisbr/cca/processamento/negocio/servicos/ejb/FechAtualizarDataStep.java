package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.processamento.constantes.EnumFechamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * @author ricardo.barcante
 */
@Stateless
@Remote(StepServico.class)
public class FechAtualizarDataStep extends ContaCapitalProcessamentoStep {
	private static final ISicoobLogger LOG = getLogger(FechAtualizarDataStep.class);
	
	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServicoLocal;
	
	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			rodarFechamento(produtoLegadoServicoLocal, getNumCoop(ctx), EnumFechamento.ID_PROCESSO_ATUALIZAR_DATA.getIntValue());
			return sucesso();
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}
	}
}
