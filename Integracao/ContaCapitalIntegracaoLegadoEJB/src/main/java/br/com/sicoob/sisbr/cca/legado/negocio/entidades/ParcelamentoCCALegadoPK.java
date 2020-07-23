/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * A Classe ParcelamentoCCALegadoPK.
 */
@Embeddable
public class ParcelamentoCCALegadoPK implements Serializable {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** O atributo contaCapitalLegado. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "numMatricula", referencedColumnName = "numMatricula")
	private ContaCapitalLegado contaCapitalLegado;
	
	/** O atributo numParcelamento. */
	@Column(name="NumParcelamento")
	private Integer numParcelamento;
	
	/** O atributo numParcela. */
	@Column(name="NumParcela")
	private Integer numParcela;
	
	/** O atributo codTipoParcelamento. */
	@Column(name="CodTipoParcelamento")
	private Integer codTipoParcelamento;	
	
	/**
	 * Recupera o valor de contaCapitalLegado.
	 *
	 * @return o valor de contaCapitalLegado
	 */
	public ContaCapitalLegado getContaCapitalLegado() {
		return contaCapitalLegado;
	}

	/**
	 * Define o valor de contaCapitalLegado.
	 *
	 * @param contaCapitalLegado o novo valor de contaCapitalLegado
	 */
	public void setContaCapitalLegado(ContaCapitalLegado contaCapitalLegado) {
		this.contaCapitalLegado = contaCapitalLegado;
	}

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
	 * Recupera o valor de codTipoParcelamento.
	 *
	 * @return o valor de codTipoParcelamento
	 */
	public Integer getCodTipoParcelamento() {
		return codTipoParcelamento;
	}

	/**
	 * Define o valor de codTipoParcelamento.
	 *
	 * @param codTipoParcelamento o novo valor de codTipoParcelamento
	 */
	public void setCodTipoParcelamento(Integer codTipoParcelamento) {
		this.codTipoParcelamento = codTipoParcelamento;
	}

}