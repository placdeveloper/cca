/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.enumintegracao;

/**
 * O Enum TipoReferenciaEnum.
 */
public enum TipoReferenciaEnum {

	/** O atributo BANCARIA. */
	BANCARIA((short) 0, "Bancária"),
	
	/** O atributo COMERCIAL. */
	COMERCIAL((short) 1, "Comercial"),
	
	/** O atributo PARTICULAR. */
	PARTICULAR((short) 2, "Particular"),
	
	/** O atributo COMERCIALIZACAO_SAFRA. */
	COMERCIALIZACAO_SAFRA((short) 3, "Comercialização da Safra"),
	
	/** O atributo COOPERADO. */
	COOPERADO((short) 4, "Cooperado"),
	
	/** O atributo FORNECEDOR. */
	FORNECEDOR((short) 5, "Fornecedor"),
	
	/** O atributo CLIENTE. */
	CLIENTE((short) 6, "Cliente");
	
	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Instancia um novo TipoReferenciaEnum.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private TipoReferenciaEnum(Short codigo, String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	/**
	 * Find.
	 *
	 * @param codigo o valor de codigo
	 * @return TipoReferenciaEnum
	 */
	public static TipoReferenciaEnum find(Short codigo) {
		for(TipoReferenciaEnum obj : values()) {
			if(obj.codigo.equals(codigo)) {
				return obj;
			}
		}
		return null;
	}

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
}

