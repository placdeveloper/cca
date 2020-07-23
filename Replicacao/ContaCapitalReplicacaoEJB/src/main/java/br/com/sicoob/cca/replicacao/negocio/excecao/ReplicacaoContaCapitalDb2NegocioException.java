/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * A excecao ReplicacaoContaCapitalDb2NegocioException
 */
@ApplicationException(rollback=true)
public class ReplicacaoContaCapitalDb2NegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ReplicacaoContaCapitalDb2NegocioException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ReplicacaoContaCapitalDb2NegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ReplicacaoContaCapitalDb2NegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ReplicacaoContaCapitalDb2NegocioException(Throwable excecao) {
		super(excecao);
	}
	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 */
	public ReplicacaoContaCapitalDb2NegocioException(String chave, String mensagem) {
		super(chave, mensagem);
	}
	
	/**
	 * @param chave
	 * @param mensagem
	 * @param excecao
	 */
	public ReplicacaoContaCapitalDb2NegocioException(String chave, String mensagem, Throwable excecao) {
		super(chave, mensagem, excecao);
	}	
}
