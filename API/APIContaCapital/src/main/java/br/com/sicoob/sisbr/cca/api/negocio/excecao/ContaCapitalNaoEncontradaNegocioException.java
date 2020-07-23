/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe ContaCapitalNaoEncontradaNegocioException.
 */
@ApplicationException(rollback=true)
public class ContaCapitalNaoEncontradaNegocioException extends APIContaCapitalNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param chave
	 * @param idClienteLegado
	 */
	public ContaCapitalNaoEncontradaNegocioException(String chave, String idClienteLegado,Throwable excecao) {
		super(chave, idClienteLegado,excecao);
	}	

	/**
	 * 
	 * @param chave
	 * @param idClienteLegado
	 */
	public ContaCapitalNaoEncontradaNegocioException(String chave, String idClienteLegado) {
		super(chave, idClienteLegado);
	}	
}
