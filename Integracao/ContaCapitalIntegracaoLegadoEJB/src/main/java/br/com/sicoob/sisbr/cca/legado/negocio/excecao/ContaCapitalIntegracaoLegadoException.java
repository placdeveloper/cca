/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema ContaCapitalIntegracaoLegado.
 * 
 */
public class ContaCapitalIntegracaoLegadoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ContaCapitalIntegracaoLegadoException(String chave) {
		super(chave);
	}
	
	/**
	 * Instancia um novo ContaCapitalIntegracaoLegadoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalIntegracaoLegadoException(Throwable excecao) {
		super(excecao);
	}	

	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalIntegracaoLegadoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

}
