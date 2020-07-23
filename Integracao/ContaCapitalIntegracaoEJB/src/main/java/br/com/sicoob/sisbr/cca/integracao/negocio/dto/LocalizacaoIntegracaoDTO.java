/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe LocalizacaoIntegracaoDTO.
 */
public class LocalizacaoIntegracaoDTO extends BancoobDto {
	
	/** O atributo uf. */
	private String uf;
	
	/** O atributo municipio. */
	private String municipio;
	
	/** O atributo nomeLogradouro. */
	private String nomeLogradouro;
	
	
	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}
	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}
	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}
	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	/**
	 * Recupera o valor de nomeLogradouro.
	 *
	 * @return o valor de nomeLogradouro
	 */
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}
	
	/**
	 * Define o valor de nomeLogradouro.
	 *
	 * @param nomeLogradouro o novo valor de nomeLogradouro
	 */
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}
}
