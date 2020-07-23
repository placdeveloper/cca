/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao ProcessamentoObjetoReplicacaoInvalidoException
 */
public class ReplicacaoObjetoReplicacaoInvalidoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ReplicacaoObjetoReplicacaoInvalidoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ReplicacaoObjetoReplicacaoInvalidoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ReplicacaoObjetoReplicacaoInvalidoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ReplicacaoObjetoReplicacaoInvalidoException(Throwable excecao) {
		super(excecao);
	}

}
