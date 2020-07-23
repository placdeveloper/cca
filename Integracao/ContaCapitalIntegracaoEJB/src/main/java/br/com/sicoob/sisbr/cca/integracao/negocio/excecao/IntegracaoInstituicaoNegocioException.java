/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoInstituicaoNegocioException.
 */
@ApplicationException(rollback=true)
public class IntegracaoInstituicaoNegocioException extends ContaCapitalIntegracaoNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo IntegracaoInstituicaoNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public IntegracaoInstituicaoNegocioException(String chave) {
		super(chave);
	}		
	
}
