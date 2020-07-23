/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

// TODO: Auto-generated Javadoc
/**
 * A Classe ParcelamentoCapitalDTO.
 *
 * @author Antonio.Genaro
 */
public class ParcelamentoCapitalDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6137150194847295217L;
	
	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numParcelamento. */
	private Short numParcelamento;
	
	/** O atributo numParcela. */
	private Short numParcela;
	
	/** O atributo numContaCorrente. */
	private Long numContaCorrente;	
	
	/** O atributo dataVencimento. */
	private DateTimeDB dataVencimento;
	
	/** O atributo valorParcela. */
	private BigDecimal valorParcela;	
	
	/** O atributo idTipoIntegralizacao. */
	private Short idTipoIntegralizacao;
	
	/** O atributo idTipoParcelamento. */
	private Short idTipoParcelamento ;
	
	/** O atributo idSituacaoParcelamento. */
	private Short idSituacaoParcelamento;
	
	/** O atributo idCanal. */
	private Integer codCanal;
	
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
	 * Recupera o valor de dataVencimento.
	 *
	 * @return o valor de dataVencimento
	 */
	public DateTimeDB getDataVencimento() {
		return dataVencimento;
	}
	
	/**
	 * Define o valor de dataVencimento.
	 *
	 * @param dataVencimento o novo valor de dataVencimento
	 */
	public void setDataVencimento(DateTimeDB dataVencimento) {
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
	 * Recupera o valor de idTipoIntegralizacao.
	 *
	 * @return o valor de idTipoIntegralizacao
	 */
	public Short getIdTipoIntegralizacao() {
		return idTipoIntegralizacao;
	}
	
	/**
	 * Define o valor de idTipoIntegralizacao.
	 *
	 * @param idTipoIntegralizacao o novo valor de idTipoIntegralizacao
	 */
	public void setIdTipoIntegralizacao(Short idTipoIntegralizacao) {
		this.idTipoIntegralizacao = idTipoIntegralizacao;
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

	public Short getNumParcelamento() {
		return numParcelamento;
	}

	public void setNumParcelamento(Short numParcelamento) {
		this.numParcelamento = numParcelamento;
	}

}
