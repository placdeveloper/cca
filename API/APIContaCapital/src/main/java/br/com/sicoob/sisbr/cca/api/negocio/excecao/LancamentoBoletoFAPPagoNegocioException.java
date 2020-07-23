/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * Excecao de negocio para integralizacao de capital via boleto quando ja existe lancamento para a mesma operacao.
 */
@ApplicationException(rollback=true)
public class LancamentoBoletoFAPPagoNegocioException extends NegocioException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public LancamentoBoletoFAPPagoNegocioException(String chave) {
		super(chave);
	}

	/**
	 * @param excecao
	 */
	public LancamentoBoletoFAPPagoNegocioException(Throwable excecao) {
		super(excecao);
	}	

	/**
	 * @param chave
	 * @param excecao
	 */
	public LancamentoBoletoFAPPagoNegocioException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public LancamentoBoletoFAPPagoNegocioException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 *  @param mensagem
	 * @param parametros
	 */
	public LancamentoBoletoFAPPagoNegocioException(String chave, Object... parametros) {
		super(chave, parametros);
	}

}
