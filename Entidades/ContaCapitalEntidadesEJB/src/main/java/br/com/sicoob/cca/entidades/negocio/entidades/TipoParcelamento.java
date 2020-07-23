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
 * Tipo de Parcelamento
 * @author marco.nascimento
 * @since 15/05/2014
 */
@Entity
@Table(name = "TIPOPARCELAMENTO", schema = "cca")
public class TipoParcelamento extends ContaCapitalEntidade<Short> {

	
	/**
	 * Construtor Padrão
	 */
	public TipoParcelamento() {

	}
	
	/**
	 * Construtor com o ID
	 * @param id
	 */
	public TipoParcelamento(Short id) {
		this.id = id;
	}		
	
	/**
	 * Identificador do tipo de parcelamento
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTIPOPARCELAMENTO")
	private Short id;
	
	/**
	 * Descrição do tipo de parcelamento
	 */
	@Column(name = "DESCTIPOPARCELAMENTO", length = 200, nullable = false)
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