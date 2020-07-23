package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelLancContabilServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.processamento.constantes.EnumFechamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
* @author Ricardo.Barcante
*/
@Stateless
@Remote(StepServico.class)
public class FechRelLancContabilStep extends ContaCapitalProcessamentoStep {
	
	private static final ISicoobLogger LOG = getLogger(FechRelLancContabilStep.class);
	
	@EJB
	private FechRelLancContabilServicoLocal fechRelLancContabilServicoLocal;
	
	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			rodarFechamento(fechRelLancContabilServicoLocal, getNumCoop(ctx), EnumFechamento.ID_PROCESSO_REL_LANC_CONTABIL.getIntValue());
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}
		return sucesso();
	}
}