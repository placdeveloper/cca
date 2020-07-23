/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * @author Marco.Nascimento
 */
public enum EnumTipoCooperativa {
	
	/** O atributo COD_APLIC_COOP_CRED_MUTUO. */
	COD_APLIC_COOP_CRED_MUTUO(1,"CREDITO MUTUO"),
	
	/** O atributo COD_APLIC_COOP_CRED_RURAL. */
	COD_APLIC_COOP_CRED_RURAL(2,"CREDITO RURAL"),
	
	/** O atributo COD_APLIC_COOP_CRED_AMBOS. */
	COD_APLIC_COOP_CRED_AMBOS(3,"AMBOS");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumTipoCooperativa.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoCooperativa(Integer codigo, String descricao) {
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