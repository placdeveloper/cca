/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe SaldoContaCapitalDTO.
 */
public class SaldoContaCapitalDTO extends BancoobDto {
	

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 5931199169022071892L;
	
	/** O atributo numMatricula. */
	private Integer numMatricula;   
	
	/** O atributo numCliente. */
	private Integer numCliente; 	
	
	/** O atributo valorSaldoSubscrito. */
	private BigDecimal valorSaldoSubscrito; 	 
	
	/** O atributo valorSaldoIntegralizado. */
	private BigDecimal valorSaldoIntegralizado;
	
	/** O atributo valorSaldoDevolver. */
	private BigDecimal valorSaldoDevolver;
	
	/** O atributo valorSaldoRealizar. */
	private BigDecimal valorSaldoRealizar;	
	
	/** O atributo valorSaldoBloqueado. */
	private BigDecimal valorSaldoBloqueado;
	
	/**
	 * Recupera o valor de numMatricula.
	 *
	 * @return o valor de numMatricula
	 */
	public Integer getNumMatricula() {
		return numMatricula;
	}
	
	/**
	 * Define o valor de numMatricula.
	 *
	 * @param numMatricula o novo valor de numMatricula
	 */
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}
	
	/**
	 * Recupera o valor de numCliente.
	 *
	 * @return o valor de numCliente
	 */
	public Integer getNumCliente() {
		return numCliente;
	}
	
	/**
	 * Define o valor de numCliente.
	 *
	 * @param numCliente o novo valor de numCliente
	 */
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}
	
	/**
	 * Recupera o valor de valorSaldoSubscrito.
	 *
	 * @return o valor de valorSaldoSubscrito
	 */
	public BigDecimal getValorSaldoSubscrito() {
		return valorSaldoSubscrito;
	}
	
	/**
	 * Define o valor de valorSaldoSubscrito.
	 *
	 * @param valorSaldoSubscrito o novo valor de valorSaldoSubscrito
	 */
	public void setValorSaldoSubscrito(BigDecimal valorSaldoSubscrito) {
		this.valorSaldoSubscrito = valorSaldoSubscrito;
	}
	
	/**
	 * Recupera o valor de valorSaldoIntegralizado.
	 *
	 * @return o valor de valorSaldoIntegralizado
	 */
	public BigDecimal getValorSaldoIntegralizado() {
		return valorSaldoIntegralizado;
	}
	
	/**
	 * Define o valor de valorSaldoIntegralizado.
	 *
	 * @param valorSaldoIntegralizado o novo valor de valorSaldoIntegralizado
	 */
	public void setValorSaldoIntegralizado(BigDecimal valorSaldoIntegralizado) {
		this.valorSaldoIntegralizado = valorSaldoIntegralizado;
	}
	
	/**
	 * Recupera o valor de valorSaldoDevolver.
	 *
	 * @return o valor de valorSaldoDevolver
	 */
	public BigDecimal getValorSaldoDevolver() {
		return valorSaldoDevolver;
	}
	
	/**
	 * Define o valor de valorSaldoDevolver.
	 *
	 * @param valorSaldoDevolver o novo valor de valorSaldoDevolver
	 */
	public void setValorSaldoDevolver(BigDecimal valorSaldoDevolver) {
		this.valorSaldoDevolver = valorSaldoDevolver;
	}
	
	/**
	 * Recupera o valor de valorSaldoRealizar.
	 *
	 * @return o valor de valorSaldoRealizar
	 */
	public BigDecimal getValorSaldoRealizar() {
		return valorSaldoRealizar;
	}
	
	/**
	 * Define o valor de valorSaldoRealizar.
	 *
	 * @param valorSaldoRealizar o novo valor de valorSaldoRealizar
	 */
	public void setValorSaldoRealizar(BigDecimal valorSaldoRealizar) {
		this.valorSaldoRealizar = valorSaldoRealizar;
	}
	
	/**
	 * Recupera o valor de valorSaldoBloqueado.
	 *
	 * @return o valor de valorSaldoBloqueado
	 */
	public BigDecimal getValorSaldoBloqueado() {
		return valorSaldoBloqueado;
	}
	
	/**
	 * Define o valor de valorSaldoBloqueado.
	 *
	 * @param valorSaldoBloqueado o novo valor de valorSaldoBloqueado
	 */
	public void setValorSaldoBloqueado(BigDecimal valorSaldoBloqueado) {
		this.valorSaldoBloqueado = valorSaldoBloqueado;
	}
	
		
	
}
