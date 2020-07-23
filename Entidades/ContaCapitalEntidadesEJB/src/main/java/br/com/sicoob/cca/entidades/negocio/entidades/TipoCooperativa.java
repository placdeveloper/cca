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
 * Tipo de cooperativa
 * 1 - Crédito Mútuo(que exigem um vínculo entre os associados),
 * 2 - Crédito Rural 
 * 3 - Livre Admissão
 * @author marco.nascimento
 * @since 15/05/2014
 */
@Entity
@Table(name = "TIPOCOOPERATIVA", schema = "cca")
public class TipoCooperativa extends ContaCapitalEntidade<Short> {
	
	/**
	 * Identificador do tipo de cooperativa. 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTIPOCOOPERATIVA")
	private Short id;
	
	/**
	 * Descrição do tipo de cooperativa
	 */
	@Column(name = "DESCTIPOCOOPERATIVA", length = 100, nullable = false)
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