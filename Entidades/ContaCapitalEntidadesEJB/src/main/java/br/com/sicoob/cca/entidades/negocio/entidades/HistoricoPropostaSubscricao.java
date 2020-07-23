/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Conta capital
 * @author marco.nascimento
 * @since 25/11/2015
 */
@Entity
@Table(name = "HISTPROPOSTASUBSCRICAO", schema = "cca")
public class HistoricoPropostaSubscricao extends ContaCapitalEntidade<HistoricoPropostaSubscricaoPK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7456211251602556745L;

	/** O atributo id. */
	@EmbeddedId
	private HistoricoPropostaSubscricaoPK id;
	
	/** O atributo tipoIntegralizacao. */
	@ManyToOne
	@JoinColumn(name = "IDTIPOINTEGRALIZACAO", nullable = false)
	private TipoIntegralizacao tipoIntegralizacao;
	
	/** O atributo valorSubsProposta. */
	@Column(name = "VALORSUBSCPROPOSTA", nullable = false)
	private BigDecimal valorSubsProposta;
	
	/** O atributo valorIntegProposta. */
	@Column(name = "VALORINTEGPROPOSTA", nullable = false)
	private BigDecimal valorIntegProposta;
	
	/** O atributo qtdParcelaProposta. */
	@Column(name = "QTDPARCELAPROPOSTA", nullable = false)
	private Integer qtdParcelaProposta;
	
	/** O atributo valorParcelaProposta. */
	@Column(name = "VALORPARCELAPROPOSTA", nullable = false)
	private BigDecimal valorParcelaProposta;
	
	/** O atributo diaDebitoProposta. */
	@Column(name = "DIADEBITOPROPOSTA", nullable = false)
	private Integer diaDebitoProposta;
	
	/** O atributo numContaCorrente. */
	@Column(name = "NUMCONTACORRENTE", nullable = true)
	private Long numContaCorrente;
	
	/** O atributo idUsuario. */
	@Column(name = "IDUSUARIO", length = 40, nullable = false)
	private String idUsuario;
	
	/** O atributo bolSubscricaoProposta. */
	@Column(name = "BOLSUBSCRICAOPROPOSTA", nullable = false)
	private Integer bolSubscricaoProposta;
	
	/**
	 * Recupera o valor de bolSubscricaoProposta.
	 *
	 * @return o valor de bolSubscricaoProposta
	 */
	public Integer getBolSubscricaoProposta() {
		return bolSubscricaoProposta;
	}

	/**
	 * Define o valor de bolSubscricaoProposta.
	 *
	 * @param bolSubscricaoProposta o novo valor de bolSubscricaoProposta
	 */
	public void setBolSubscricaoProposta(Integer bolSubscricaoProposta) {
		this.bolSubscricaoProposta = bolSubscricaoProposta;
	}

	/**
	 * @return the tipoIntegralizacao
	 */
	public TipoIntegralizacao getTipoIntegralizacao() {
		return tipoIntegralizacao;
	}

	/**
	 * @param tipoIntegralizacao the tipoIntegralizacao to set
	 */
	public void setTipoIntegralizacao(TipoIntegralizacao tipoIntegralizacao) {
		this.tipoIntegralizacao = tipoIntegralizacao;
	}

	/**
	 * @return the valorSubsProposta
	 */
	public BigDecimal getValorSubsProposta() {
		return valorSubsProposta;
	}

	/**
	 * @param valorSubsProposta the valorSubsProposta to set
	 */
	public void setValorSubsProposta(BigDecimal valorSubsProposta) {
		this.valorSubsProposta = valorSubsProposta;
	}

	/**
	 * @return the valorIntegProposta
	 */
	public BigDecimal getValorIntegProposta() {
		return valorIntegProposta;
	}

	/**
	 * @param valorIntegProposta the valorIntegProposta to set
	 */
	public void setValorIntegProposta(BigDecimal valorIntegProposta) {
		this.valorIntegProposta = valorIntegProposta;
	}

	/**
	 * @return the qtdParcelaProposta
	 */
	public Integer getQtdParcelaProposta() {
		return qtdParcelaProposta;
	}

	/**
	 * @param qtdParcelaProposta the qtdParcelaProposta to set
	 */
	public void setQtdParcelaProposta(Integer qtdParcelaProposta) {
		this.qtdParcelaProposta = qtdParcelaProposta;
	}

	/**
	 * @return the valorParcelaProposta
	 */
	public BigDecimal getValorParcelaProposta() {
		return valorParcelaProposta;
	}

	/**
	 * @param valorParcelaProposta the valorParcelaProposta to set
	 */
	public void setValorParcelaProposta(BigDecimal valorParcelaProposta) {
		this.valorParcelaProposta = valorParcelaProposta;
	}

	/**
	 * @return the diaDebitoProposta
	 */
	public Integer getDiaDebitoProposta() {
		return diaDebitoProposta;
	}

	/**
	 * @param diaDebitoProposta the diaDebitoProposta to set
	 */
	public void setDiaDebitoProposta(Integer diaDebitoProposta) {
		this.diaDebitoProposta = diaDebitoProposta;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the id
	 */
	public HistoricoPropostaSubscricaoPK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(HistoricoPropostaSubscricaoPK id) {
		this.id = id;
	}

	/**
	 * @return the numContaCorrente
	 */
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}

	/**
	 * @param numContaCorrente the numContaCorrente to set
	 */
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
}