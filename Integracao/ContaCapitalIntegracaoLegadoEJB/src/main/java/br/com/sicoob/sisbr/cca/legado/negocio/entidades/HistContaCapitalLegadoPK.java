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

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe HistContaCapitalLegadoPK.
 */
@Embeddable
public class HistContaCapitalLegadoPK implements Serializable {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 231755915401333124L;

	/** O atributo contaCapitalLegado. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "numMatricula", referencedColumnName = "numMatricula")
	private ContaCapitalLegado contaCapitalLegado;
	
	/** O atributo dataOcorrencia. */
	@Column(name="DataOcorrencia")	
	private DateTimeDB dataOcorrencia;

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
	 * Recupera o valor de dataOcorrencia.
	 *
	 * @return o valor de dataOcorrencia
	 */
	public DateTimeDB getDataOcorrencia() {
		return dataOcorrencia;
	}
	
	/**
	 * Define o valor de dataOcorrencia.
	 *
	 * @param dataOcorrencia o novo valor de dataOcorrencia
	 */
	public void setDataOcorrencia(DateTimeDB dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	
}
