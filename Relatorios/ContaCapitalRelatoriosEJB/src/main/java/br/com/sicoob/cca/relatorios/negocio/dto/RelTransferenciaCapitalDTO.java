/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO RelTransferenciaCapitalDTO
 */
public class RelTransferenciaCapitalDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3581447824270212915L;

	/** O atributo idPessoaDebito. */
	private Integer idPessoaDebito;
	
	/** O atributo idContaCapitalDebito. */
	private Integer idContaCapitalDebito;
	
	/** O atributo idInstituicaoDebito. */
	private Integer idInstituicaoDebito;
	
	/** O atributo numContaCapitalDebito. */
	private Integer numContaCapitalDebito;

	/** O atributo idPessoaCredito. */
	private Integer idPessoaCredito;
	
	/** O atributo idInstituicaoCredito. */
	private Integer idInstituicaoCredito;
	
	/** O atributo numContaCapitalCredito. */
	private Integer numContaCapitalCredito;		
	
	/** O atributo idContaCapitalCredito. */
	private Integer idContaCapitalCredito;
	
	/** O atributo vlrTransferir. */
	private BigDecimal vlrTransferir;

	public Integer getIdPessoaDebito() {
		return idPessoaDebito;
	}

	public void setIdPessoaDebito(Integer idPessoaDebito) {
		this.idPessoaDebito = idPessoaDebito;
	}

	public Integer getIdContaCapitalDebito() {
		return idContaCapitalDebito;
	}

	public void setIdContaCapitalDebito(Integer idContaCapitalDebito) {
		this.idContaCapitalDebito = idContaCapitalDebito;
	}

	public Integer getIdInstituicaoDebito() {
		return idInstituicaoDebito;
	}

	public void setIdInstituicaoDebito(Integer idInstituicaoDebito) {
		this.idInstituicaoDebito = idInstituicaoDebito;
	}

	public Integer getNumContaCapitalDebito() {
		return numContaCapitalDebito;
	}

	public void setNumContaCapitalDebito(Integer numContaCapitalDebito) {
		this.numContaCapitalDebito = numContaCapitalDebito;
	}

	public Integer getIdPessoaCredito() {
		return idPessoaCredito;
	}

	public void setIdPessoaCredito(Integer idPessoaCredito) {
		this.idPessoaCredito = idPessoaCredito;
	}

	public Integer getIdInstituicaoCredito() {
		return idInstituicaoCredito;
	}

	public void setIdInstituicaoCredito(Integer idInstituicaoCredito) {
		this.idInstituicaoCredito = idInstituicaoCredito;
	}

	public Integer getNumContaCapitalCredito() {
		return numContaCapitalCredito;
	}

	public void setNumContaCapitalCredito(Integer numContaCapitalCredito) {
		this.numContaCapitalCredito = numContaCapitalCredito;
	}

	public Integer getIdContaCapitalCredito() {
		return idContaCapitalCredito;
	}

	public void setIdContaCapitalCredito(Integer idContaCapitalCredito) {
		this.idContaCapitalCredito = idContaCapitalCredito;
	}

	public BigDecimal getVlrTransferir() {
		return vlrTransferir;
	}

	public void setVlrTransferir(BigDecimal vlrTransferir) {
		this.vlrTransferir = vlrTransferir;
	}


	
}
