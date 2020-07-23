/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoGftNegocioException.
 */
@ApplicationException(rollback=true)
public class IntegracaoGftNegocioException extends ContaCapitalIntegracaoNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo IntegracaoGftNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public IntegracaoGftNegocioException(String chave) {
		super(chave);
	}	
}
