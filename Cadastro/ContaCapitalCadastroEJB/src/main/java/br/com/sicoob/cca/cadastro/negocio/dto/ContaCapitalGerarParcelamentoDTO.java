/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe ContaCapitalGerarParcelamentoDTO.
 */
public class ContaCapitalGerarParcelamentoDTO extends BancoobDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4641032564147483116L;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numContaCorrente. */
	private Long numContaCorrente;
	
	/** O atributo uidTrabalha. */
	private String uidTrabalha;
	
	/** O atributo valorSubsc. */
	private BigDecimal valorSubsc;
	
	/** O atributo valorInt. */
	private BigDecimal valorInt;
	
	/** O atributo codModoLanc. */
	private Integer codModoLanc; //Via cx via cco e etc	
	
	/** O atributo numQtdParcelas. */
	private Integer numQtdParcelas;
	
	
	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	
	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
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
	 * Recupera o valor de valorSubsc.
	 *
	 * @return o valor de valorSubsc
	 */
	public BigDecimal getValorSubsc() {
		return valorSubsc;
	}
	
	/**
	 * Define o valor de valorSubsc.
	 *
	 * @param valorSubsc o novo valor de valorSubsc
	 */
	public void setValorSubsc(BigDecimal valorSubsc) {
		this.valorSubsc = valorSubsc;
	}
	
	/**
	 * Recupera o valor de valorInt.
	 *
	 * @return o valor de valorInt
	 */
	public BigDecimal getValorInt() {
		return valorInt;
	}
	
	/**
	 * Define o valor de valorInt.
	 *
	 * @param valorInt o novo valor de valorInt
	 */
	public void setValorInt(BigDecimal valorInt) {
		this.valorInt = valorInt;
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
	 * Recupera o valor de numQtdParcelas.
	 *
	 * @return o valor de numQtdParcelas
	 */
	public Integer getNumQtdParcelas() {
		return numQtdParcelas;
	}
	
	/**
	 * Define o valor de numQtdParcelas.
	 *
	 * @param numQtdParcelas o novo valor de numQtdParcelas
	 */
	public void setNumQtdParcelas(Integer numQtdParcelas) {
		this.numQtdParcelas = numQtdParcelas;
	}
		
	
}
