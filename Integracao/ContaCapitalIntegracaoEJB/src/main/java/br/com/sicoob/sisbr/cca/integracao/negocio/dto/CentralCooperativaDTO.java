/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe CentralCooperativaDTO.
 */
public class CentralCooperativaDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6835689304298204310L;
	
	/**
	 * Valores numericos representativos do Legado SQL (numCentral,numCooperativa)
	 */
	private Short numCentral ;
	
	/** O atributo numCooperativa. */
	private Short numCooperativa ;
	
	/** O atributo descCooperativa. */
	private String descCooperativa ;
	
	/** O atributo descCentral. */
	private String descCentral ;	
	
	/** O atributo idInstituicaoCentral. */
	private Integer idInstituicaoCentral;
	
	/** O atributo idInstituicaoCooperativa. */
	private Integer idInstituicaoCooperativa;

	/**
	 * Recupera o valor de idInstituicaoCentral.
	 *
	 * @return o valor de idInstituicaoCentral
	 */
	public Integer getIdInstituicaoCentral() {
		return idInstituicaoCentral;
	}
	
	/**
	 * Define o valor de idInstituicaoCentral.
	 *
	 * @param idInstituicaoCentral o novo valor de idInstituicaoCentral
	 */
	public void setIdInstituicaoCentral(Integer idInstituicaoCentral) {
		this.idInstituicaoCentral = idInstituicaoCentral;
	}
	
	/**
	 * Recupera o valor de idInstituicaoCooperativa.
	 *
	 * @return o valor de idInstituicaoCooperativa
	 */
	public Integer getIdInstituicaoCooperativa() {
		return idInstituicaoCooperativa;
	}
	
	/**
	 * Define o valor de idInstituicaoCooperativa.
	 *
	 * @param idInstituicaoCooperativa o novo valor de idInstituicaoCooperativa
	 */
	public void setIdInstituicaoCooperativa(Integer idInstituicaoCooperativa) {
		this.idInstituicaoCooperativa = idInstituicaoCooperativa;
	}
	
	/**
	 * Recupera o valor de numCentral.
	 *
	 * @return o valor de numCentral
	 */
	public Short getNumCentral() {
		return numCentral;
	}
	
	/**
	 * Define o valor de numCentral.
	 *
	 * @param numCentral o novo valor de numCentral
	 */
	public void setNumCentral(Short numCentral) {
		this.numCentral = numCentral;
	}
	
	/**
	 * Recupera o valor de numCooperativa.
	 *
	 * @return o valor de numCooperativa
	 */
	public Short getNumCooperativa() {
		return numCooperativa;
	}
	
	/**
	 * Define o valor de numCooperativa.
	 *
	 * @param numCooperativa o novo valor de numCooperativa
	 */
	public void setNumCooperativa(Short numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	
	/**
	 * Recupera o valor de descCooperativa.
	 *
	 * @return o valor de descCooperativa
	 */
	public String getDescCooperativa() {
		return descCooperativa;
	}
	
	/**
	 * Define o valor de descCooperativa.
	 *
	 * @param descCooperativa o novo valor de descCooperativa
	 */
	public void setDescCooperativa(String descCooperativa) {
		this.descCooperativa = descCooperativa;
	}
	
	/**
	 * Recupera o valor de descCentral.
	 *
	 * @return o valor de descCentral
	 */
	public String getDescCentral() {
		return descCentral;
	}
	
	/**
	 * Define o valor de descCentral.
	 *
	 * @param descCentral o novo valor de descCentral
	 */
	public void setDescCentral(String descCentral) {
		this.descCentral = descCentral;
	}

	
	
	
}

