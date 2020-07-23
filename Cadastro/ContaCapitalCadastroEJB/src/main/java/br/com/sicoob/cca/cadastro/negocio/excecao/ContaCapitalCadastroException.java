/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Excecao padrao para o sistema ContaCapitalCadastro.
 * 
 * @author Stefanini IT Solutions
 */
public class ContaCapitalCadastroException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo ContaCapitalCadastroException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 */
	public ContaCapitalCadastroException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * @param chave
	 */
	public ContaCapitalCadastroException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ContaCapitalCadastroException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalCadastroException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ContaCapitalCadastroException(Throwable excecao) {
		super(excecao);
	}
}
