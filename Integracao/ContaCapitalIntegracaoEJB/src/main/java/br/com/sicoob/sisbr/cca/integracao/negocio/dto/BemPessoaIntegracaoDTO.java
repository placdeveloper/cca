/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;

/**
 * A Classe BemPessoaIntegracaoDTO.
 */
public class BemPessoaIntegracaoDTO {

	/** O atributo descBemPessoa. */
	private String descBemPessoa;
	
	/** O atributo valorAtualMercado. */
	private BigDecimal valorAtualMercado;
	
	/** O atributo valorTotalBens. */
	private BigDecimal valorTotalBens;

	
	/**
	 * Recupera o valor de descBemPessoa.
	 *
	 * @return o valor de descBemPessoa
	 */
	public String getDescBemPessoa() {
		return descBemPessoa;
	}

	/**
	 * Define o valor de descBemPessoa.
	 *
	 * @param descBemPessoa o novo valor de descBemPessoa
	 */
	public void setDescBemPessoa(String descBemPessoa) {
		this.descBemPessoa = descBemPessoa;
	}

	/**
	 * Recupera o valor de valorAtualMercado.
	 *
	 * @return o valor de valorAtualMercado
	 */
	public BigDecimal getValorAtualMercado() {
		return valorAtualMercado;
	}

	/**
	 * Define o valor de valorAtualMercado.
	 *
	 * @param valorAtualMercado o novo valor de valorAtualMercado
	 */
	public void setValorAtualMercado(BigDecimal valorAtualMercado) {
		this.valorAtualMercado = valorAtualMercado;
	}

	/**
	 * Recupera o valor de valorTotalBens.
	 *
	 * @return o valor de valorTotalBens
	 */
	public BigDecimal getValorTotalBens() {
		return valorTotalBens;
	}

	/**
	 * Define o valor de valorTotalBens.
	 *
	 * @param valorTotalBens o novo valor de valorTotalBens
	 */
	public void setValorTotalBens(BigDecimal valorTotalBens) {
		this.valorTotalBens = valorTotalBens;
	}
}
