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
 * @since 10/07/2017
 */
@Entity
@Table(name = "ORIGEMBLOQUEIOCAPITAL ", schema = "CCA")
public class OrigemBloqueioCapital extends ContaCapitalEntidade<Short> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4982233619201540580L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDORIGEMBLOQUEIOCAPITAL")
	private Short id;	
	
	/**
	 * Nome da Origem do Bloqueio de Capital
	 */
	@Column(name = "NOMEORIGEMBLOQUEIO", nullable = false, length = 80)
	private String nomeOrigemBloqueio;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNomeOrigemBloqueio() {
		return nomeOrigemBloqueio;
	}

	public void setNomeOrigemBloqueio(String nomeOrigemBloqueio) {
		this.nomeOrigemBloqueio = nomeOrigemBloqueio;
	}	

}
