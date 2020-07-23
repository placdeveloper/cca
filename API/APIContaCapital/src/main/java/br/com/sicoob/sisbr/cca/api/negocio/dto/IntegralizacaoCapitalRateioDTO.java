package br.com.sicoob.sisbr.cca.api.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO de entrada da integralizacao do rateio
 * @author Nairon.Silva
 */
public class IntegralizacaoCapitalRateioDTO extends BancoobDto {

	private static final long serialVersionUID = 3138773081589955756L;

	private Integer idContaCapital;
	private Integer idInstituicao;
	private BigDecimal valorLancamento;
	private String idUsuario;
	private Date dataCalculo;
	
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
	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}
	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getDataCalculo() {
		return dataCalculo;
	}
	public void setDataCalculo(Date dataCalculo) {
		this.dataCalculo = dataCalculo;
	}
	
	@Override
	public String toString() {
		return "IntegralizacaoCapitalRateioDTO [idContaCapital=" + idContaCapital + ", idInstituicao=" + idInstituicao
				+ ", valorLancamento=" + valorLancamento + ", idUsuario=" + idUsuario + ", dataCalculo=" + dataCalculo
				+ "]";
	}
	
}
