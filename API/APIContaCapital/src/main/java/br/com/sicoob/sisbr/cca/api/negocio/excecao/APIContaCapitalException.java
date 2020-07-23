/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema APIContaCapital.
 * 
 */
public class APIContaCapitalException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public APIContaCapitalException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public APIContaCapitalException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public APIContaCapitalException(Throwable excecao) {
		super(excecao);
	}	
}
