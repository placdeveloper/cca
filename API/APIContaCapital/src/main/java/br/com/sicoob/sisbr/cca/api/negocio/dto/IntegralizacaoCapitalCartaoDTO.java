/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO IntegralizacaoCapitalCartaoDTO
 */
public class IntegralizacaoCapitalCartaoDTO extends BancoobDto {

	private static final long serialVersionUID = 8056332418589516492L;
	
	private Integer numContaCapital;
	private Integer idInstituicao;
	private String idOperacao;
	private BigDecimal valorSubscricao;	
	private BigDecimal valorIntegralizacao;	
	
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public String getIdOperacao() {
		return idOperacao;
	}
	public void setIdOperacao(String idOperacao) {
		this.idOperacao = idOperacao;
	}
	public BigDecimal getValorSubscricao() {
		return valorSubscricao;
	}
	public void setValorSubscricao(BigDecimal valorSubscricao) {
		this.valorSubscricao = valorSubscricao;
	}
	public BigDecimal getValorIntegralizacao() {
		return valorIntegralizacao;
	}
	public void setValorIntegralizacao(BigDecimal valorIntegralizacao) {
		this.valorIntegralizacao = valorIntegralizacao;
	}	
	

	
}
