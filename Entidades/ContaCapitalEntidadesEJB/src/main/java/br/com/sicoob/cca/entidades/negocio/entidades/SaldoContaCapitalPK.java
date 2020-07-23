/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * Chave primária de saldo de conta capital
 * @author marco.nascimento
 * @see SaldoContaCapital
 * @since 21/05/2014
 */
@Embeddable
public class SaldoContaCapitalPK extends BancoobChavePrimaria {

	/**
	 * Conta Capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDCONTACAPITAL")
	private ContaCapital contaCapital;
	
	/**
	 * Identificador da instituição no SCI
	 */
	@Column(name = "IDINSTITUICAO")
	private Long idInstituicao;
	
	/**
	 * Data do saldo na Conta Capital
	 */
	@Column(name = "DATASALDO")
	private DateTimeDB dataSaldo;

	/**
	 * @return the contaCapital
	 */
	public ContaCapital getContaCapital() {
		return contaCapital;
	}

	/**
	 * @param contaCapital the contaCapital to set
	 */
	public void setContaCapital(ContaCapital contaCapital) {
		this.contaCapital = contaCapital;
	}

	/**
	 * @return the idInstituicao
	 */
	public Long getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * @return the dataSaldo
	 */
	public DateTimeDB getDataSaldo() {
		return dataSaldo;
	}

	/**
	 * @param dataSaldo the dataSaldo to set
	 */
	public void setDataSaldo(DateTimeDB dataSaldo) {
		this.dataSaldo = dataSaldo;
	}
}