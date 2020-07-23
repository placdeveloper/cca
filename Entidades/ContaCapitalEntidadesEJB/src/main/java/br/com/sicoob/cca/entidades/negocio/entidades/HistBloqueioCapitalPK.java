/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * PK HistBloqueioCapitalPK
 */
@Embeddable
public class HistBloqueioCapitalPK extends BancoobChavePrimaria {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4686187953364467383L;

	/**
	 * Instancia um novo HistoricoPropostaSubscricaoPK.
	 */
	public HistBloqueioCapitalPK() {
		
	}
	
	/**
	 * Instancia um novo HistoricoPropostaSubscricaoPK.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param dataHoraAtualizacao o valor de data hora atualizacao
	 */
	public HistBloqueioCapitalPK(Integer idBloqueioCapital) {
		this.idBloqueioCapital = idBloqueioCapital;
		this.dataHoraAtualizacao =  new DateTimeDB();
	}
	
	
	@Column(name = "IDBLOQUEIOCAPITAL")
	private Integer idBloqueioCapital;		
	
	/**
	 * Data e hora de atualização do cadastro
	 */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;

	public Integer getIdBloqueioCapital() {
		return idBloqueioCapital;
	}

	public void setIdBloqueioCapital(Integer idBloqueioCapital) {
		this.idBloqueioCapital = idBloqueioCapital;
	}

	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}

	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}	
	
}
