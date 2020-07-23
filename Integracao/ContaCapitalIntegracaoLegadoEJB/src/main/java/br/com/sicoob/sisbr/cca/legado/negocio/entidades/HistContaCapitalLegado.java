/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * A Classe HistContaCapitalLegado.
 */
@Entity
@Table(name="HistContaCapital", schema="dbo")
public class HistContaCapitalLegado extends ContaCapitalIntegracaoLegadoEntidade {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** O atributo histContaCapitalLegadoPK. */
	@EmbeddedId
	private HistContaCapitalLegadoPK histContaCapitalLegadoPK;
	
	/** O atributo codSituacao. */
	@Column(name="CodSituacao")
	private Integer codSituacao;
	
	/** O atributo bolAtualizado. */
	@Column(name="BolAtualizado")
	private Boolean bolAtualizado;
	
	/** O atributo numCoop. */
	@Transient
	private Integer numCoop;
	
	/**
	 * Recupera o valor de histContaCapitalLegadoPK.
	 *
	 * @return o valor de histContaCapitalLegadoPK
	 */
	public HistContaCapitalLegadoPK getHistContaCapitalLegadoPK() {
		return histContaCapitalLegadoPK;
	}
	
	/**
	 * Define o valor de histContaCapitalLegadoPK.
	 *
	 * @param histContaCapitalLegadoPK o novo valor de histContaCapitalLegadoPK
	 */
	public void setHistContaCapitalLegadoPK(
			HistContaCapitalLegadoPK histContaCapitalLegadoPK) {
		this.histContaCapitalLegadoPK = histContaCapitalLegadoPK;
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
	 * Recupera o valor de bolAtualizado.
	 *
	 * @return o valor de bolAtualizado
	 */
	public Boolean getBolAtualizado() {
		return bolAtualizado;
	}
	
	/**
	 * Define o valor de bolAtualizado.
	 *
	 * @param bolAtualizado o novo valor de bolAtualizado
	 */
	public void setBolAtualizado(Boolean bolAtualizado) {
		this.bolAtualizado = bolAtualizado;
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
}