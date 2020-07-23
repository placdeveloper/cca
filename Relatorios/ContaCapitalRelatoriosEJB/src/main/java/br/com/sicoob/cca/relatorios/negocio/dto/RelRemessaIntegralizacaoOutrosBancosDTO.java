package br.com.sicoob.cca.relatorios.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

public class RelRemessaIntegralizacaoOutrosBancosDTO extends BancoobDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sequencialArquivo;
	private String nomeArquivo;
	private Integer numBanco;
	private Integer numAgencia;
	private String descBanco;
	private Integer qtdRegistros;
	private DateTime dataGeracao;
	private BigDecimal valorTotal;
	private Integer numAgenciaFavorecido;
	private Integer numContaFavorecido;
	private Integer idNegocio;
	private BigDecimal valorLiquido;
	private Integer idInstituicao;		
	private Integer codSituacaoArquivo;
	
	public Integer getNumAgenciaFavorecido() {
		return numAgenciaFavorecido;
	}
	public void setNumAgenciaFavorecido(Integer numAgenciaFavorecido) {
		this.numAgenciaFavorecido = numAgenciaFavorecido;
	}
	public Integer getNumContaFavorecido() {
		return numContaFavorecido;
	}
	public void setNumContaFavorecido(Integer numContaFavorecido) {
		this.numContaFavorecido = numContaFavorecido;
	}
	public Integer getIdNegocio() {
		return idNegocio;
	}
	public void setIdNegocio(Integer idNegocio) {
		this.idNegocio = idNegocio;
	}
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	public Integer getSequencialArquivo() {
		return sequencialArquivo;
	}
	public void setSequencialArquivo(Integer sequencialArquivo) {
		this.sequencialArquivo = sequencialArquivo;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public Integer getNumBanco() {
		return numBanco;
	}
	public void setNumBanco(Integer numBanco) {
		this.numBanco = numBanco;
	}
	public Integer getNumAgencia() {
		return numAgencia;
	}
	public void setNumAgencia(Integer numAgencia) {
		this.numAgencia = numAgencia;
	}
	public String getDescBanco() {
		return descBanco;
	}
	public void setDescBanco(String descBanco) {
		this.descBanco = descBanco;
	}
	public Integer getQtdRegistros() {
		return qtdRegistros;
	}
	public void setQtdRegistros(Integer qtdRegistros) {
		this.qtdRegistros = qtdRegistros;
	}
	public DateTime getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(DateTime dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getCodSituacaoArquivo() {
		return codSituacaoArquivo;
	}
	public void setCodSituacaoArquivo(Integer codSituacaoArquivo) {
		this.codSituacaoArquivo = codSituacaoArquivo;
	}

	public static final class Builder {
		
		private final RelRemessaIntegralizacaoOutrosBancosDTO dto;
		
		public Builder() {
			dto = new RelRemessaIntegralizacaoOutrosBancosDTO();
		}
		
		public Builder setNumBanco(Integer numBanco) {
			dto.setNumBanco(numBanco);
			return this;
		}
		
		public Builder setDataGeracao(DateTime dataGeracao) {
			dto.setDataGeracao(dataGeracao);
			return this;
		}
		
		public RelRemessaIntegralizacaoOutrosBancosDTO build() {
			return dto;
		}
		
	}	
	
}
