/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;

/**
 * A Classe PessoaJuridicaIntegracaoDTO.
 */
public class PessoaJuridicaIntegracaoDTO {

	/** O atributo razaoSocialEmpresa. */
	private String razaoSocialEmpresa;
	
	/** O atributo cnpjEmpresa. */
	private String cnpjEmpresa;
	
	/** O atributo nomeFantasia. */
	private String nomeFantasia;
	
	/** O atributo inscricaoEstadual. */
	private String inscricaoEstadual;
	
	/** O atributo capitalSocial. */
	private BigDecimal capitalSocial;
	
	
	/**
	 * @return the razaoSocialEmpresa
	 */
	public String getRazaoSocialEmpresa() {
		return razaoSocialEmpresa;
	}
	/**
	 * @param razaoSocialEmpresa the razaoSocialEmpresa to set
	 */
	public void setRazaoSocialEmpresa(String razaoSocialEmpresa) {
		this.razaoSocialEmpresa = razaoSocialEmpresa;
	}
	/**
	 * @return the cnpjEmpresa
	 */
	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}
	/**
	 * @param cnpjEmpresa the cnpjEmpresa to set
	 */
	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}
	/**
	 * @return the nomeFantasia
	 */
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	/**
	 * @param nomeFantasia the nomeFantasia to set
	 */
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	/**
	 * @return the inscricaoEstadual
	 */
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	/**
	 * @param inscricaoEstadual the inscricaoEstadual to set
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	
	/**
	 * Recupera o valor de capitalSocial.
	 *
	 * @return o valor de capitalSocial
	 */
	public BigDecimal getCapitalSocial() {
		return capitalSocial;
	}
	
	/**
	 * Define o valor de capitalSocial.
	 *
	 * @param capitalSocial o novo valor de capitalSocial
	 */
	public void setCapitalSocial(BigDecimal capitalSocial) {
		this.capitalSocial = capitalSocial;
	}
}
