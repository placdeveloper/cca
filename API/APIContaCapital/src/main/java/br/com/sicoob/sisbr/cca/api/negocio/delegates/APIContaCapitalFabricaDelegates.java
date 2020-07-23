/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema APIContaCapital.
 * 
 */
public final class APIContaCapitalFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static APIContaCapitalFabricaDelegates fabrica = new APIContaCapitalFabricaDelegates();

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static APIContaCapitalFabricaDelegates getInstance() {
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	protected  APIContaCapitalFabricaDelegates() {
		
	}
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�os do Conta Capital
	 * @return
	 */
	public ContaCapitalDelegate criarContaCapitalDelegate() {
		return new ContaCapitalDelegate();
	}	

	/**
	 * Classe Delegate respons�vel por integralizar valores na conta capital
	 * @return
	 */	
	public IntegralizacaoCapitalDelegate criarIntegralizacaoCapitalDelegate() {
		return new IntegralizacaoCapitalDelegate();
	}	

	/**
	 * Classe Delegate respons�vel por cadastrar um associado
	 * @return
	 */
	public CadastroContaCapitalDelegate criarCadastroContaCapitalDelegate() {
		return new CadastroContaCapitalDelegate();
	}	
	
	/**
	 * Classe Delegate respons�vel por incluir debito indeterminado a uma conta capital
	 * @return
	 */
	public DebIndeterminadoContaCapitalDelegate criarDebIndeterminadoContaCapitalDelegate() {
		return new DebIndeterminadoContaCapitalDelegate();
	}	
}