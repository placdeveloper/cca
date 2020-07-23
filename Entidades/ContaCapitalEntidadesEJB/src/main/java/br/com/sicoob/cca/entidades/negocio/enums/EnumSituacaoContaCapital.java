/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Dominio de situacoes da conta capital (associado)
 * @author Marcos.Balbi
 */
public enum EnumSituacaoContaCapital {

	/** O atributo COD_SITUACAO_COOPERADO_ATIVO. */
	COD_SITUACAO_COOPERADO_ATIVO(1,"ATIVO"),
	
	/** O atributo COD_SITUACAO_COOPERADO_DEMITIDO. */
	COD_SITUACAO_COOPERADO_DEMITIDO(2,"DEMITIDO"),
	
	/** O atributo COD_SITUACAO_COOPERADO_EXCLUIDO. */
	COD_SITUACAO_COOPERADO_EXCLUIDO(3,"EXCLUÍDO"),
	
	/** O atributo COD_SITUACAO_COOPERADO_ELIMINADO. */
	COD_SITUACAO_COOPERADO_ELIMINADO(4,"ELIMINADO");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumSituacaoContaCapital.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumSituacaoContaCapital(Integer codigo, String descricao) {
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
