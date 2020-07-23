/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Enum com as situacoes de BloqueioCapital
 * @author Nairon.Silva
 */
public enum EnumSituacaoBloqueio {

	ATIVO(1,"ATIVO"),
	INATIVO(0,"INATIVO");	
	
	private Integer codigo;	
	private String descricao;
	
	private EnumSituacaoBloqueio(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}	
	
	/**
	 * Retorna o Enum pelo codigo
	 * @param codigo
	 * @return
	 */
	public static EnumSituacaoBloqueio getPorCodigo(Integer codigo) {
		for (EnumSituacaoBloqueio situacao : values()) {
			if (situacao.getCodigo().equals(codigo)) {
				return situacao;
			}
		}
		return null;
	}
	
}
