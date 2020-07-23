/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * @author Marco.Nascimento
 */
@ApplicationException(rollback = true)
public class DebIndeterminadoContaCapitalNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public DebIndeterminadoContaCapitalNegocioException(String chave) {
		super(chave);
	}
	
	/**
	 * @param excecao
	 */
	public DebIndeterminadoContaCapitalNegocioException(Throwable excecao) {
		super(excecao);
	}	

	/**
	 * @param chave
	 * @param excecao
	 */
	public DebIndeterminadoContaCapitalNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
	
	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public DebIndeterminadoContaCapitalNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}
	
	/**
	 *  @param mensagem
	 * @param parametros
	 */
	public DebIndeterminadoContaCapitalNegocioException(String chave, Object... parametros) {
		super(chave, parametros);
	}
}