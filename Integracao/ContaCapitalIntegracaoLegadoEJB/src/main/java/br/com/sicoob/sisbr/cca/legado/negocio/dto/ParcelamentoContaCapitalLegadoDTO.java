/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe ParcelamentoContaCapitalLegadoDTO.
 */
public class ParcelamentoContaCapitalLegadoDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6802918043543057852L;
	
	/** O atributo numParcelamento. */
	private Integer numParcelamento = ContaCapitalConstantes.COD_INCREMENTADOR_INICIAL;
	
	/** O atributo numParcela. */
	private Integer numParcela = ContaCapitalConstantes.COD_INCREMENTADOR_PARC_INICIAL;
	
	/** O atributo dataVencimento. */
	private DateTime dataVencimento;
	
	/** O atributo dataSituacao. */
	private DateTime dataSituacao;
	
	/** O atributo valorParcela. */
	private BigDecimal valorParcela;
	
	/** O atributo codModoLanc. */
	private Integer	codModoLanc;
	
	/** O atributo uidTrabalha. */
	private String uidTrabalha;
	
	/** O atributo numContaCorrente. */
	private Long numContaCorrente;
	
	/** O atributo codSituacaoParcela. */
	private Integer codSituacaoParcela = ContaCapitalConstantes.COD_PARCELA_GERADA;
	
	/**
	 * Recupera o valor de numParcelamento.
	 *
	 * @return o valor de numParcelamento
	 */
	public Integer getNumParcelamento() {
		return numParcelamento;
	}
	
	/**
	 * Define o valor de numParcelamento.
	 *
	 * @param numParcelamento o novo valor de numParcelamento
	 */
	public void setNumParcelamento(Integer numParcelamento) {
		this.numParcelamento = numParcelamento;
	}
	
	/**
	 * Recupera o valor de numParcela.
	 *
	 * @return o valor de numParcela
	 */
	public Integer getNumParcela() {
		return numParcela;
	}
	
	/**
	 * Define o valor de numParcela.
	 *
	 * @param numParcela o novo valor de numParcela
	 */
	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}
	
	/**
	 * Recupera o valor de dataVencimento.
	 *
	 * @return o valor de dataVencimento
	 */
	public DateTime getDataVencimento() {
		return dataVencimento;
	}
	
	/**
	 * Define o valor de dataVencimento.
	 *
	 * @param dataVencimento o novo valor de dataVencimento
	 */
	public void setDataVencimento(DateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	/**
	 * Recupera o valor de dataSituacao.
	 *
	 * @return o valor de dataSituacao
	 */
	public DateTime getDataSituacao() {
		return dataSituacao;
	}
	
	/**
	 * Define o valor de dataSituacao.
	 *
	 * @param dataSituacao o novo valor de dataSituacao
	 */
	public void setDataSituacao(DateTime dataSituacao) {
		this.dataSituacao = dataSituacao;
	}
	
	/**
	 * Recupera o valor de valorParcela.
	 *
	 * @return o valor de valorParcela
	 */
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	
	/**
	 * Define o valor de valorParcela.
	 *
	 * @param valorParcela o novo valor de valorParcela
	 */
	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	
	/**
	 * Recupera o valor de codModoLanc.
	 *
	 * @return o valor de codModoLanc
	 */
	public Integer getCodModoLanc() {
		return codModoLanc;
	}
	
	/**
	 * Define o valor de codModoLanc.
	 *
	 * @param codModoLanc o novo valor de codModoLanc
	 */
	public void setCodModoLanc(Integer codModoLanc) {
		this.codModoLanc = codModoLanc;
	}
	
	/**
	 * Recupera o valor de uidTrabalha.
	 *
	 * @return o valor de uidTrabalha
	 */
	public String getUidTrabalha() {
		return uidTrabalha;
	}
	
	/**
	 * Define o valor de uidTrabalha.
	 *
	 * @param uidTrabalha o novo valor de uidTrabalha
	 */
	public void setUidTrabalha(String uidTrabalha) {
		this.uidTrabalha = uidTrabalha;
	}
	
	/**
	 * Recupera o valor de numContaCorrente.
	 *
	 * @return o valor de numContaCorrente
	 */
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}
	
	/**
	 * Define o valor de numContaCorrente.
	 *
	 * @param numContaCorrente o novo valor de numContaCorrente
	 */
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
	
	/**
	 * Recupera o valor de codSituacaoParcela.
	 *
	 * @return o valor de codSituacaoParcela
	 */
	public Integer getCodSituacaoParcela() {
		return codSituacaoParcela;
	}
	
	/**
	 * Define o valor de codSituacaoParcela.
	 *
	 * @param codSituacaoParcela o novo valor de codSituacaoParcela
	 */
	public void setCodSituacaoParcela(Integer codSituacaoParcela) {
		this.codSituacaoParcela = codSituacaoParcela;
	}
	
	
	
}
