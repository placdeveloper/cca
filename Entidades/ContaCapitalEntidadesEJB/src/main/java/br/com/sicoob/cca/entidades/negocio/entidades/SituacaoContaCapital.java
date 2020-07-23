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
 * Situacao da conta capital
 * @author marco.nascimento
 * @since 15/05/2014
 */
@Entity
@Table(name = "SITUACAOCONTACAPITAL", schema = "cca")
public class SituacaoContaCapital extends ContaCapitalEntidade<Short> {


	/**
	 * Identificador da situação da Conta Capital
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDSITUACAOCONTACAPITAL")
	private Short id;
	
	/**
	 * Descrição da situação da Conta Capital
	 */
	@Column(name = "DESCSITUACAOCONTACAPITAL")
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