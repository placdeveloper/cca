/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoDocumentoNegocioException.
 */
@ApplicationException(rollback=true)
public class IntegracaoDocumentoNegocioException extends ContaCapitalIntegracaoNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo IntegracaoDocumentoNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public IntegracaoDocumentoNegocioException(String chave) {
		super(chave);
	}		
	
}
