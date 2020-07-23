/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoGenIntNegocioException.
 */
@ApplicationException(rollback=true)
public class IntegracaoGenIntNegocioException extends ContaCapitalIntegracaoNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo IntegracaoGenIntNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public IntegracaoGenIntNegocioException(String chave) {
		super(chave);
	}	
}
