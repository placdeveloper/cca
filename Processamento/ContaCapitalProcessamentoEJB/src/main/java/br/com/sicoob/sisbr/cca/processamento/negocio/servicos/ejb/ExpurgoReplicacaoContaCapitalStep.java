package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.OperacaoContaCapitalServico;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoConsultaLegadoException;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ReplicacaoContaCapitalLegadoServicoLocal;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * @author Antonio.Genaro
 */
@Stateless
@Remote(StepServico.class)
public class ExpurgoReplicacaoContaCapitalStep extends ContaCapitalProcessamentoStep {

	private static final ISicoobLogger LOG = getLogger(ExpurgoReplicacaoContaCapitalStep.class);
	
	@EJB
	private ReplicacaoContaCapitalLegadoServicoLocal replicacaoContaCapitalLegadoServicoLocal;

	@EJB
	private OperacaoContaCapitalServico operacaoContaCapitalServico; 
	
	/**
	 * Executa Expurgo Replicacao Conta Capital
	 */
	public RetornoExecucao executar(ContextoExecucao contexto) {
		try {			
			replicacaoContaCapitalLegadoServicoLocal.expurgarReplicacaoSucesso();			
			
			if(!replicacaoContaCapitalLegadoServicoLocal.validarSuspensaoExpurgoOperacao()) {
				operacaoContaCapitalServico.expurgarOperacao(replicacaoContaCapitalLegadoServicoLocal.consultarDataExpurgoOperacao());				
			}
			
			return sucesso();
		}catch (ReplicacaoConsultaLegadoException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());	
		} catch (BancoobException e) {
			LOG.erro(e, e.getMessage());
			return erro(e.getMessage());
		}
	}
	
}
