package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelConciliacaoContabilServicoRemote;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.processamento.constantes.EnumFechamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * @author Kleber Alves
 */
@Stateless
@Remote(StepServico.class)
public class FechRelConciliacaoContabilStep extends ContaCapitalProcessamentoStep {

	private static final ISicoobLogger LOGGER = getLogger(FechRelConciliacaoContabilStep.class);
	
	@EJB
	private FechRelConciliacaoContabilServicoRemote relatorioConciliacaoService;
	
	public RetornoExecucao executar(ContextoExecucao ctx) {

		try{
			rodarFechamento(relatorioConciliacaoService, getNumCoop(ctx), EnumFechamento.ID_PROCESSO_REL_CONCILIACAO_CONTABIL.getIntValue());
		} catch (BancoobException e) {
			LOGGER.erro(e, e.getMessage());
			return erro(e.getMessage());
		}

		return sucesso();
	}
}
