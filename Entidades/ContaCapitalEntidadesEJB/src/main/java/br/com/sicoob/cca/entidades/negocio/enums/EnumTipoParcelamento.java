/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * @author Marco.Nascimento
 */
public enum EnumTipoParcelamento {
	
	/** O atributo COD_TIPO_PARCELAMENTO_INTEGRAL. */
	COD_TIPO_PARCELAMENTO_INTEGRAL(1,"INTEGRALIZAÇÃO", "Integralização"),
	
	/** O atributo COD_TIPO_PARCELAMENTO_DEVOLUCAO. */
	COD_TIPO_PARCELAMENTO_DEVOLUCAO(2,"DEVOLUÇÃO", "Devolução");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo descricaoRelatorio. */
	private String descricaoRelatorio;
	
	/**
	 * Instancia um novo EnumTipoParcelamento.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoParcelamento(Integer codigo, String descricao, String descricaoRelatorio) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoRelatorio = descricaoRelatorio;
	}
	
	/**
	 * Buscar por codigo.
	 *
	 * @param value o valor de value
	 * @return EnumTipoParcelamento
	 */
	public static EnumTipoParcelamento buscarPorCodigo(int value) {
	  for(EnumTipoParcelamento e : EnumTipoParcelamento.values()) {
		  if(e.getCodigo().intValue() == value) {
		      return e;
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

	/**
	 * @return the descricaoRelatorio
	 */
	public String getDescricaoRelatorio() {
		return descricaoRelatorio;
	}
}