package br.com.sicoob.cca.cadastro.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO ContaCapitalResumoDTO
 */
public class ContaCapitalResumoDTO extends BancoobDto {

	private static final long serialVersionUID = 8107913290271461479L;
	
	private Integer idContaCapital;
	private Integer idInstituicao;
	private Integer idSituacaoContaCapital;
	private Integer idPessoa;
	private Integer numContaCapital;
	private BigDecimal valorInteg;
	private BigDecimal valorSubs;
	private BigDecimal valorDevol;
	private BigDecimal valorBloq;
	
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Integer getIdSituacaoContaCapital() {
		return idSituacaoContaCapital;
	}
	public void setIdSituacaoContaCapital(Integer idSituacaoContaCapital) {
		this.idSituacaoContaCapital = idSituacaoContaCapital;
	}
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	public BigDecimal getValorInteg() {
		return valorInteg;
	}
	public void setValorInteg(BigDecimal valorInteg) {
		this.valorInteg = valorInteg;
	}
	public BigDecimal getValorSubs() {
		return valorSubs;
	}
	public void setValorSubs(BigDecimal valorSubs) {
		this.valorSubs = valorSubs;
	}
	public BigDecimal getValorDevol() {
		return valorDevol;
	}
	public void setValorDevol(BigDecimal valorDevol) {
		this.valorDevol = valorDevol;
	}
	public BigDecimal getValorBloq() {
		return valorBloq;
	}
	public void setValorBloq(BigDecimal valorBloq) {
		this.valorBloq = valorBloq;
	}
	public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	@Override
	public String toString() {
		return "ContaCapitalResumoDTO [idContaCapital=" + idContaCapital + ", idInstituicao=" + idInstituicao
				+ ", idSituacaoContaCapital=" + idSituacaoContaCapital + ", idPessoa=" + idPessoa + ", numContaCapital="
				+ numContaCapital + ", valorInteg=" + valorInteg + ", valorSubs=" + valorSubs + ", valorDevol="
				+ valorDevol + ", valorBloq=" + valorBloq + "]";
	}
	
}
