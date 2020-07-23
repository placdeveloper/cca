package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelPosicaoDiariaCarteiraServicoLocal;
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
public class FechRelPosicaoDiariaCarteiraStep extends ContaCapitalProcessamentoStep {
	
	private static final ISicoobLogger LOG = getLogger(FechRelPosicaoDiariaCarteiraStep.class);
	
	@EJB
	private FechRelPosicaoDiariaCarteiraServicoLocal fechRelPosicaoDiariaCarteiraServicoLocal;
	
	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			rodarFechamento(fechRelPosicaoDiariaCarteiraServicoLocal, getNumCoop(ctx), EnumFechamento.ID_PROCESSO_REL_POSICAO_DIARIA_CARTEIRA.getIntValue());
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}
		return sucesso();
	}
}
