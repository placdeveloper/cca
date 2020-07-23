/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

/**
 * A Classe RelacionamentoPessoaIntegracaoDTO.
 */
public class RelacionamentoPessoaIntegracaoDTO {

	/** O atributo nomeConjuge. */
	private String nomeConjuge;
	
	/** O atributo cpfConjuge. */
	private String cpfConjuge;
	
	/** O atributo nomeRepresentanteLegal. */
	private String nomeRepresentanteLegal;
	
	/** O atributo tipoRepresentanteLegal. */
	private String tipoRepresentanteLegal;
	
	/** O atributo cpfRepresentanteLegal. */
	private String cpfRepresentanteLegal;
	
	/** O atributo nomeResponsavelLegal. */
	private String nomeResponsavelLegal;
	
	/** O atributo cpfResponsavelLegal. */
	private String cpfResponsavelLegal;
	
	
	/**
	 * @return the nomeConjuge
	 */
	public String getNomeConjuge() {
		return nomeConjuge;
	}
	/**
	 * @param nomeConjuge the nomeConjuge to set
	 */
	public void setNomeConjuge(String nomeConjuge) {
		this.nomeConjuge = nomeConjuge;
	}
	/**
	 * @return the nomeRepresentanteLegal
	 */
	public String getNomeRepresentanteLegal() {
		return nomeRepresentanteLegal;
	}
	/**
	 * @param nomeRepresentanteLegal the nomeRepresentanteLegal to set
	 */
	public void setNomeRepresentanteLegal(String nomeRepresentanteLegal) {
		this.nomeRepresentanteLegal = nomeRepresentanteLegal;
	}
	/**
	 * @return the tipoRepresentanteLegal
	 */
	public String getTipoRepresentanteLegal() {
		return tipoRepresentanteLegal;
	}
	/**
	 * @param tipoRepresentanteLegal the tipoRepresentanteLegal to set
	 */
	public void setTipoRepresentanteLegal(String tipoRepresentanteLegal) {
		this.tipoRepresentanteLegal = tipoRepresentanteLegal;
	}
	/**
	 * @return the cpfRepresentanteLegal
	 */
	public String getCpfRepresentanteLegal() {
		return cpfRepresentanteLegal;
	}
	/**
	 * @param cpfRepresentanteLegal the cpfRepresentanteLegal to set
	 */
	public void setCpfRepresentanteLegal(String cpfRepresentanteLegal) {
		this.cpfRepresentanteLegal = cpfRepresentanteLegal;
	}
	
	/**
	 * Recupera o valor de cpfConjuge.
	 *
	 * @return o valor de cpfConjuge
	 */
	public String getCpfConjuge() {
		return cpfConjuge;
	}
	
	/**
	 * Define o valor de cpfConjuge.
	 *
	 * @param cpfConjuge o novo valor de cpfConjuge
	 */
	public void setCpfConjuge(String cpfConjuge) {
		this.cpfConjuge = cpfConjuge;
	}
	
	/**
	 * Recupera o valor de nomeResponsavelLegal.
	 *
	 * @return o valor de nomeResponsavelLegal
	 */
	public String getNomeResponsavelLegal() {
		return nomeResponsavelLegal;
	}
	
	/**
	 * Define o valor de nomeResponsavelLegal.
	 *
	 * @param nomeResponsavelLegal o novo valor de nomeResponsavelLegal
	 */
	public void setNomeResponsavelLegal(String nomeResponsavelLegal) {
		this.nomeResponsavelLegal = nomeResponsavelLegal;
	}
	
	/**
	 * Recupera o valor de cpfResponsavelLegal.
	 *
	 * @return o valor de cpfResponsavelLegal
	 */
	public String getCpfResponsavelLegal() {
		return cpfResponsavelLegal;
	}
	
	/**
	 * Define o valor de cpfResponsavelLegal.
	 *
	 * @param cpfResponsavelLegal o novo valor de cpfResponsavelLegal
	 */
	public void setCpfResponsavelLegal(String cpfResponsavelLegal) {
		this.cpfResponsavelLegal = cpfResponsavelLegal;
	}
}
