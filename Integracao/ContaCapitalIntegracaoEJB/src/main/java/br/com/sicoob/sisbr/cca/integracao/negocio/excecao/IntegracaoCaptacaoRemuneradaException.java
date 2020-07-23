/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Excecao IntegracaoCaptacaoRemuneradaException
 */
@ApplicationException(rollback=true)
public class IntegracaoCaptacaoRemuneradaException extends ContaCapitalIntegracaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	 * Chave da mensagem
	 */
	private static final String CHAVE = "MSG_001";

	/**
	 * Construtor da exceção.
	 * @param excecao A exceção causadora do problema.
	 */
	public IntegracaoCaptacaoRemuneradaException(Throwable excecao) {
		super(CHAVE, excecao);
	}
	
}
