/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.enums;

/**
 * @author Marco.Nascimento
 */
public enum EnumTipoLiquidacao {
	
	/**
	 * Boleto Liquidado Via Compe
	 * 133 - INTEGRALIZACAO VIA BOLETO BANCARIO - COMPE
	 */
	COD_BOLETO_LIQ_COMPE(1, "Boleto Liquidado Via Compe", 133),
	
	/**
	 * Boleto Liquidado Via Caixa
	 * 135 - INTEGRALIZACAO VIA BOLETO BANCARIO - CAIXA
	 */
	COD_BOLETO_LIQ_CAIXA(2, "Boleto Liquidado Via Caixa", 135),
	
	/**
	 * Boleto Liquidado Via Conta Corrente
	 * 136 - INTEGRALIZACAO VIA BOLETO BANCARIO - CONTA CORRENT
	 */
	COD_BOLETO_LIQ_CCO(3, "Boleto Liquidado Via Conta Corrente", 136);
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo codigoHistorico. */
	private Integer codigoHistorico;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumTipoLiquidacao.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoLiquidacao(Integer codigo, String descricao, Integer codigoHistorico) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.codigoHistorico = codigoHistorico;
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
	 * Recupera o valor de codigoHistorico.
	 *
	 * @return o valor de codigoHistorico
	 */
	public Integer getCodigoHistorico() {
		return codigoHistorico;
	}
}