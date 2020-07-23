/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema ContaCapitalIntegracao.
 * 
 */
public class ContaCapitalIntegracaoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ContaCapitalIntegracaoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ContaCapitalIntegracaoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalIntegracaoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ContaCapitalIntegracaoException(Throwable excecao) {
		super(excecao);
	}
	
	/**
	 * Instancia um novo ContaCapitalIntegracaoException.
	 *
	 * @param chave o valor de chave
	 * @param parametros o valor de parametros
	 */
	public ContaCapitalIntegracaoException(String chave, Object... parametros) {
		super(chave, parametros);
	}
	
}
