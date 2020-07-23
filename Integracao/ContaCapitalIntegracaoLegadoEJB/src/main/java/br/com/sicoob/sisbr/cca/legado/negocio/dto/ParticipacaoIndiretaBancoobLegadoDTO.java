/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Marco.Nascimento
 * @since 03/06/2014
 */
public final class ParticipacaoIndiretaBancoobLegadoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;

	/** O atributo valor. */
	private BigDecimal valor;
	
	/** O atributo ano. */
	private Integer ano;
	
	/** O atributo mes. */
	private Integer mes;
	
	/** O atributo numCooperativa. */
	private Integer numCooperativa;
	
	/** O atributo anoMesFormatado. */
	private Integer anoMesFormatado;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo idInstituicaoPai. */
	private Integer idInstituicaoPai;
	
	/**
	 * Percentual de participacao da Singular na Central
	 */
	private BigDecimal percPartCentral = BigDecimal.ZERO;
	
	/**
	 * Valor participacao indireta da Singular no Bancoob
	 */
	private BigDecimal vlrPartBancoob = BigDecimal.ZERO;

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the ano
	 */
	public Integer getAno() {
		return ano;
	}

	/**
	 * @param ano the ano to set
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	/**
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * @return the numCooperativa
	 */
	public Integer getNumCooperativa() {
		return numCooperativa;
	}

	/**
	 * @param numCooperativa the numCooperativa to set
	 */
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	/**
	 * @return the percPartCentral
	 */
	public BigDecimal getPercPartCentral() {
		return percPartCentral;
	}

	/**
	 * @param percPartCentral the percPartCentral to set
	 */
	public void setPercPartCentral(BigDecimal percPartCentral) {
		this.percPartCentral = percPartCentral;
	}

	/**
	 * @return the vlrPartBancoob
	 */
	public BigDecimal getVlrPartBancoob() {
		return vlrPartBancoob;
	}

	/**
	 * @param vlrPartBancoob the vlrPartBancoob to set
	 */
	public void setVlrPartBancoob(BigDecimal vlrPartBancoob) {
		this.vlrPartBancoob = vlrPartBancoob;
	}

	/**
	 * Formato: yyyyMM
	 * @return the mesAnoFormatado
	 */
	public Integer getAnoMesFormatado() {
		if(ano != null && mes != null) {
			anoMesFormatado = Integer.valueOf(String.valueOf(ano) + StringUtils.leftPad(String.valueOf(this.mes), 2, "0"));
		}
		return anoMesFormatado;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * @return the idInstituicaoPai
	 */
	public Integer getIdInstituicaoPai() {
		return idInstituicaoPai;
	}

	/**
	 * @param idInstituicaoPai the idInstituicaoPai to set
	 */
	public void setIdInstituicaoPai(Integer idInstituicaoPai) {
		this.idInstituicaoPai = idInstituicaoPai;
	}
}