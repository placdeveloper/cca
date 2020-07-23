/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * 
 * @author Balbi
 */
public final class ContaCapitalReplicacaoFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static ContaCapitalReplicacaoFabricaDelegates fabrica = new ContaCapitalReplicacaoFabricaDelegates();

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static ContaCapitalReplicacaoFabricaDelegates getInstance() {
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	protected ContaCapitalReplicacaoFabricaDelegates() {
		
	}
	
	/**
	 * Cria ReplicacaoContaCapitalDelegate
	 * @return
	 */
	public ReplicacaoContaCapitalDelegate criarReplicacaoContaCapitalDelegate() {
		return new ReplicacaoContaCapitalDelegate();
	}

	/**
	 * Cria EmailReplicacaoDelegate
	 * @return
	 */
	public EmailReplicacaoDelegate criarEmailReplicacaoDelegate() {
		return new EmailReplicacaoDelegate();
	}
	
	/**
	 * Cria ControleReplicacaoDelegate
	 * @return
	 */
	public ControleReplicacaoDelegate criarControleReplicacaoDelegate() {
		return new ControleReplicacaoDelegate();
	}
	
}