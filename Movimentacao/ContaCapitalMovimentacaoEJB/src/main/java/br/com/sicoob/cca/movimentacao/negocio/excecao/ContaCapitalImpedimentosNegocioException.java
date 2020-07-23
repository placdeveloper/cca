/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

// TODO: Auto-generated Javadoc
/**
 * Sinaliza impedimentos ao desligar conta capital.
 *
 * @author marco.nascimento
 */
@ApplicationException(rollback=true)
public class ContaCapitalImpedimentosNegocioException extends NegocioException {

	/** uid. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo ContaCapitalImpedimentosNegocioException.
	 *
	 * @param chave o valor de chave
	 */
	public ContaCapitalImpedimentosNegocioException(String chave) {
		super(chave);
	}

	/**
	 * Instancia um novo ContaCapitalImpedimentosNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param parametros o valor de parametros
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalImpedimentosNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}
	
	/**
	 * Instancia um novo ContaCapitalImpedimentosNegocioException.
	 *
	 * @param chave o valor de chave
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalImpedimentosNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}
	
	/**
	 * Instancia um novo ContaCapitalImpedimentosNegocioException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 * @see NegocioException
	 */
	public ContaCapitalImpedimentosNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}	

	/**
	 * Instancia um novo ContaCapitalImpedimentosNegocioException.
	 *
	 * @param excecao o valor de excecao
	 */
	public ContaCapitalImpedimentosNegocioException(Throwable excecao) {
		super(excecao);
	}
}