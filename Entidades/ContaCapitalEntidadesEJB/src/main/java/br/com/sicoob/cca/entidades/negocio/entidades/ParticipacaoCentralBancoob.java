/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * Participação central
 * @author antonio.genaro
 * @since 04/06/2014
 */
@Entity
@Table(name = "PARTICIPACAOCENTRALBANCOOB", schema = "cca")
public class ParticipacaoCentralBancoob extends ContaCapitalEntidade<ParticipacaoCentralBancoobPK> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2346598680360650483L;
	
	/**
	 * PK
	 */
	@EmbeddedId
	private ParticipacaoCentralBancoobPK id;
	
	/**
	 * Valor da participação da central no bancoob
	 */
	@Column(name = "VALORPARTICIPACAO", nullable = false)
	private BigDecimal valorParticipacao;	
	
	/**
	 * Data/hora da atualização do registro
	 */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;	
	/**
	 * Identificador do usuario
	 */
	@Column(name = "IDUSUARIO", length = 40, nullable = false)
	private String idUsuario;
	/**
	 * @return the id
	 */
	/**
	 * @return the id
	 */
	public ParticipacaoCentralBancoobPK getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(ParticipacaoCentralBancoobPK id) {
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