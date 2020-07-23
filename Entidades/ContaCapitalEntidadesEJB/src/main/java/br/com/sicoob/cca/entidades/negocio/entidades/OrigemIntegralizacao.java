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
 * Origem integralizacao
 * @author marco.nascimento
 * @since 22/05/2014
 */
@Entity
@Table(name = "ORIGEMINTEGRALIZACAO", schema = "cca")
public class OrigemIntegralizacao extends ContaCapitalEntidade<Short> {

	/**
	 * Identificador da origem de integralizacao de capital
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDORIGEMINTEGRALIZACAO")
	private Short id;
	
	/**
	 * Descrição da origem da integralização.
	 */
	@Column(name = "DESCORIGEMINTEGRALIZACAO", length = 100, nullable = false)
	private String descricao;
	
	/**
	 * Sigla de origem da integralizacao.
	 * D - Debito Indeter. 
	 * C - Cadastro 
	 * P - Parcelamento
	 */
	@Column(name = "SIGLAORIGEMINTEGRALIZACAO", length = 2, nullable = false)
	private String sigla;

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