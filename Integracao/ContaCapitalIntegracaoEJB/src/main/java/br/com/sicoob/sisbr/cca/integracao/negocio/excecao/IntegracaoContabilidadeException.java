/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoContabilidadeException.
 */
@ApplicationException(rollback=true)
public class IntegracaoContabilidadeException extends ContaCapitalIntegracaoException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 
	 * Chave da mensagem
	 */
	private static final String CHAVE = "MSG_009";

	
	/**
	 * Instancia um novo IntegracaoContabilidadeException.
	 */
	public IntegracaoContabilidadeException() {
		super(CHAVE);
	}
	
	
	/**
	 * Instancia um novo IntegracaoContabilidadeException.
	 *
	 * @param excecao o valor de excecao
	 */
	public IntegracaoContabilidadeException(Throwable excecao) {
		super(CHAVE, excecao);
	}
	
	
	
	/**
	 * Instancia um novo IntegracaoContabilidadeException.
	 *
	 * @param parametros o valor de parametros
	 */
	public IntegracaoContabilidadeException(Object... parametros) {
		super(CHAVE, parametros);
	}

}
