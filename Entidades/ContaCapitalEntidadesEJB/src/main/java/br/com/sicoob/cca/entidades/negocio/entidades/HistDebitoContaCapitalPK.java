/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * PK HistDebitoContaCapitalPK
 */
@Embeddable
public class HistDebitoContaCapitalPK extends BancoobChavePrimaria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4734734631695026305L;

	/**
	 * Instancia um novo HistDebitoCapitalPK.
	 */
	public HistDebitoContaCapitalPK() {
		
	}
	
	/**
	 * Instancia um novo HistDebitoCapitalPK.
	 *
	 * @param idDebitoContaCapital o valor de id conta capital
	 * @param dataHoraDebito o valor de data hora atualizacao
	 */
	public HistDebitoContaCapitalPK(Long idDebitoContaCapital, DateTimeDB dataHoraDebito) {
		this.idDebitoContaCapital = idDebitoContaCapital;
		this.dataHoraDebito = dataHoraDebito;
	}
	
	/**
	 * Chave de Debito Conta Capital
	 */
	@Column(name = "IDDEBITOCONTACAPITAL")
	private Long idDebitoContaCapital;
	
	/**
	 * Data e hora da efetivação do débito.
	 */
	@Column(name = "DATAHORADEBITO", nullable = false)
	private DateTimeDB dataHoraDebito;

	/**
	 * @return the idDebitoContaCapital
	 */
	public Long getIdDebitoContaCapital() {
		return idDebitoContaCapital;
	}

	/**
	 * @param idDebitoContaCapital the idDebitoContaCapital to set
	 */
	public void setIdDebitoContaCapital(Long idDebitoContaCapital) {
		this.idDebitoContaCapital = idDebitoContaCapital;
	}

	/**
	 * @return the dataHoraDebito
	 */
	public DateTimeDB getDataHoraDebito() {
		return dataHoraDebito;
	}

	/**
	 * @param dataHoraDebito the dataHoraDebito to set
	 */
	public void setDataHoraDebito(DateTimeDB dataHoraDebito) {
		this.dataHoraDebito = dataHoraDebito;
	}
}