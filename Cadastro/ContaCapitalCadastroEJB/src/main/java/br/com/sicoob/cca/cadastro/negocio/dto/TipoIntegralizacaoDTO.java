/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe TipoIntegralizacaoDTO.
 */
public class TipoIntegralizacaoDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1617707060930014082L;

	/** O atributo codTipoIntegralizacao. */
	private Integer codTipoIntegralizacao;
	
	/** O atributo descTipoIntegralizacao. */
	private String descTipoIntegralizacao;
	
	/**
	 * Instancia um novo TipoIntegralizacaoDTO.
	 *
	 * @param codTipoIntegralizacao o valor de cod tipo integralizacao
	 * @param descTipoIntegralizacao o valor de desc tipo integralizacao
	 */
	public TipoIntegralizacaoDTO(Integer codTipoIntegralizacao,String descTipoIntegralizacao) {
		super();
		this.codTipoIntegralizacao = codTipoIntegralizacao;
		this.descTipoIntegralizacao = descTipoIntegralizacao;
	}
	
	/**
	 * Recupera o valor de codTipoIntegralizacap.
	 *
	 * @return o valor de codTipoIntegralizacap
	 */
	public Integer getCodTipoIntegralizacap() {
		return codTipoIntegralizacao;
	}
	
	/**
	 * Define o valor de codTipoIntegralizacap.
	 *
	 * @param codTipoIntegralizacap o novo valor de codTipoIntegralizacap
	 */
	public void setCodTipoIntegralizacap(Integer codTipoIntegralizacap) {
		this.codTipoIntegralizacao = codTipoIntegralizacap;
	}
	
	/**
	 * Recupera o valor de descTipoIntegralizacao.
	 *
	 * @return o valor de descTipoIntegralizacao
	 */
	public String getDescTipoIntegralizacao() {
		return descTipoIntegralizacao;
	}
	
	/**
	 * Define o valor de descTipoIntegralizacao.
	 *
	 * @param descTipoIntegralizacao o novo valor de descTipoIntegralizacao
	 */
	public void setDescTipoIntegralizacao(String descTipoIntegralizacao) {
		this.descTipoIntegralizacao = descTipoIntegralizacao;
	}

	
	
}
