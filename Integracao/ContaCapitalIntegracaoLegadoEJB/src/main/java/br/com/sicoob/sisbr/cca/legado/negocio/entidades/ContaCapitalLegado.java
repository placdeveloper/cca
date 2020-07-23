/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe ContaCapitalLegado.
 */
@Entity
@Table(name="ContaCapital", schema="dbo")
public class ContaCapitalLegado extends	ContaCapitalIntegracaoLegadoEntidade {

	private static final long serialVersionUID = 7753261204844910875L;

	/** O atributo numMatricula. */
	@Id
	@Column(name="NumMatricula", nullable=false)
	private Integer numMatricula;

	/** O atributo numCliente. */
	@Column(name="NumCliente")
	private Integer numCliente;
	
	/** O atributo bolDebIndeterminado. */
	@Column(name="BolDebIndeterminado")
	private Boolean bolDebIndeterminado;
	
	/** O atributo bolDireitoVoto. */
	@Column(name="BolDireitoVoto")	
	private Boolean bolDireitoVoto;
	
	/** O atributo bolParticipaRateio. */
	@Column(name="BolParticipaRateio")	
	private Boolean bolParticipaRateio;
	
	/** O atributo bolRestricaoRateio. */
	@Column(name="BolRestricaoRateio")	
	private Boolean bolRestricaoRateio;
	
	/** O atributo codFormaDeb. */
	@Column(name="CodFormaDeb")	
	private Integer codFormaDeb;
	
	/** O atributo codSituacao. */
	@Column(name="CodSituacao")	
	private Integer codSituacao;
	
	/** O atributo codTipoValorDebito. */
	@Column(name="CodTipoValorDebito")	
	private Integer codTipoValorDebito;
	
	/** O atributo dataMatricula. */
	@Column(name="DataMatricula")
	private DateTimeDB dataMatricula;
	
	/** O atributo dataSaida. */
	@Column(name="DataSaida")	
	private DateTimeDB dataSaida;
	
	/** O atributo dataSaldoAnterior. */
	@Column(name="DataSaldoAnterior")	
	private DateTimeDB dataSaldoAnterior;
	
	/** O atributo dataVencimentoDeb. */
	@Column(name="DataVencimentoDeb")	
	private DateTimeDB dataVencimentoDeb;
	
	/** O atributo idCondicaoAssociacao. */
	@Column(name="IDCondicaoAssociacao")	
	private Integer idCondicaoAssociacao;

	/** O atributo numContaCorrente. */
	@Column(name="NumContaCorrente")
	private Long numContaCorrente;
	
	/** O atributo obsRestricao. */
	@Column(name="ObsRestricao")	
	private String obsRestricao;
	
	/** O atributo periodoProxDeb. */
	@Column(name="PeriodoProxDeb")	
	private Integer periodoProxDeb;

	/** O atributo tipoPeriodoDeb. */
	@Column(name="TipoPeriodoDeb")	
	private Integer tipoPeriodoDeb;
	
	/** O atributo uidTrabalha. */
	@Column(name="UIDTrabalha", columnDefinition="uniqueidentifier")	
	private String uidTrabalha;
	
	/** O atributo valorDeb. */
	@Column(name="ValorDeb")	
	private BigDecimal valorDeb;

	/** O atributo valorSaldoAntDevolver. */
	@Column(name="ValorSaldoAntDevolver")
	private BigDecimal valorSaldoAntDevolver;
	
	/** O atributo valorSaldoAntDivs. */
	@Column(name="ValorSaldoAntDivs")	
	private BigDecimal valorSaldoAntDivs;
	
	/** O atributo valorSaldoAntInteg. */
	@Column(name="ValorSaldoAntInteg")	
	private BigDecimal valorSaldoAntInteg;
	
	/** O atributo valorSaldoAntSubsc. */
	@Column(name="ValorSaldoAntSubsc")	
	private BigDecimal valorSaldoAntSubsc;
	
	/** O atributo valorSaldoAtuDevolver. */
	@Column(name="ValorSaldoAtuDevolver")	
	private BigDecimal valorSaldoAtuDevolver;
	
