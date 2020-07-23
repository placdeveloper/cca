/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao ProcessamentoObjetoReplicacaoNaoEncontradoException
 */
public class ReplicacaoObjetoReplicacaoNaoEncontradoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ReplicacaoObjetoReplicacaoNaoEncontradoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ReplicacaoObjetoReplicacaoNaoEncontradoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ReplicacaoObjetoReplicacaoNaoEncontradoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ReplicacaoObjetoReplicacaoNaoEncontradoException(Throwable excecao) {
		super(excecao);
	}
}
