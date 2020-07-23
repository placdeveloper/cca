/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe ParcelamentoCCALegado.
 */
@Entity
@Table(name="ParcelamentoCCA", schema="dbo")
public class ParcelamentoCCALegado extends ContaCapitalIntegracaoLegadoEntidade {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** O atributo parcelamentoCCALegadoPK. */
	@EmbeddedId
	private ParcelamentoCCALegadoPK parcelamentoCCALegadoPK;	
	
	/** O atributo dataVencParcela. */
	@Column(name="DataVencParcela")
	private DateTimeDB dataVencParcela;
	
	/** O atributo dataSituacaoParcela. */
	@Column(name="DataSituacaoParcela")
	private DateTimeDB dataSituacaoParcela;
	
	/** O atributo valorParcela. */
	@Column(name="ValorParcela")
	private BigDecimal valorParcela;
	
	/** O atributo codModoLanc. */
	@Column(name="CodModoLanc")
	private Integer codModoLanc;
	
	/** O atributo codSituacaoParcela. */
	@Column(name="CodSituacaoParcela")
	private Integer codSituacaoParcela;
	
	/** O atributo numContaCorrente. */
	@Column(name="NumContaCorrente")
	private Long numContaCorrente;
	
	/** O atributo dataEnvioCob. */
	@Column(name="DataEnvioCob")
	private DateTimeDB dataEnvioCob;
	
	/** O atributo uIDTrabalha. */
	@Column(name="UIDTrabalha", columnDefinition="uniqueidentifier")
	private String uIDTrabalha;
	
	/** O atributo codMotivoDevolucao. */
	@Column(name="CodMotivoDevolucao")
	private Integer codMotivoDevolucao;
	
	/** O atributo descObservacao. */
	@Column(name="DescObservacao")
	private String descObservacao;	
	
	/** O atributo idParcelamentoContaCapital. */
	@Column(name="IDPARCELAMENTOCONTACAPITAL")
	private Long idParcelamentoContaCapital;
	
	/** O atributo codCanal. */
	@Column(name="IDCANAL")
	private Integer codCanal;
	
	/**
	 * Recupera o valor de parcelamentoCCALegadoPK.
	 *
	 * @return o valor de parcelamentoCCALegadoPK
	 */
	public ParcelamentoCCALegadoPK getParcelamentoCCALegadoPK() {
		return parcelamentoCCALegadoPK;
	}
	
	/**
	 * Define o valor de parcelamentoCCALegadoPK.
	 *
	 * @param parcelamentoCCALegadoPK o novo valor de parcelamentoCCALegadoPK
	 */
	public void setParcelamentoCCALegadoPK(
			ParcelamentoCCALegadoPK parcelamentoCCALegadoPK) {
		this.parcelamentoCCALegadoPK = parcelamentoCCALegadoPK;
	}
	
	/**
	 * Recupera o valor de dataVencParcela.
	 *
	 * @return o valor de dataVencParcela
	 */
	public DateTimeDB getDataVencParcela() {
		return dataVencParcela;
	}
	
	/**
	 * Define o valor de dataVencParcela.
	 *
	 * @param dataVencParcela o novo valor de dataVencParcela
	 */
	public void setDataVencParcela(DateTimeDB dataVencParcela) {
		this.dataVencParcela = dataVencParcela;
	}
	
	/**
	 * Recupera o valor de dataSituacaoParcela.
	 *
	 * @return o valor de dataSituacaoParcela
	 */
	public DateTimeDB getDataSituacaoParcela() {
		return dataSituacaoParcela;
	}
	
	/**
	 * Define o valor de dataSituacaoParcela.
	 *
	 * @param dataSituacaoParcela o novo valor de dataSituacaoParcela
	 */
	public void setDataSituacaoParcela(DateTimeDB dataSituacaoParcela) {
		this.dataSituacaoParcela = dataSituacaoParcela;
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
	 * Recupera o valor de dataEnvioCob.
	 *
	 * @return o valor de dataEnvioCob
	 */
	public DateTimeDB getDataEnvioCob() {
		return dataEnvioCob;
	}
	
	/**
	 * Define o valor de dataEnvioCob.
	 *
	 * @param dataEnvioCob o novo valor de dataEnvioCob
	 */
	public void setDataEnvioCob(DateTimeDB dataEnvioCob) {
		this.dataEnvioCob = dataEnvioCob;
	}
	
	/**
	 * Recupera o valor de uIDTrabalha.
	 *
	 * @return o valor de uIDTrabalha
	 */
	public String getuIDTrabalha() {
		return uIDTrabalha;
	}
	
	/**
	 * Define o valor de uIDTrabalha.
	 *
	 * @param uIDTrabalha o novo valor de uIDTrabalha
	 */
	public void setuIDTrabalha(String uIDTrabalha) {
		this.uIDTrabalha = uIDTrabalha;
	}
	
	/**
	 * Recupera o valor de codMotivoDevolucao.
	 *
	 * @return o valor de codMotivoDevolucao
	 */
	public Integer getCodMotivoDevolucao() {
		return codMotivoDevolucao;
	}
	
	/**
	 * Define o valor de codMotivoDevolucao.
	 *
	 * @param codMotivoDevolucao o novo valor de codMotivoDevolucao
	 */
	public void setCodMotivoDevolucao(Integer codMotivoDevolucao) {
		this.codMotivoDevolucao = codMotivoDevolucao;
	}
	
	/**
	 * Recupera o valor de descObservacao.
	 *
	 * @return o valor de descObservacao
	 */
	public String getDescObservacao() {
		return descObservacao;
	}
	
	/**
	 * Define o valor de descObservacao.
	 *
	 * @param descObservacao o novo valor de descObservacao
	 */
	public void setDescObservacao(String descObservacao) {
		this.descObservacao = descObservacao;
	}

	public Long getIdParcelamentoContaCapital() {
		return idParcelamentoContaCapital;
	}

	public void setIdParcelamentoContaCapital(Long idParcelamentoContaCapital) {
		this.idParcelamentoContaCapital = idParcelamentoContaCapital;
	}

	/**
	 * @return the codCanal
	 */
	public Integer getCodCanal() {
		return codCanal;
	}

	/**
	 * @param codCanal the codCanal to set
	 */
	public void setCodCanal(Integer codCanal) {
		this.codCanal = codCanal;
	}

}	
	