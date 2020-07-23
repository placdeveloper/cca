/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe DadosContaCapitalDTO.
 */
public class DadosContaCapitalDTO extends BancoobDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4430009716280809051L;
	
	/** O atributo numMatricula. */
	private Integer numMatricula;
	
	/** O atributo codSituacao. */
	private Integer codSituacao;
	
	/** O atributo dataMatricula. */
	private DateTime dataMatricula;
	
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
	 * Recupera o valor de codSituacao.
	 *
	 * @return o valor de codSituacao
	 */
	public Integer getCodSituacao() {
		return codSituacao;
	}
	
	/**
	 * Define o valor de codSituacao.
	 *
	 * @param codSituacao o novo valor de codSituacao
	 */
	public void setCodSituacao(Integer codSituacao) {
		this.codSituacao = codSituacao;
	}
	
	/**
	 * Recupera o valor de dataMatricula.
	 *
	 * @return o valor de dataMatricula
	 */
	public DateTime getDataMatricula() {
		return dataMatricula;
	}
	
	/**
	 * Define o valor de dataMatricula.
	 *
	 * @param dataMatricula o novo valor de dataMatricula
	 */
	public void setDataMatricula(DateTime dataMatricula) {
		this.dataMatricula = dataMatricula;
	}	
		
}
