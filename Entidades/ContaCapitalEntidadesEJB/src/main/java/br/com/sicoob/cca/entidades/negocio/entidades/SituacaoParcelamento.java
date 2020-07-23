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
 * Situação do parcelamento
 * @author marco.nascimento
 * @since 15/05/2014
 */
@Entity
@Table(name = "SITUACAOPARCELAMENTO", schema = "cca")
public class SituacaoParcelamento extends ContaCapitalEntidade<Short> {

	/**
	 * Construtor Padrão
	 */
	public SituacaoParcelamento() {

	}
	
	/**
	 * Construtor com o ID
	 * @param id
	 */
	public SituacaoParcelamento(Short id) {
		this.id = id;
	}		
	
	/**
	 * Identificador da situação do parcelamento.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDSITUACAOPARCELAMENTO")
	private Short id;
	
	/**
	 * Descrição da situação do parcelamento.
	 */
	@Column(name = "DESCSITUACAOPARCELAMENTO")
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