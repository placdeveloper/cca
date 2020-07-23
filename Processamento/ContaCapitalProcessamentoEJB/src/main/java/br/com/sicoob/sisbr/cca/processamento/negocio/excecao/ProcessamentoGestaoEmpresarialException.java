/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema Processamento.
 * 
 */
public class ProcessamentoGestaoEmpresarialException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ProcessamentoGestaoEmpresarialException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ProcessamentoGestaoEmpresarialException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ProcessamentoGestaoEmpresarialException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ProcessamentoGestaoEmpresarialException(Throwable excecao) {
		super(excecao);
	}
}