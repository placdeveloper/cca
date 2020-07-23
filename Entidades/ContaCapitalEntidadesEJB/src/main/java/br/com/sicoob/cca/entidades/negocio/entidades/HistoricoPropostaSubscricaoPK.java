/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * Historico valor cota
 * @author marco.nascimento
 * @since 25/11/2015
 * @see HistoricoPropostaSubscricaoPK
 */
@Embeddable
public class HistoricoPropostaSubscricaoPK extends BancoobChavePrimaria {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319390198486333170L;

	/**
	 * Instancia um novo HistoricoPropostaSubscricaoPK.
	 */
	public HistoricoPropostaSubscricaoPK() {
		
	}
	
	/**
	 * Instancia um novo HistoricoPropostaSubscricaoPK.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param dataHoraAtualizacao o valor de data hora atualizacao
	 */
	public HistoricoPropostaSubscricaoPK(Integer idContaCapital, DateTimeDB dataHoraAtualizacao) {
		this.idContaCapital = idContaCapital;
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
	
	
	/** O atributo idContaCapital. */
	@Column(name = "IDCONTACAPITAL", nullable = false)
	private Integer idContaCapital;
	
	/** O atributo dataHoraAtualizacao. */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;

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
}