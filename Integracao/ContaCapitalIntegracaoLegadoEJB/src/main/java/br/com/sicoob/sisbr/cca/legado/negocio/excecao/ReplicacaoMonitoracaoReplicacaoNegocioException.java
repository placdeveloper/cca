/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.excecao;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * Excecao ReplicacaoMonitoracaoReplicacaoNegocioException
 */
public class ReplicacaoMonitoracaoReplicacaoNegocioException extends NegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ReplicacaoMonitoracaoReplicacaoNegocioException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ReplicacaoMonitoracaoReplicacaoNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ReplicacaoMonitoracaoReplicacaoNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ReplicacaoMonitoracaoReplicacaoNegocioException(Throwable excecao) {
		super(excecao);
	}	
	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 */
	public ReplicacaoMonitoracaoReplicacaoNegocioException(String chave, String mensagem) {
		super(chave, mensagem);
	}		
	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 * @param excecao
	 */
	public ReplicacaoMonitoracaoReplicacaoNegocioException(String chave, String mensagem, Throwable excecao) {
		super(chave, mensagem, excecao);
	}	
	
}
