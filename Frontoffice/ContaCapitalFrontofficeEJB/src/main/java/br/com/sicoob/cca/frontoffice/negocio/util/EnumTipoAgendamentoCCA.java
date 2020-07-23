package br.com.sicoob.cca.frontoffice.negocio.util;

/**
 * Os tipos de agendamento utilizados na transacao CCAINT
 * @author Nairon.Silva
 */
public enum EnumTipoAgendamentoCCA {

	NESTA_DATA(0),
	PROGRAMADO(1);
	
	private Integer codigo;

	private EnumTipoAgendamentoCCA(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Retorna o codigo
	 * @return
	 */
	public Integer getCodigo() {
		return this.codigo;
	}
	
	/**
	 * Retorna o Enum pelo codigo
	 * @param codigo
	 * @return
	 */
	public static EnumTipoAgendamentoCCA getTipoPorCodigo(Integer codigo) {
		for (EnumTipoAgendamentoCCA tipo : values()) {
			if (tipo.getCodigo().equals(codigo)) {
				return tipo;
			}
		}
		return null;
	}
	
}
