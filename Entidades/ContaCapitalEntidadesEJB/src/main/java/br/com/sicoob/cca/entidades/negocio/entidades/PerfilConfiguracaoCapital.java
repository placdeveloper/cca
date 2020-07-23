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
 * @since 30/09/2015
 */
@Entity
@Table(name = "PERFILCONFIGURACAOCAPITAL", schema = "cca")
public class PerfilConfiguracaoCapital extends ContaCapitalEntidade<Short> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1499813537589323464L;

	/**
	 * PK
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPERFILCONFIGURACAOCAPITAL")
	private Short id;
	
	/**
	 * Descrição do perfil de configuração do conta capital
	 */
	@Column(name = "DESCPERFILCONFIGURACAOCAPITAL", nullable = false)
	private String descPerfilConfiguracaoCapital;

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
	 * Recupera o valor de descPerfilConfiguracaoCapital.
	 *
	 * @return o valor de descPerfilConfiguracaoCapital
	 */
	public String getDescPerfilConfiguracaoCapital() {
		return descPerfilConfiguracaoCapital;
	}

	/**
	 * Define o valor de descPerfilConfiguracaoCapital.
	 *
	 * @param descPerfilConfiguracaoCapital o novo valor de descPerfilConfiguracaoCapital
	 */
	public void setDescPerfilConfiguracaoCapital(
			String descPerfilConfiguracaoCapital) {
		this.descPerfilConfiguracaoCapital = descPerfilConfiguracaoCapital;
	}

	
}
