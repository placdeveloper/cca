/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * @author Marco.Nascimento
 */
public enum EnumTipoIntegralizacao {
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_MIGRAINCORP. */
	COD_MODO_LANCAMENTO_VIA_MIGRAINCORP(0, "MIGRAÇÃO/INCORP"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_CAIXA. */
	COD_MODO_LANCAMENTO_VIA_CAIXA(1,"CAIXA"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_CONTA. */
	COD_MODO_LANCAMENTO_VIA_CONTA(2,"CONTA CORRENTE"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_FOLHA. */
	COD_MODO_LANCAMENTO_VIA_FOLHA(3,"FOLHA"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_COBRANCA. */
	COD_MODO_LANCAMENTO_VIA_COBRANCA(4,"COBRANCA"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_MIGRACAO. */
	COD_MODO_LANCAMENTO_VIA_MIGRACAO(5,"MIGRACAO"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN. */
	COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN(6,"BANCO"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_RATEIO. */
	COD_MODO_LANCAMENTO_VIA_RATEIO(7,"RATEIO"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_RESERVA. */
	COD_MODO_LANCAMENTO_VIA_RESERVA(8,"RESERVA"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_ESTORNO_DEVOLUCAO. */
	COD_MODO_LANCAMENTO_VIA_ESTORNO_DEVOLUCAO(9,"ESTORNO DEVOLUCAO"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_CAPTACAO_REMUNERADA. */
	COD_MODO_LANCAMENTO_VIA_CAPTACAO_REMUNERADA(10,"CAPTACAO REMUNERADA"),

	/** O atributo COD_MODO_LANCAMENTO_VIA_PONTOS_CABAL_SICOOBCARD. */
	COD_MODO_LANCAMENTO_VIA_PONTOS_CABAL_SICOOBCARD(11,"PONTOS CABAL SICOOBCARD"),
	
	/** O atributo COD_MODO_LANCAMENTO_VIA_BOLETO. */
	COD_MODO_LANCAMENTO_VIA_BOLETO(12,"BOLETO BANCARIO");	

	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumTipoIntegralizacao.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoIntegralizacao(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * Buscar por codigo.
	 *
	 * @param value o valor de value
	 * @return EnumTipoIntegralizacao
	 */
	public static EnumTipoIntegralizacao buscarPorCodigo(int value) {
	  for(EnumTipoIntegralizacao e : EnumTipoIntegralizacao.values()) {
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
}