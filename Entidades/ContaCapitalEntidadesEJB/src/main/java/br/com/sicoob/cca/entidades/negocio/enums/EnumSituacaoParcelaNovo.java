/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Situacoes de parcela 
 */
public enum EnumSituacaoParcelaNovo {

	COD_ABERTO(1,"EM ABERTO"),
	COD_PAGO(2,"PAGO"),
	COD_CANCELADA_EXCLUIDA(3,"CANCELADA\\EXCLUÍDA");	
	
	private Integer codigo;	
	private String descricao;
	
	private EnumSituacaoParcelaNovo(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * Buscar por codigo.
	 *
	 * @param value o valor de value
	 * @return EnumSituacaoParcela
	 */
	public static EnumSituacaoParcelaNovo buscarPorCodigo(int value) {
	  for(EnumSituacaoParcelaNovo e : EnumSituacaoParcelaNovo.values()) {
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
