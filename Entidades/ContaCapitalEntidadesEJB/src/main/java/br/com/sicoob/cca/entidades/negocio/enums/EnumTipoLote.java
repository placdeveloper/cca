/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.enums;

import java.util.Date;

import br.com.sicoob.cca.entidades.negocio.util.DatasVigencia;

/**
 * @author Marco.Nascimento
 */
public enum EnumTipoLote {
	
	/** O atributo COD_LOTE_CCA_PARC_AVISTA. */
	COD_LOTE_CCA_PARC_AVISTA(9000,"PARCELA A VISTA"),
	
	/** O atributo COD_LOTE_CCA_PARC_APRAZO. */
	COD_LOTE_CCA_PARC_APRAZO(9001,"PARCELA A PRAZO"),
	
	/** O atributo COD_LOTE_CCA_DEST_JUROS. */
	COD_LOTE_CCA_DEST_JUROS(9002,"JUROS AO CAPITAL"),
	
	/** O atributo COD_LOTE_CCA_DEST_RATEIO. */
	COD_LOTE_CCA_DEST_RATEIO(9003,"RATEIO DE SOBRAS"),
	
	/** O atributo COD_LOTE_CCA_TRANSFERENCIA. */
	COD_LOTE_CCA_TRANSFERENCIA(9004,"TRANSFERENCIA DE CAPITAL"),
	
	/** O atributo COD_LOTE_CCA_COBRANCA. */
	COD_LOTE_CCA_COBRANCA(9005,"COBRANCA"),
	
	/** O atributo COD_LOTE_CCA_ARQUIVO. */
	COD_LOTE_CCA_ARQUIVO(9006,"ARQUIVO OU FOLHA"),
	
	/** O atributo COD_LOTE_CCA_MIGRACAO. */
	COD_LOTE_CCA_MIGRACAO(9007,"MIGRACAO"),
	
	/** O atributo COD_LOTE_CCA_INCORP. */
	COD_LOTE_CCA_INCORP(9008,"INCORPORACAO"),
	
	/** O atributo COD_LOTE_CCA_CAPITAL_CONSIGNADO. */
	COD_LOTE_CCA_CAPITAL_CONSIGNADO(9009,"CAPITAL CONSIGNADO"),
	
	/** O atributo COD_LOTE_CCA_PROV_EFETIVADA. */
	COD_LOTE_CCA_PROV_EFETIVADA(9901,"PROVISAO EFETIVADA"),
	
	/** O atributo COD_LOTE_CCA_PROV_CANCELADA. */
	COD_LOTE_CCA_PROV_CANCELADA(9902,"PROVISAO CANCELADA"),
	
	COD_LOTE_CCA_PONTOS_CABAL(9010, "PONTOS CABAL", DatasVigencia.LOTES_CABAL_FAP_CANAIS),
	
	COD_LOTE_CCA_INTEGRALIZACAO_FAP(9011, "INTEGRALIZACAO FAP", DatasVigencia.LOTES_CABAL_FAP_CANAIS),
	
	COD_LOTE_CCA_CANAIS_ATENDIMENTO(9012, "CANAIS DE ATENDIMENTO", DatasVigencia.LOTES_CABAL_FAP_CANAIS),
	
	COD_LOTE_CCA_OUTROS_BANCOS(9013, "OUTROS BANCOS");

	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	private Date dataVigencia;
	
	/**
	 * Instancia um novo EnumTipoLote.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private EnumTipoLote(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * Construtor adicionando dataVigencia
	 * 
	 * @param codigo
	 * @param descricao
	 * @param dataVigencia
	 */
	private EnumTipoLote(Integer codigo, String descricao, Date dataVigencia) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataVigencia = dataVigencia;
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
	
	/**
	 * Recupera a data de vigencia, se aplicavel.
	 * 
	 * @return
	 */
	public Date getDataVigencia() {
		return dataVigencia;
	}
	
	/**
	 * Verifica se o lote esta vigente
	 * @return
	 */
	public boolean isVigente() {
		return dataVigencia == null || !new Date().before(dataVigencia);
	}
	
}