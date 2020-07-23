/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Marco.Nascimento
 */
public class ContaCapitalDebIndeterminadoDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9025549316708754964L;

	private Integer idInstituicao;
	
	private Integer numMatricula;
	
	private Long numContaCorrente;
	
	private BigDecimal valorDebito;
	
	private Short diaDebitoMensal;

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Long getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public Short getDiaDebitoMensal() {
		return diaDebitoMensal;
	}

	public void setDiaDebitoMensal(Short diaDebitoMensal) {
		this.diaDebitoMensal = diaDebitoMensal;
	}

	@Override
	public String toString() {
		return "ContaCapitalDebIndeterminadoDTO [idInstituicao=" + idInstituicao + ", numMatricula=" + numMatricula
				+ ", numContaCorrente=" + numContaCorrente + ", valorDebito=" + valorDebito + ", diaDebitoMensal="
				+ diaDebitoMensal + "]";
	}
	
}