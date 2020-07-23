/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;

/**
* 
 * @author Balbi
 */
@ApplicationException(rollback=true)
public class ContaCapitalReplicacaoNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @see BancoobException
	 * @param mensagem
	 * @param parametros
	 */
	public ContaCapitalReplicacaoNegocioException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * @param chave
	 */
	public ContaCapitalReplicacaoNegocioException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ContaCapitalReplicacaoNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ContaCapitalReplicacaoNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ContaCapitalReplicacaoNegocioException(Throwable excecao) {
		super(excecao);
	}
}
