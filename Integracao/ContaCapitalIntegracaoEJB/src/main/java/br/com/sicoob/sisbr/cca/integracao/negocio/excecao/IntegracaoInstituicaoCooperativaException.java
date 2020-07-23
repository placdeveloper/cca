/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoInstituicaoCooperativaException.
 */
@ApplicationException(rollback=true)
public class IntegracaoInstituicaoCooperativaException extends ContaCapitalIntegracaoException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 
	 * Chave da mensagem
	 */
	private static final String CHAVE = "MSG_003";

	/**
	 * Construtor da exceção.
	 * @param excecao A exceção causadora do problema.
	 */
	public IntegracaoInstituicaoCooperativaException(Throwable excecao) {
		super(CHAVE, excecao);
	}		
	
}
