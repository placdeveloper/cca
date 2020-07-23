/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * EnumOrdenacaoParcelamento
 * Utilizado na funcionalidade de relatorio (parcelameto cca)
 * @author Marco.Nascimento
 */
public enum EnumOrdenacaoParcelamento {
	
	MATRICULA(1, "CONTA CAPITAL", "Conta Capital"),
	CLIENTE(2, "NOME", "Nome do Cliente"),
	DATA_VENCIMENTO(3, "DATA VENCIMENTO", "Data Vencimento");
	
	private Integer codigo;	
	private String descricao;
	private String descricaoRelatorio;
	
	private EnumOrdenacaoParcelamento(Integer codigo, String descricao, String descricaoRelatorio) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoRelatorio = descricaoRelatorio;
	}
	
	/**
	 * Buscar por codigo.
	 *
	 * @param value o valor de value
	 * @return EnumOrdenacaoParcelamento
	 */
	public static EnumOrdenacaoParcelamento buscarPorCodigo(int value) {
	  for(EnumOrdenacaoParcelamento e : EnumOrdenacaoParcelamento.values()) {
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

	/**
	 * @return the descricaoRelatorio
	 */
	public String getDescricaoRelatorio() {
		return descricaoRelatorio;
	}	
}