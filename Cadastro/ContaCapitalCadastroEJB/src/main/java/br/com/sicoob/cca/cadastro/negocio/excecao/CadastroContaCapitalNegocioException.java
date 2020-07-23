/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe CadastroContaCapitalNegocioException.
 */
@ApplicationException(rollback=true)
public class CadastroContaCapitalNegocioException extends ContaCapitalCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instancia um novo CadastroContaCapitalNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public CadastroContaCapitalNegocioException(String chave) {
		super(chave);
	}
	
	/**
	 * Instancia um novo CadastroContaCapitalNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param e o valor de e
	 */
	public CadastroContaCapitalNegocioException(String chave, Throwable e) {
		super(chave, e);
	}
	/**
	 * 
	 * @param chave
	 * @param idContaCapitalLegado
	 */
	public CadastroContaCapitalNegocioException(String chave, String mensagem) {
		super(chave, mensagem);
	}	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 * @param mensagem2
	 */
	public CadastroContaCapitalNegocioException(String chave, String mensagem, String mensagem2) {
		super(chave, mensagem, mensagem2);
	}	
	
}
