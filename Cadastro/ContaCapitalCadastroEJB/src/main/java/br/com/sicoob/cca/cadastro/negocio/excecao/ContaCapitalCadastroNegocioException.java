/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * A Classe ContaCapitalCadastroNegocioException.
 */
@ApplicationException(rollback=true)
public class ContaCapitalCadastroNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ContaCapitalCadastroNegocioException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ContaCapitalCadastroNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}
	
	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalCadastroNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
	
	/**
	 * Instancia um novo ContaCapitalCadastroNegocioException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 */
	public ContaCapitalCadastroNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}	

	/**
	 * @param excecao
	 */
	public ContaCapitalCadastroNegocioException(Throwable excecao) {
		super(excecao);
	}
	
}
