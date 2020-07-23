/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe ContaCapitalLegadoException.
 */
@ApplicationException(rollback=true)
public class ContaCapitalLegadoException extends ContaCapitalIntegracaoLegadoException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 
	 * Chave da mensagem
	 */
	private static final String CHAVE = "MSG_001";

	/**
	 * Instancia um novo ContaCapitalLegadoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalLegadoException(Throwable excecao) {
		super(CHAVE, excecao);
	}		
	
	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalLegadoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}	

}
