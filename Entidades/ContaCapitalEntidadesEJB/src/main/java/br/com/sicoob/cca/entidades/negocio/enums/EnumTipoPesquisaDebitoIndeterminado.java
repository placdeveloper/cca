/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Tipos de Pesquisa validos para debito indeterminado
 * @author Marco.Nascimento
 */
public enum EnumTipoPesquisaDebitoIndeterminado {
	
	/** O atributo EM_BRANCO. */
	EM_BRANCO(1, "Todos"),
	
	/** O atributo TIPO_PESSOA. */
	TIPO_PESSOA(2, "Tipo de Pessoa"),
			
	/** O atributo TIPO_DEB. */
	TIPO_DEB(3, "Tipo de Débito"),
	
	/** O atributo VALOR. */
	VALOR(4, "Valor"),
	
	/** O atributo DIA_DEB. */
	DIA_DEB(5, "Dia de Débito"),

	/** O atributo CONTA_CAPITAL. */
	CONTA_CAPITAL(6, "Conta Capital"),
	
	/** O atributo NOME. */
	NOME(7, "Nome"),
	
	/** O atributo CPF_CNPJ. */
	CPF_CNPJ(8, "CPF/CNPJ");
	
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
	private EnumTipoPesquisaDebitoIndeterminado(Integer codigo, String descricao) {
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
