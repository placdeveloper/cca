/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao ReplicacaoContaCapitalDb2Exception
 */
public class ReplicacaoContaCapitalDb2Exception extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ReplicacaoContaCapitalDb2Exception(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ReplicacaoContaCapitalDb2Exception(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ReplicacaoContaCapitalDb2Exception(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ReplicacaoContaCapitalDb2Exception(Throwable excecao) {
		super(excecao);
	}
	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 */
	public ReplicacaoContaCapitalDb2Exception(String chave, String mensagem) {
		super(chave, mensagem);
	}
	
	/**
	 * @param chave
	 * @param mensagem
	 * @param excecao
	 */
	public ReplicacaoContaCapitalDb2Exception(String chave, String mensagem, Throwable excecao) {
		super(chave, mensagem, excecao);
	}	
}
