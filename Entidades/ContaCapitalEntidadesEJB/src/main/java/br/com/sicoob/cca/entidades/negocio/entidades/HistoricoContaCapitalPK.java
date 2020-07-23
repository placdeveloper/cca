/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * Historico conta capital
 * @author marco.nascimento
 * @since 16/05/2014
 * @see HistoricoContaCapital
 */
@Embeddable
public class HistoricoContaCapitalPK extends BancoobChavePrimaria {
	
	/**
	 * no args constructor 
	 */
	public HistoricoContaCapitalPK() {
		
	}
	
	/**
	 * Cria chave do historico de conta capital 
	 * @see ContaCapital, HistoricoContaCapital 
	 * @param idContaCapital
	 */
	public HistoricoContaCapitalPK(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
		this.dataHoraAtualizacao = new DateTimeDB();
	}
	

	/**
	 * Identificador da Conta Capital
	 */
	@Column(name = "IDCONTACAPITAL", nullable = false)
	private Integer idContaCapital;
	
	/**
	 * Data e hora de atualização do cadastro
	 */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;

	/**
	 * @return the idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}

	/**
	 * @param idContaCapital the idContaCapital to set
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}

	/**
	 * @return the dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}

	/**
	 * @param dataHoraAtualizacao the dataHoraAtualizacao to set
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
}