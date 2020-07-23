package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechCalculoProvisaoJurosServicoLocal;
import br.com.sicoob.sisbr.cca.processamento.constantes.EnumFechamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;
/**
* @author antonio.genaro
*/
@Stateless
@Remote(StepServico.class)
public class FechGerarCalculoProvisaoJurosStep extends ContaCapitalProcessamentoStep {

	private static final ISicoobLogger LOG = getLogger(FechGerarCalculoProvisaoJurosStep.class);
	
	@EJB
	private FechCalculoProvisaoJurosServicoLocal fechCalculoProvisaoJurosServicoLocal;
	
	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			rodarFechamento(fechCalculoProvisaoJurosServicoLocal, getNumCoop(ctx), EnumFechamento.ID_PROCESSO_GERAR_CALC_PROV_JUROS.getIntValue());
			return sucesso();
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}
	}

}
