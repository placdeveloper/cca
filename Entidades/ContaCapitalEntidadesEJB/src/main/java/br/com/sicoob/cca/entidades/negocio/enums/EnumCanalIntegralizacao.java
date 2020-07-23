package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Enum com os tipos de integralizacao para IDCANAL
 * @author Nairon.Silva
 */
public enum EnumCanalIntegralizacao {

	FAP_BOLETO(1, "Faça Parte - Boleto"),
	FAP_CARTAO(2, "Faça Parte - Cartão"),
	PONTOS_CARTAO(3, "Pontos Cartão"),
	FRONTOFFICE_IB(4, "Frontoffice Internet Banking"),
	FRONTOFFICE_MOBILE(6, "Frontoffice Mobile");
	
	private Integer codigo;
	private String descricao;
	
	private EnumCanalIntegralizacao(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * Retorna o código do canal de integralização.
	 * @return
	 */
	public Integer getCodigo() {
		return this.codigo;
	}
	
	/**
	 * Retorna a descrição do canal de integralização.
	 * @return
	 */
	public String getDescricao() {
		return this.descricao;
	}
	
}
