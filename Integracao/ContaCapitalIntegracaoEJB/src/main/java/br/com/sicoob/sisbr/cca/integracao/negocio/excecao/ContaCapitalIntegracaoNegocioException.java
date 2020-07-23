/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import br.com.bancoob.negocio.excecao.NegocioException;


/**
 * A Classe ContaCapitalIntegracaoNegocioException.
 */
public class ContaCapitalIntegracaoNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ContaCapitalIntegracaoNegocioException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ContaCapitalIntegracaoNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}
	
	/**
	 * 
	 * @param chave
	 * @param parametros
	 */
	public ContaCapitalIntegracaoNegocioException(String chave, Object... parametros) {
		super(chave, parametros);
	}
	
	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalIntegracaoNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ContaCapitalIntegracaoNegocioException(Throwable excecao) {
		super(excecao);
	}
}
