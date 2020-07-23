/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe TelefonePessoaIntegracaoDTO.
 */
public class TelefonePessoaIntegracaoDTO extends BancoobDto {

	/** O atributo telefoneEnderecoResidencial. */
	private String telefoneEnderecoResidencial;
	
	/** O atributo ramalEnderecoResidencial. */
	private String ramalEnderecoResidencial;
	
	/** O atributo telefoneEnderecoComercial. */
	private String telefoneEnderecoComercial;
	
	/** O atributo ramalEnderecoComercial. */
	private String ramalEnderecoComercial;
	
	private String outroTelefone;
	
	/**
	 * @return the telefoneEnderecoResidencial
	 */
	public String getTelefoneEnderecoResidencial() {
		return telefoneEnderecoResidencial;
	}
	/**
	 * @param telefoneEnderecoResidencial the telefoneEnderecoResidencial to set
	 */
	public void setTelefoneEnderecoResidencial(String telefoneEnderecoResidencial) {
		this.telefoneEnderecoResidencial = telefoneEnderecoResidencial;
	}
	/**
	 * @return the ramalEnderecoResidencial
	 */
	public String getRamalEnderecoResidencial() {
		return ramalEnderecoResidencial;
	}
	/**
	 * @param ramalEnderecoResidencial the ramalEnderecoResidencial to set
	 */
	public void setRamalEnderecoResidencial(String ramalEnderecoResidencial) {
		this.ramalEnderecoResidencial = ramalEnderecoResidencial;
	}
	/**
	 * @return the telefoneEnderecoComercial
	 */
	public String getTelefoneEnderecoComercial() {
		return telefoneEnderecoComercial;
	}
	/**
	 * @param telefoneEnderecoComercial the telefoneEnderecoComercial to set
	 */
	public void setTelefoneEnderecoComercial(String telefoneEnderecoComercial) {
		this.telefoneEnderecoComercial = telefoneEnderecoComercial;
	}
	/**
	 * @return the ramalEnderecoComercial
	 */
	public String getRamalEnderecoComercial() {
		return ramalEnderecoComercial;
	}
	/**
	 * @param ramalEnderecoComercial the ramalEnderecoComercial to set
	 */
	public void setRamalEnderecoComercial(String ramalEnderecoComercial) {
		this.ramalEnderecoComercial = ramalEnderecoComercial;
	}
	public String getOutroTelefone() {
		return outroTelefone;
	}
	public void setOutroTelefone(String outroTelefone) {
		this.outroTelefone = outroTelefone;
	}
}
