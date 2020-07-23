/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe ParticipanteContaCorrenteIntegracaoRetDTO.
 */
public class ParticipanteContaCorrenteIntegracaoRetDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7623230126614105342L;
	
	/** O atributo idParticipanteConta. */
	private Long idParticipanteConta;
	
	/** O atributo numOrdemTitularidade. */
	private Integer numOrdemTitularidade;
	
	/** O atributo nomeEmbossamento. */
	private String nomeEmbossamento;
	
	/** O atributo bolAtivo. */
	private Integer bolAtivo;
	
	/** O atributo idPessoa. */
	private Long idPessoa;
	
	/** O atributo numeroCliente. */
	private Long numeroCliente;
	
	/** O atributo numeroClienteLegado. */
	private Long numeroClienteLegado;
	
	/** O atributo idResponsabilidade. */
	private Integer idResponsabilidade;
	
	/** O atributo descricaoResponsabilidade. */
	private String descricaoResponsabilidade;
	  
	/**
	 * Recupera o valor de idParticipanteConta.
	 *
	 * @return o valor de idParticipanteConta
	 */
	public Long getIdParticipanteConta() {
		return idParticipanteConta;
	}
	
	/**
	 * Define o valor de idParticipanteConta.
	 *
	 * @param idParticipanteConta o novo valor de idParticipanteConta
	 */
	public void setIdParticipanteConta(Long idParticipanteConta) {
		this.idParticipanteConta = idParticipanteConta;
	}
	
	/**
	 * Recupera o valor de numOrdemTitularidade.
	 *
	 * @return o valor de numOrdemTitularidade
	 */
	public Integer getNumOrdemTitularidade() {
		return numOrdemTitularidade;
	}
	
	/**
	 * Define o valor de numOrdemTitularidade.
	 *
	 * @param numOrdemTitularidade o novo valor de numOrdemTitularidade
	 */
	public void setNumOrdemTitularidade(Integer numOrdemTitularidade) {
		this.numOrdemTitularidade = numOrdemTitularidade;
	}
	
	/**
	 * Recupera o valor de nomeEmbossamento.
	 *
	 * @return o valor de nomeEmbossamento
	 */
	public String getNomeEmbossamento() {
		return nomeEmbossamento;
	}
	
	/**
	 * Define o valor de nomeEmbossamento.
	 *
	 * @param nomeEmbossamento o novo valor de nomeEmbossamento
	 */
	public void setNomeEmbossamento(String nomeEmbossamento) {
		this.nomeEmbossamento = nomeEmbossamento;
	}
	
	/**
	 * Recupera o valor de bolAtivo.
	 *
	 * @return o valor de bolAtivo
	 */
	public Integer getBolAtivo() {
		return bolAtivo;
	}
	
	/**
	 * Define o valor de bolAtivo.
	 *
	 * @param bolAtivo o novo valor de bolAtivo
	 */
	public void setBolAtivo(Integer bolAtivo) {
		this.bolAtivo = bolAtivo;
	}
	
	/**
	 * Recupera o valor de idPessoa.
	 *
	 * @return o valor de idPessoa
	 */
	public Long getIdPessoa() {
		return idPessoa;
	}
	
	/**
	 * Define o valor de idPessoa.
	 *
	 * @param idPessoa o novo valor de idPessoa
	 */
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	/**
	 * Recupera o valor de numeroCliente.
	 *
	 * @return o valor de numeroCliente
	 */
	public Long getNumeroCliente() {
		return numeroCliente;
	}
	
	/**
	 * Define o valor de numeroCliente.
	 *
	 * @param numeroCliente o novo valor de numeroCliente
	 */
	public void setNumeroCliente(Long numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	
	/**
	 * Recupera o valor de numeroClienteLegado.
	 *
	 * @return o valor de numeroClienteLegado
	 */
	public Long getNumeroClienteLegado() {
		return numeroClienteLegado;
	}
	
	/**
	 * Define o valor de numeroClienteLegado.
	 *
	 * @param numeroClienteLegado o novo valor de numeroClienteLegado
	 */
	public void setNumeroClienteLegado(Long numeroClienteLegado) {
		this.numeroClienteLegado = numeroClienteLegado;
	}
	
	/**
	 * Recupera o valor de idResponsabilidade.
	 *
	 * @return o valor de idResponsabilidade
	 */
	public Integer getIdResponsabilidade() {
		return idResponsabilidade;
	}
	
	/**
	 * Define o valor de idResponsabilidade.
	 *
	 * @param idResponsabilidade o novo valor de idResponsabilidade
	 */
	public void setIdResponsabilidade(Integer idResponsabilidade) {
		this.idResponsabilidade = idResponsabilidade;
	}
	
	/**
	 * Recupera o valor de descricaoResponsabilidade.
	 *
	 * @return o valor de descricaoResponsabilidade
	 */
	public String getDescricaoResponsabilidade() {
		return descricaoResponsabilidade;
	}
	
	/**
	 * Define o valor de descricaoResponsabilidade.
	 *
	 * @param descricaoResponsabilidade o novo valor de descricaoResponsabilidade
	 */
	public void setDescricaoResponsabilidade(String descricaoResponsabilidade) {
		this.descricaoResponsabilidade = descricaoResponsabilidade;
	}
	  
	  

	
}
