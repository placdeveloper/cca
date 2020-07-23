/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * @author Marco.Nascimento
 */
public enum EnumSituacaoParcelamento {
	
	/** O atributo COD_PARCELA_GERADA. */
	COD_PARCELA_GERADA(1,"EM ABERTO"),
	
	/** O atributo COD_PARCELA_PAGA_VIA_CAIXA. */
	COD_PARCELA_PAGA_VIA_CAIXA(2,"PAGA VIA CAIXA"),
	
	/** O atributo COD_PARCELA_PAGA_VIA_CONTA. */
	COD_PARCELA_PAGA_VIA_CONTA(3,"PAGA VIA CONTA"),
	
	/** O atributo COD_PARCELA_CANCELADA. */
	COD_PARCELA_CANCELADA(4,"CANCELADA"),
	
	/** O atributo COD_PARCELA_PAGA_VIA_CHADMIN. */
	COD_PARCELA_PAGA_VIA_CHADMIN(5,"PAGA VIA BANCO/CHQ. ADM."),
	
	/** O atributo COD_PARCELA_EXCLUIDA. */
	COD_PARCELA_EXCLUIDA(6,"EXCLUIDA"),
	
	/** O atributo COD_PARCELA_PAGA_VIA_FOLHA. */
	COD_PARCELA_PAGA_VIA_FOLHA(7,"PAGA VIA FOLHA"),
	
	/** O atributo COD_PARCELA_PAGA_VIA_COBRANCA. */
	COD_PARCELA_PAGA_VIA_COBRANCA(8,"PAGA VIA COBRANCA"),
	
	/** O atributo COD_PARCELA_PAGA_ANTES_MIGRACAO. */
	COD_PARCELA_PAGA_ANTES_MIGRACAO(9,"PAGA ANTES DA MIGRACAO"),
	
	/** O atributo COD_PARCELA_PAGA_VIA_RATEIO. */
	COD_PARCELA_PAGA_VIA_RATEIO(10,"PAGA VIA RATEIO"),
	
	/** O atributo COD_PARCELA_PAGA_VIA_RESERVA. */
	COD_PARCELA_PAGA_VIA_RESERVA(11,"PAGA VIA RESERVA"),
	
	/** O atributo COD_PARCELA_AGUARDA_RETORNO. */
	COD_PARCELA_AGUARDA_RETORNO(12,"AGUARDANDO RETORNO ARQUIVO");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumSituacaoParcelamento.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumSituacaoParcelamento(Integer codigo, String descricao) {
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