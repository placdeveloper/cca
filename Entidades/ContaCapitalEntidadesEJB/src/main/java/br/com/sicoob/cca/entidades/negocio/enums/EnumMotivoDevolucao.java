/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

/**
 * @author Marco.Nascimento
 */
public enum EnumMotivoDevolucao {
	
	/** O atributo COD_MOT_DEV_DEMISSAO. */
	COD_MOT_DEV_DEMISSAO(2,"DEMISSAO"),
	
	/** O atributo COD_MOT_DEV_EXCLUSAO. */
	COD_MOT_DEV_EXCLUSAO(3,"EXCLUSAO"),
	
	/** O atributo COD_MOT_DEV_ELIMINACAO. */
	COD_MOT_DEV_ELIMINACAO(4,"ELIMINACAO"),
	
	/** O atributo COD_MOT_DEV_COB_DEB_VENC_OU_VINC_COOP. */
	COD_MOT_DEV_COB_DEB_VENC_OU_VINC_COOP(5,"COBERTURA DE DEBITOS VENCIDOS OU VINCENDOS COM A COOPERATIVA"),
	
	/** O atributo COD_MOT_DEV_RESG_PARC_IDADE_TEMPO. */
	COD_MOT_DEV_RESG_PARC_IDADE_TEMPO(6,"EM FUNCAO DA IDADE E/OU TEMPO DE ASSOCIACAO"),
	
	/** O atributo COD_MOT_DEV_POR_DEC_DO_CONS_DE_ADM. */
	COD_MOT_DEV_POR_DEC_DO_CONS_DE_ADM(7,"POR DECISAO DO CONSELHO DE ADMINISTRACAO"),

	/** O atributo COD_MOT_DEV_OUTROS. */
	COD_MOT_DEV_OUTROS(99,"OUTROS");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumMotivoDevolucao.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumMotivoDevolucao(Integer codigo, String descricao) {
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