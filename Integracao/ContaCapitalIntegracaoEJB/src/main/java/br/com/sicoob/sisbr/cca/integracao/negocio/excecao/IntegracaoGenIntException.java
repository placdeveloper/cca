/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoGenIntException.
 */
@ApplicationException(rollback=true)
public class IntegracaoGenIntException extends ContaCapitalIntegracaoException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 
	 * Chave da mensagem
	 */
	private static final String CHAVE = "MSG_010";

	/**
	 * Construtor da exceção.
	 * @param excecao A exceção causadora do problema.
	 */
	public IntegracaoGenIntException(Throwable excecao) {
		super(CHAVE, excecao);
	}	
	
}
