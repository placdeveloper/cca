/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema ContaCapitalComum.
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalComumFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static ContaCapitalComumFabricaDelegates fabrica = new ContaCapitalComumFabricaDelegates();

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static ContaCapitalComumFabricaDelegates getInstance() {
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	protected ContaCapitalComumFabricaDelegates() {
		
	}
	
	/**
	 * @return FechamentoContaCapitalDelegate
	 */
	public FechamentoContaCapitalDelegate criarFechamentoContaCapitalDelegate() {
		return new FechamentoContaCapitalDelegate();
	}
	
	/**
	 * @return PesquisaContaCapitalDelegate
	 */
	public PesquisaContaCapitalDelegate criarPesquisaContaCapitalDelegate() {
		return new PesquisaContaCapitalDelegate();
	}
	
	/**
	 * @return ViewInstituicaoDelegate
	 */
	public ViewInstituicaoDelegate criarViewInstituicaoDelegate() {
		return new ViewInstituicaoDelegate();
	}
	
}