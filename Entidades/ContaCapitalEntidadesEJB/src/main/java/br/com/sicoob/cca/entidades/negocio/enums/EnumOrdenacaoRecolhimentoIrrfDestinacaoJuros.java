package br.com.sicoob.cca.entidades.negocio.enums;

public enum EnumOrdenacaoRecolhimentoIrrfDestinacaoJuros {
	
	CONTACAPITAL(1, "CONTA CAPITAL", "Conta Capital"),
	NOME(2, "NOME", "Nome");
	
	private Integer codigo;
	private String descricao;
	private String descricaoRelatorio;
	
	private EnumOrdenacaoRecolhimentoIrrfDestinacaoJuros(Integer codigo, String descricao, String descricaoRelatorio) {
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
	public static EnumOrdenacaoRecolhimentoIrrfDestinacaoJuros buscarPorCodigo(int value) {
	  for(EnumOrdenacaoRecolhimentoIrrfDestinacaoJuros e : EnumOrdenacaoRecolhimentoIrrfDestinacaoJuros.values()) {
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
