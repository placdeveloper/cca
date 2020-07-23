/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Situacoes de parcela
 */
public enum EnumSituacaoParcela {

	COD_ABERTO(1,"EM ABERTO"),
	COD_PAGO(2,"PAGO");	
	
	private Integer codigo;	
	private String descricao;
	
	private EnumSituacaoParcela(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * Buscar por codigo.
	 *
	 * @param value o valor de value
	 * @return EnumSituacaoParcela
	 */
	public static EnumSituacaoParcela buscarPorCodigo(int value) {
	  for(EnumSituacaoParcela e : EnumSituacaoParcela.values()) {
		  if(e.getCodigo().intValue() == value) {
		      return e;
		  }
	  }
	  return null;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}	
}
