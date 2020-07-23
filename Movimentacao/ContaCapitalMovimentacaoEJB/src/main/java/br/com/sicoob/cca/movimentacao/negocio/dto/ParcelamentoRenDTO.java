/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

// TODO: Auto-generated Javadoc
/**
 * A Classe ParcelamentoRenDTO.
 *
 * @author Antonio.Genaro
 */
public class ParcelamentoRenDTO extends BancoobDto implements Comparable<ParcelamentoRenDTO>{

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6367867654165034254L;
	
	/** O atributo numParcela. */
	private Short numParcela;
	
	/** O atributo dataVencimento. */
	private DateTime dataVencimento;
	
	/** O atributo valorParcela. */
	private BigDecimal valorParcela;
	
	/** O atributo idTipoInteg. */
	private Short idTipoInteg;
	
	/** O atributo descNumMatriculaFunc. */
	private String descNumMatriculaFunc;
	
	/** O atributo numContaCorrente. */
	private Long numContaCorrente;
	
	/** O atributo dataVencimentoOrdenacao. */
	private String dataVencimentoOrdenacao;

	/** O atributo dataSituacao. */
	private String dataSituacao;
	
	/** O atributo descSituacao. */
	private String descSituacao;
	
	/** O atributo descFormaPagamento. */
	private String descFormaPagamento;
	
	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** O atributo descTipoParcelamento. */
	private String descTipoParcelamento;
	
	/** O atributo numParcelamento. */
	private Short numParcelamento;	
	
	/** O atributo qtdParcelas. */
	private Integer qtdParcelas;	
	
	/** O atributo valorTotal. */
	private BigDecimal valorTotal;
	
	/** O atributo valorAberto. */
	private BigDecimal valorAberto;
	
	/** O atributo idTipoParcelamento. */
	private Short idTipoParcelamento;	
	
	/** O atributo idSituacaoParcelamento. */
	private Short idSituacaoParcelamento;
	
	/** O atributo idContaCapital. */
	private Integer idContaCapital;
	
	/** O atributo idParcelamento. */
	private Long idParcelamento;
	
	/** O atributo idMotivoDevolucao. */
	private Short idMotivoDevolucao;
		
	
	/**
	 * Recupera o valor de idMotivoDevolucao.
	 *
	 * @return o valor de idMotivoDevolucao
	 */
	public Short getIdMotivoDevolucao() {
		return idMotivoDevolucao;
	}

	/**
	 * Define o valor de idMotivoDevolucao.
	 *
	 * @param idMotivoDevolucao o novo valor de idMotivoDevolucao
	 */
	public void setIdMotivoDevolucao(Short idMotivoDevolucao) {
		this.idMotivoDevolucao = idMotivoDevolucao;
	}

	/**
	 * Recupera o valor de idContaCapital.
	 *
	 * @return o valor de idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}

	/**
	 * Define o valor de idContaCapital.
	 *
	 * @param idContaCapital o novo valor de idContaCapital
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}

	/**
	 * Recupera o valor de numParcela.
	 *
	 * @return o valor de numParcela
	 */
	public Short getNumParcela() {
		return numParcela;
	}


