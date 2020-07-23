package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Código de resultado do log.
 * @author Nairon.Silva
 */
public enum EnumResultadoOperacaoContaCapital {

	SUCESSO(1, "Sucesso"),
	ERRO_NEGOCIO(2, "Erro de négocio"),
	ERRO_SISTEMA(3, "Erro de sistema");
	
	private Integer codigo;
	private String descricao;
	
	private EnumResultadoOperacaoContaCapital(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
