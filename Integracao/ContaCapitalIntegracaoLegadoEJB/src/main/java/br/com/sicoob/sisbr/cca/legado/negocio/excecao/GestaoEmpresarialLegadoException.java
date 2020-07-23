/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao GestaoEmpresarialLegadoException
 */
public class GestaoEmpresarialLegadoException extends
		BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public GestaoEmpresarialLegadoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public GestaoEmpresarialLegadoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public GestaoEmpresarialLegadoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public GestaoEmpresarialLegadoException(Throwable excecao) {
		super(excecao);
	}	
	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 * @param excecao
	 */
	public GestaoEmpresarialLegadoException(String chave, String mensagem, Throwable excecao) {
		super(chave,mensagem, excecao);
	}	
		
	
	
}
