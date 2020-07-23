/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Situacao do cadastro da proposta (conta capital)
 * @author marco.nascimento
 * @since 23/11/2015
 */
@Entity
@Table(name = "SITUACAOAPROVACAOCAPITAL", schema = "cca")
public class SituacaoCadastroProposta extends ContaCapitalEntidade<Integer> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2223455250841979642L;

	/**
	 * Identificador da situação da Conta Capital
	 */
	@Id
	@Column(name = "IDSITUACAOAPROVACAOCAPITAL")
	private Integer id;
	
	/**
	 * Descrição da situação da Conta Capital
	 */
	@Column(name = "DESCSITUACAOAPROVACAOCAPITAL")
	private String descricao;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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