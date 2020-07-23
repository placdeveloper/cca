/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Agrupador de configuracao conta capital
 * @author marco.nascimento
 */
@Entity
@Table(name = "AGRUPADORCONFIGURACAOCAPITAL", schema = "cca")
public class AgrupadorConfiguracaoCapital extends ContaCapitalEntidade<Integer> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6372189015668846323L;

	/**
	 * PK
	 */
	@Id
	@Column(name = "IDAGRUPADORCONFIGURACAOCAPITAL")
	private Integer id;
	
	/**
	 * Nome do agrupador
	 */
	@Column(name = "NOMEAGRUPADORCONFIGURACAOCAPITAL", nullable = false)
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}