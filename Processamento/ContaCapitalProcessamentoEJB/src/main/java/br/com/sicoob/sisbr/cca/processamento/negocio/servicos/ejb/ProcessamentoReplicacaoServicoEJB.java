package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.replicacao.negocio.excecao.ContaCapitalReplicacaoException;
import br.com.sicoob.cca.replicacao.negocio.servicos.interfaces.ControleReplicacaoServicoLocal;
import br.com.sicoob.sisbr.cca.processamento.negocio.excecao.ProcessamentoException;
import br.com.sicoob.sisbr.cca.processamento.negocio.servicos.interfaces.ProcessamentoReplicacaoServicoLocal;
import br.com.sicoob.sisbr.cca.processamento.negocio.servicos.interfaces.ProcessamentoReplicacaoServicoRemote;

/**
 * Servico ProcessamentoReplicacaoServicoEJB
 */
@Stateless
@Local(ProcessamentoReplicacaoServicoLocal.class)
@Remote(ProcessamentoReplicacaoServicoRemote.class)	
public class ProcessamentoReplicacaoServicoEJB extends ProcessamentoServicoEJB implements ProcessamentoReplicacaoServicoLocal,ProcessamentoReplicacaoServicoRemote{

	@EJB
	private ControleReplicacaoServicoLocal controleReplicacaoServicoLocal;
	
	/**
	 * Metodo que inicia a rotina de replicacao das tabelas do conta capital
	 * Consulta lista de replicacao
	 * Chama metodo de replicacao da tabela
	 * Chama metodo de atualizacao de status da tabela
	 * @author Marcos.Balbi
	 * @throws ProcessamentoException 
	 */
	public void iniciarReplicacao() throws ProcessamentoException {
		try {			
			controleReplicacaoServicoLocal.iniciarReplicacao();
		} catch (ContaCapitalReplicacaoException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ProcessamentoException(e.getMessage(),e);
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ProcessamentoException(e.getMessage(),e);			
		}
	}
	
}
