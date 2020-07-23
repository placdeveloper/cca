/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * @author gesin1
 */
@ApplicationException(rollback=true)
public class ContaCapitalRelatoriosNegocioException extends NegocioException {

	/**
	 * uid
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ContaCapitalRelatoriosNegocioException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ContaCapitalRelatoriosNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}
	
	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalRelatoriosNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
	
	/**
	 * @see NegocioException
	 * @param mensagem
	 * @param parametros
	 */
	public ContaCapitalRelatoriosNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}	

	/**
	 * @param excecao
	 */
	public ContaCapitalRelatoriosNegocioException(Throwable excecao) {
		super(excecao);
	}
	
}
