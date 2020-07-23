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
 * Grupo Histórico
 * @author marco.nascimento
 * @since 16/05/2014
 */
@Entity
@Table(name = "GRUPOHISTORICO", schema = "cca")
public class GrupoHistorico extends ContaCapitalEntidade<Short> {
	

	/**
	 * Identificador do grupo histórico
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDGRUPOHISTORICO")
	private Short id;
	
	/**
	 * Descrição do grupo histórico
	 */
	@Column(name = "DESCGRUPOHISTORICO", length = 200, nullable = false)
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