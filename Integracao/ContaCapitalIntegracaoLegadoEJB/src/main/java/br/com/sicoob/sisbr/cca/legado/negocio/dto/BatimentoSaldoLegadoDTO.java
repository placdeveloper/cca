package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO para batimento de saldos legado.
 * @author Nairon.Silva
 */
public class BatimentoSaldoLegadoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer numMatricula;
	private BigDecimal valorSaldoInteg;
	private BigDecimal valorSaldoSubsc;
	private BigDecimal valorSaldoDevol;
	private BigDecimal valorSaldoBloq;
	
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
	
}
