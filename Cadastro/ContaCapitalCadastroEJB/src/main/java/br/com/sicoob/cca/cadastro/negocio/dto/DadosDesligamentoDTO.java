package br.com.sicoob.cca.cadastro.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DadosDesligamentoDTO utilizado para o relatorio de desligamentos com encontro de contas (lista)
 */
public class DadosDesligamentoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Date dataSaida;
	private String descSituacaoContaCapital;
	private String descDadosDesligamento;
	
	// infos relatorio
	private String nomeCompleto;
	private Integer numContaCapital;
	private BigDecimal valorIntegralizado;
	private BigDecimal valorEmprestimos;
	private BigDecimal valorAmortizado;
	private BigDecimal saldoDevedorEmprestimos;
	private BigDecimal saldoDevolverProgramado;
	private BigDecimal saldoDevolverResidualEmprestimo;
	
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getDescSituacaoContaCapital() {
		return descSituacaoContaCapital;
	}
	public void setDescSituacaoContaCapital(String descSituacaoContaCapital) {
		this.descSituacaoContaCapital = descSituacaoContaCapital;
	}
	public String getDescDadosDesligamento() {
		return descDadosDesligamento;
	}
	public void setDescDadosDesligamento(String descDadosDesligamento) {
		this.descDadosDesligamento = descDadosDesligamento;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	public BigDecimal getValorIntegralizado() {
		return valorIntegralizado;
	}
	public void setValorIntegralizado(BigDecimal valorIntegralizado) {
		this.valorIntegralizado = valorIntegralizado;
	}
	public BigDecimal getValorEmprestimos() {
		return valorEmprestimos;
	}
	public void setValorEmprestimos(BigDecimal valorEmprestimos) {
		this.valorEmprestimos = valorEmprestimos;
	}
	public BigDecimal getValorAmortizado() {
		return valorAmortizado;
	}
	public void setValorAmortizado(BigDecimal valorAmortizado) {
		this.valorAmortizado = valorAmortizado;
	}
	public BigDecimal getSaldoDevedorEmprestimos() {
		return saldoDevedorEmprestimos;
	}
	public void setSaldoDevedorEmprestimos(BigDecimal saldoDevedorEmprestimos) {
		this.saldoDevedorEmprestimos = saldoDevedorEmprestimos;
	}
	public BigDecimal getSaldoDevolverProgramado() {
		return saldoDevolverProgramado;
	}
	public void setSaldoDevolverProgramado(BigDecimal saldoDevolverProgramado) {
		this.saldoDevolverProgramado = saldoDevolverProgramado;
	}
	public BigDecimal getSaldoDevolverResidualEmprestimo() {
		return saldoDevolverResidualEmprestimo;
	}
	public void setSaldoDevolverResidualEmprestimo(BigDecimal saldoDevolverResidualEmprestimo) {
		this.saldoDevolverResidualEmprestimo = saldoDevolverResidualEmprestimo;
	}
	
}
