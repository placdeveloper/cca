/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * O Enum EnumPerfilConfiguracaoCapital.
 */
public enum EnumPerfilConfiguracaoCapital {

	/** O atributo COD_PERFIL_CONFIG_CCA_CONFEDERACAO. */
	COD_PERFIL_CONFIG_CCA_CONFEDERACAO(1,"CONFEDERAÇÃO"),
	
	/** O atributo COD_PERFIL_CONFIG_CCA_CENTRAL. */
	COD_PERFIL_CONFIG_CCA_CENTRAL(2,"CENTRAL"),
	
	/** O atributo COD_PERFIL_CONFIG_CCA_SINGULAR. */
	COD_PERFIL_CONFIG_CCA_SINGULAR(3,"SINGULAR");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumPerfilConfiguracaoCapital.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumPerfilConfiguracaoCapital(Integer codigo, String descricao) {
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
