/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;


/**
 * A Classe APIContaCapitalNegocioException.
 */
@ApplicationException(rollback=true)
public class APIContaCapitalNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public APIContaCapitalNegocioException(String chave) {
		super(chave);
	}
	
	/**
	 * @param excecao
	 */
	public APIContaCapitalNegocioException(Throwable excecao) {
		super(excecao);
	}	

	/**
	 * @param chave
	 * @param excecao
	 */
	public APIContaCapitalNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
	
	
	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public APIContaCapitalNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}
	
	/**
	 *  @param mensagem
	 * @param parametros
	 */
	public APIContaCapitalNegocioException(String chave, Object... parametros) {
		super(chave, parametros);
	}



	
	
}
