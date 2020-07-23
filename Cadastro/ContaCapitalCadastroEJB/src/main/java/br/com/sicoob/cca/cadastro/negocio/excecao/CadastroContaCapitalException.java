/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.excecao;

/**
 * A Classe CadastroContaCapitalException.
 */
public class CadastroContaCapitalException extends ContaCapitalCadastroException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2642724031953925405L;

	/**
	 * Instancia um novo CadastroContaCapitalException.
	 *
	 * @param chave o valor de chave
	 */
	public CadastroContaCapitalException(String chave) {
		super(chave);
	}
	
	/**
	 * Instancia um novo CadastroContaCapitalException.
	 *
	 * @param excecao o valor de excecao
	 */
	public CadastroContaCapitalException(Throwable excecao) {
		super(excecao);
	}	

	/**
	 * Instancia um novo CadastroContaCapitalException.
	 *
	 * @param chave o valor de chave
	 * @param excecao o valor de excecao
	 */
	public CadastroContaCapitalException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
}