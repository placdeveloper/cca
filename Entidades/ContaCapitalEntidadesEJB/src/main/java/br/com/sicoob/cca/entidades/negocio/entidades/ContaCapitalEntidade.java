/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import java.io.Serializable;

import br.com.bancoob.negocio.entidades.BancoobEntidade;

/**
 * @author marco.nascimento
 * @see BancoobEntidade, Serializable
 * @since 15/05/2014
 */
public abstract class ContaCapitalEntidade<ID extends Serializable> extends BancoobEntidade {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** O atributo id. */
	protected ID id;
	
	/**
	 * Recupera o valor de id.
	 *
	 * @return o valor de id
	 */
	public abstract ID getId();
	
}