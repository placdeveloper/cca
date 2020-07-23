/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Historico Participação central
 * @author antonio.genaro
 * @since 04/06/2014
 */
@Entity
@Table(name = "HISTPARTICIPACAOCENTRALBANCOOB", schema = "cca")
public class HistParticipacaoCentralBancoob extends ContaCapitalEntidade<HistParticipacaoCentralBancoobPK> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7436487579020053125L;
	
	/**
	 * Identificador da instituição central que se relaciona com o bancoob
	 */
	@EmbeddedId
	private HistParticipacaoCentralBancoobPK id;
	
	/** O atributo valorParticipacao. */
	@Column(name = "VALORPARTICIPACAO", nullable = false)
	private BigDecimal valorParticipacao;	
	
	/**
	 * Identificador do usuario
	 */
	@Column(name = "IDUSUARIO", length = 40, nullable = false)
	private String idUsuario;

	/**
	 * @return the id
	 */
	public HistParticipacaoCentralBancoobPK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(HistParticipacaoCentralBancoobPK id) {
		this.id = id;
	}

	/**
	 * @return the valorParticipacao
	 */
	public BigDecimal getValorParticipacao() {
		return valorParticipacao;
	}

	/**
	 * @param valorParticipacao the valorParticipacao to set
	 */
	public void setValorParticipacao(BigDecimal valorParticipacao) {
		this.valorParticipacao = valorParticipacao;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
}