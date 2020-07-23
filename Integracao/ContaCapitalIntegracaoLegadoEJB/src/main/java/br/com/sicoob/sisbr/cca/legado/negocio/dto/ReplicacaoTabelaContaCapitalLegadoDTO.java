/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * DTO ReplicacaoTabelaContaCapitalLegadoDTO
 */
public class ReplicacaoTabelaContaCapitalLegadoDTO extends BancoobDto {

	private static final long serialVersionUID = -2785716549998280527L;
	private Integer idContaCapital;
	private Integer idInstituicao;
	private Integer idPessoa;
	private Integer numMatricula;
	private Integer numCliente;
	private Integer codSituacao;
	private DateTime dataMatricula;
	private DateTime dataSaida;
	private BigDecimal valorSaldoAtuSubsc;		
	private BigDecimal valorSaldoAtuInteg;
	private BigDecimal valorSaldoAtuDevolver;
	private BigDecimal valorSaldoBloqInt;
	private Integer numCooperativa;

	// debito indeterminado
	private Boolean bolDebIndeterminado;
	private BigDecimal valorDeb;
	private DateTime dataVencimentoDeb;
	private Integer codFormaDeb;
	private Integer periodoProxDeb;
	private Integer tipoPeriodoDeb;
	private Integer codTipoValorDebito;
	private Long numContaCorrente;
	
	public Boolean getBolDebIndeterminado() {
		return bolDebIndeterminado;
	}
	public void setBolDebIndeterminado(Boolean bolDebIndeterminado) {
		this.bolDebIndeterminado = bolDebIndeterminado;
	}
	public BigDecimal getValorDeb() {
		return valorDeb;
	}
	public void setValorDeb(BigDecimal valorDeb) {
		this.valorDeb = valorDeb;
	}
	public DateTime getDataVencimentoDeb() {
		return dataVencimentoDeb;
	}
	public void setDataVencimentoDeb(DateTime dataVencimentoDeb) {
		this.dataVencimentoDeb = dataVencimentoDeb;
	}
	public Integer getCodFormaDeb() {
		return codFormaDeb;
	}
	public void setCodFormaDeb(Integer codFormaDeb) {
		this.codFormaDeb = codFormaDeb;
	}
	public Integer getPeriodoProxDeb() {
		return periodoProxDeb;
	}
	public void setPeriodoProxDeb(Integer periodoProxDeb) {
		this.periodoProxDeb = periodoProxDeb;
	}
	public Integer getTipoPeriodoDeb() {
		return tipoPeriodoDeb;
	}
	public void setTipoPeriodoDeb(Integer tipoPeriodoDeb) {
		this.tipoPeriodoDeb = tipoPeriodoDeb;
	}
	public Integer getCodTipoValorDebito() {
		return codTipoValorDebito;
	}
	public void setCodTipoValorDebito(Integer codTipoValorDebito) {
		this.codTipoValorDebito = codTipoValorDebito;
	}
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}	
	
	public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Integer getNumMatricula() {
		return numMatricula;
	}
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}
	public Integer getNumCliente() {
		return numCliente;
	}
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}
	public Integer getCodSituacao() {
		return codSituacao;
	}
	public void setCodSituacao(Integer codSituacao) {
		this.codSituacao = codSituacao;
	}
	public DateTime getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(DateTime dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	public DateTime getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(DateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public BigDecimal getValorSaldoAtuSubsc() {
		return valorSaldoAtuSubsc;
	}
	public void setValorSaldoAtuSubsc(BigDecimal valorSaldoAtuSubsc) {
		this.valorSaldoAtuSubsc = valorSaldoAtuSubsc;
	}
	public BigDecimal getValorSaldoAtuInteg() {
		return valorSaldoAtuInteg;
	}
	public void setValorSaldoAtuInteg(BigDecimal valorSaldoAtuInteg) {
		this.valorSaldoAtuInteg = valorSaldoAtuInteg;
	}
	public BigDecimal getValorSaldoAtuDevolver() {
		return valorSaldoAtuDevolver;
	}
	public void setValorSaldoAtuDevolver(BigDecimal valorSaldoAtuDevolver) {
		this.valorSaldoAtuDevolver = valorSaldoAtuDevolver;
	}
	public BigDecimal getValorSaldoBloqInt() {
		return valorSaldoBloqInt;
	}
	public void setValorSaldoBloqInt(BigDecimal valorSaldoBloqInt) {
		this.valorSaldoBloqInt = valorSaldoBloqInt;
	}
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
	
}
