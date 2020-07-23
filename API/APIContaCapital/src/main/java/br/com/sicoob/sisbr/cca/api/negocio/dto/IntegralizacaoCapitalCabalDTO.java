/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO IntegralizacaoCapitalCabalDTO
 */
public class IntegralizacaoCapitalCabalDTO extends BancoobDto {

	
	private static final long serialVersionUID = -1611949017840689750L;
	
	private String numCpfCnpj;
	private Integer numCooperativa;
	private String idOperacaoCabal;
	private BigDecimal valorIntegralizacao;
	
	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}
	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	public String getIdOperacaoCabal() {
		return idOperacaoCabal;
	}
	public void setIdOperacaoCabal(String idOperacaoCabal) {
		this.idOperacaoCabal = idOperacaoCabal;
	}
	public BigDecimal getValorIntegralizacao() {
		return valorIntegralizacao;
	}
	public void setValorIntegralizacao(BigDecimal valorIntegralizacao) {
		this.valorIntegralizacao = valorIntegralizacao;
	}
	
	@Override
	public String toString() {
		return "IntegralizacaoCapitalCabalDTO [numCpfCnpj=" + numCpfCnpj + ", numCooperativa=" + numCooperativa
				+ ", idOperacaoCabal=" + idOperacaoCabal + ", valorIntegralizacao=" + valorIntegralizacao + "]";
	}
	
}
