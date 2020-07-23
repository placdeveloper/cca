/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao ProcessamentoObjetoReplicacaoNaoEncontradoException
 */
public class ProcessamentoObjetoReplicacaoNaoEncontradoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ProcessamentoObjetoReplicacaoNaoEncontradoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ProcessamentoObjetoReplicacaoNaoEncontradoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ProcessamentoObjetoReplicacaoNaoEncontradoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ProcessamentoObjetoReplicacaoNaoEncontradoException(Throwable excecao) {
		super(excecao);
	}
}
