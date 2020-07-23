/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * @author Marco.Nascimento
 */
public enum EnumGrupoHistorico {
	
	/** O atributo COD_GRUPO_HIST_SUBSCRICAO. */
	COD_GRUPO_HIST_SUBSCRICAO(1,"SUBSCRICAO"),
	
	/** O atributo COD_GRUPO_HIST_INTEGRALIZACAO. */
	COD_GRUPO_HIST_INTEGRALIZACAO(2,"INTEGRALIZACAO"),
	
	/** O atributo COD_GRUPO_HIST_DEVOLUCAO. */
	COD_GRUPO_HIST_DEVOLUCAO(3,"DEVOLUCAO"),
	
	/** O atributo COD_GRUPO_HIST_VALORES_DIVERSOS. */
	COD_GRUPO_HIST_VALORES_DIVERSOS(4,"VALORES DIVERSOS"),
	
	/** O atributo COD_GRUPO_HIST_NAO_SENSIBILIZA. */
	COD_GRUPO_HIST_NAO_SENSIBILIZA(9,"NAO SENSIBILIZADO");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumGrupoHistorico.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumGrupoHistorico(Integer codigo, String descricao) {
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