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
 * Tipo valor debito
 * @author marco.nascimento
 * @since 22/05/2014
 */
@Entity
@Table(name = "TIPOVALORDEBITO", schema = "cca")
public class TipoValorDebito extends ContaCapitalEntidade<Short> {
	
	public TipoValorDebito() {
		
	}
	
	/**
	 * Construtor com o ID
	 * @param id
	 */
	public TipoValorDebito(Short id) {
		this.id = id;
	}	

	/**
	 * Identificador do tipo de valor.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTIPOVALORDEBITO")
	private Short id;
	
	/**
	 * Descrição do tipo de valor.
	 */
	@Column(name = "DESCTIPOVALORDEBITO", length = 40, nullable = false)
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