	/**
	 * Define o valor de numParcela.
	 *
	 * @param numParcela o novo valor de numParcela
	 */
	public void setNumParcela(Short numParcela) {
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
	 * Recupera o valor de idTipoInteg.
	 *
	 * @return o valor de idTipoInteg
	 */
	public Short getIdTipoInteg() {
		return idTipoInteg;
	}


	/**
	 * Define o valor de idTipoInteg.
	 *
	 * @param idTipoInteg o novo valor de idTipoInteg
	 */
	public void setIdTipoInteg(Short idTipoInteg) {
		this.idTipoInteg = idTipoInteg;
	}


	/**
	 * Recupera o valor de descNumMatriculaFunc.
	 *
	 * @return o valor de descNumMatriculaFunc
	 */
	public String getDescNumMatriculaFunc() {
		return descNumMatriculaFunc;
	}


	/**
	 * Define o valor de descNumMatriculaFunc.
	 *
	 * @param descNumMatriculaFunc o novo valor de descNumMatriculaFunc
	 */
	public void setDescNumMatriculaFunc(String descNumMatriculaFunc) {
		this.descNumMatriculaFunc = descNumMatriculaFunc;
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
	 * Recupera o valor de dataVencimentoOrdenacao.
	 *
	 * @return o valor de dataVencimentoOrdenacao
	 */
	public String getDataVencimentoOrdenacao() {
		return dataVencimentoOrdenacao;
	}


	/**
	 * Define o valor de dataVencimentoOrdenacao.
	 *
	 * @param dataVencimentoOrdenacao o novo valor de dataVencimentoOrdenacao
	 */
	public void setDataVencimentoOrdenacao(String dataVencimentoOrdenacao) {
		this.dataVencimentoOrdenacao = dataVencimentoOrdenacao;
	}


	/**
	 * Recupera o valor de dataSituacao.
	 *
	 * @return o valor de dataSituacao
	 */
	public String getDataSituacao() {
		return dataSituacao;
	}


	/**
	 * Define o valor de dataSituacao.
	 *
	 * @param dataSituacao o novo valor de dataSituacao
	 */
	public void setDataSituacao(String dataSituacao) {
		this.dataSituacao = dataSituacao;
	}


	/**
	 * Recupera o valor de descSituacao.
	 *
	 * @return o valor de descSituacao
	 */
	public String getDescSituacao() {
		return descSituacao;
	}


	/**
	 * Define o valor de descSituacao.
	 *
	 * @param descSituacao o novo valor de descSituacao
	 */
	public void setDescSituacao(String descSituacao) {
		this.descSituacao = descSituacao;
	}


	/**
	 * Recupera o valor de descFormaPagamento.
	 *
	 * @return o valor de descFormaPagamento
	 */
	public String getDescFormaPagamento() {
		return descFormaPagamento;
	}


	/**
	 * Define o valor de descFormaPagamento.
	 *
	 * @param descFormaPagamento o novo valor de descFormaPagamento
	 */
	public void setDescFormaPagamento(String descFormaPagamento) {
		this.descFormaPagamento = descFormaPagamento;
	}


	/**
	 * Recupera o valor de numContaCapital.
	 *
	 * @return o valor de numContaCapital
	 */
	public Integer getNumContaCapital() {
		return numContaCapital;
	}


	/**
	 * Define o valor de numContaCapital.
	 *
	 * @param numContaCapital o novo valor de numContaCapital
	 */
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}


	/**
	 * Recupera o valor de descTipoParcelamento.
	 *
	 * @return o valor de descTipoParcelamento
	 */
	public String getDescTipoParcelamento() {
		return descTipoParcelamento;
	}


	/**
	 * Define o valor de descTipoParcelamento.
	 *
	 * @param descTipoParcelamento o novo valor de descTipoParcelamento
	 */
	public void setDescTipoParcelamento(String descTipoParcelamento) {
		this.descTipoParcelamento = descTipoParcelamento;
	}


	/**
	 * Recupera o valor de numParcelamento.
	 *
	 * @return o valor de numParcelamento
	 */
	public Short getNumParcelamento() {
		return numParcelamento;
	}


	/**
	 * Define o valor de numParcelamento.
	 *
	 * @param numParcelamento o novo valor de numParcelamento
	 */
	public void setNumParcelamento(Short numParcelamento) {
		this.numParcelamento = numParcelamento;
	}


	/**
	 * Recupera o valor de qtdParcelas.
	 *
	 * @return o valor de qtdParcelas
	 */
	public Integer getQtdParcelas() {
		return qtdParcelas;
	}


	/**
	 * Define o valor de qtdParcelas.
	 *
	 * @param qtdParcelas o novo valor de qtdParcelas
	 */
	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}


	/**
	 * Recupera o valor de valorTotal.
	 *
	 * @return o valor de valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}


	/**
	 * Define o valor de valorTotal.
	 *
	 * @param valorTotal o novo valor de valorTotal
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}


	/**
	 * Recupera o valor de valorAberto.
	 *
	 * @return o valor de valorAberto
	 */
	public BigDecimal getValorAberto() {
		return valorAberto;
	}


	/**
	 * Define o valor de valorAberto.
	 *
	 * @param valorAberto o novo valor de valorAberto
	 */
	public void setValorAberto(BigDecimal valorAberto) {
		this.valorAberto = valorAberto;
	}


	/**
	 * Recupera o valor de idTipoParcelamento.
	 *
	 * @return o valor de idTipoParcelamento
	 */
	public Short getIdTipoParcelamento() {
		return idTipoParcelamento;
	}


	/**
	 * Define o valor de idTipoParcelamento.
	 *
	 * @param idTipoParcelamento o novo valor de idTipoParcelamento
	 */
	public void setIdTipoParcelamento(Short idTipoParcelamento) {
		this.idTipoParcelamento = idTipoParcelamento;
	}


	/**
	 * Recupera o valor de idSituacaoParcelamento.
	 *
	 * @return o valor de idSituacaoParcelamento
	 */
	public Short getIdSituacaoParcelamento() {
		return idSituacaoParcelamento;
	}


	/**
	 * Define o valor de idSituacaoParcelamento.
	 *
	 * @param idSituacaoParcelamento o novo valor de idSituacaoParcelamento
	 */
	public void setIdSituacaoParcelamento(Short idSituacaoParcelamento) {
		this.idSituacaoParcelamento = idSituacaoParcelamento;
	}


	/**
	 * Recupera o valor de idParcelamento.
	 *
	 * @return o valor de idParcelamento
	 */
	public Long getIdParcelamento() {
		return idParcelamento;
	}


	/**
	 * Define o valor de idParcelamento.
	 *
	 * @param idParcelamento o novo valor de idParcelamento
	 */
	public void setIdParcelamento(Long idParcelamento) {
		this.idParcelamento = idParcelamento;
	}


	/**
	 * {@inheritDoc}
	 */
	public int compareTo(ParcelamentoRenDTO o) {
		return this.getDataVencimentoOrdenacao().compareTo(o.getDataVencimentoOrdenacao());
	}

	@Override
	public String toString() {
		return "ParcelamentoRenDTO [numParcela=" + numParcela + ", dataVencimento=" + dataVencimento + ", valorParcela="
				+ valorParcela + ", idTipoInteg=" + idTipoInteg + ", descNumMatriculaFunc=" + descNumMatriculaFunc
				+ ", numContaCorrente=" + numContaCorrente + ", idTipoParcelamento=" + idTipoParcelamento
				+ ", idMotivoDevolucao=" + idMotivoDevolucao + ", idSituacaoParcelamento=" + idSituacaoParcelamento + "]";
	}
	
}
