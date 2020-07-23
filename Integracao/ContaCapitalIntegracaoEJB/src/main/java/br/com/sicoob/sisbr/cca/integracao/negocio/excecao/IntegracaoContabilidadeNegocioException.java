/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoContabilidadeNegocioException.
 */
@ApplicationException(rollback=true)
public class IntegracaoContabilidadeNegocioException extends ContaCapitalIntegracaoNegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9018424388431897119L;

	/**
	 * Instancia um novo IntegracaoContabilidadeNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public IntegracaoContabilidadeNegocioException(String chave) {
		super(chave);
	}
	

	/**
	 * Instancia um novo IntegracaoContabilidadeNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param excecao o valor de excecao
	 */
	public IntegracaoContabilidadeNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
	
	/**
	 * Instancia um novo IntegracaoContabilidadeNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param mensagem o valor de mensagem
	 */
	public IntegracaoContabilidadeNegocioException(String chave, String mensagem) {
		super(chave, mensagem);
	}		
}
