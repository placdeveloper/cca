package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

public class LancamentoContaCorrenteIntegracaoLoteDTO extends BancoobDto {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dataLancamento;
	private Integer numLoteLanc;
	private String descNumDocumento;
	private Long numContaCorrente;
	private BigDecimal valorLanc;
	private Integer idTipoHistoricoLanc;
	private Integer idTipoHistoricoEstorno;
	private Boolean bolVerificaSaldo;
	private String descInfComplementar;
	private String hashIdentificacaoLancamento;
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public Integer getNumLoteLanc() {
		return numLoteLanc;
	}
	public void setNumLoteLanc(Integer numLoteLanc) {
		this.numLoteLanc = numLoteLanc;
	}
	public String getDescNumDocumento() {
		return descNumDocumento;
	}
	public void setDescNumDocumento(String descNumDocumento) {
		this.descNumDocumento = descNumDocumento;
	}
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
	public BigDecimal getValorLanc() {
		return valorLanc;
	}
	public void setValorLanc(BigDecimal valorLanc) {
		this.valorLanc = valorLanc;
	}
	public Integer getIdTipoHistoricoLanc() {
		return idTipoHistoricoLanc;
	}
	public void setIdTipoHistoricoLanc(Integer idTipoHistoricoLanc) {
		this.idTipoHistoricoLanc = idTipoHistoricoLanc;
	}
	public Integer getIdTipoHistoricoEstorno() {
		return idTipoHistoricoEstorno;
	}
	public void setIdTipoHistoricoEstorno(Integer idTipoHistoricoEstorno) {
		this.idTipoHistoricoEstorno = idTipoHistoricoEstorno;
	}
	public Boolean getBolVerificaSaldo() {
		return bolVerificaSaldo;
	}
	public void setBolVerificaSaldo(Boolean bolVerificaSaldo) {
		this.bolVerificaSaldo = bolVerificaSaldo;
	}
	public String getDescInfComplementar() {
		return descInfComplementar;
	}
	public void setDescInfComplementar(String descInfComplementar) {
		this.descInfComplementar = descInfComplementar;
	}
	public String getHashIdentificacaoLancamento() {
		return hashIdentificacaoLancamento;
	}
	public void setHashIdentificacaoLancamento(String hashIdentificacaoLancamento) {
		this.hashIdentificacaoLancamento = hashIdentificacaoLancamento;
	}
	
	
}
