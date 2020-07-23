/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe DadosConciliacaoContabilIntegracaoDTO.
 */
public class DadosConciliacaoContabilIntegracaoDTO extends BancoobDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4476992306986004488L;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo idUnidadeInstituicao. */
	private Integer idUnidadeInstituicao;
	
	/** O atributo idProduto. */
	private Integer idProduto;
	
	/** O atributo dataContabilizacao. */
	private DateTimeDB dataContabilizacao;
	
	/** O atributo numConta. */
	private String numConta;
	
	/** O atributo idTipoSaldoConciliacao. */
	private Integer idTipoSaldoConciliacao;
	
	/** O atributo descTipoSaldoConciliacao. */
	private String descTipoSaldoConciliacao;
	
	/** O atributo valorSaldo. */
	private BigDecimal valorSaldo;
	
	/** O atributo codRelatorio. */
	private String codRelatorio;
	
	/** O atributo bolContabilizado. */
	private boolean bolContabilizado;
	
	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	
	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	/**
	 * Recupera o valor de idUnidadeInstituicao.
	 *
	 * @return o valor de idUnidadeInstituicao
	 */
	public Integer getIdUnidadeInstituicao() {
		return idUnidadeInstituicao;
	}
	
	/**
	 * Define o valor de idUnidadeInstituicao.
	 *
	 * @param idUnidadeInstituicao o novo valor de idUnidadeInstituicao
	 */
	public void setIdUnidadeInstituicao(Integer idUnidadeInstituicao) {
		this.idUnidadeInstituicao = idUnidadeInstituicao;
	}
	
	/**
	 * Recupera o valor de idProduto.
	 *
	 * @return o valor de idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}
	
	/**
	 * Define o valor de idProduto.
	 *
	 * @param idProduto o novo valor de idProduto
	 */
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	/**
	 * Recupera o valor de dataContabilizacao.
	 *
	 * @return o valor de dataContabilizacao
	 */
	public DateTimeDB getDataContabilizacao() {
		return dataContabilizacao;
	}
	
	/**
	 * Define o valor de dataContabilizacao.
	 *
	 * @param dataContabilizacao o novo valor de dataContabilizacao
	 */
	public void setDataContabilizacao(DateTimeDB dataContabilizacao) {
		this.dataContabilizacao = dataContabilizacao;
	}
	
	/**
	 * Recupera o valor de numConta.
	 *
	 * @return o valor de numConta
	 */
	public String getNumConta() {
		return numConta;
	}
	
	/**
	 * Define o valor de numConta.
	 *
	 * @param numConta o novo valor de numConta
	 */
	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}
	
	/**
	 * Recupera o valor de idTipoSaldoConciliacao.
	 *
	 * @return o valor de idTipoSaldoConciliacao
	 */
	public Integer getIdTipoSaldoConciliacao() {
		return idTipoSaldoConciliacao;
	}
	
	/**
	 * Define o valor de idTipoSaldoConciliacao.
	 *
	 * @param idTipoSaldoConciliacao o novo valor de idTipoSaldoConciliacao
	 */
	public void setIdTipoSaldoConciliacao(Integer idTipoSaldoConciliacao) {
		this.idTipoSaldoConciliacao = idTipoSaldoConciliacao;
	}
	
	/**
	 * Recupera o valor de descTipoSaldoConciliacao.
	 *
	 * @return o valor de descTipoSaldoConciliacao
	 */
	public String getDescTipoSaldoConciliacao() {
		return descTipoSaldoConciliacao;
	}
	
	/**
	 * Define o valor de descTipoSaldoConciliacao.
	 *
	 * @param descTipoSaldoConciliacao o novo valor de descTipoSaldoConciliacao
	 */
	public void setDescTipoSaldoConciliacao(String descTipoSaldoConciliacao) {
		this.descTipoSaldoConciliacao = descTipoSaldoConciliacao;
	}
	
	/**
	 * Recupera o valor de valorSaldo.
	 *
	 * @return o valor de valorSaldo
	 */
	public BigDecimal getValorSaldo() {
		return valorSaldo;
	}
	
	/**
	 * Define o valor de valorSaldo.
	 *
	 * @param valorSaldo o novo valor de valorSaldo
	 */
	public void setValorSaldo(BigDecimal valorSaldo) {
		this.valorSaldo = valorSaldo;
	}
	
	/**
	 * Recupera o valor de codRelatorio.
	 *
	 * @return o valor de codRelatorio
	 */
	public String getCodRelatorio() {
		return codRelatorio;
	}
	
	/**
	 * Define o valor de codRelatorio.
	 *
	 * @param codRelatorio o novo valor de codRelatorio
	 */
	public void setCodRelatorio(String codRelatorio) {
		this.codRelatorio = codRelatorio;
	}
	
	/**
	 * Recupera o valor de bolContabilizado.
	 *
	 * @return o valor de bolContabilizado
	 */
	public boolean getBolContabilizado() {
		return bolContabilizado;
	}
	
	/**
	 * Define o valor de bolContabilizado.
	 *
	 * @param bolContabilizado o novo valor de bolContabilizado
	 */
	public void setBolContabilizado(boolean bolContabilizado) {
		this.bolContabilizado = bolContabilizado;
	}	
}
