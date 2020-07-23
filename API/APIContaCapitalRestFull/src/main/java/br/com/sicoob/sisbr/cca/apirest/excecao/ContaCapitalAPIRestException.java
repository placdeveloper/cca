/*
 * 
 */
package br.com.sicoob.sisbr.cca.apirest.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;


/**
 * A Classe APIRestContaCapitalNegocioException.
 */
@ApplicationException(rollback=true)
public class ContaCapitalAPIRestException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ContaCapitalAPIRestException(String chave) {
		super(chave);
	}
	
	/**
	 * @param excecao
	 */
	public ContaCapitalAPIRestException(Throwable excecao) {
		super(excecao);
	}	

	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalAPIRestException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
	
	
	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ContaCapitalAPIRestException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}
	
	/**
	 *  @param mensagem
	 * @param parametros
	 */
	public ContaCapitalAPIRestException(String chave, Object... parametros) {
		super(chave, parametros);
	}



	
	
}
