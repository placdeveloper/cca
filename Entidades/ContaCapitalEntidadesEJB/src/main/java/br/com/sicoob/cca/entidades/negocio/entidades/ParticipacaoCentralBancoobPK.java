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
 * @see ParticipacaoCentralBancoob
 */
@Embeddable
public class ParticipacaoCentralBancoobPK extends BancoobChavePrimaria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -786249880287957705L;

	/**
	 * no args constructor 
	 */
	public ParticipacaoCentralBancoobPK() {
		
	}
	
	/**
	 * @see ParticipacaoIndiretaBancoob 
	 * @param idInstituicaoCentral
	 * @param numAnoMesBase
	 */
	public ParticipacaoCentralBancoobPK(Integer idInstituicaoCentral, Integer numAnoMesBase) {
		this.idInstituicaoCentral = idInstituicaoCentral;
		this.numAnoMesBase = numAnoMesBase;
	}

	/**
	 * 
	 */
	@Column(name = "IDINSTITUICAOCENTRAL", nullable = false)
	private Integer idInstituicaoCentral;
	
	/**
	 * 
	 */
	@Column(name = "ANOMESBASE", nullable = false)
	private Integer numAnoMesBase;

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
}