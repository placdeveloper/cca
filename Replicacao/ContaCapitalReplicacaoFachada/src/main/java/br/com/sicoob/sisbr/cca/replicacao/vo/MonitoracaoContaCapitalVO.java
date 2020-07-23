/*
 * 
 */
package br.com.sicoob.sisbr.cca.replicacao.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * VO MonitoracaoContaCapitalVO
 */
public class MonitoracaoContaCapitalVO extends BancoobDto {

	private static final long serialVersionUID = -687920047437402761L;
	
	private Integer idContaCapital;
	private Integer idInstituicao;
	private Integer idPessoa;
	private Integer numMatricula;
	private DateTime dataMatricula;
	private BigDecimal valorInteg;
	private BigDecimal valorSubs;
	private BigDecimal valorDevol;
	private BigDecimal valorBloq;
	private DateTime dataSaida;
	private String idUsuario;
	
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
	public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	public Integer getNumMatricula() {
		return numMatricula;
	}
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}
	public DateTime getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(DateTime dataMatricula) {
		this.dataMatricula = dataMatricula;
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
	public DateTime getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(DateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
