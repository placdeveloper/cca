/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoContaCorrenteNegocioException.
 */
@ApplicationException(rollback=true)
public class IntegracaoContaCorrenteNegocioException extends ContaCapitalIntegracaoNegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo IntegracaoContaCorrenteNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public IntegracaoContaCorrenteNegocioException(String chave) {
		super(chave);
	}
	

	/**
	 * Instancia um novo IntegracaoContaCorrenteNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param excecao o valor de excecao
	 */
	public IntegracaoContaCorrenteNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
	
	/**
	 * Instancia um novo IntegracaoContaCorrenteNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param mensagem o valor de mensagem
	 */
	public IntegracaoContaCorrenteNegocioException(String chave, String mensagem) {
		super(chave, mensagem);
	}		
	
	
}



