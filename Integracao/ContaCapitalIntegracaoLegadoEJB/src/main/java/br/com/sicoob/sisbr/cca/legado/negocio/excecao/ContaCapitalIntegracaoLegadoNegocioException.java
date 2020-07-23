/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;


/**
 * A Classe ContaCapitalIntegracaoLegadoNegocioException.
 */
@ApplicationException(rollback=true)
public class ContaCapitalIntegracaoLegadoNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ContaCapitalIntegracaoLegadoNegocioException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ContaCapitalIntegracaoLegadoNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}
	

	/**
	 * 
	 * @param chave
	 * @param parametros
	 */
	public ContaCapitalIntegracaoLegadoNegocioException(String chave, Object[] parametros) {
		super(chave, parametros);
	}	

	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalIntegracaoLegadoNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}	
	
	/**
	 * @param excecao
	 */
	public ContaCapitalIntegracaoLegadoNegocioException(Throwable excecao) {
		super(excecao);
	}
	
	/**
	 * 
	 * @param chave
	 * @param idContaCapitalLegado
	 */
	public ContaCapitalIntegracaoLegadoNegocioException(String chave, String mensagem) {
		super(chave, mensagem);
	}	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 * @param mensagem2
	 */
	public ContaCapitalIntegracaoLegadoNegocioException(String chave, String mensagem, String mensagem2) {
		super(chave, mensagem, mensagem2);
	}		
	
	
}
