/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * @author marco.nascimento
 * @since 11/06/2014
 * @see HistParticipacaoCentralBancoob
 */
@Embeddable
public class HistParticipacaoCentralBancoobPK extends BancoobChavePrimaria {
	
	/**
	 * no args constructor 
	 */
	public HistParticipacaoCentralBancoobPK() {
		
	}
	
	/**
	 * @see ParticipacaoIndiretaBancoob 
	 * @param idInstituicaoCentral
	 * @param numAnoMesBase
	 */
	public HistParticipacaoCentralBancoobPK(Integer idInstituicaoCentral, Integer numAnoMesBase, DateTimeDB dataHoraAtualizacao) {
		this.idInstituicaoCentral = idInstituicaoCentral;
		this.numAnoMesBase = numAnoMesBase;
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
	
	/**
	 * 
	 */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;	

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

	/**
	 * @return the dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}

	/**
	 * @param dataHoraAtualizacao the dataHoraAtualizacao to set
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
}