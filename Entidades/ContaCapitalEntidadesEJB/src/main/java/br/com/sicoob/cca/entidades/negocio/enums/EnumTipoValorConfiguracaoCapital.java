/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * O Enum EnumTipoValorConfiguracaoCapital.
 */
public enum EnumTipoValorConfiguracaoCapital {

	/** O atributo COD_TIPO_VALOR_CONFIG_CCA_NUMERO. */
	COD_TIPO_VALOR_CONFIG_CCA_NUMERO(1,"NUMÉRICO"),
	
	/** O atributo COD_TIPO_VALOR_CONFIG_CCA_DATA. */
	COD_TIPO_VALOR_CONFIG_CCA_DATA(2,"DATA"),
	
	/** O atributo COD_TIPO_VALOR_CONFIG_CCA_CARACTER. */
	COD_TIPO_VALOR_CONFIG_CCA_CARACTER(3,"TEXTO"),
	
	/** O atributo COD_TIPO_VALOR_CONFIG_CCA_LOGICO. */
	COD_TIPO_VALOR_CONFIG_CCA_LOGICO(4,"LÓGICO");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumTipoValorConfiguracaoCapital.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoValorConfiguracaoCapital(Integer codigo, String descricao) {
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
