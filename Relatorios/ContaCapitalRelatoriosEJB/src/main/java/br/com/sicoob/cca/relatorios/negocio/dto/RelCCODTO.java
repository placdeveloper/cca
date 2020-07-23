/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO RelCCODTO
 */
public class RelCCODTO extends BancoobDto {

	private Long numeroContaCorrente;
	
	private String titularidade;

	public Long getNumeroContaCorrente() {
		return numeroContaCorrente;
	}

	public void setNumeroContaCorrente(Long numeroContaCorrente) {
		this.numeroContaCorrente = numeroContaCorrente;
	}

	public String getTitularidade() {
		return titularidade;
	}

	public void setTitularidade(String titularidade) {
		this.titularidade = titularidade;
	}
	
}
