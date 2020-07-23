/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.cca.replicacao.negocio.servicos.ControleReplicacaoServico;
import br.com.sicoob.cca.replicacao.negocio.servicos.EmailReplicacaoServico;
import br.com.sicoob.cca.replicacao.negocio.servicos.ReplicacaoContaCapitalServico;

/**
 * Service Locator usado pelo sistema
 * 
 * @author Balbi
 */
public final class ContaCapitalReplicacaoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static ContaCapitalReplicacaoServiceLocator locator = new ContaCapitalReplicacaoServiceLocator();
	
	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static ContaCapitalReplicacaoServiceLocator getInstance() {
		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private ContaCapitalReplicacaoServiceLocator() {
		super("cca_replicacao");
	}
		
	/**
	 * Recupera ReplicacaoContaCapitalServico
	 * @return
	 */
	public ReplicacaoContaCapitalServico localizarReplicacaoContaCapitalServico() {
		return (ReplicacaoContaCapitalServico) localizar("locator.cca_replicacao.ReplicacaoContaCapitalServico");
	}
	
	/**
	 * Recupera EmailReplicacaoServico
	 * @return
	 */
	public EmailReplicacaoServico localizarEmailReplicacaoServico() {
		return (EmailReplicacaoServico) localizar("locator.cca_replicacao.EmailReplicacaoServico");
	}

	/**
	 * Recupera ControleReplicacaoServico
	 * @return
	 */
	public ControleReplicacaoServico localizarControleReplicacaoServico() {
		return (ControleReplicacaoServico) localizar("locator.cca_replicacao.ControleReplicacaoServico");
	}	
	
}
