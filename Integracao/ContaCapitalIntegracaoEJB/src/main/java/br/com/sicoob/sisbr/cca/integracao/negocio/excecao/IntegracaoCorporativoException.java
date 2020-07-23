/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoCorporativoException.
 */
@ApplicationException(rollback=true)
public class IntegracaoCorporativoException extends
		ContaCapitalIntegracaoException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 
	 * Chave da mensagem
	 */
	private static final String CHAVE = "MSG_017";

	/**
	 * Construtor da exceção.
	 * @param excecao A exceção causadora do problema.
	 */
	public IntegracaoCorporativoException(Throwable excecao) {
		super(CHAVE, excecao);
	}	
		

}
