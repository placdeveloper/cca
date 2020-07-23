/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * 
 * @author Marco.Nascimento
 */
@ApplicationException(rollback=true)
public class CadastroContaCapitalTransacaoException extends ContaCapitalCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instancia um novo CadastroContaCapitalTransacaoException.
	 *
	 * @param chave o valor de chave
	 */
	public CadastroContaCapitalTransacaoException(String chave) {
		super(chave);
	}
	
	/**
	 * Instancia um novo CadastroContaCapitalTransacaoException.
	 *
	 * @param chave o valor de chave
	 * @param e o valor de e
	 */
	public CadastroContaCapitalTransacaoException(String chave, Throwable e) {
		super(chave, e);
	}
	/**
	 * 
	 * @param chave
	 * @param idContaCapitalLegado
	 */
	public CadastroContaCapitalTransacaoException(String chave, String mensagem) {
		super(chave, mensagem);
	}	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 * @param mensagem2
	 */
	public CadastroContaCapitalTransacaoException(String chave, String mensagem, String mensagem2) {
		super(chave, mensagem, mensagem2);
	}	
	
}
