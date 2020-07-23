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
 * Configuração capital
 * @author antonio.genaro
 * @since 28/09/2015
 */
@Entity
@Table(name = "TIPOVALORCONFIGURACAOCAPITAL", schema = "cca")
public class TipoValorConfiguracaoCapital extends ContaCapitalEntidade<Short> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3262349201625821119L;

	/**
	 * PK
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTIPOVALORCONFIGURACAOCAPITAL")
	private Short id;
	
	/**
	 * Descrição do Tipo de Valor Parâmetro
	 */
	@Column(name = "DESCTIPOVALORCONFIGURACAOCAPITAL", nullable = false)
	private String descTipoValorConfiguracaoCapital;

	/**
	 * {@inheritDoc}
	 */
	public Short getId() {
		return id;
	}

	/**
	 * Define o valor de id.
	 *
	 * @param id o novo valor de id
	 */
	public void setId(Short id) {
		this.id = id;
	}

	/**
	 * Recupera o valor de descTipoValorConfiguracaoCapital.
	 *
	 * @return o valor de descTipoValorConfiguracaoCapital
	 */
	public String getDescTipoValorConfiguracaoCapital() {
		return descTipoValorConfiguracaoCapital;
	}

	/**
	 * Define o valor de descTipoValorConfiguracaoCapital.
	 *
	 * @param descTipoValorConfiguracaoCapital o novo valor de descTipoValorConfiguracaoCapital
	 */
	public void setDescTipoValorConfiguracaoCapital(
			String descTipoValorConfiguracaoCapital) {
		this.descTipoValorConfiguracaoCapital = descTipoValorConfiguracaoCapital;
	}


}
