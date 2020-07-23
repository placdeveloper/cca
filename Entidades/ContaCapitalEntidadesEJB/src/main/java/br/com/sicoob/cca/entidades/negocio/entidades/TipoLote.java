/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Tipo de Lote
 * @author marco.nascimento
 * @since 15/05/2014
 */
@Entity
@Table(name = "TIPOLOTE", schema = "cca")
public class TipoLote extends ContaCapitalEntidade<Short> {

	/**
	 * Construtor Padrao
	 */
	public TipoLote() {

	}
	
	/**
	 * Construtor com o ID
	 * @param id
	 */
	public TipoLote(Short id) {
		this.id = id;
	}	

	/**
	 * Identificador do tipo de lote.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTIPOLOTE")
	private Short id;
	
	/**
	 * Descrição do tipo de lote.
	 */
	@Column(name = "DESCTIPOLOTE", length = 200, nullable = false)
	private String descricao;

	/**
	 * @return the id
	 */
	public Short getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Short id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}