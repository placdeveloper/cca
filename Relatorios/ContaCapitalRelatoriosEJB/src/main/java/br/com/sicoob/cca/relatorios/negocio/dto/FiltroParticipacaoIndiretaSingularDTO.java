/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 *  DTO que transforma filtro do flex para filtro do ejb.
 * @author Sron.Cruz
 */
public class FiltroParticipacaoIndiretaSingularDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7842061675679643048L;
	
	/** O atributo idInstituicaoSingular. */
	private Integer idInstituicaoSingular;
	
	/** O atributo idInstituicaoCentral. */
	private Integer idInstituicaoCentral;
	
	/** O atributo anoMesBase. */
	private Integer anoMesBase;
	
	/** O atributo ano. */
	private Integer ano;
	
	/** O atributo mes. */
	private Integer mes;
	
	/** O atributo numCooperativa. */
	private Integer numCooperativa;
	
	/** O atributo numCentral. */
	private Integer numCentral;
	
	/** O atributo chkArquivoExcel. */
	private Boolean chkArquivoExcel;
	
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
	 * @return ano
	 */
	public Integer getAno() {
		return ano;
	}
	/**
	 * @param ano
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	/**
	 * @return mes
	 */
	public Integer getMes() {
		return mes;
	}
	/**
	 * @param mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	/**
	 * @return numCooperativa
	 */
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	/**
	 * @param numCooperativa
	 */
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	/**
	 * @return numCentral
	 */
	public Integer getNumCentral() {
		return numCentral;
	}
	/**
	 * @param numCentral
	 */
	public void setNumCentral(Integer numCentral) {
		this.numCentral = numCentral;
	}
	/**
	 * @return chkArquivoExcel
	 */
	public Boolean getChkArquivoExcel() {
		return chkArquivoExcel;
	}
	/**
	 * @param chkArquivoExcel
	 */
	public void setChkArquivoExcel(Boolean chkArquivoExcel) {
		this.chkArquivoExcel = chkArquivoExcel;
	}
	
}
