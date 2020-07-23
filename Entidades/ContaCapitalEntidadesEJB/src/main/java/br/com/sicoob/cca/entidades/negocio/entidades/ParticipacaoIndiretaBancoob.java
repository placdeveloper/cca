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
 * Instituição singular
 * @author antonio.genaro
 * @since 04/06/2014
 */
@Entity
@Table(name = "PARTICIPACAOINDIRETABANCOOB", schema = "cca")
public class ParticipacaoIndiretaBancoob extends ContaCapitalEntidade<ParticipacaoIndiretaBancoobPK> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1352062362291719867L;

	/**
	 * PK
	 */
	@EmbeddedId
	private ParticipacaoIndiretaBancoobPK id;
	
	/**
	 * Valor Total Integralizado da singular na Central
	 */
	@Column(name = "VALORSALDOINTEG", nullable = false)
	private BigDecimal valorSaldoInteg;	
	/**
	 * Percentual de participação da singular na central
	 */
	@Column(name = "PERCPARTICIPACAO", nullable = false)
	private BigDecimal percParticipacaoCentral;	
	/**
	 * Valor da participação indireta da instituição  no Bancoob
	 */
	@Column(name = "VALORPARTICIPACAOBANCOOB", nullable = true)
	private BigDecimal valorParticipacaoBancoob;
	
	/**
	 * @return the id
	 */
	public ParticipacaoIndiretaBancoobPK getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(ParticipacaoIndiretaBancoobPK id) {
		this.id = id;
	}
	/**
	 * @return the valorSaldoInteg
	 */
	public BigDecimal getValorSaldoInteg() {
		return valorSaldoInteg;
	}
	/**
	 * @param valorSaldoInteg the valorSaldoInteg to set
	 */
	public void setValorSaldoInteg(BigDecimal valorSaldoInteg) {
		this.valorSaldoInteg = valorSaldoInteg;
	}
	/**
	 * @return the valorParticipacaoBancoob
	 */
	public BigDecimal getValorParticipacaoBancoob() {
		return valorParticipacaoBancoob;
	}
	/**
	 * @param valorParticipacaoBancoob the valorParticipacaoBancoob to set
	 */
	public void setValorParticipacaoBancoob(BigDecimal valorParticipacaoBancoob) {
		this.valorParticipacaoBancoob = valorParticipacaoBancoob;
	}
	/**
	 * @return the percParticipacaoCentral
	 */
	public BigDecimal getPercParticipacaoCentral() {
		return percParticipacaoCentral;
	}
	/**
	 * @param percParticipacaoCentral the percParticipacaoCentral to set
	 */
	public void setPercParticipacaoCentral(BigDecimal percParticipacaoCentral) {
		this.percParticipacaoCentral = percParticipacaoCentral;
	}
}