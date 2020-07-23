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
 * Tipo Periodo Debito
 * @author marco.nascimento
 * @since 22/05/2014
 */
@Entity
@Table(name = "TIPOPERIODODEBITO", schema = "cca")
public class TipoPeriodoDebito extends ContaCapitalEntidade<Short> {
	
	public TipoPeriodoDebito() {
		
	}
	
	/**
	 * Construtor com o ID
	 * @param id
	 */
	public TipoPeriodoDebito(Short id) {
		this.id = id;
	}	

	/**
	 * Identificador do tipo de período.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTIPOPERIODODEBITO")
	private Short id;
	
	/**
	 * Descrição do tipo de período.
	 */
	@Column(name = "DESCTIPOPERIODODEBITO", length = 40, nullable = false)
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