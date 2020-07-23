/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * Sinaliza impedimentos ao cadastrar debito indeterminado 
 *
 * @author marco.nascimento
 */
@ApplicationException(rollback=true)
public class DebitoIndeterminadoNegocioException extends NegocioException {

	/** uid. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo ContaCapitalImpedimentosNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public DebitoIndeterminadoNegocioException(String chave) {
		super(chave);
	}

	/**
	 * Instancia um novo ContaCapitalImpedimentosNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param parametros o valor de parametros
	 * @param excecao o valor de excecao
	 */
	public DebitoIndeterminadoNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}
	
	/**
	 * Instancia um novo ContaCapitalImpedimentosNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param excecao o valor de excecao
	 */
	public DebitoIndeterminadoNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
	
	/**
	 * Instancia um novo ContaCapitalImpedimentosNegocioException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 * @see NegocioException
	 */
	public DebitoIndeterminadoNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}	

	/**
	 * Instancia um novo ContaCapitalImpedimentosNegocioException.
	 *
	 * @param excecao o valor de excecao
	 */
	public DebitoIndeterminadoNegocioException(Throwable excecao) {
		super(excecao);
	}
}