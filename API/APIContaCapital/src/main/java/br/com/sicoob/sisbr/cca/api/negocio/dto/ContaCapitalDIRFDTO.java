/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO ContaCapitalDIRFDTO
 */
public class ContaCapitalDIRFDTO extends BancoobDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2599091906489383285L;

	private Integer numPac;
	
	private Integer idInstituicao;
	
	private Integer codPFPJ;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private BigDecimal baseIRRF;
	
	private BigDecimal valorIRRF;
	
	private Integer idProduto;
	
	private String nomeProduto;

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

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return the idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}

	/**
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	/**
	 * @return the nomeProduto
	 */
	public String getNomeProduto() {
		return nomeProduto;
	}

	/**
	 * @param nomeProduto the nomeProduto to set
	 */
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
}