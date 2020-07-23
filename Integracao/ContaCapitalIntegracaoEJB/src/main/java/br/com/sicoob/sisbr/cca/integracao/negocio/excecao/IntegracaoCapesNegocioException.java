/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoCapesNegocioException.
 */
@ApplicationException(rollback=true)
public class IntegracaoCapesNegocioException extends ContaCapitalIntegracaoNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instancia um novo IntegracaoCapesNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param e o valor de e
	 */
	public IntegracaoCapesNegocioException(String chave, Throwable e) {
		super(chave, e);
	}
		
	
}
