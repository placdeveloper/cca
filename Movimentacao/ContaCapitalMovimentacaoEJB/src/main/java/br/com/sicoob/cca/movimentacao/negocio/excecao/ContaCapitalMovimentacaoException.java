/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

// TODO: Auto-generated Javadoc
/**
 * A Classe ContaCapitalMovimentacaoException.
 *
 * @author Balbi
 */
public class ContaCapitalMovimentacaoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo ContaCapitalMovimentacaoException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 * @see BancoobException
	 */
	public ContaCapitalMovimentacaoException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoException.
	 *
	 * @param chave o valor de chave
	 */
	public ContaCapitalMovimentacaoException(String chave) {
		super(chave);
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoException.
	 *
	 * @param chave o valor de chave
	 * @param parametros o valor de parametros
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalMovimentacaoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoException.
	 *
	 * @param chave o valor de chave
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalMovimentacaoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalMovimentacaoException(Throwable excecao) {
		super(excecao);
	}
}
