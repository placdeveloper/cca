/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO RelParcelamentoDTO
 */
public class RelParcelamentoDTO extends BancoobDto {

	private Integer numParcelamento;
	private String tipoParcelamento;
	private Integer parcelasAbertas;
	private BigDecimal valorTotal;
	/**
	 * @return the numParcelamento
	 */
	public Integer getNumParcelamento() {
		return numParcelamento;
	}
	/**
	 * @param numParcelamento the numParcelamento to set
	 */
	public void setNumParcelamento(Integer numParcelamento) {
		this.numParcelamento = numParcelamento;
	}
	/**
	 * @return the tipoParcelamento
	 */
	public String getTipoParcelamento() {
		return tipoParcelamento;
	}
	/**
	 * @param tipoParcelamento the tipoParcelamento to set
	 */
	public void setTipoParcelamento(String tipoParcelamento) {
		this.tipoParcelamento = tipoParcelamento;
	}
	/**
	 * @return the parcelasAbertas
	 */
	public Integer getParcelasAbertas() {
		return parcelasAbertas;
	}
	/**
	 * @param parcelasAbertas the parcelasAbertas to set
	 */
	public void setParcelasAbertas(Integer parcelasAbertas) {
		this.parcelasAbertas = parcelasAbertas;
	}
	/**
	 * @return the valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
