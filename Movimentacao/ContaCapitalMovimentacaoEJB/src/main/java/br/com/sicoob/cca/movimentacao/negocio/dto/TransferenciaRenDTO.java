/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

// TODO: Auto-generated Javadoc
/**
 * A Classe TransferenciaRenDTO.
 *
 * @author Antonio.Genaro
 */
public class TransferenciaRenDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 5471004902362844269L;

	/** O atributo idContaCapitalDebito. */
	private Integer idContaCapitalDebito;
	
	/** O atributo idInstituicaoDebito. */
	private Integer idInstituicaoDebito;
	
	/** O atributo numContaCapitalDebito. */
	private Integer numContaCapitalDebito;

	/** O atributo idContaCapitalCredito. */
	private Integer idContaCapitalCredito;
	
	/** O atributo idInstituicaoCredito. */
	private Integer idInstituicaoCredito;
	
	/** O atributo numContaCapitalCredito. */
	private Integer numContaCapitalCredito;		
	
	/** O atributo vlrTransferir. */
	private BigDecimal vlrTransferir;

	/**
	 * Recupera o valor de idContaCapitalDebito.
	 *
	 * @return o valor de idContaCapitalDebito
	 */
	public Integer getIdContaCapitalDebito() {
		return idContaCapitalDebito;
	}

	/**
	 * Define o valor de idContaCapitalDebito.
	 *
	 * @param idContaCapitalDebito o novo valor de idContaCapitalDebito
	 */
	public void setIdContaCapitalDebito(Integer idContaCapitalDebito) {
		this.idContaCapitalDebito = idContaCapitalDebito;
	}

	/**
	 * Recupera o valor de idInstituicaoDebito.
	 *
	 * @return o valor de idInstituicaoDebito
	 */
	public Integer getIdInstituicaoDebito() {
		return idInstituicaoDebito;
	}

	/**
	 * Define o valor de idInstituicaoDebito.
	 *
	 * @param idInstituicaoDebito o novo valor de idInstituicaoDebito
	 */
	public void setIdInstituicaoDebito(Integer idInstituicaoDebito) {
		this.idInstituicaoDebito = idInstituicaoDebito;
	}

	/**
	 * Recupera o valor de numContaCapitalDebito.
	 *
	 * @return o valor de numContaCapitalDebito
	 */
	public Integer getNumContaCapitalDebito() {
		return numContaCapitalDebito;
	}

	/**
	 * Define o valor de numContaCapitalDebito.
	 *
	 * @param numContaCapitalDebito o novo valor de numContaCapitalDebito
	 */
	public void setNumContaCapitalDebito(Integer numContaCapitalDebito) {
		this.numContaCapitalDebito = numContaCapitalDebito;
	}

	/**
	 * Recupera o valor de idContaCapitalCredito.
	 *
	 * @return o valor de idContaCapitalCredito
	 */
	public Integer getIdContaCapitalCredito() {
		return idContaCapitalCredito;
	}

	/**
	 * Define o valor de idContaCapitalCredito.
	 *
	 * @param idContaCapitalCredito o novo valor de idContaCapitalCredito
	 */
	public void setIdContaCapitalCredito(Integer idContaCapitalCredito) {
		this.idContaCapitalCredito = idContaCapitalCredito;
	}

	/**
	 * Recupera o valor de idInstituicaoCredito.
	 *
	 * @return o valor de idInstituicaoCredito
	 */
	public Integer getIdInstituicaoCredito() {
		return idInstituicaoCredito;
	}

	/**
	 * Define o valor de idInstituicaoCredito.
	 *
	 * @param idInstituicaoCredito o novo valor de idInstituicaoCredito
	 */
	public void setIdInstituicaoCredito(Integer idInstituicaoCredito) {
		this.idInstituicaoCredito = idInstituicaoCredito;
	}

	/**
	 * Recupera o valor de numContaCapitalCredito.
	 *
	 * @return o valor de numContaCapitalCredito
	 */
	public Integer getNumContaCapitalCredito() {
		return numContaCapitalCredito;
	}

	/**
	 * Define o valor de numContaCapitalCredito.
	 *
	 * @param numContaCapitalCredito o novo valor de numContaCapitalCredito
	 */
	public void setNumContaCapitalCredito(Integer numContaCapitalCredito) {
		this.numContaCapitalCredito = numContaCapitalCredito;
	}

	/**
	 * Recupera o valor de vlrTransferir.
	 *
	 * @return o valor de vlrTransferir
	 */
	public BigDecimal getVlrTransferir() {
		return vlrTransferir;
	}

	/**
	 * Define o valor de vlrTransferir.
	 *
	 * @param vlrTransferir o novo valor de vlrTransferir
	 */
	public void setVlrTransferir(BigDecimal vlrTransferir) {
		this.vlrTransferir = vlrTransferir;
	}	
	
	
}
