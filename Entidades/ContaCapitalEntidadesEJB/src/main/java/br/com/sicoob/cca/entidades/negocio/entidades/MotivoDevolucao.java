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
 * Motivo devolução
 * @author marco.nascimento
 * @since 15/05/2014
 */
@Entity
@Table(name = "MOTIVODEVOLUCAO", schema = "cca")
public class MotivoDevolucao extends ContaCapitalEntidade<Short> {

	
	/**
	 * Construtor Padrão
	 */
	public MotivoDevolucao() {

	}
	
	/**
	 * Construtor com o ID
	 * @param id
	 */
	public MotivoDevolucao(Short id) {
		this.id = id;
	}		
	
	/**
	 * Identificador do motivo de devolução
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDMOTIVODEVOLUCAO")
	private Short id;
	
	/**
	 * Descrição do motivo de devolução
	 */
	@Column(name = "DESCMOTIVODEVOLUCAO", length = 100, nullable = false)
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