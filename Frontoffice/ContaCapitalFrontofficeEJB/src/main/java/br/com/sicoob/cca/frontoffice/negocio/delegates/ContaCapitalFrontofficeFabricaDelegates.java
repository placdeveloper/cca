package br.com.sicoob.cca.frontoffice.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica para criacao de Delegates do modulo ContaCapitalFrontoffice.
 */
public final class ContaCapitalFrontofficeFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static ContaCapitalFrontofficeFabricaDelegates fabrica = new ContaCapitalFrontofficeFabricaDelegates();

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static ContaCapitalFrontofficeFabricaDelegates getInstance() {
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe.
	 */
	private ContaCapitalFrontofficeFabricaDelegates() {
	}
	
	/**
	 * Cria uma instância da IntegralizacaoCapitalDelegate.
	 * @return uma instância da IntegralizacaoCapitalDelegate.
	 */
	public IntegralizacaoCapitalDelegate criarIntegralizacaoCapitalDelegate(){
		return new IntegralizacaoCapitalDelegate();
	}
	
}