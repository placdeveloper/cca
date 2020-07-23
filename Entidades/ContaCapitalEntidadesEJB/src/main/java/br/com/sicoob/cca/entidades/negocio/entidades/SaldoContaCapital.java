/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Saldo conta capital
 * @author marco.nascimento
 * @see SaldoContaCapitalPK
 * @since 15/05/2014
 */
@Entity
@Table(name = "SALDOCONTACAPITAL", schema = "cca")
public class SaldoContaCapital extends ContaCapitalEntidade<SaldoContaCapitalPK> {

	/**
	 * PK
	 */
	@EmbeddedId
	private SaldoContaCapitalPK id;
	
	/**
	 * Valor de Integralização na Conta Capital
	 */
	@Column(name = "VALORSALDOINTEG", nullable = false)
	private BigDecimal valorInteg;
	
	/**
	 * Valor de subscrição na Conta Capital
	 */
	@Column(name = "VALORSALDOSUBS", nullable = false)
	private BigDecimal valorSubs;
	
	/**
	 * Valor de devolução na Conta Capital
	 */
	@Column(name = "VALORSALDODEVOL", nullable = false)
	private BigDecimal valorDevol;
	
	/**
	 * Valor de bloqueio na Conta Capital
	 */
	@Column(name = "VALORSALDOBLOQ", nullable = false)
	private BigDecimal valorBloq;

	/**
	 * @return the id
	 */
	public SaldoContaCapitalPK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(SaldoContaCapitalPK id) {
		this.id = id;
	}

	/**
	 * @return the valorInteg
	 */
	public BigDecimal getValorInteg() {
		return valorInteg;
	}

	/**
	 * @param valorInteg the valorInteg to set
	 */
	public void setValorInteg(BigDecimal valorInteg) {
		this.valorInteg = valorInteg;
	}

	/**
	 * @return the valorSubs
	 */
	public BigDecimal getValorSubs() {
		return valorSubs;
	}

	/**
	 * @param valorSubs the valorSubs to set
	 */
	public void setValorSubs(BigDecimal valorSubs) {
		this.valorSubs = valorSubs;
	}

	/**
	 * @return the valorDevol
	 */
	public BigDecimal getValorDevol() {
		return valorDevol;
	}

	/**
	 * @param valorDevol the valorDevol to set
	 */
	public void setValorDevol(BigDecimal valorDevol) {
		this.valorDevol = valorDevol;
	}

	/**
	 * @return the valorBloq
	 */
	public BigDecimal getValorBloq() {
		return valorBloq;
	}

	/**
	 * @param valorBloq the valorBloq to set
	 */
	public void setValorBloq(BigDecimal valorBloq) {
		this.valorBloq = valorBloq;
	}
}