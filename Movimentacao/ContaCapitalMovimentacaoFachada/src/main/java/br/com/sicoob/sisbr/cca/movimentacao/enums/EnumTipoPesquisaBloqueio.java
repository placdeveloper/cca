package br.com.sicoob.sisbr.cca.movimentacao.enums;

/**
 * Enum com os valores possiveis para pesquisar bloqueios
 * @author Nairon.Silva
 */
public enum EnumTipoPesquisaBloqueio {

	TODOS("0", "TODOS"),
	NOME("1", "NOME"),
	CPF_CNPJ("2", "CPF/CNPJ"),
	TIPO_BLOQUEIO("3", "TIPO BLOQUEIO"),
	CONTA_CAPITAL("4", "CONTA CAPITAL"),
	NUM_PROTOCOLO("5", "Nº PROTOCOLO"),
	NUM_PROCESSO("6", "Nº PROCESSO");
	
	private String cod;
	private String label;
	
	private EnumTipoPesquisaBloqueio(String cod, String label) {
		this.cod = cod;
		this.label = label;
	}
	
	/**
	 * Codigo
	 * @return
	 */
	public String getCod() {
		return this.cod;
	}
	
	/**
	 * Label
	 * @return
	 */
	public String getLabel() {
		return this.label;
	}
	
}
