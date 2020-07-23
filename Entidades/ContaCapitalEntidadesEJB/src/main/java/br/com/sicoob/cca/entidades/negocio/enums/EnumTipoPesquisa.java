/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Tipos de Pesquisa validos para conta capital
 * @author Marco.Nascimento
 */
public enum EnumTipoPesquisa {
	
	/** O atributo EM_BRANCO. */
	EM_BRANCO(1, " "),

	/** O atributo CONTA_CAPITAL. */
	CONTA_CAPITAL(2, "CONTA CAPITAL"),
	
	/** O atributo CPF_CNPJ. */
	CPF_CNPJ(3, "CPF/CNPJ"),
	
	/** O atributo NOME. */
	NOME(4, "NOME");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumTipoPesquisa.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoPesquisa(Integer codigo, String descricao) {
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
