/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe GftIntegracaoDTO.
 */
public class GftIntegracaoDTO extends BancoobDto  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6595195342552046428L;

	/** O atributo idRegistroControlado. */
	private Integer idRegistroControlado;
	
	/** O atributo idInstituicaoProcesso. */
	private Integer idInstituicaoProcesso;
	
	/** O atributo executarAtividadeAprovacao. */
	private Boolean executarAtividadeAprovacao = Boolean.FALSE;
	
	/** O atributo nomeProcedimento. */
	private String nomeProcedimento;
	
	/** O atributo idAtividade. */
	private Integer idAtividade;
	
	/** O atributo idOcorrenciaAtividade. */
	private Integer idOcorrenciaAtividade;
	
	/** O atributo idProcedimento. */
	private Integer idProcedimento;
	
	/** O atributo idProcedimentoControle. */
	private Integer idProcedimentoControle;
	
	/** O atributo idProcesso. */
	private Integer idProcesso;
	
	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** O atributo justificativa. */
	private String justificativa;
	
	/**
	 * @return the idRegistroControlado
	 */
	public Integer getIdRegistroControlado() {
		return idRegistroControlado;
	}

	/**
	 * @param idRegistroControlado the idRegistroControlado to set
	 */
	public void setIdRegistroControlado(Integer idRegistroControlado) {
		this.idRegistroControlado = idRegistroControlado;
	}

	/**
	 * @return the idInstituicaoProcesso
	 */
	public Integer getIdInstituicaoProcesso() {
		return idInstituicaoProcesso;
	}

	/**
	 * @param idInstituicaoProcesso the idInstituicaoProcesso to set
	 */
	public void setIdInstituicaoProcesso(Integer idInstituicaoProcesso) {
		this.idInstituicaoProcesso = idInstituicaoProcesso;
	}

	/**
	 * @return the executarAtividadeAprovacao
	 */
	public Boolean getExecutarAtividadeAprovacao() {
		return executarAtividadeAprovacao;
	}

	/**
	 * @param executarAtividadeAprovacao the executarAtividadeAprovacao to set
	 */
	public void setExecutarAtividadeAprovacao(Boolean executarAtividadeAprovacao) {
		this.executarAtividadeAprovacao = executarAtividadeAprovacao;
	}

	/**
	 * @return the nomeProcedimento
	 */
	public String getNomeProcedimento() {
		return nomeProcedimento;
	}

	/**
	 * @param nomeProcedimento the nomeProcedimento to set
	 */
	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}

	/**
	 * @return the idAtividade
	 */
	public Integer getIdAtividade() {
		return idAtividade;
	}

	/**
	 * @param idAtividade the idAtividade to set
	 */
	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
	}

	/**
	 * @return the idProcedimento
	 */
	public Integer getIdProcedimento() {
		return idProcedimento;
	}

	/**
	 * @param idProcedimento the idProcedimento to set
	 */
	public void setIdProcedimento(Integer idProcedimento) {
		this.idProcedimento = idProcedimento;
	}

	/**
	 * @return the idProcedimentoControle
	 */
	public Integer getIdProcedimentoControle() {
		return idProcedimentoControle;
	}

	/**
	 * @param idProcedimentoControle the idProcedimentoControle to set
	 */
	public void setIdProcedimentoControle(Integer idProcedimentoControle) {
		this.idProcedimentoControle = idProcedimentoControle;
	}

	/**
	 * @return the idProcesso
	 */
	public Integer getIdProcesso() {
		return idProcesso;
	}

	/**
	 * @param idProcesso the idProcesso to set
	 */
	public void setIdProcesso(Integer idProcesso) {
		this.idProcesso = idProcesso;
	}

	public Integer getNumContaCapital() {
		return numContaCapital;
	}

	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}

	/**
	 * @return the justificativa
	 */
	public String getJustificativa() {
		return justificativa;
	}

	/**
	 * @param justificativa the justificativa to set
	 */
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	/**
	 * @return the idOcorrenciaAtividade
	 */
	public Integer getIdOcorrenciaAtividade() {
		return idOcorrenciaAtividade;
	}

	/**
	 * @param idOcorrenciaAtividade the idOcorrenciaAtividade to set
	 */
	public void setIdOcorrenciaAtividade(Integer idOcorrenciaAtividade) {
		this.idOcorrenciaAtividade = idOcorrenciaAtividade;
	}
}