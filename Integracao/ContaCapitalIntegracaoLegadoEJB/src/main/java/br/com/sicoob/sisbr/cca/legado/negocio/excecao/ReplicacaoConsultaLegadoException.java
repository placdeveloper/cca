/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao ReplicacaoConsultaLegadoException
 */
public class ReplicacaoConsultaLegadoException extends
		BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ReplicacaoConsultaLegadoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ReplicacaoConsultaLegadoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ReplicacaoConsultaLegadoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ReplicacaoConsultaLegadoException(Throwable excecao) {
		super(excecao);
	}	
	
	/**
	 * 
	 * @param chave
	 * @param mensagem
	 * @param excecao
	 */
	public ReplicacaoConsultaLegadoException(String chave, String mensagem, Throwable excecao) {
		super(chave,mensagem, excecao);
	}	
		
	
	
}
