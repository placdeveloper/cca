/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.api.negocio.enums.EnumTipoLiquidacao;

/**
 * DTO IntegralizacaoCapitalBoletoDTO
 */
public class IntegralizacaoCapitalBoletoDTO extends BancoobDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3119291851093413764L;
	
	
	//Dados Integralizacao
	private Integer numContaCapital;
	private Integer idInstituicao;
	private String idOperacao;
	private BigDecimal valorSubscricao;
	private BigDecimal valorIntegralizacao;
	
	private EnumTipoLiquidacao tipoLiquidacao;
	
	//Dados Parcelamento
	private Integer qtdeParcelas;	
	private DateTimeDB dataInicioParcelamento;
	private Long numContaCorrente;		
	
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
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
	public Integer getQtdeParcelas() {
		return qtdeParcelas;
	}
	public void setQtdeParcelas(Integer qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}
	public DateTimeDB getDataInicioParcelamento() {
		return dataInicioParcelamento;
	}
	public void setDataInicioParcelamento(DateTimeDB dataInicioParcelamento) {
		this.dataInicioParcelamento = dataInicioParcelamento;
	}
	public EnumTipoLiquidacao getTipoLiquidacao() {
		return tipoLiquidacao;
	}
	public void setTipoLiquidacao(EnumTipoLiquidacao tipoLiquidacao) {
		this.tipoLiquidacao = tipoLiquidacao;
	}
}