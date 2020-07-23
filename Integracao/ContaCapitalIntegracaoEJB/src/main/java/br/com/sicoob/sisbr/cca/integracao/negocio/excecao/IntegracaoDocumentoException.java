/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoDocumentoException.
 */
@ApplicationException(rollback=true)
public class IntegracaoDocumentoException extends ContaCapitalIntegracaoException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 
	 * Chave da mensagem
	 */
	private static final String CHAVE = "MSG_002";

	/**
	 * Construtor da exceção.
	 * @param excecao A exceção causadora do problema.
	 */
	public IntegracaoDocumentoException(Throwable excecao) {
		super(CHAVE, excecao);
	}		
	
}
