/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe FonteRendaPessoaIntegracaoDTO.
 */
public class FonteRendaPessoaIntegracaoDTO extends BancoobDto {

	/** O atributo rendaMensal. */
	private BigDecimal rendaMensal;

	
	/**
	 * Recupera o valor de rendaMensal.
	 *
	 * @return o valor de rendaMensal
	 */
	public BigDecimal getRendaMensal() {
		return rendaMensal;
	}

	/**
	 * Define o valor de rendaMensal.
	 *
	 * @param rendaMensal o novo valor de rendaMensal
	 */
	public void setRendaMensal(BigDecimal rendaMensal) {
		this.rendaMensal = rendaMensal;
	}
}
