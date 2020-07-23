/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe ConciliacaoContabilLegadoDTO.
 */
public final class ConciliacaoContabilLegadoDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2646772164398891484L;
	
	/** O atributo numContaBACEN. */
	private String numContaBACEN;	
	
	/** O atributo saldoTotal. */
	private BigDecimal saldoTotal;
	
	/** O atributo dataContabilizacao. */
	private DateTime dataContabilizacao;
	
	/**
	 * Recupera o valor de dataContabilizacao.
	 *
	 * @return o valor de dataContabilizacao
	 */
	public DateTime getDataContabilizacao() {
		return dataContabilizacao;
	}
	
	/**
	 * Define o valor de dataContabilizacao.
	 *
	 * @param dataContabilizacao o novo valor de dataContabilizacao
	 */
	public void setDataContabilizacao(DateTime dataContabilizacao) {
		this.dataContabilizacao = dataContabilizacao;
	}
	
	/**
	 * Recupera o valor de numContaBACEN.
	 *
	 * @return o valor de numContaBACEN
	 */
	public String getNumContaBACEN() {
		return numContaBACEN;
	}
	
	/**
	 * Define o valor de numContaBACEN.
	 *
	 * @param numContaBACEN o novo valor de numContaBACEN
	 */
	public void setNumContaBACEN(String numContaBACEN) {
		this.numContaBACEN = numContaBACEN;
	}
	
	/**
	 * Recupera o valor de saldoTotal.
	 *
	 * @return o valor de saldoTotal
	 */
	public BigDecimal getSaldoTotal() {
		return saldoTotal;
	}
	
	/**
	 * Define o valor de saldoTotal.
	 *
	 * @param saldoTotal o novo valor de saldoTotal
	 */
	public void setSaldoTotal(BigDecimal saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
}