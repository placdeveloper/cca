/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO que serve de parametro para o relatorio
 * 
 * @author Sron.Cruz
 */
public class RelParticipacaoIndiretaSingularDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 414572058419017327L;

	/** O atributo idInstituicaoSingular. */
	private Integer idInstituicaoSingular;
	
	/** O atributo idInstituicaoCentral. */
	private Integer idInstituicaoCentral;
	
	/** O atributo anoMesBase. */
	private Integer anoMesBase;
	
	/** O atributo valorSaldoInteg. */
	private BigDecimal valorSaldoInteg;
	
	/** O atributo percParticipacao. */
	private BigDecimal percParticipacao;
	
	/** O atributo valorParticipacaoBancoob. */
	private BigDecimal valorParticipacaoBancoob;
	
	/** O atributo nomeInstituicaoSingular. */
	private String nomeInstituicaoSingular;
	
	/** O atributo nomeInstituicaoCentral. */
	private String nomeInstituicaoCentral;
	
	/** O atributo mes. */
	private String mes;
	
	/** O atributo valorParticipacao. */
	private BigDecimal valorParticipacao;
	
	/** O atributo numInstituicaoSingular. */
	private Integer numInstituicaoSingular;
	
	/** O atributo numInstituicaoCentral. */
	private Integer numInstituicaoCentral;

	/**
	 * @return valorParticipacao
	 */
	public BigDecimal getValorParticipacao() {
		return valorParticipacao;
	}
	
	/**
	 * @param valorParticipacao
	 */
	public void setValorParticipacao(BigDecimal valorParticipacao) {
		this.valorParticipacao = valorParticipacao;
	}
	
	/**
	 * @return numInstituicaoSingular
	 */
	public Integer getNumInstituicaoSingular() {
		return numInstituicaoSingular;
	}
	
	/**
	 * @param numInstituicaoSingular
	 */
	public void setNumInstituicaoSingular(Integer numInstituicaoSingular) {
		this.numInstituicaoSingular = numInstituicaoSingular;
	}
	
	/**
	 * @return numInstituicaoCentral
	 */
	public Integer getNumInstituicaoCentral() {
		return numInstituicaoCentral;
	}
	
	/**
	 * @param numInstituicaoCentral
	 */
	public void setNumInstituicaoCentral(Integer numInstituicaoCentral) {
		this.numInstituicaoCentral = numInstituicaoCentral;
	}

	/**
	 * @return nomeInstituicaoSingular
	 */
	public String getNomeInstituicaoSingular() {
		return nomeInstituicaoSingular;
	}

	/**
	 * @param nomeInstituicaoSingular
	 */
	public void setNomeInstituicaoSingular(String nomeInstituicaoSingular) {
		this.nomeInstituicaoSingular = nomeInstituicaoSingular;
	}

	/**
	 * @return nomeInstituicaoCentral
	 */
	public String getNomeInstituicaoCentral() {
		return nomeInstituicaoCentral;
	}

	/**
	 * @param nomeInstituicaoCentral
	 */
	public void setNomeInstituicaoCentral(String nomeInstituicaoCentral) {
		this.nomeInstituicaoCentral = nomeInstituicaoCentral;
	}

	/**
	 * @return idInstituicaoSingular
	 */
	public Integer getIdInstituicaoSingular() {
		return idInstituicaoSingular;
	}

	/**
	 * @param idInstituicaoSingular
	 */
	public void setIdInstituicaoSingular(Integer idInstituicaoSingular) {
		this.idInstituicaoSingular = idInstituicaoSingular;
	}

	/**
	 * @return idInstituicaoCentral
	 */
	public Integer getIdInstituicaoCentral() {
		return idInstituicaoCentral;
	}

	/**
	 * @param idInstituicaoCentral
	 */
	public void setIdInstituicaoCentral(Integer idInstituicaoCentral) {
		this.idInstituicaoCentral = idInstituicaoCentral;
	}

	/**
	 * @return anoMesBase
	 */
	public Integer getAnoMesBase() {
		return anoMesBase;
	}

	/**
	 * @param anoMesBase
	 */
	public void setAnoMesBase(Integer anoMesBase) {
		this.anoMesBase = anoMesBase;
	}

	/**
	 * @return valorSaldoInteg
	 */
	public BigDecimal getValorSaldoInteg() {
		return valorSaldoInteg;
	}

	/**
	 * @param valorSaldoInteg
	 */
	public void setValorSaldoInteg(BigDecimal valorSaldoInteg) {
		this.valorSaldoInteg = valorSaldoInteg;
	}

	/**
	 * @return percParticipacao
	 */
	public BigDecimal getPercParticipacao() {
		return percParticipacao;
	}

	/**
	 * @param percParticipacao
	 */
	public void setPercParticipacao(BigDecimal percParticipacao) {
		this.percParticipacao = percParticipacao;
	}

	/**
	 * @return valorParticipacaoBancoob
	 */
	public BigDecimal getValorParticipacaoBancoob() {
		return valorParticipacaoBancoob;
	}

	/**
	 * @param valorParticipacaoBancoob
	 */
	public void setValorParticipacaoBancoob(BigDecimal valorParticipacaoBancoob) {
		this.valorParticipacaoBancoob = valorParticipacaoBancoob;
	}

	/**
	 * Retorna somente o mes
	 * 
	 * @return mes
	 */
	public String getMes() {
		if (this.anoMesBase != null) {
			mes = this.anoMesBase.toString().substring(4, 6);
		}
		return mes;
	}
}
