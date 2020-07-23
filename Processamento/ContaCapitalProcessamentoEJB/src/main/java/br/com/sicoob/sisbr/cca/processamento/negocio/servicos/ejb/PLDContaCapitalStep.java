package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.PLDContaCapitalLegadoServicoLocal;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Remote(StepServico.class)
public class PLDContaCapitalStep extends ContaCapitalProcessamentoStep {
	
	private static final ISicoobLogger LOG = getLogger(PLDContaCapitalStep.class);
	
	@EJB
	private PLDContaCapitalLegadoServicoLocal pldServico;

	/**
	 * Executa PLD Conta Capital
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			Integer numCoop = getNumCoop(ctx);
			pldServico.gerarPLD(numCoop);
			return sucesso();
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}
	}
	
}