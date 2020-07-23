package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

public class LancamentoContaCorrenteIntegracaoLoteRetDTO extends BancoobDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer numSeqLanc;
	private Integer codRetorno;
	private String mensagem;
	private String codErroRetorno;
	private DateTime dataLote;
	private BigDecimal valorSaldoAnteriorLanc;
	private String hashIdentificacaoLancamento;
	
	public Integer getNumSeqLanc() {
		return numSeqLanc;
	}
	public void setNumSeqLanc(Integer numSeqLanc) {
		this.numSeqLanc = numSeqLanc;
	}
	public Integer getCodRetorno() {
		return codRetorno;
	}
	public void setCodRetorno(Integer codRetorno) {
		this.codRetorno = codRetorno;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getCodErroRetorno() {
		return codErroRetorno;
	}
	public void setCodErroRetorno(String codErroRetorno) {
		this.codErroRetorno = codErroRetorno;
	}
	public DateTime getDataLote() {
		return dataLote;
	}
	public void setDataLote(DateTime dataLote) {
		this.dataLote = dataLote;
	}
	public BigDecimal getValorSaldoAnteriorLanc() {
		return valorSaldoAnteriorLanc;
	}
	public void setValorSaldoAnteriorLanc(BigDecimal valorSaldoAnteriorLanc) {
		this.valorSaldoAnteriorLanc = valorSaldoAnteriorLanc;
	}
	public String getHashIdentificacaoLancamento() {
		return hashIdentificacaoLancamento;
	}
	public void setHashIdentificacaoLancamento(String hashIdentificacaoLancamento) {
		this.hashIdentificacaoLancamento = hashIdentificacaoLancamento;
	}
	
	
}
