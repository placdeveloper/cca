/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
* 
 * @author Balbi
 */
public class ContaCapitalReplicacaoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @see BancoobException
	 * @param mensagem
	 * @param parametros
	 */
	public ContaCapitalReplicacaoException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * @param chave
	 */
	public ContaCapitalReplicacaoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ContaCapitalReplicacaoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalReplicacaoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ContaCapitalReplicacaoException(Throwable excecao) {
		super(excecao);
	}
}
