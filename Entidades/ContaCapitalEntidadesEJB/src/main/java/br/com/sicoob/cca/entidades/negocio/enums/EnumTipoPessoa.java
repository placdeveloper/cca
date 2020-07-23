/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * @author Marco.Nascimento
 */
public enum EnumTipoPessoa {
	
	/** O atributo COD_TIPO_PES_FISICA. */
	COD_TIPO_PES_FISICA(0,"PESSOA FISICA"),
	
	/** O atributo COD_TIPO_PES_JURIDICA. */
	COD_TIPO_PES_JURIDICA(1,"PESSOA JURIDICA"),
	
	/** O atributo COD_TIPO_AMBAS. */
	COD_TIPO_AMBAS(2,"AMBAS");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumTipoPessoa.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoPessoa(Integer codigo, String descricao) {
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