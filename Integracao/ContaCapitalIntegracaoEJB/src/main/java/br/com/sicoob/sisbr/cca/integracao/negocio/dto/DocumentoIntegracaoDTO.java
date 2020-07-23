/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe DocumentoIntegracaoDTO.
 */
public class DocumentoIntegracaoDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** O atributo idDocumento. */
	private Long idDocumento;
	
	/** O atributo descTipoDoc. */
	private String descTipoDoc;
	
	/** O atributo idOcorrenciaAssuntoDocumento. */
	private Integer idOcorrenciaAssuntoDocumento;
	
	/** O atributo bolDocVisualizado. */
	private Boolean bolDocVisualizado;
	
	/** O atributo descricaoJustificativa. */
	private String descricaoJustificativa;
	
	/** O atributo descricaoTipoDocumento. */
	private String descricaoTipoDocumento;


	/**
	 * @return the descTipoDoc
	 */
	public String getDescTipoDoc() {
		return descTipoDoc;
	}


	/**
	 * @param descTipoDoc the descTipoDoc to set
	 */
	public void setDescTipoDoc(String descTipoDoc) {
		this.descTipoDoc = descTipoDoc;
	}

	/**
	 * @return the idDocumento
	 */
	public Long getIdDocumento() {
		return idDocumento;
	}


	/**
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}


	/**
	 * Recupera o valor de idOcorrenciaAssuntoDocumento.
	 *
	 * @return o valor de idOcorrenciaAssuntoDocumento
	 */
	public Integer getIdOcorrenciaAssuntoDocumento() {
		return idOcorrenciaAssuntoDocumento;
	}


	/**
	 * Define o valor de idOcorrenciaAssuntoDocumento.
	 *
	 * @param idOcorrenciaAssuntoDocumento o novo valor de idOcorrenciaAssuntoDocumento
	 */
	public void setIdOcorrenciaAssuntoDocumento(Integer idOcorrenciaAssuntoDocumento) {
		this.idOcorrenciaAssuntoDocumento = idOcorrenciaAssuntoDocumento;
	}


	/**
	 * Recupera o valor de bolDocVisualizado.
	 *
	 * @return o valor de bolDocVisualizado
	 */
	public Boolean getBolDocVisualizado() {
		return bolDocVisualizado;
	}


	/**
	 * Define o valor de bolDocVisualizado.
	 *
	 * @param bolDocVisualizado o novo valor de bolDocVisualizado
	 */
	public void setBolDocVisualizado(Boolean bolDocVisualizado) {
		this.bolDocVisualizado = bolDocVisualizado;
	}


	/**
	 * Recupera o valor de descricaoJustificativa.
	 *
	 * @return o valor de descricaoJustificativa
	 */
	public String getDescricaoJustificativa() {
		return descricaoJustificativa;
	}


	/**
	 * Define o valor de descricaoJustificativa.
	 *
	 * @param descricaoJustificativa o novo valor de descricaoJustificativa
	 */
	public void setDescricaoJustificativa(String descricaoJustificativa) {
		this.descricaoJustificativa = descricaoJustificativa;
	}


	/**
	 * Recupera o valor de descricaoTipoDocumento.
	 *
	 * @return o valor de descricaoTipoDocumento
	 */
	public String getDescricaoTipoDocumento() {
		return descricaoTipoDocumento;
	}


	/**
	 * Define o valor de descricaoTipoDocumento.
	 *
	 * @param descricaoTipoDocumento o novo valor de descricaoTipoDocumento
	 */
	public void setDescricaoTipoDocumento(String descricaoTipoDocumento) {
		this.descricaoTipoDocumento = descricaoTipoDocumento;
	}
}