	/** O atributo valorSaldoAtuDivs. */
	@Column(name="ValorSaldoAtuDivs")	
	private BigDecimal valorSaldoAtuDivs;
	
	/** O atributo valorSaldoAtuInteg. */
	@Column(name="ValorSaldoAtuInteg")	
	private BigDecimal valorSaldoAtuInteg;

	/** O atributo valorSaldoAtuSubsc. */
	@Column(name="ValorSaldoAtuSubsc")
	private BigDecimal valorSaldoAtuSubsc;
	
	/** O atributo valorSaldoBloqInt. */
	@Column(name="ValorSaldoBloqInt")	
	private BigDecimal valorSaldoBloqInt;
	
	/** O atributo valorSaldoIntegralDispAcum. */
	@Column(name="ValorSaldoIntegralDispAcum")	
	private BigDecimal valorSaldoIntegralDispAcum;
	
	/** O atributo valorSaldoMedADevAcu. */
	@Column(name="ValorSaldoMedADevAcu")	
	private BigDecimal valorSaldoMedADevAcu;
	
	/** O atributo valorSaldoMedPosAcu. */
	@Column(name="ValorSaldoMedPosAcu")	
	private BigDecimal valorSaldoMedPosAcu;
	
	/** O atributo valorSaldoMedRealAcu. */
	@Column(name="ValorSaldoMedRealAcu")	
	private BigDecimal valorSaldoMedRealAcu;
	
	/** O atributo numCoop. */
	@Transient
	private Integer numCoop;

	/** O atributo idContaCapital. */
	@Column(name="IDCONTACAPITAL")	
	private Integer idContaCapital;
	
	@Override
	public String toString() {
		return "ContaCapitalLegado[numMatricula=" + numMatricula + ", idContaCapital=" + idContaCapital + ", numCliente=" + numCliente + 
				", dataVencimentoDeb=" + dataVencimentoDeb + "] " + super.toString();
	}
	
    /**
     * Instancia um novo ContaCapitalLegado.
     */
    public ContaCapitalLegado() {
    }
  
	/**
	 * Recupera o valor de numMatricula.
	 *
	 * @return o valor de numMatricula
	 */
	public Integer getNumMatricula() {
		return this.numMatricula;
	}
	
