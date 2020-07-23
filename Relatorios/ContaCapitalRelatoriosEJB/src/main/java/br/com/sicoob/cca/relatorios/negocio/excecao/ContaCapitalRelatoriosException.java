/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema ContaCapitalRelatorios.
 * 
 * @author Stefanini IT Solutions
 */
public class ContaCapitalRelatoriosException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @see BancoobException
	 * @param mensagem
	 * @param parametros
	 */
	public ContaCapitalRelatoriosException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * @param chave
	 */
	public ContaCapitalRelatoriosException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ContaCapitalRelatoriosException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalRelatoriosException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ContaCapitalRelatoriosException(Throwable excecao) {
		super(excecao);
	}
}
