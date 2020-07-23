/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A Classe IntegracaoCorporativoNegocioException.
 */
@ApplicationException(rollback=true)
public class IntegracaoCorporativoNegocioException extends ContaCapitalIntegracaoNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instancia um novo IntegracaoCorporativoNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param e o valor de e
	 */
	public IntegracaoCorporativoNegocioException(String chave, Throwable e) {
		super(chave, e);
	}
	
	/**
	 * Instancia um novo IntegracaoCorporativoNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param idParametro o valor de id parametro
	 * @param numCooperativa o valor de num cooperativa
	 */
	public IntegracaoCorporativoNegocioException(String chave, String idParametro ,String numCooperativa) {
		super(chave, idParametro,numCooperativa);
	}	

	/**
	 * Instancia um novo IntegracaoCorporativoNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param idParametro o valor de id parametro
	 */
	public IntegracaoCorporativoNegocioException(String chave, String idParametro) {
		super(chave, idParametro);
	}	
	
}
