/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tipo de Subscricao
 * @author antonio.genaro
 * @since 14/04/2016
 */
@Entity
@Table(name = "TIPOSUBSCRICAO", schema = "cca")
public class TipoSubscricao extends ContaCapitalEntidade<Short> {

	/**
	 * Construtor Padrao	
	 */
	public TipoSubscricao() {

	}
	
	/**
	 * Construtor com ID
	 * @param id
	 */
	public TipoSubscricao(Short id) {
		this.id = id;
	}
	
	/**
	 * Identificador do tipo de Subscricao
	 */
	@Id
	@Column(name = "IDTIPOSUBSCRICAO")
	private Short id;
	
	/**
	 * Descrição do tipo de Subscricao
	 */
	@Column(name = "DESCTIPOSUBSCRICAO", length = 40, nullable = false)
	private String descTipoSubscricao;

	/**
	 * {@inheritDoc}
	 */
	public Short getId() {
		return id;
	}

	/**
	 * Define o valor de id.
	 *
	 * @param id o novo valor de id
	 */
	public void setId(Short id) {
		this.id = id;
	}

	/**
	 * Recupera o valor de descTipoSubscricao.
	 *
	 * @return o valor de descTipoSubscricao
	 */
	public String getDescTipoSubscricao() {
		return descTipoSubscricao;
	}

	/**
	 * Define o valor de descTipoSubscricao.
	 *
	 * @param descTipoSubscricao o novo valor de descTipoSubscricao
	 */
	public void setDescTipoSubscricao(String descTipoSubscricao) {
		this.descTipoSubscricao = descTipoSubscricao;
	}	
	
}
