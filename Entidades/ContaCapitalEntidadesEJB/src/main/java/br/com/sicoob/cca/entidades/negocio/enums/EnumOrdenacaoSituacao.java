/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;


/**
 * The Enum EnumOrdenacaoSituacao.
 */
public enum EnumOrdenacaoSituacao {
	
	/** The saldo. */
	NOME_CLIENTE(1, "CLIENTE", "Nome do Cliente"),
	
	/** The matricula. */
	MATRICULA(2, "MATRICULA", "Matrícula");
	
	/** The codigo. */
	private Integer codigo;	
	
	/** The descricao. */
	private String descricao;
	
	/** The descricao relatorio. */
	private String descricaoRelatorio;
	
	/**
	 * Instantiates a new enum ordenacao situacao.
	 *
	 * @param codigo the codigo
	 * @param descricao the descricao
	 * @param descricaoRelatorio the descricao relatorio
	 */
	private EnumOrdenacaoSituacao(Integer codigo, String descricao, String descricaoRelatorio) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoRelatorio = descricaoRelatorio;
	}
	
	/**
	 * Buscar por codigo.
	 *
	 * @param value the value
	 * @return the enum ordenacao situacao
	 */
	public static EnumOrdenacaoSituacao buscarPorCodigo(int value) {
	  for(EnumOrdenacaoSituacao e : EnumOrdenacaoSituacao.values()) {
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

	/**
	 * Gets the descricao relatorio.
	 *
	 * @return the descricaoRelatorio
	 */
	public String getDescricaoRelatorio() {
		return descricaoRelatorio;
	}	
}