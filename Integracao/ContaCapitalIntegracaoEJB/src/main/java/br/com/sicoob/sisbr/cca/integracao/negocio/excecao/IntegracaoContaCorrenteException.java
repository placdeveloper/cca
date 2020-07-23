/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoContaCorrenteException.
 */
@ApplicationException(rollback=true)
public class IntegracaoContaCorrenteException extends ContaCapitalIntegracaoException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 
	 * Chave da mensagem
	 */
	private static final String CHAVE = "MSG_018";

	/**
	 * Construtor da exceção.
	 * @param excecao A exceção causadora do problema.
	 */
	public IntegracaoContaCorrenteException(Throwable excecao) {
		super(CHAVE, excecao);
	}	
	
}
