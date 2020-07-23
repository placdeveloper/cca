/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * EnumOrdenacaoSaldoAtual
 * Utilizado na funcionalidade de relatorio (saldo cca)
 * @author Marco.Nascimento
 */
public enum EnumOrdenacaoSaldoAtual {
	
	CONTACAPITAL(1, "CONTA CAPITAL", "Conta Capital"),
	NOME(2, "NOME", "Nome"),
	SALDO(3, "SALDO INTEGRALIZADO", "Saldo Integralizado"),
	MATRICULA(4, "MATRÍCULA EMPRESA", "Matrícula da Empresa");
	
	private Integer codigo;	
	private String descricao;
	private String descricaoRelatorio;
	
	private EnumOrdenacaoSaldoAtual(Integer codigo, String descricao, String descricaoRelatorio) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoRelatorio = descricaoRelatorio;
	}
	
	/**
	 * Buscar por codigo.
	 *
	 * @param value o valor de value
	 * @return EnumOrdenacaoParcelamento
	 */
	public static EnumOrdenacaoSaldoAtual buscarPorCodigo(int value) {
	  for(EnumOrdenacaoSaldoAtual e : EnumOrdenacaoSaldoAtual.values()) {
		  if(e.getCodigo().intValue() == value) {
		      return e;
		  }
	  }
	  return null;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the descricaoRelatorio
	 */
	public String getDescricaoRelatorio() {
		return descricaoRelatorio;
	}	
}