/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe ContaCapitalCadastradaNegocioException.
 */
@ApplicationException(rollback=true)
public class ContaCapitalCadastradaNegocioException extends ContaCapitalCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instancia um novo ContaCapitalCadastradaNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public ContaCapitalCadastradaNegocioException(String chave) {
		super(chave);
	}
	
	/**
	 * Instancia um novo ContaCapitalCadastradaNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param e o valor de e
	 */
	public ContaCapitalCadastradaNegocioException(String chave, Throwable e) {
		super(chave, e);
	}
	/**
	 * 
	 * @param chave
	 * @param idContaCapitalLegado
	 */
	public ContaCapitalCadastradaNegocioException(String chave, String mensagem) {
		super(chave, mensagem);
	}	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 * @param mensagem2
	 */
	public ContaCapitalCadastradaNegocioException(String chave, String mensagem, String mensagem2) {
		super(chave, mensagem, mensagem2);
	}	
	
	/**
	 * 
	 * @param mensagem
	 * @param parametros
	 */
	public ContaCapitalCadastradaNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}
}