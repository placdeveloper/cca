/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * @author Marco.Nascimento
 */
public enum EnumTipoDocumento {
	
	/** O atributo FICHA_PROPOSTA_DE_MATRICULA. */
	FICHA_PROPOSTA_DE_MATRICULA(1, "FICHA PROPOSTA DE MATRICULA", "FICHAPROPMATRICULA"),
	TERMO_ELIMINACAO_EXCLUSAO(2, "TERMO DE ELIMINAÇÃO OU EXCLUSÃO", "TERELIEXCLU"),
	CARTA_DEMISSAO(3, "CARTA DE DEMISSÃO", "CARTADEDEMISSAO"),
	RECIBO_TRANSFERENCIA(4, "RECIBO DE TRANSFERÊNCIA", "RECTRANS"),
	ORDEM_JUDICIAL_BLOQUEIO_CAPITAL(5, "ORDEM JUDICIAL - BLOQUEIO DE CAPITAL", "ORDJUDBLOQCA");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	private String sigla;
	
	/**
	 * Instancia um novo EnumTipoDocumento.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoDocumento(Integer codigo, String descricao, String sigla) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.sigla = sigla;
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
	
	public String getSigla() {
		return sigla;
	}
	
	/**
	 * Busca o tipo pela sigla
	 * @param sigla
	 * @return
	 */
	public static EnumTipoDocumento bsucarPorSigla(String sigla) {
		for (EnumTipoDocumento tipo : values()) {
			if (tipo.getSigla().equals(sigla)) {
				return tipo;
			}
		}
		return null;
	}
}