/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe HistParticipacaoCentralBancoobDTO.
 */
public class HistParticipacaoCentralBancoobDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2410948811859586867L;
	
	/** O atributo idInstituicaoCentral. */
	private Integer idInstituicaoCentral;
	
	/** O atributo numAmoMesBase. */
	private Integer numAmoMesBase;		
	
	/** O atributo valorParticipacao. */
	private BigDecimal valorParticipacao;	
	
	/** O atributo dataHoraAtualizacao. */
	private DateTimeDB dataHoraAtualizacao;	
	
	/** O atributo idUsuario. */
	private String idUsuario;
	
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
	 * Recupera o valor de numAmoMesBase.
	 *
	 * @return o valor de numAmoMesBase
	 */
	public Integer getNumAmoMesBase() {
		return numAmoMesBase;
	}
	
	/**
	 * Define o valor de numAmoMesBase.
	 *
	 * @param numAmoMesBase o novo valor de numAmoMesBase
	 */
	public void setNumAmoMesBase(Integer numAmoMesBase) {
		this.numAmoMesBase = numAmoMesBase;
	}
	
	/**
	 * Recupera o valor de valorParticipacao.
	 *
	 * @return o valor de valorParticipacao
	 */
	public BigDecimal getValorParticipacao() {
		return valorParticipacao;
	}
	
	/**
	 * Define o valor de valorParticipacao.
	 *
	 * @param valorParticipacao o novo valor de valorParticipacao
	 */
	public void setValorParticipacao(BigDecimal valorParticipacao) {
		this.valorParticipacao = valorParticipacao;
	}
	
	/**
	 * Recupera o valor de dataHoraAtualizacao.
	 *
	 * @return o valor de dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}
	
	/**
	 * Define o valor de dataHoraAtualizacao.
	 *
	 * @param dataHoraAtualizacao o novo valor de dataHoraAtualizacao
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
	
	/**
	 * Recupera o valor de idUsuario.
	 *
	 * @return o valor de idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Define o valor de idUsuario.
	 *
	 * @param idUsuario o novo valor de idUsuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
