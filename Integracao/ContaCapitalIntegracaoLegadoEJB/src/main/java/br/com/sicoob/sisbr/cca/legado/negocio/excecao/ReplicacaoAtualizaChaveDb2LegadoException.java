/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao ReplicacaoAtualizaChaveDb2LegadoException
 */
public class ReplicacaoAtualizaChaveDb2LegadoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ReplicacaoAtualizaChaveDb2LegadoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ReplicacaoAtualizaChaveDb2LegadoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ReplicacaoAtualizaChaveDb2LegadoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ReplicacaoAtualizaChaveDb2LegadoException(Throwable excecao) {
		super(excecao);
	}	
	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 */
	public ReplicacaoAtualizaChaveDb2LegadoException(String chave, String mensagem) {
		super(chave, mensagem);
	}	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 * @param excecao
	 */
	public ReplicacaoAtualizaChaveDb2LegadoException(String chave, String mensagem,Throwable excecao) {
		super(chave, mensagem,excecao);
	}		
	
}
