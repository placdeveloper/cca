package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContratoLiquidacaoDTO;

/**
 * DesligarContaCapitalDTO
 */
public class DesligarContaCapitalDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer idContaCapital;
	private BigDecimal valorIntegralizado;
	private Integer idTipoDesligamento;
	private BigDecimal valorEmprestimos;
	private List<ContratoLiquidacaoDTO> contratosAbertos;
	private List<ContratoLiquidacaoDTO> contratosLiquidar;
	
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}
	public BigDecimal getValorIntegralizado() {
		return valorIntegralizado;
	}
	public void setValorIntegralizado(BigDecimal valorIntegralizado) {
		this.valorIntegralizado = valorIntegralizado;
	}
	public Integer getIdTipoDesligamento() {
		return idTipoDesligamento;
	}
	public void setIdTipoDesligamento(Integer idTipoDesligamento) {
		this.idTipoDesligamento = idTipoDesligamento;
	}
	public BigDecimal getValorEmprestimos() {
		return valorEmprestimos;
	}
	public void setValorEmprestimos(BigDecimal valorEmprestimos) {
		this.valorEmprestimos = valorEmprestimos;
	}
	@Override
	public String toString() {
		return "DesligarContaCapitalDTO [idContaCapital=" + idContaCapital + ", valorIntegralizado="
				+ valorIntegralizado + ", idTipoDesligamento=" + idTipoDesligamento + ", valorEmprestimos="
				+ valorEmprestimos + "]";
	}
	public List<ContratoLiquidacaoDTO> getContratosAbertos() {
		return contratosAbertos;
	}
	public void setContratosAbertos(List<ContratoLiquidacaoDTO> contratosAbertos) {
		this.contratosAbertos = contratosAbertos;
	}
	public List<ContratoLiquidacaoDTO> getContratosLiquidar() {
		return contratosLiquidar;
	}
	public void setContratosLiquidar(List<ContratoLiquidacaoDTO> contratosLiquidar) {
		this.contratosLiquidar = contratosLiquidar;
	}

}
