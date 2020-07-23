/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;


/**
 * @author marco.nascimento
 * @since 10/06/2014
 * @see ParticipacaoIndiretaBancoob
 */
@Embeddable
public class ParticipacaoIndiretaBancoobPK extends BancoobChavePrimaria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7103664630402759680L;


	/**
	 * no args constructor 
	 */
	public ParticipacaoIndiretaBancoobPK() {
		
	}
	
	/**
	 * @see ParticipacaoIndiretaBancoob 
	 * @param idInstituicaoSingular
	 * @param numAnoMesBase
	 */
	public ParticipacaoIndiretaBancoobPK(Integer idInstituicaoSingular, Integer numAnoMesBase, Integer idInstituicaoCentral) {
		this.idInstituicaoSingular = idInstituicaoSingular;
		this.numAnoMesBase = numAnoMesBase;
		this.idInstituicaoCentral = idInstituicaoCentral;
	}
	
	/** O atributo idInstituicaoCentral. */
	@Column(name = "IDINSTITUICAOCENTRAL", nullable = false)
	private Integer idInstituicaoCentral;
	
	/**
	 * 
	 */
	@Column(name = "IDINSTITUICAOSINGULAR", nullable = false)
	private Integer idInstituicaoSingular;
	
	/**
	 * 
	 */
	@Column(name = "ANOMESBASE", nullable = false)
	private Integer numAnoMesBase;
	

	/**
	 * @return the idInstituicaoSingular
	 */
	public Integer getIdInstituicaoSingular() {
		return idInstituicaoSingular;
	}

	/**
	 * @param idInstituicaoSingular the idInstituicaoSingular to set
	 */
	public void setIdInstituicaoSingular(Integer idInstituicaoSingular) {
		this.idInstituicaoSingular = idInstituicaoSingular;
	}

	/**
	 * @return the numAnoMesBase
	 */
	public Integer getNumAnoMesBase() {
		return numAnoMesBase;
	}
	
	/**
	 * @param numAnoMesBase the numAnoMesBase to set
	 */
	public void setNumAnoMesBase(Integer numAnoMesBase) {
		this.numAnoMesBase = numAnoMesBase;
	}

	/**
	 * @return the idInstituicaoCentral
	 */
	public Integer getIdInstituicaoCentral() {
		return idInstituicaoCentral;
	}

	/**
	 * @param idInstituicaoCentral the idInstituicaoCentral to set
	 */
	public void setIdInstituicaoCentral(Integer idInstituicaoCentral) {
		this.idInstituicaoCentral = idInstituicaoCentral;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParticipacaoIndiretaBancoobPK[idInstituicaoCentral=" + this.idInstituicaoCentral + ", numAnoMesBase=" + this.numAnoMesBase + ", idInstituicaoSingular" + this.idInstituicaoSingular + "]";
	}
}