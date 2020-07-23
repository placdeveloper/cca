/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Condição de associação
 * @author marco.nascimento
 * @see TipoPessoa, TipoCooperativa
 * @since 15/05/2014
 */
@Entity
@Table(name = "CONDICAOASSOCIACAO", schema = "cca")
public class CondicaoAssociacao extends ContaCapitalEntidade<Short> {


	/**
	 * Identificador da condição de associação
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCONDICAOASSOCIACAO")
	private Short id;
	
	/**
	 * Descrição da condição de associação
	 */
	@Column(name = "DESCCONDICAOASSOCIACAO", length = 400, nullable = false)
	private String descricao;
	
	/**
	 * Identificador do tipo de pessoa.
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOPESSOA", nullable = false)
	private TipoPessoa tipoPessoa;
	
	/**
	 * Identificador do tipo de cooperativa
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOCOOPERATIVA", nullable = false)
	private TipoCooperativa tipoCooperativa;
	
	/**
	 * Resolução que dispõe sobre as cooperativas de crédito
	 */
	@Column(name = "DESCREGULAMENTACAO", length = 1000, nullable = false)
	private String regulamentacao;

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
	 * @return the condicaoAssociacao
	 */
	public String getCondicaoAssociacao() {
		return descricao;
	}

	/**
	 * @param condicaoAssociacao the condicaoAssociacao to set
	 */
	public void setCondicaoAssociacao(String condicaoAssociacao) {
		this.descricao = condicaoAssociacao;
	}

	/**
	 * @return the tipoPessoa
	 */
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	/**
	 * @param tipoPessoa the tipoPessoa to set
	 */
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	/**
	 * @return the tipoCooperativa
	 */
	public TipoCooperativa getTipoCooperativa() {
		return tipoCooperativa;
	}

	/**
	 * @param tipoCooperativa the tipoCooperativa to set
	 */
	public void setTipoCooperativa(TipoCooperativa tipoCooperativa) {
		this.tipoCooperativa = tipoCooperativa;
	}

	/**
	 * @return the regulamentacao
	 */
	public String getRegulamentacao() {
		return regulamentacao;
	}

	/**
	 * @param regulamentacao the regulamentacao to set
	 */
	public void setRegulamentacao(String regulamentacao) {
		this.regulamentacao = regulamentacao;
	}
}