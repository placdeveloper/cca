/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe ContaCapitalLegadoNegocioException.
 */
@ApplicationException(rollback=true)
public class ContaCapitalLegadoNegocioException extends ContaCapitalIntegracaoLegadoNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo ContaCapitalLegadoNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public ContaCapitalLegadoNegocioException(String chave) {
		super(chave);
	}
	
	/**
	 * @param excecao
	 */
	public ContaCapitalLegadoNegocioException(Throwable excecao) {
		super(excecao);
	}

}
