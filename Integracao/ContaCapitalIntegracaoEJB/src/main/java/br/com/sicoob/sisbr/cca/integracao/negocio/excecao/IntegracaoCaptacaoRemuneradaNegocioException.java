/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Excecao IntegracaoCaptacaoRemuneradaNegocioException
 */
@ApplicationException(rollback=true)
public class IntegracaoCaptacaoRemuneradaNegocioException extends ContaCapitalIntegracaoNegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor
	 * @param chave
	 * @param e
	 */
	public IntegracaoCaptacaoRemuneradaNegocioException(String chave, Throwable e) {
		super(chave, e);
	}	
	
}
