/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * DTO ReplicacaoTabelaParcelamentoCCALegadoDTO
 */
public class ReplicacaoTabelaParcelamentoCCALegadoDTO extends BancoobDto {

	private static final long serialVersionUID = -3156859910129863875L;
	
	private Integer numMatricula;
	private Integer numParcelamento;
	private Integer numParcela;
	private Integer codTipoParcelamento;	
	private DateTime dataVencParcela;
	private DateTime dataSituacaoParcela;
	private BigDecimal valorParcela;
	private Integer codModoLanc;
	private Integer codSituacaoParcela;
	private Long numContaCorrente;
	private DateTime dataEnvioCob;
	private String uIDTrabalha;
	private Integer codMotivoDevolucao;
	private String descObservacao;	
	private Long idParcelamentoContaCapital;
	private Integer idInstituicao;
	private Integer numCooperativa;

	public Integer getNumMatricula() {
		return numMatricula;
	}
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}	

	public Integer getNumParcelamento() {
		return numParcelamento;
	}
	public void setNumParcelamento(Integer numParcelamento) {
		this.numParcelamento = numParcelamento;
	}
	public Integer getNumParcela() {
		return numParcela;
	}
	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}
	public Integer getCodTipoParcelamento() {
		return codTipoParcelamento;
	}
	public void setCodTipoParcelamento(Integer codTipoParcelamento) {
		this.codTipoParcelamento = codTipoParcelamento;
	}
	public DateTime getDataVencParcela() {
		return dataVencParcela;
	}
	public void setDataVencParcela(DateTime dataVencParcela) {
		this.dataVencParcela = dataVencParcela;
	}
	public DateTime getDataSituacaoParcela() {
		return dataSituacaoParcela;
	}
	public void setDataSituacaoParcela(DateTime dataSituacaoParcela) {
		this.dataSituacaoParcela = dataSituacaoParcela;
	}
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	public Integer getCodModoLanc() {
		return codModoLanc;
	}
	public void setCodModoLanc(Integer codModoLanc) {
		this.codModoLanc = codModoLanc;
	}
	public Integer getCodSituacaoParcela() {
		return codSituacaoParcela;
	}
	public void setCodSituacaoParcela(Integer codSituacaoParcela) {
		this.codSituacaoParcela = codSituacaoParcela;
	}
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
	public DateTime getDataEnvioCob() {
		return dataEnvioCob;
	}
	public void setDataEnvioCob(DateTime dataEnvioCob) {
		this.dataEnvioCob = dataEnvioCob;
	}
	public String getuIDTrabalha() {
		return uIDTrabalha;
	}
	public void setuIDTrabalha(String uIDTrabalha) {
		this.uIDTrabalha = uIDTrabalha;
	}
	public Integer getCodMotivoDevolucao() {
		return codMotivoDevolucao;
	}
	public void setCodMotivoDevolucao(Integer codMotivoDevolucao) {
		this.codMotivoDevolucao = codMotivoDevolucao;
	}
	public String getDescObservacao() {
		return descObservacao;
	}
	public void setDescObservacao(String descObservacao) {
		this.descObservacao = descObservacao;
	}
	public Long getIdParcelamentoContaCapital() {
		return idParcelamentoContaCapital;
	}
	public void setIdParcelamentoContaCapital(Long idParcelamentoContaCapital) {
		this.idParcelamentoContaCapital = idParcelamentoContaCapital;
	}
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	
}
