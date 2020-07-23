package br.com.sicoob.cca.frontoffice.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * A Classe ContaCapitalFrontofficeNegocioException.
 */
@ApplicationException(rollback=true)
public class ContaCapitalFrontofficeNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo ContaCapitalFrontofficeNegocioException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 */
	public ContaCapitalFrontofficeNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * Instancia um novo ContaCapitalFrontofficeNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public ContaCapitalFrontofficeNegocioException(String chave) {
		super(chave);
	}

	/**
	 * Instancia um novo ContaCapitalFrontofficeNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param parametros o valor de parametros
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalFrontofficeNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * Instancia um novo ContaCapitalFrontofficeNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalFrontofficeNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * Instancia um novo ContaCapitalFrontofficeNegocioException.
	 *
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalFrontofficeNegocioException(Throwable excecao) {
		super(excecao);
	}
}
