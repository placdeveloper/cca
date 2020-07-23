package br.com.sicoob.cca.entidades.negocio.enums;

public enum EnumSituacaoArquivoRemessa {

	/** O atributo COD_SITUACAO_ARQUIVO_REMESSA_EM_ABERTO. */
	COD_SITUACAO_ARQUIVO_REMESSA_EM_ABERTO(0,"EM ABERTO"),
	
	/** O atributo COD_SITUACAO_ARQUIVO_REMESSA_PROCESSADO_PARA_DEBITO. */
	COD_SITUACAO_ARQUIVO_REMESSA_PROCESSADO_PARA_DEBITO(1,"PROCESSADO PARA DEBITO"),

	/** O atributo COD_SITUACAO_ARQUIVO_REMESSA_PROCESSADO_PARA_LIQUIDACAO. */
	COD_SITUACAO_ARQUIVO_REMESSA_PROCESSADO_PARA_LIQUIDACAO(2,"PROCESSADO PARA LIQUIDACAO"),

	/** O atributo COD_SITUACAO_ARQUIVO_REMESSA_ARQUIVO_INVALIDO. */
	COD_SITUACAO_ARQUIVO_REMESSA_ARQUIVO_INVALIDO(3,"ARQUIVO INVALIDO"),
	
	/** O atributo COD_SITUACAO_ARQUIVO_REMESSA_NAO_ENVIADO. */
	COD_SITUACAO_ARQUIVO_REMESSA_NAO_ENVIADO(4,"NÃO ENVIADO"),
	
	/** O atributo COD_SITUACAO_ARQUIVO_REMESSA_PROCESSADO_PARA_CREDITO. */
	COD_SITUACAO_ARQUIVO_REMESSA_PROCESSADO_PARA_CREDITO(5,"PROCESSADO PARA CREDITO");
	
	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo EnumSituacaoContaCapital.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumSituacaoArquivoRemessa(Integer codigo, String descricao) {
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
