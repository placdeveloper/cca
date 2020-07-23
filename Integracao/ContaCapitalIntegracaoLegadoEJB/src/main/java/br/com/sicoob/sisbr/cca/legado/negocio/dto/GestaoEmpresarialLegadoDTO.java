/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Marco.Nascimento
 */
public class GestaoEmpresarialLegadoDTO extends BancoobDto {
	
	private Integer numPac;
	
	private Integer idInstituicao;
	
	private Integer codPFPJ;
	
	private Date dataLote;
	
	private BigDecimal baseIRRF;
	
	private BigDecimal valorIRRF;

	/**
	 * @return the numPac
	 */
	public Integer getNumPac() {
		return numPac;
	}

	/**
	 * @param numPac the numPac to set
	 */
	public void setNumPac(Integer numPac) {
		this.numPac = numPac;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * @return the codPFPJ
	 */
	public Integer getCodPFPJ() {
		return codPFPJ;
	}

	/**
	 * @param codPFPJ the codPFPJ to set
	 */
	public void setCodPFPJ(Integer codPFPJ) {
		this.codPFPJ = codPFPJ;
	}

	/**
	 * @return the dataLote
	 */
	public Date getDataLote() {
		return dataLote;
	}

	/**
	 * @param dataLote the dataLote to set
	 */
	public void setDataLote(Date dataLote) {
		this.dataLote = dataLote;
	}

	/**
	 * @return the baseIRRF
	 */
	public BigDecimal getBaseIRRF() {
		return baseIRRF;
	}

	/**
	 * @param baseIRRF the baseIRRF to set
	 */
	public void setBaseIRRF(BigDecimal baseIRRF) {
		this.baseIRRF = baseIRRF;
	}

	/**
	 * @return the valorIRRF
	 */
	public BigDecimal getValorIRRF() {
		return valorIRRF;
	}

	/**
	 * @param valorIRRF the valorIRRF to set
	 */
	public void setValorIRRF(BigDecimal valorIRRF) {
		this.valorIRRF = valorIRRF;
	}
}