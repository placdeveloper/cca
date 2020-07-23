package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.FechBaixarParcDebIndetServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.processamento.constantes.EnumFechamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;
/**
 * @author antonio.genaro
 */
@Stateless
@Remote(StepServico.class)
public class FechBaixarIntegDebIndetDB2Step extends ContaCapitalProcessamentoStep {

	private static final ISicoobLogger LOG = getLogger(FechBaixarIntegDebIndetDB2Step.class);
	
	@EJB
	private FechBaixarParcDebIndetServicoLocal fechBaixarParcDebIndetServicoLocal;
	
	/**
	 * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
	 */
	public RetornoExecucao executar(ContextoExecucao ctx) {
		try {
			rodarFechamento(fechBaixarParcDebIndetServicoLocal, getNumCoop(ctx), EnumFechamento.ID_PROCESSO_BAIXA_INT_DEB_INDET_DB2.getIntValue());
			return sucesso();
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}	
	}

}
