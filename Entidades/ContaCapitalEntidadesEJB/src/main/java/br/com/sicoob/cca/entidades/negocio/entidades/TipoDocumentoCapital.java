/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Tipo de documentos 
 * @author marco.nascimento
 * @since 25/11/2015
 */
@Entity
@Table(name = "TIPODOCUMENTOCAPITAL", schema = "cca")
public class TipoDocumentoCapital extends ContaCapitalEntidade<Integer> {
	
	/**
	 * Instancia um novo TipoDocumentoCapital.
	 */
	public TipoDocumentoCapital() {
	
	}
	
	/**
	 * Instancia um novo TipoDocumentoCapital.
	 *
	 * @param id o valor de id
	 */
	public TipoDocumentoCapital(Integer id) {
		this.id = id;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8656837142462004787L;

	/** O atributo id. */
	@Id
	@Column(name = "IDTIPODOCUMENTOCAPITAL", nullable = false)
	private Integer id;
	
	/** O atributo descricao. */
	@Column(name = "DESCTIPODOCUMENTOCAPITAL", length = 200, nullable = false)
	private String descricao;
	
	/** O atributo sigla. */
	@Column(name = "SIGLATIPODOCUMENTO", length = 20, nullable = false)
	private String sigla;

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

	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}