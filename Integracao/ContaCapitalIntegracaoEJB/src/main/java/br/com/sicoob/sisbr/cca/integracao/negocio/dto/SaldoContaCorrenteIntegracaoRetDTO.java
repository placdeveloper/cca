/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe SaldoContaCorrenteIntegracaoRetDTO.
 */
public class SaldoContaCorrenteIntegracaoRetDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 9151764851771144856L;
	
	/** O atributo valorSaldoDispAtual. */
	private BigDecimal valorSaldoDispAtual;
	
	/** O atributo valorLimite. */
	private BigDecimal valorLimite;
	
	/** O atributo valorLimiteUtilizado. */
	private BigDecimal valorLimiteUtilizado;
	
	/** O atributo valorSaldoBloqAtual. */
	private BigDecimal valorSaldoBloqAtual;
	
	/** O atributo valorSaldoBloqJudicialAtual. */
	private BigDecimal valorSaldoBloqJudicialAtual;
	
	/** O atributo valorSaldoDispAtualSemProvisionamento. */
	private BigDecimal valorSaldoDispAtualSemProvisionamento;
	
	/** O atributo saldoAplicacaoResgate. */
	private BigDecimal saldoAplicacaoResgate;
	
	/** O atributo valorSaldoChequeEspecial. */
	private BigDecimal valorSaldoChequeEspecial;
	
	/** O atributo valorSaldoRespIndireta. */
	private BigDecimal valorSaldoRespIndireta;
	
	/**
	 * Recupera o valor de valorSaldoDispAtual.
	 *
	 * @return o valor de valorSaldoDispAtual
	 */
	public BigDecimal getValorSaldoDispAtual() {
		return valorSaldoDispAtual;
	}
	
	/**
	 * Define o valor de valorSaldoDispAtual.
	 *
	 * @param valorSaldoDispAtual o novo valor de valorSaldoDispAtual
	 */
	public void setValorSaldoDispAtual(BigDecimal valorSaldoDispAtual) {
		this.valorSaldoDispAtual = valorSaldoDispAtual;
	}
	
	/**
	 * Recupera o valor de valorLimite.
	 *
	 * @return o valor de valorLimite
	 */
	public BigDecimal getValorLimite() {
		return valorLimite;
	}
	
	/**
	 * Define o valor de valorLimite.
	 *
	 * @param valorLimite o novo valor de valorLimite
	 */
	public void setValorLimite(BigDecimal valorLimite) {
		this.valorLimite = valorLimite;
	}
	
	/**
	 * Recupera o valor de valorSaldoBloqAtual.
	 *
	 * @return o valor de valorSaldoBloqAtual
	 */
	public BigDecimal getValorSaldoBloqAtual() {
		return valorSaldoBloqAtual;
	}
	
	/**
	 * Define o valor de valorSaldoBloqAtual.
	 *
	 * @param valorSaldoBloqAtual o novo valor de valorSaldoBloqAtual
	 */
	public void setValorSaldoBloqAtual(BigDecimal valorSaldoBloqAtual) {
		this.valorSaldoBloqAtual = valorSaldoBloqAtual;
	}
	
	/**
	 * Recupera o valor de valorSaldoBloqJudicialAtual.
	 *
	 * @return o valor de valorSaldoBloqJudicialAtual
	 */
	public BigDecimal getValorSaldoBloqJudicialAtual() {
		return valorSaldoBloqJudicialAtual;
	}
	
	/**
	 * Define o valor de valorSaldoBloqJudicialAtual.
	 *
	 * @param valorSaldoBloqJudicialAtual o novo valor de valorSaldoBloqJudicialAtual
	 */
	public void setValorSaldoBloqJudicialAtual(
			BigDecimal valorSaldoBloqJudicialAtual) {
		this.valorSaldoBloqJudicialAtual = valorSaldoBloqJudicialAtual;
	}
	
	/**
	 * Recupera o valor de valorSaldoDispAtualSemProvisionamento.
	 *
	 * @return o valor de valorSaldoDispAtualSemProvisionamento
	 */
	public BigDecimal getValorSaldoDispAtualSemProvisionamento() {
		return valorSaldoDispAtualSemProvisionamento;
	}
	
	/**
	 * Define o valor de valorSaldoDispAtualSemProvisionamento.
	 *
	 * @param valorSaldoDispAtualSemProvisionamento o novo valor de valorSaldoDispAtualSemProvisionamento
	 */
	public void setValorSaldoDispAtualSemProvisionamento(
			BigDecimal valorSaldoDispAtualSemProvisionamento) {
		this.valorSaldoDispAtualSemProvisionamento = valorSaldoDispAtualSemProvisionamento;
	}
	
	/**
	 * Recupera o valor de saldoAplicacaoResgate.
	 *
	 * @return o valor de saldoAplicacaoResgate
	 */
	public BigDecimal getSaldoAplicacaoResgate() {
		return saldoAplicacaoResgate;
	}
	
	/**
	 * Define o valor de saldoAplicacaoResgate.
	 *
	 * @param saldoAplicacaoResgate o novo valor de saldoAplicacaoResgate
	 */
	public void setSaldoAplicacaoResgate(BigDecimal saldoAplicacaoResgate) {
		this.saldoAplicacaoResgate = saldoAplicacaoResgate;
	}
	
	/**
	 * Recupera o valor de valorSaldoChequeEspecial.
	 *
	 * @return o valor de valorSaldoChequeEspecial
	 */
	public BigDecimal getValorSaldoChequeEspecial() {
		return valorSaldoChequeEspecial;
	}
	
	/**
	 * Define o valor de valorSaldoChequeEspecial.
	 *
	 * @param valorSaldoChequeEspecial o novo valor de valorSaldoChequeEspecial
	 */
	public void setValorSaldoChequeEspecial(BigDecimal valorSaldoChequeEspecial) {
		this.valorSaldoChequeEspecial = valorSaldoChequeEspecial;
	}
	
	/**
	 * Recupera o valor de valorLimiteUtilizado.
	 *
	 * @return o valor de valorLimiteUtilizado
	 */
	public BigDecimal getValorLimiteUtilizado() {
		return valorLimiteUtilizado;
	}
	
	/**
	 * Define o valor de valorLimiteUtilizado.
	 *
	 * @param valorLimiteUtilizado o novo valor de valorLimiteUtilizado
	 */
	public void setValorLimiteUtilizado(BigDecimal valorLimiteUtilizado) {
		this.valorLimiteUtilizado = valorLimiteUtilizado;
	}
	
	/**
	 * Recupera o valor de valorSaldoRespIndireta.
	 *
	 * @return o valor de valorSaldoRespIndireta
	 */
	public BigDecimal getValorSaldoRespIndireta() {
		return valorSaldoRespIndireta;
	}
	
	/**
	 * Define o valor de valorSaldoRespIndireta.
	 *
	 * @param valorSaldoRespIndireta o novo valor de valorSaldoRespIndireta
	 */
	public void setValorSaldoRespIndireta(BigDecimal valorSaldoRespIndireta) {
		this.valorSaldoRespIndireta = valorSaldoRespIndireta;
	}
	

}
