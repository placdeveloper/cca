/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao ProcessamentoObjetoReplicacaoInvalidoException
 */
public class ProcessamentoObjetoReplicacaoInvalidoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ProcessamentoObjetoReplicacaoInvalidoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ProcessamentoObjetoReplicacaoInvalidoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ProcessamentoObjetoReplicacaoInvalidoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ProcessamentoObjetoReplicacaoInvalidoException(Throwable excecao) {
		super(excecao);
	}

}
