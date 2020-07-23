/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO CaptacaoRemuneradaIntegracaoDTO
 */
public class CaptacaoRemuneradaIntegracaoDTO extends BancoobDto {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7791481691569419764L;

	/** O atributo numContaCorrente. */
	private BigDecimal numContaCorrente;
	
	/** O atributo idModalidadeProduto. */
	private Long idModalidadeProduto;
	
	/** O atributo valorAplicacao. */
	private BigDecimal valorAplicacao;	
	
	/** O atributo qtdParcelas. */
	private Integer qtdParcelas;

	public BigDecimal getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(BigDecimal numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	public Long getIdModalidadeProduto() {
		return idModalidadeProduto;
	}

	public void setIdModalidadeProduto(Long idModalidadeProduto) {
		this.idModalidadeProduto = idModalidadeProduto;
	}

	public BigDecimal getValorAplicacao() {
		return valorAplicacao;
	}

	public void setValorAplicacao(BigDecimal valorAplicacao) {
		this.valorAplicacao = valorAplicacao;
	}

	public Integer getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}		
	
	
}