	/**
	 * Define o valor de numMatricula.
	 *
	 * @param numMatricula o novo valor de numMatricula
	 */
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}
	

	/**
	 * Recupera o valor de bolDebIndeterminado.
	 *
	 * @return o valor de bolDebIndeterminado
	 */
	public Boolean getBolDebIndeterminado() {
		return bolDebIndeterminado;
	}

	/**
	 * Define o valor de bolDebIndeterminado.
	 *
	 * @param bolDebIndeterminado o novo valor de bolDebIndeterminado
	 */
	public void setBolDebIndeterminado(Boolean bolDebIndeterminado) {
		this.bolDebIndeterminado = bolDebIndeterminado;
	}


	/**
	 * Recupera o valor de bolDireitoVoto.
	 *
	 * @return o valor de bolDireitoVoto
	 */
	public Boolean getBolDireitoVoto() {
		return bolDireitoVoto;
	}

	/**
	 * Define o valor de bolDireitoVoto.
	 *
	 * @param bolDireitoVoto o novo valor de bolDireitoVoto
	 */
	public void setBolDireitoVoto(Boolean bolDireitoVoto) {
		this.bolDireitoVoto = bolDireitoVoto;
	}


	/**
	 * Recupera o valor de bolParticipaRateio.
	 *
	 * @return o valor de bolParticipaRateio
	 */
	public Boolean getBolParticipaRateio() {
		return bolParticipaRateio;
	}

	/**
	 * Define o valor de bolParticipaRateio.
	 *
	 * @param bolParticipaRateio o novo valor de bolParticipaRateio
	 */
	public void setBolParticipaRateio(Boolean bolParticipaRateio) {
		this.bolParticipaRateio = bolParticipaRateio;
	}

	/**
	 * Recupera o valor de bolRestricaoRateio.
	 *
	 * @return o valor de bolRestricaoRateio
	 */
	public Boolean getBolRestricaoRateio() {
		return bolRestricaoRateio;
	}

	/**
	 * Define o valor de bolRestricaoRateio.
	 *
	 * @param bolRestricaoRateio o novo valor de bolRestricaoRateio
	 */
	public void setBolRestricaoRateio(Boolean bolRestricaoRateio) {
		this.bolRestricaoRateio = bolRestricaoRateio;
	}

	/**
	 * Recupera o valor de codFormaDeb.
	 *
	 * @return o valor de codFormaDeb
	 */
	public Integer getCodFormaDeb() {
		return codFormaDeb;
	}

	/**
	 * Define o valor de codFormaDeb.
	 *
	 * @param codFormaDeb o novo valor de codFormaDeb
	 */
	public void setCodFormaDeb(Integer codFormaDeb) {
		this.codFormaDeb = codFormaDeb;
	}

	/**
	 * Recupera o valor de codSituacao.
	 *
	 * @return o valor de codSituacao
	 */
	public Integer getCodSituacao() {
		return codSituacao;
	}

	/**
	 * Define o valor de codSituacao.
	 *
	 * @param codSituacao o novo valor de codSituacao
	 */
	public void setCodSituacao(Integer codSituacao) {
		this.codSituacao = codSituacao;
	}
	

	/**
	 * Recupera o valor de codTipoValorDebito.
	 *
	 * @return o valor de codTipoValorDebito
	 */
	public Integer getCodTipoValorDebito() {
		return codTipoValorDebito;
	}

	/**
	 * Define o valor de codTipoValorDebito.
	 *
	 * @param codTipoValorDebito o novo valor de codTipoValorDebito
	 */
	public void setCodTipoValorDebito(Integer codTipoValorDebito) {
		this.codTipoValorDebito = codTipoValorDebito;
	}
	

	/**
	 * Recupera o valor de dataMatricula.
	 *
	 * @return o valor de dataMatricula
	 */
	public DateTimeDB getDataMatricula() {
		DateTimeDB clone = null;
        if(this.dataMatricula != null){
              clone = new DateTimeDB(this.dataMatricula.getTime());
        } 
        return clone;
	}

	/**
	 * Define o valor de dataMatricula.
	 *
	 * @param dataMatricula o novo valor de dataMatricula
	 */
	public void setDataMatricula(DateTimeDB dataMatricula) {
		
		if(dataMatricula != null){
			this.dataMatricula = new DateTimeDB(dataMatricula.getTime());
		} else {
			this.dataMatricula = null;
		}           
		
	}
	
	/**
	 * Recupera o valor de dataSaida.
	 *
	 * @return o valor de dataSaida
	 */
	public DateTimeDB getDataSaida() {
		DateTimeDB clone = null;
        if(this.dataSaida != null){
              clone = new DateTimeDB(this.dataSaida.getTime());
        } 
        return clone;		
	}

	/**
	 * Define o valor de dataSaida.
	 *
	 * @param dataSaida o novo valor de dataSaida
	 */
	public void setDataSaida(DateTimeDB dataSaida) {
		if(dataSaida != null){
			this.dataSaida = new DateTimeDB(dataSaida.getTime());
		} else {
			this.dataSaida = null;
		} 
	}
	

	/**
	 * Recupera o valor de dataSaldoAnterior.
	 *
	 * @return o valor de dataSaldoAnterior
	 */
	public DateTimeDB getDataSaldoAnterior() {
		DateTimeDB clone = null;
        if(this.dataSaldoAnterior != null){
              clone = new DateTimeDB(this.dataSaldoAnterior.getTime());
        } 
        return clone;		
	}

	/**
	 * Define o valor de dataSaldoAnterior.
	 *
	 * @param dataSaldoAnterior o novo valor de dataSaldoAnterior
	 */
	public void setDataSaldoAnterior(DateTimeDB dataSaldoAnterior) {
		
		if(dataSaldoAnterior != null){
			this.dataSaldoAnterior = new DateTimeDB(dataSaldoAnterior.getTime());
		} else {
			this.dataSaldoAnterior = null;
		} 		
		
	}

	/**
	 * Recupera o valor de dataVencimentoDeb.
	 *
	 * @return o valor de dataVencimentoDeb
	 */
	public DateTimeDB getDataVencimentoDeb() {
		DateTimeDB clone = null;
        if(this.dataVencimentoDeb != null){
              clone = new DateTimeDB(this.dataVencimentoDeb.getTime());
        } 
        return clone;
	}

	/**
	 * Define o valor de dataVencimentoDeb.
	 *
	 * @param dataVencimentoDeb o novo valor de dataVencimentoDeb
	 */
	public void setDataVencimentoDeb(DateTimeDB dataVencimentoDeb) {		
		if(dataVencimentoDeb != null){
			this.dataVencimentoDeb = new DateTimeDB(dataVencimentoDeb.getTime());
		} else {
			this.dataVencimentoDeb = null;
		} 					
	}
	

	/**
	 * Recupera o valor de idCondicaoAssociacao.
	 *
	 * @return o valor de idCondicaoAssociacao
	 */
	public Integer getIdCondicaoAssociacao() {
		return idCondicaoAssociacao;
	}

	/**
	 * Define o valor de idCondicaoAssociacao.
	 *
	 * @param idCondicaoAssociacao o novo valor de idCondicaoAssociacao
	 */
	public void setIdCondicaoAssociacao(Integer idCondicaoAssociacao) {
		this.idCondicaoAssociacao = idCondicaoAssociacao;
	}

	/**
	 * Recupera o valor de numCliente.
	 *
	 * @return o valor de numCliente
	 */
	public Integer getNumCliente() {
		return numCliente;
	}

	/**
	 * Define o valor de numCliente.
	 *
	 * @param numCliente o novo valor de numCliente
	 */
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
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
	 * Recupera o valor de obsRestricao.
	 *
	 * @return o valor de obsRestricao
	 */
	public String getObsRestricao() {
		return obsRestricao;
	}

	/**
	 * Define o valor de obsRestricao.
	 *
	 * @param obsRestricao o novo valor de obsRestricao
	 */
	public void setObsRestricao(String obsRestricao) {
		this.obsRestricao = obsRestricao;
	}

	/**
	 * Recupera o valor de periodoProxDeb.
	 *
	 * @return o valor de periodoProxDeb
	 */
	public Integer getPeriodoProxDeb() {
		return periodoProxDeb;
	}

	/**
	 * Define o valor de periodoProxDeb.
	 *
	 * @param periodoProxDeb o novo valor de periodoProxDeb
	 */
	public void setPeriodoProxDeb(Integer periodoProxDeb) {
		this.periodoProxDeb = periodoProxDeb;
	}

	/**
	 * Recupera o valor de tipoPeriodoDeb.
	 *
	 * @return o valor de tipoPeriodoDeb
	 */
	public Integer getTipoPeriodoDeb() {
		return tipoPeriodoDeb;
	}

	/**
	 * Define o valor de tipoPeriodoDeb.
	 *
	 * @param tipoPeriodoDeb o novo valor de tipoPeriodoDeb
	 */
	public void setTipoPeriodoDeb(Integer tipoPeriodoDeb) {
		this.tipoPeriodoDeb = tipoPeriodoDeb;
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
	 * Recupera o valor de valorDeb.
	 *
	 * @return o valor de valorDeb
	 */
	public BigDecimal getValorDeb() {
		return valorDeb;
	}

	/**
	 * Define o valor de valorDeb.
	 *
	 * @param valorDeb o novo valor de valorDeb
	 */
	public void setValorDeb(BigDecimal valorDeb) {
		this.valorDeb = valorDeb;
	}

	/**
	 * Recupera o valor de valorSaldoAntDevolver.
	 *
	 * @return o valor de valorSaldoAntDevolver
	 */
	public BigDecimal getValorSaldoAntDevolver() {
		return valorSaldoAntDevolver;
	}

	/**
	 * Define o valor de valorSaldoAntDevolver.
	 *
	 * @param valorSaldoAntDevolver o novo valor de valorSaldoAntDevolver
	 */
	public void setValorSaldoAntDevolver(BigDecimal valorSaldoAntDevolver) {
		this.valorSaldoAntDevolver = valorSaldoAntDevolver;
	}

	/**
	 * Recupera o valor de valorSaldoAntDivs.
	 *
	 * @return o valor de valorSaldoAntDivs
	 */
	public BigDecimal getValorSaldoAntDivs() {
		return valorSaldoAntDivs;
	}

	/**
	 * Define o valor de valorSaldoAntDivs.
	 *
	 * @param valorSaldoAntDivs o novo valor de valorSaldoAntDivs
	 */
	public void setValorSaldoAntDivs(BigDecimal valorSaldoAntDivs) {
		this.valorSaldoAntDivs = valorSaldoAntDivs;
	}

	/**
	 * Recupera o valor de valorSaldoAntInteg.
	 *
	 * @return o valor de valorSaldoAntInteg
	 */
	public BigDecimal getValorSaldoAntInteg() {
		return valorSaldoAntInteg;
	}

	/**
	 * Define o valor de valorSaldoAntInteg.
	 *
	 * @param valorSaldoAntInteg o novo valor de valorSaldoAntInteg
	 */
	public void setValorSaldoAntInteg(BigDecimal valorSaldoAntInteg) {
		this.valorSaldoAntInteg = valorSaldoAntInteg;
	}

	/**
	 * Recupera o valor de valorSaldoAntSubsc.
	 *
	 * @return o valor de valorSaldoAntSubsc
	 */
	public BigDecimal getValorSaldoAntSubsc() {
		return valorSaldoAntSubsc;
	}

	/**
	 * Define o valor de valorSaldoAntSubsc.
	 *
	 * @param valorSaldoAntSubsc o novo valor de valorSaldoAntSubsc
	 */
	public void setValorSaldoAntSubsc(BigDecimal valorSaldoAntSubsc) {
		this.valorSaldoAntSubsc = valorSaldoAntSubsc;
	}

	/**
	 * Recupera o valor de valorSaldoAtuDevolver.
	 *
	 * @return o valor de valorSaldoAtuDevolver
	 */
	public BigDecimal getValorSaldoAtuDevolver() {
		return valorSaldoAtuDevolver;
	}

	/**
	 * Define o valor de valorSaldoAtuDevolver.
	 *
	 * @param valorSaldoAtuDevolver o novo valor de valorSaldoAtuDevolver
	 */
	public void setValorSaldoAtuDevolver(BigDecimal valorSaldoAtuDevolver) {
		this.valorSaldoAtuDevolver = valorSaldoAtuDevolver;
	}

	/**
	 * Recupera o valor de valorSaldoAtuDivs.
	 *
	 * @return o valor de valorSaldoAtuDivs
	 */
	public BigDecimal getValorSaldoAtuDivs() {
		return valorSaldoAtuDivs;
	}

	/**
	 * Define o valor de valorSaldoAtuDivs.
	 *
	 * @param valorSaldoAtuDivs o novo valor de valorSaldoAtuDivs
	 */
	public void setValorSaldoAtuDivs(BigDecimal valorSaldoAtuDivs) {
		this.valorSaldoAtuDivs = valorSaldoAtuDivs;
	}

	/**
	 * Recupera o valor de valorSaldoAtuInteg.
	 *
	 * @return o valor de valorSaldoAtuInteg
	 */
	public BigDecimal getValorSaldoAtuInteg() {
		return valorSaldoAtuInteg;
	}

	/**
	 * Define o valor de valorSaldoAtuInteg.
	 *
	 * @param valorSaldoAtuInteg o novo valor de valorSaldoAtuInteg
	 */
	public void setValorSaldoAtuInteg(BigDecimal valorSaldoAtuInteg) {
		this.valorSaldoAtuInteg = valorSaldoAtuInteg;
	}

	/**
	 * Recupera o valor de valorSaldoAtuSubsc.
	 *
	 * @return o valor de valorSaldoAtuSubsc
	 */
	public BigDecimal getValorSaldoAtuSubsc() {
		return valorSaldoAtuSubsc;
	}

	/**
	 * Define o valor de valorSaldoAtuSubsc.
	 *
	 * @param valorSaldoAtuSubsc o novo valor de valorSaldoAtuSubsc
	 */
	public void setValorSaldoAtuSubsc(BigDecimal valorSaldoAtuSubsc) {
		this.valorSaldoAtuSubsc = valorSaldoAtuSubsc;
	}

	/**
	 * Recupera o valor de valorSaldoBloqInt.
	 *
	 * @return o valor de valorSaldoBloqInt
	 */
	public BigDecimal getValorSaldoBloqInt() {
		return valorSaldoBloqInt;
	}

	/**
	 * Define o valor de valorSaldoBloqInt.
	 *
	 * @param valorSaldoBloqInt o novo valor de valorSaldoBloqInt
	 */
	public void setValorSaldoBloqInt(BigDecimal valorSaldoBloqInt) {
		this.valorSaldoBloqInt = valorSaldoBloqInt;
	}

	/**
	 * Recupera o valor de valorSaldoIntegralDispAcum.
	 *
	 * @return o valor de valorSaldoIntegralDispAcum
	 */
	public BigDecimal getValorSaldoIntegralDispAcum() {
		return valorSaldoIntegralDispAcum;
	}

	/**
	 * Define o valor de valorSaldoIntegralDispAcum.
	 *
	 * @param valorSaldoIntegralDispAcum o novo valor de valorSaldoIntegralDispAcum
	 */
	public void setValorSaldoIntegralDispAcum(BigDecimal valorSaldoIntegralDispAcum) {
		this.valorSaldoIntegralDispAcum = valorSaldoIntegralDispAcum;
	}

	/**
	 * Recupera o valor de valorSaldoMedADevAcu.
	 *
	 * @return o valor de valorSaldoMedADevAcu
	 */
	public BigDecimal getValorSaldoMedADevAcu() {
		return valorSaldoMedADevAcu;
	}

	/**
	 * Define o valor de valorSaldoMedADevAcu.
	 *
	 * @param valorSaldoMedADevAcu o novo valor de valorSaldoMedADevAcu
	 */
	public void setValorSaldoMedADevAcu(BigDecimal valorSaldoMedADevAcu) {
		this.valorSaldoMedADevAcu = valorSaldoMedADevAcu;
	}

	/**
	 * Recupera o valor de valorSaldoMedPosAcu.
	 *
	 * @return o valor de valorSaldoMedPosAcu
	 */
	public BigDecimal getValorSaldoMedPosAcu() {
		return valorSaldoMedPosAcu;
	}

	/**
	 * Define o valor de valorSaldoMedPosAcu.
	 *
	 * @param valorSaldoMedPosAcu o novo valor de valorSaldoMedPosAcu
	 */
	public void setValorSaldoMedPosAcu(BigDecimal valorSaldoMedPosAcu) {
		this.valorSaldoMedPosAcu = valorSaldoMedPosAcu;
	}

	/**
	 * Recupera o valor de valorSaldoMedRealAcu.
	 *
	 * @return o valor de valorSaldoMedRealAcu
	 */
	public BigDecimal getValorSaldoMedRealAcu() {
		return valorSaldoMedRealAcu;
	}

	/**
	 * Define o valor de valorSaldoMedRealAcu.
	 *
	 * @param valorSaldoMedRealAcu o novo valor de valorSaldoMedRealAcu
	 */
	public void setValorSaldoMedRealAcu(BigDecimal valorSaldoMedRealAcu) {
		this.valorSaldoMedRealAcu = valorSaldoMedRealAcu;
	}

	/**
	 * @return the numCoop
	 */
	public Integer getNumCoop() {
		return numCoop;
	}

	/**
	 * @param numCoop the numCoop to set
	 */
	public void setNumCoop(Integer numCoop) {
		this.numCoop = numCoop;
	}

	public Integer getIdContaCapital() {
		return idContaCapital;
	}

	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}	
	
}
