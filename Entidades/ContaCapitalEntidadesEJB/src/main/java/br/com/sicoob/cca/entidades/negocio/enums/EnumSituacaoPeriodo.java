/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * The Enum EnumSituacaoPeriodo.
 */
public enum EnumSituacaoPeriodo {

	/** The cod aberto. */
	COD_ATIVO(1,"ATIVO"),
	
	/** The cod desliagado. */
	COD_DESLIAGADO(2,"DESLIGADO");	
	
	/** The codigo. */
	private Integer codigo;	
	
	/** The descricao. */
	private String descricao;
	
	/**
	 * Instantiates a new enum situacao periodo.
	 *
	 * @param codigo the codigo
	 * @param descricao the descricao
	 */
	private EnumSituacaoPeriodo(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * Buscar por codigo.
	 *
	 * @param value o valor de value
	 * @return EnumSituacaoParcela
	 */
	public static EnumSituacaoPeriodo buscarPorCodigo(int value) {
	  for(EnumSituacaoPeriodo e : EnumSituacaoPeriodo.values()) {
		  if(e.getCodigo().intValue() == value) {
		      return e;
		  }
	  }
	  return null;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}	
}
