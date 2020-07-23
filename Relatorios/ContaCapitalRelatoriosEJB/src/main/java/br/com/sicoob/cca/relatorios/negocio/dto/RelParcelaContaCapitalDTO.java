/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Marco.Nascimento
 */
public class RelParcelaContaCapitalDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -120565210505268621L;
	
	private Integer idInstituicao;
	private Integer numContaCapitalInicial;
	private Integer numContaCapitalFinal;
	private Integer tipoParcelamento;	
	private Integer formaParcelamento;
	private Integer situacaoParcela;	
	private Date dataInicialVencimento;
	private Date dataFinalVencimento;
	private Date dataSituacao;
	private Integer numPA;
	private Integer ordenacao;
	private String nomePessoa;
	private Integer numParcelamento;
	private Integer idContaCapital;
	private Integer numParcela;
	private BigDecimal valorParcela;
	private String descTipoIntegralizacao;
	private String descSituacaoParcelamento;
	private Date dataVencimentoParcela;
	private Integer numContaCapital;
	private Long numContaCorrente;
	
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
	 * @return the numContaCapitalInicial
	 */
	public Integer getNumContaCapitalInicial() {
		return numContaCapitalInicial;
	}
	/**
	 * @param numContaCapitalInicial the numContaCapitalInicial to set
	 */
	public void setNumContaCapitalInicial(Integer numContaCapitalInicial) {
		this.numContaCapitalInicial = numContaCapitalInicial;
	}
	/**
	 * @return the numContaCapitalFinal
	 */
	public Integer getNumContaCapitalFinal() {
		return numContaCapitalFinal;
	}
	/**
	 * @param numContaCapitalFinal the numContaCapitalFinal to set
	 */
	public void setNumContaCapitalFinal(Integer numContaCapitalFinal) {
		this.numContaCapitalFinal = numContaCapitalFinal;
	}
	/**
	 * @return the tipoParcelamento
	 */
	public Integer getTipoParcelamento() {
		return tipoParcelamento;
	}
	/**
	 * @param tipoParcelamento the tipoParcelamento to set
	 */
	public void setTipoParcelamento(Integer tipoParcelamento) {
		this.tipoParcelamento = tipoParcelamento;
	}
	/**
	 * @return the formaParcelamento
	 */
	public Integer getFormaParcelamento() {
		return formaParcelamento;
	}
	/**
	 * @param formaParcelamento the formaParcelamento to set
	 */
	public void setFormaParcelamento(Integer formaParcelamento) {
		this.formaParcelamento = formaParcelamento;
	}
	/**
	 * @return the situacaoParcela
	 */
	public Integer getSituacaoParcela() {
		return situacaoParcela;
	}
	/**
	 * @param situacaoParcela the situacaoParcela to set
	 */
	public void setSituacaoParcela(Integer situacaoParcela) {
		this.situacaoParcela = situacaoParcela;
	}
	/**
	 * @return the dataInicialVencimento
	 */
	public Date getDataInicialVencimento() {
		return dataInicialVencimento;
	}
	/**
	 * @param dataInicialVencimento the dataInicialVencimento to set
	 */
	public void setDataInicialVencimento(Date dataInicialVencimento) {
		this.dataInicialVencimento = dataInicialVencimento;
	}
	/**
	 * @return the dataFinalVencimento
	 */
	public Date getDataFinalVencimento() {
		return dataFinalVencimento;
	}
	/**
	 * @param dataFinalVencimento the dataFinalVencimento to set
	 */
	public void setDataFinalVencimento(Date dataFinalVencimento) {
		this.dataFinalVencimento = dataFinalVencimento;
	}
	/**
	 * @return the dataSituacao
	 */
	public Date getDataSituacao() {
		return dataSituacao;
	}
	/**
	 * @param dataSituacao the dataSituacao to set
	 */
	public void setDataSituacao(Date dataSituacao) {
		this.dataSituacao = dataSituacao;
	}
	/**
	 * @return the numPA
	 */
	public Integer getNumPA() {
		return numPA;
	}
	/**
	 * @param numPA the numPA to set
	 */
	public void setNumPA(Integer numPA) {
		this.numPA = numPA;
	}
	/**
	 * @return the ordenacao
	 */
	public Integer getOrdenacao() {
		return ordenacao;
	}
	/**
	 * @param ordenacao the ordenacao to set
	 */
	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}
	/**
	 * @return the nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}
	/**
	 * @param nomePessoa the nomePessoa to set
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	/**
	 * @return the numParcelamento
	 */
	public Integer getNumParcelamento() {
		return numParcelamento;
	}
	/**
	 * @param numParcelamento the numParcelamento to set
	 */
	public void setNumParcelamento(Integer numParcelamento) {
		this.numParcelamento = numParcelamento;
	}
	/**
	 * @return the idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	/**
	 * @param idContaCapital the idContaCapital to set
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}
	/**
	 * @return the numParcela
	 */
	public Integer getNumParcela() {
		return numParcela;
	}
	/**
	 * @param numParcela the numParcela to set
	 */
	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}
	/**
	 * @return the valorParcela
	 */
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	/**
	 * @param valorParcela the valorParcela to set
	 */
	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	/**
	 * @return the descTipoIntegralizacao
	 */
	public String getDescTipoIntegralizacao() {
		return descTipoIntegralizacao;
	}
	/**
	 * @param descTipoIntegralizacao the descTipoIntegralizacao to set
	 */
	public void setDescTipoIntegralizacao(String descTipoIntegralizacao) {
		this.descTipoIntegralizacao = descTipoIntegralizacao;
	}
	/**
	 * @return the descSituacaoParcelamento
	 */
	public String getDescSituacaoParcelamento() {
		return descSituacaoParcelamento;
	}
	/**
	 * @param descSituacaoParcelamento the descSituacaoParcelamento to set
	 */
	public void setDescSituacaoParcelamento(String descSituacaoParcelamento) {
		this.descSituacaoParcelamento = descSituacaoParcelamento;
	}
	/**
	 * @return the dataVencimentoParcela
	 */
	public Date getDataVencimentoParcela() {
		return dataVencimentoParcela;
	}
	/**
	 * @param dataVencimentoParcela the dataVencimentoParcela to set
	 */
	public void setDataVencimentoParcela(Date dataVencimentoParcela) {
		this.dataVencimentoParcela = dataVencimentoParcela;
	}
	/**
	 * @return the numContaCapital
	 */
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	/**
	 * @param numContaCapital the numContaCapital to set
	 */
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	/**
	 * @return the numContaCorrente
	 */
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}
	/**
	 * @param numContaCorrente the numContaCorrente to set
	 */
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
}