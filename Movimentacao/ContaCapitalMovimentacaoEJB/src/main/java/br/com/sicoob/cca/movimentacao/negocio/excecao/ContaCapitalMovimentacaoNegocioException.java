/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

// TODO: Auto-generated Javadoc
/**
 * A Classe ContaCapitalMovimentacaoNegocioException.
 *
 * @author Balbi
 */
@ApplicationException(rollback=true)
public class ContaCapitalMovimentacaoNegocioException extends NegocioException {

	/** uid. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo ContaCapitalMovimentacaoNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public ContaCapitalMovimentacaoNegocioException(String chave) {
		super(chave);
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param parametros o valor de parametros
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalMovimentacaoNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}
	
	/**
	 * Instancia um novo ContaCapitalMovimentacaoNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalMovimentacaoNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
	
	/**
	 * Instancia um novo ContaCapitalMovimentacaoNegocioException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 * @see NegocioException
	 */
	public ContaCapitalMovimentacaoNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}	

	/**
	 * Instancia um novo ContaCapitalMovimentacaoNegocioException.
	 *
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalMovimentacaoNegocioException(Throwable excecao) {
		super(excecao);
	}
	
}
