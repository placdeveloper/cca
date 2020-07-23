package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO para lançamento de estorno do rateio.
 * @author Nairon.Silva
 */
public class LancamentoEstornoRateioDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer idContaCapital;
	private Integer idInstituicao;
	private Integer idSituacaoContaCapital;
	private Integer idTipoHistorico;
	private Integer numContaCapital;
	private BigDecimal valorLanc;
	private String descNumDocumento;
	private String descOperacaoExterna;
	
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
	public BigDecimal getValorLanc() {
		return valorLanc;
	}
	public void setValorLanc(BigDecimal valorLanc) {
		this.valorLanc = valorLanc;
	}
	public String getDescNumDocumento() {
		return descNumDocumento;
	}
	public void setDescNumDocumento(String descNumDocumento) {
		this.descNumDocumento = descNumDocumento;
	}
	public String getDescOperacaoExterna() {
		return descOperacaoExterna;
	}
	public void setDescOperacaoExterna(String descOperacaoExterna) {
		this.descOperacaoExterna = descOperacaoExterna;
	}
	public Integer getIdTipoHistorico() {
		return idTipoHistorico;
	}
	public void setIdTipoHistorico(Integer idTipoHistorico) {
		this.idTipoHistorico = idTipoHistorico;
	}
	
}
