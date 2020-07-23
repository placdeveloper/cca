/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO ValorIngressoCapitalDTO
 */
public class ValorIngressoCapitalDTO extends BancoobDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1748656264893537602L;
	
	private Integer idInstituicao;
	private BigDecimal valorCapitalIngresso;
	private BigDecimal valorCapitalMensalFixo;
	private BigDecimal valorCapitalMensalPercentual;
	
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	public BigDecimal getValorCapitalIngresso() {
		return valorCapitalIngresso;
	}
	
	public void setValorCapitalIngresso(BigDecimal valorCapitalIngresso) {
		this.valorCapitalIngresso = valorCapitalIngresso;
	}
	
	public BigDecimal getValorCapitalMensalFixo() {
		return valorCapitalMensalFixo;
	}
	
	public void setValorCapitalMensalFixo(BigDecimal valorCapitalMensalFixo) {
		this.valorCapitalMensalFixo = valorCapitalMensalFixo;
	}
	
	public BigDecimal getValorCapitalMensalPercentual() {
		return valorCapitalMensalPercentual;
	}
	
	public void setValorCapitalMensalPercentual(BigDecimal valorCapitalMensalPercentual) {
		this.valorCapitalMensalPercentual = valorCapitalMensalPercentual;
	}
}