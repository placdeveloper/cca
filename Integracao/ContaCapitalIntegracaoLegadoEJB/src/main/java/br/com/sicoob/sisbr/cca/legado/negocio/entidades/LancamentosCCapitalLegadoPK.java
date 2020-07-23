/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * A Classe LancamentosCCapitalLegadoPK.
 */
@Embeddable
public class LancamentosCCapitalLegadoPK  extends ContaCapitalIntegracaoLegadoEntidade {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5474207611120990055L;
	
	/**
	 * Construtor
	 */
	public LancamentosCCapitalLegadoPK() {
		super();
	}

	/**
	 * Construtor
	 * @param capaLoteCapitalLegado
	 * @param numSeqLanc
	 */
	public LancamentosCCapitalLegadoPK(CapaLoteCapitalLegado capaLoteCapitalLegado, Integer numSeqLanc) {
		super();
		this.capaLoteCapitalLegado = capaLoteCapitalLegado;
		this.numSeqLanc = numSeqLanc;
	}

	/** O atributo capaLoteCapitalLegado. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({@JoinColumn(name = "dataLote", referencedColumnName = "dataLote"), @JoinColumn(name ="numLoteLanc", referencedColumnName="numLoteLanc")})
	private CapaLoteCapitalLegado capaLoteCapitalLegado;	
	
	/** O atributo numSeqLanc. */
	@Column(name="NumSeqLanc")
	private Integer numSeqLanc;
	
	/**
	 * Recupera o valor de capaLoteCapitalLegado.
	 *
	 * @return o valor de capaLoteCapitalLegado
	 */
	public CapaLoteCapitalLegado getCapaLoteCapitalLegado() {
		return capaLoteCapitalLegado;
	}
	
	/**
	 * Define o valor de capaLoteCapitalLegado.
	 *
	 * @param capaLoteCapitalLegado o novo valor de capaLoteCapitalLegado
	 */
	public void setCapaLoteCapitalLegado(CapaLoteCapitalLegado capaLoteCapitalLegado) {
		this.capaLoteCapitalLegado = capaLoteCapitalLegado;
	}
	
	/**
	 * Recupera o valor de numSeqLanc.
	 *
	 * @return o valor de numSeqLanc
	 */
	public Integer getNumSeqLanc() {
		return numSeqLanc;
	}
	
	/**
	 * Define o valor de numSeqLanc.
	 *
	 * @param numSeqLanc o novo valor de numSeqLanc
	 */
	public void setNumSeqLanc(Integer numSeqLanc) {
		this.numSeqLanc = numSeqLanc;
	}


	
}
