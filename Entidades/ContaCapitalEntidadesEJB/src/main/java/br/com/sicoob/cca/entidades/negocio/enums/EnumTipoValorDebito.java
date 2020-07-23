/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * @author Marco.Nascimento
 */
public enum EnumTipoValorDebito {
	
	/** O atributo COD_TIPO_DEB_VALOR. */
	COD_TIPO_DEB_VALOR(0, "VALOR FIXO"),
	
	/** O atributo COD_TIPO_DEB_PERC_SALARIO_RENDA. */
	COD_TIPO_DEB_PERC_SALARIO_RENDA(1,"PERCENTUAL DA RENDA SALARIAL"),
	
	/** O atributo COD_TIPO_DEB_PERC_SALARIO_BASE. */
	COD_TIPO_DEB_PERC_SALARIO_BASE(2,"PERCENTUAL DO SALÁRIO BASE"),
	
	/** O atributo COD_TIPO_DEB_QTD_COTAS. */
	COD_TIPO_DEB_QTD_COTAS(3,"QUANTIDADE DE COTAS");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumTipoValorDebito.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoValorDebito(Integer codigo, String descricao) {
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