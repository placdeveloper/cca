/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.negocio.excecao;

import br.com.bancoob.excecao.BancoobException;

/**
 * Exception de prevencao de lavagem de dinheiro (PLD)
 * @author Marco.Nascimento
 */
public class ProcessamentoPLDContaCapitalException extends BancoobException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	public ProcessamentoPLDContaCapitalException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	public ProcessamentoPLDContaCapitalException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	public ProcessamentoPLDContaCapitalException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	public ProcessamentoPLDContaCapitalException(Throwable excecao) {
		super(excecao);
	}
}