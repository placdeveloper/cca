/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe CapaLoteCapitalLegadoPK.
 */
@Embeddable
public class CapaLoteCapitalLegadoPK extends ContaCapitalIntegracaoLegadoEntidade {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2997085286365208101L;	
	
	/**
	 * Construtor
	 */
	public CapaLoteCapitalLegadoPK() {
		super();
	}

	/**
	 * Construtor
	 */
	public CapaLoteCapitalLegadoPK(DateTimeDB dataLote, Integer numLoteLanc) {
		super();
		this.dataLote = dataLote;
		this.numLoteLanc = numLoteLanc;
	}

	/** O atributo dataLote. */
	@Column(name="DataLote")
	private DateTimeDB dataLote;
	
	/** O atributo numLoteLanc. */
	@Column(name="NumLoteLanc")
	private Integer numLoteLanc;
	
	/**
	 * Recupera o valor de dataLote.
	 *
	 * @return o valor de dataLote
	 */
	public DateTimeDB getDataLote() {
		return dataLote;
	}
	
	/**
	 * Define o valor de dataLote.
	 *
	 * @param dataLote o novo valor de dataLote
	 */
	public void setDataLote(DateTimeDB dataLote) {
		this.dataLote = dataLote;
	}
	
	/**
	 * Recupera o valor de numLoteLanc.
	 *
	 * @return o valor de numLoteLanc
	 */
	public Integer getNumLoteLanc() {
		return numLoteLanc;
	}
	
	/**
	 * Define o valor de numLoteLanc.
	 *
	 * @param numLoteLanc o novo valor de numLoteLanc
	 */
	public void setNumLoteLanc(Integer numLoteLanc) {
		this.numLoteLanc = numLoteLanc;
	}
}
