/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Dominio de situacoes de cadastro de proposta
 * @author marco.nascimento
 */
public enum EnumSituacaoCadastroProposta {

	/** O atributo COD_AGUARDANDO_APROVACAO. */
	COD_AGUARDANDO_APROVACAO(1, "AGUARDANDO APROVAÇÃO"), //Nao existente no GFT
	/** O atributo COD_APROVADO. */
	COD_APROVADO(2, "APROVADO"),
	
	/** O atributo COD_REJEITADO. */
	COD_REJEITADO(3, "REJEITADO"),
	
	/** O atributo COD_DEVOLVIDO. */
	COD_DEVOLVIDO(4, "DEVOLVIDO");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo codigoGFT. */
	private Integer codigoGFT;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumSituacaoCadastroProposta.
	 *
	 * @param codigo o valor de codigo
	 * @param codigoGFT o valor de codigo gft
	 * @param descricao o valor de descricao
	 */
	private EnumSituacaoCadastroProposta(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * 
	 */
	public static EnumSituacaoCadastroProposta find(Integer codigo) {
		for(EnumSituacaoCadastroProposta o : values()) {
			if(o.getCodigo().equals(codigo)) {
				return o;
			}
		}
		return null;
	}
	
	/**
	 * Find by gft.
	 *
	 * @param nameGFT o valor de name gft
	 * @return EnumSituacaoCadastroProposta
	 */
	public static EnumSituacaoCadastroProposta findByGFT(String nameGFT) {
		for (EnumSituacaoCadastroProposta o : values()) {
			if(o.getDescricao().equals(nameGFT)) {
				return o;
			}
		}
		return null;
	}

	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Recupera o valor de descricao.
	 *
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}
}