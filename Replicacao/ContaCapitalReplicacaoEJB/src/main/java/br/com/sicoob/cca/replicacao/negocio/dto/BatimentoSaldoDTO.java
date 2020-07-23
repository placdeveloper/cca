package br.com.sicoob.cca.replicacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO para batimento de saldos.
 * @author Nairon.Silva
 */
public class BatimentoSaldoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer numMatricula;

	private BigDecimal valorSaldoInteg;
	private BigDecimal valorSaldoSubsc;
	private BigDecimal valorSaldoDevol;
	private BigDecimal valorSaldoBloq;
	
	private BigDecimal valorSaldoIntegLegado;
	private BigDecimal valorSaldoSubscLegado;
	private BigDecimal valorSaldoDevolLegado;
	private BigDecimal valorSaldoBloqLegado;
	
	public Integer getNumMatricula() {
		return numMatricula;
	}
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}
	public BigDecimal getValorSaldoInteg() {
		return valorSaldoInteg;
	}
	public void setValorSaldoInteg(BigDecimal valorSaldoInteg) {
		this.valorSaldoInteg = valorSaldoInteg;
	}
	public BigDecimal getValorSaldoSubsc() {
		return valorSaldoSubsc;
	}
	public void setValorSaldoSubsc(BigDecimal valorSaldoSubsc) {
		this.valorSaldoSubsc = valorSaldoSubsc;
	}
	public BigDecimal getValorSaldoDevol() {
		return valorSaldoDevol;
	}
	public void setValorSaldoDevol(BigDecimal valorSaldoDevol) {
		this.valorSaldoDevol = valorSaldoDevol;
	}
	public BigDecimal getValorSaldoBloq() {
		return valorSaldoBloq;
	}
	public void setValorSaldoBloq(BigDecimal valorSaldoBloq) {
		this.valorSaldoBloq = valorSaldoBloq;
	}
	public BigDecimal getValorSaldoIntegLegado() {
		return valorSaldoIntegLegado;
	}
	public void setValorSaldoIntegLegado(BigDecimal valorSaldoIntegLegado) {
		this.valorSaldoIntegLegado = valorSaldoIntegLegado;
	}
	public BigDecimal getValorSaldoSubscLegado() {
		return valorSaldoSubscLegado;
	}
	public void setValorSaldoSubscLegado(BigDecimal valorSaldoSubscLegado) {
		this.valorSaldoSubscLegado = valorSaldoSubscLegado;
	}
	public BigDecimal getValorSaldoDevolLegado() {
		return valorSaldoDevolLegado;
	}
	public void setValorSaldoDevolLegado(BigDecimal valorSaldoDevolLegado) {
		this.valorSaldoDevolLegado = valorSaldoDevolLegado;
	}
	public BigDecimal getValorSaldoBloqLegado() {
		return valorSaldoBloqLegado;
	}
	public void setValorSaldoBloqLegado(BigDecimal valorSaldoBloqLegado) {
		this.valorSaldoBloqLegado = valorSaldoBloqLegado;
	}
	
}
