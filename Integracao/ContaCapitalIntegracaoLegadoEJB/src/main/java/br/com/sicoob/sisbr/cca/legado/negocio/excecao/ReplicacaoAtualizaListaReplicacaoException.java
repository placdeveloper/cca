/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao ReplicacaoAtualizaListaReplicacaoException
 */
public class ReplicacaoAtualizaListaReplicacaoException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ReplicacaoAtualizaListaReplicacaoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ReplicacaoAtualizaListaReplicacaoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ReplicacaoAtualizaListaReplicacaoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ReplicacaoAtualizaListaReplicacaoException(Throwable excecao) {
		super(excecao);
	}	
	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 */
	public ReplicacaoAtualizaListaReplicacaoException(String chave, String mensagem) {
		super(chave, mensagem);
	}		

	/**
	 * 
	 * @param chave
	 * @param mensagem
	 * @param excecao
	 */
	public ReplicacaoAtualizaListaReplicacaoException(String chave, String mensagem, Throwable excecao) {
		super(chave, mensagem,excecao);
	}		
	
}
