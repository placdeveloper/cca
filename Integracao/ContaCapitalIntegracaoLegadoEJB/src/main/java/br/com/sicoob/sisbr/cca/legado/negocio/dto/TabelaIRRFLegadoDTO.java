/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO que representa tabela de imposto progressivo (IRRF)
 */
public class TabelaIRRFLegadoDTO extends BancoobDto {
	
	private static final long serialVersionUID = 1L;
	
	private Integer anoBase;
	private BigDecimal percAliquota;
	private BigDecimal valorBaseInicial;
	private BigDecimal valorBaseFinal;
	private BigDecimal valorParcelaDeducao;
	

	/**
	 * @return the anoBase
	 */
	public Integer getAnoBase() {
		return anoBase;
	}

	/**
	 * @param anoBase the anoBase to set
	 */
	public void setAnoBase(Integer anoBase) {
		this.anoBase = anoBase;
	}

	/**
	 * @return the percAliquota
	 */
	public BigDecimal getPercAliquota() {
		return percAliquota;
	}

	/**
	 * @param percAliquota the percAliquota to set
	 */
	public void setPercAliquota(BigDecimal percAliquota) {
		this.percAliquota = percAliquota;
	}

	/**
	 * @return the valorBaseInicial
	 */
	public BigDecimal getValorBaseInicial() {
		return valorBaseInicial;
	}

	/**
	 * @param valorBaseInicial the valorBaseInicial to set
	 */
	public void setValorBaseInicial(BigDecimal valorBaseInicial) {
		this.valorBaseInicial = valorBaseInicial;
	}

	/**
	 * @return the valorBaseFinal
	 */
	public BigDecimal getValorBaseFinal() {
		return valorBaseFinal;
	}

	/**
	 * @param valorBaseFinal the valorBaseFinal to set
	 */
	public void setValorBaseFinal(BigDecimal valorBaseFinal) {
		this.valorBaseFinal = valorBaseFinal;
	}

	/**
	 * @return the valorParcelaDeducao
	 */
	public BigDecimal getValorParcelaDeducao() {
		return valorParcelaDeducao;
	}

	/**
	 * @param valorParcelaDeducao the valorParcelaDeducao to set
	 */
	public void setValorParcelaDeducao(BigDecimal valorParcelaDeducao) {
		this.valorParcelaDeducao = valorParcelaDeducao;
	}
}