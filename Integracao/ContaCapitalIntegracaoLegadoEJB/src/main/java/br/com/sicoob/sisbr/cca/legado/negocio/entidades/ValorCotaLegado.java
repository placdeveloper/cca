/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.entidades;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe ValorCotaLegado.
 */
@Entity
@Table(name="ValorCotas", schema="dbo")
public class ValorCotaLegado extends ContaCapitalIntegracaoLegadoEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3408059311909086851L;
	
	/** O atributo dataInicialCota. */
	private Date dataInicialCota;
	
	/** O atributo dataCadastroCota. */
	private Date dataCadastroCota;
	
	/** O atributo valorCota. */
	private BigDecimal valorCota;
	
	/** O atributo numMinCotasInteg. */
	private Integer numMinCotasInteg;
	
	/** O atributo percMinIntegralizacao. */
	private BigDecimal percMinIntegralizacao;
	
	/** O atributo valorSalarioBase. */
	private BigDecimal valorSalarioBase;
	
	/** O atributo bolLimIntegralCapConsignado. */
	private Boolean bolLimIntegralCapConsignado;
	
	/** O atributo valorLimiteIntegralMinimo. */
	private BigDecimal valorLimiteIntegralMinimo;
	
	/** O atributo valorLimiteIntegralMaximo. */
	private BigDecimal valorLimiteIntegralMaximo;
	
	/** O atributo numCoop. */
	private Integer numCoop;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;

	/**
	 * Recupera o valor de id.
	 *
	 * @return o valor de id
	 */
	@Id
	@Column(name="DataInicialCota", nullable=false)
	@Temporal(TemporalType.DATE)
	public Date getId() {
		if(dataInicialCota != null){
			return new Date(dataInicialCota.getTime());
		}
		return null;
	}
	
	/**
	 * Define o valor de id.
	 *
	 * @param dataInicialCota o novo valor de id
	 */
	public void setId(Date dataInicialCota) {
		if(dataInicialCota != null){
			this.dataInicialCota = new Date(dataInicialCota.getTime());
		} else{
			this.dataInicialCota = null;
		}
	}

	/**
	 * Recupera o valor de dataCadastroCota.
	 *
	 * @return o valor de dataCadastroCota
	 */
	@Column(name="DataCadastroCota")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataCadastroCota() {
		DateTimeDB clone = null;
		if(this.dataCadastroCota != null){
			clone = new DateTimeDB(this.dataCadastroCota.getTime());
		}
		return clone;
	}
	
	/**
	 * Define o valor de dataCadastroCota.
	 *
	 * @param dataCadastroCota o novo valor de dataCadastroCota
	 */
	public void setDataCadastroCota(Date dataCadastroCota) {
		if(dataCadastroCota != null){
			this.dataCadastroCota = new DateTimeDB(dataCadastroCota.getTime());
		} else{
			this.dataCadastroCota = null;	
		}
	}

	/**
	 * Recupera o valor de valorCota.
	 *
	 * @return o valor de valorCota
	 */
	@Column(name="ValorCota")
	public BigDecimal getValorCota() {
		return valorCota;
	}
	
	/**
	 * Define o valor de valorCota.
	 *
	 * @param valorCota o novo valor de valorCota
	 */
	public void setValorCota(BigDecimal valorCota) {
		this.valorCota = valorCota;
	}

	/**
	 * Recupera o valor de numMinCotasInteg.
	 *
	 * @return o valor de numMinCotasInteg
	 */
	@Column(name="NumMinCotasInteg")
	public Integer getNumMinCotasInteg() {
		return numMinCotasInteg;
	}
	
	/**
	 * Define o valor de numMinCotasInteg.
	 *
	 * @param numMinCotasInteg o novo valor de numMinCotasInteg
	 */
	public void setNumMinCotasInteg(Integer numMinCotasInteg) {
		this.numMinCotasInteg = numMinCotasInteg;
	}

	/**
	 * Recupera o valor de percMinIntegralizacao.
	 *
	 * @return o valor de percMinIntegralizacao
	 */
	@Column(name="PercMinIntegralizacao")
	public BigDecimal getPercMinIntegralizacao() {
		return percMinIntegralizacao;
	}
	
	/**
	 * Define o valor de percMinIntegralizacao.
	 *
	 * @param percMinIntegralizacao o novo valor de percMinIntegralizacao
	 */
	public void setPercMinIntegralizacao(BigDecimal percMinIntegralizacao) {
		this.percMinIntegralizacao = percMinIntegralizacao;
	}

	/**
	 * Recupera o valor de valorSalarioBase.
	 *
	 * @return o valor de valorSalarioBase
	 */
	@Column(name="ValorSalarioBase")
	public BigDecimal getValorSalarioBase() {
		return valorSalarioBase;
	}
	
	/**
	 * Define o valor de valorSalarioBase.
	 *
	 * @param valorSalarioBase o novo valor de valorSalarioBase
	 */
	public void setValorSalarioBase(BigDecimal valorSalarioBase) {
		this.valorSalarioBase = valorSalarioBase;
	}

	/**
	 * Recupera o valor de bolLimIntegralCapConsignado.
	 *
	 * @return o valor de bolLimIntegralCapConsignado
	 */
	@Column(name="BolLimIntegralCapConsignado")
	public Boolean getBolLimIntegralCapConsignado() {
		return bolLimIntegralCapConsignado;
	}
	
	/**
	 * Define o valor de bolLimIntegralCapConsignado.
	 *
	 * @param bolLimIntegralCapConsignado o novo valor de bolLimIntegralCapConsignado
	 */
	public void setBolLimIntegralCapConsignado(Boolean bolLimIntegralCapConsignado) {
		this.bolLimIntegralCapConsignado = bolLimIntegralCapConsignado;
	}

	/**
	 * Recupera o valor de valorLimiteIntegralMinimo.
	 *
	 * @return o valor de valorLimiteIntegralMinimo
	 */
	@Column(name="ValorLimiteIntegralMinimo")
	public BigDecimal getValorLimiteIntegralMinimo() {
		return valorLimiteIntegralMinimo;
	}
	
	/**
	 * Define o valor de valorLimiteIntegralMinimo.
	 *
	 * @param valorLimiteIntegralMinimo o novo valor de valorLimiteIntegralMinimo
	 */
	public void setValorLimiteIntegralMinimo(BigDecimal valorLimiteIntegralMinimo) {
		this.valorLimiteIntegralMinimo = valorLimiteIntegralMinimo;
	}

	/**
	 * Recupera o valor de valorLimiteIntegralMaximo.
	 *
	 * @return o valor de valorLimiteIntegralMaximo
	 */
	@Column(name="ValorLimiteIntegralMaximo")
	public BigDecimal getValorLimiteIntegralMaximo() {
		return valorLimiteIntegralMaximo;
	}
	
	/**
	 * Define o valor de valorLimiteIntegralMaximo.
	 *
	 * @param valorLimiteIntegralMaximo o novo valor de valorLimiteIntegralMaximo
	 */
	public void setValorLimiteIntegralMaximo(BigDecimal valorLimiteIntegralMaximo) {
		this.valorLimiteIntegralMaximo = valorLimiteIntegralMaximo;
	}

	/**
	 * @return the numCoop
	 */
	@Transient
	public Integer getNumCoop() {
		return numCoop;
	}

	/**
	 * @param numCoop the numCoop to set
	 */
	public void setNumCoop(Integer numCoop) {
		this.numCoop = numCoop;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	@Transient
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
}