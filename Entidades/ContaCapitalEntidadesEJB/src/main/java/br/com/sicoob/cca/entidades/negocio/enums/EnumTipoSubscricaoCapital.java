/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * O Enum EnumTipoSubscricaoCapital.
 */
public enum EnumTipoSubscricaoCapital {

	/** O atributo COD_TIPO_SUBSCRICAO_CCA_INGRESSO. */
	COD_TIPO_SUBSCRICAO_CCA_INGRESSO(1,"INGRESSO"),
	
	/** O atributo COD_TIPO_SUBSCRICAO_CCA_ESTATUTARIA. */
	COD_TIPO_SUBSCRICAO_CCA_ESTATUTARIA(2,"ESTATUTÁRIA"),
	
	/** O atributo COD_TIPO_SUBSCRICAO_CCA_VOLUNTARIA. */
	COD_TIPO_SUBSCRICAO_CCA_VOLUNTARIA(3,"VOLUNTÁRIA"),
	
	/** O atributo COD_TIPO_SUBSCRICAO_CCA_CAMPANHA. */
	COD_TIPO_SUBSCRICAO_CCA_CAMPANHA(4, "CAMPANHA");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumTipoSubscricaoCapital.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoSubscricaoCapital(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
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
