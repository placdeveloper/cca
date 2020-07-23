/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * A Classe DebitoIndeterminadoException.
 *
 * @author Balbi
 */
public class DebitoIndeterminadoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo ContaCapitalMovimentacaoException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 * @see BancoobException
	 */
	public DebitoIndeterminadoException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoException.
	 *
	 * @param chave o valor de chave
	 */
	public DebitoIndeterminadoException(String chave) {
		super(chave);
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoException.
	 *
	 * @param chave o valor de chave
	 * @param parametros o valor de parametros
	 * @param excecao o valor de excecao
	 */
	public DebitoIndeterminadoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoException.
	 *
	 * @param chave o valor de chave
	 * @param excecao o valor de excecao
	 */
	public DebitoIndeterminadoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public DebitoIndeterminadoException(Throwable excecao) {
		super(excecao);
	}
}
