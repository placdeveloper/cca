/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoCapesException.
 */
@ApplicationException(rollback=true)
public class IntegracaoCapesException extends ContaCapitalIntegracaoException {

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
	public IntegracaoCapesException(Throwable excecao) {
		super(CHAVE, excecao);
	}	
	
	
}
