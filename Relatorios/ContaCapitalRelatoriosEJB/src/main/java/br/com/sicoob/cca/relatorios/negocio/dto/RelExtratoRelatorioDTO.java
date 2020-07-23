/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe RelExtratoRelatorioDTO.
 */
public class RelExtratoRelatorioDTO extends BancoobDto {
	
	/**
	 * DTO que serve de parametro para relatório de extrato
	 */
	private static final long serialVersionUID = 6516628597651715211L;
	
	/** O atributo numMatricula. */
	private Long numMatricula;
	
	/** O atributo dataInicio. */
	private DateTime dataInicio;
	
	/** O atributo dataFim. */
	private DateTime dataFim;
	
	/**
	 * Recupera o valor de numMatricula.
	 *
	 * @return o valor de numMatricula
	 */
	public Long getNumMatricula() {
		return numMatricula;
	}
	
	/**
	 * Define o valor de numMatricula.
	 *
	 * @param numMatricula o novo valor de numMatricula
	 */
	public void setNumMatricula(Long numMatricula) {
		this.numMatricula = numMatricula;
	}
	
	/**
	 * Recupera o valor de dataInicio.
	 *
	 * @return o valor de dataInicio
	 */
	public DateTime getDataInicio() {
		return dataInicio;
	}
	
	/**
	 * Define o valor de dataInicio.
	 *
	 * @param dataInicio o novo valor de dataInicio
	 */
	public void setDataInicio(DateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	/**
	 * Recupera o valor de dataFim.
	 *
	 * @return o valor de dataFim
	 */
	public DateTime getDataFim() {
		return dataFim;
	}
	
	/**
	 * Define o valor de dataFim.
	 *
	 * @param dataFim o novo valor de dataFim
	 */
	public void setDataFim(DateTime dataFim) {
		this.dataFim = dataFim;
	}

}
