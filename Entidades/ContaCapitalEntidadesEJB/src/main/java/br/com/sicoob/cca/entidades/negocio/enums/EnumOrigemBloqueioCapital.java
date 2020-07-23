package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Enum com os tipos de bloqueio
 * @author Nairon.Silva
 */
public enum EnumOrigemBloqueioCapital {

	TRANSFERENCIA_CAPITAL(1, "TRANSFERENCIA DE CAPITAL"),
	JUDICIAL(2, "JUDICIAL");	
	
	private Integer codigo;	
	private String descricao;
	
	private EnumOrigemBloqueioCapital(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
