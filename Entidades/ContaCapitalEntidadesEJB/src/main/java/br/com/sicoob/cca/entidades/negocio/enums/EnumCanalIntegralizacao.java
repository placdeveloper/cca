package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * Enum com os tipos de integralizacao para IDCANAL
 * @author Nairon.Silva
 */
public enum EnumCanalIntegralizacao {

	FAP_BOLETO(1, "Fa�a Parte - Boleto"),
	FAP_CARTAO(2, "Fa�a Parte - Cart�o"),
	PONTOS_CARTAO(3, "Pontos Cart�o"),
	FRONTOFFICE_IB(4, "Frontoffice Internet Banking"),
	FRONTOFFICE_MOBILE(6, "Frontoffice Mobile");
	
	private Integer codigo;
	private String descricao;
	
	private EnumCanalIntegralizacao(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * Retorna o c�digo do canal de integraliza��o.
	 * @return
	 */
	public Integer getCodigo() {
		return this.codigo;
	}
	
	/**
	 * Retorna a descri��o do canal de integraliza��o.
	 * @return
	 */
	public String getDescricao() {
		return this.descricao;
	}
	
}
