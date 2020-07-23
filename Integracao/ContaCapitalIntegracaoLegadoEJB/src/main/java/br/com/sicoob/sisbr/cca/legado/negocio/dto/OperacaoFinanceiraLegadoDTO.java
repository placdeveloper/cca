/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.tipos.DateTime;


/**
 * @author Marco.Nascimento
 */
public class OperacaoFinanceiraLegadoDTO extends BancoobDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4379603895243112918L;
	
	/** O atributo idOperacaoFinanceira. */
	private Integer idOperacaoFinanceira;
	
	/** O atributo dataOpFinanceira. */
	private DateTime dataOpFinanceira;
	
	/** O atributo dataProcessamento. */
	private DateTime dataProcessamento;
	
	/** O atributo codStatusOpFin. */
	private Integer codStatusOpFin;
	
	/** O atributo codTipoMovimento. */
	private Integer codTipoMovimento;
	
	/** O atributo codSubRelCont. */
	private Integer codSubRelCont;
	
	/** O atributo codTipoContabilizacao. */
	private Integer codTipoContabilizacao;
	
	/** O atributo numReferOpFin. */
	private BigDecimal numReferOpFin;
	
	/** O atributo numCliente. */
	private Integer numCliente;
	
	/** O atributo idOperacao. */
	private Integer idOperacao;
	
	/** O atributo idModalidadeProduto. */
	private Integer idModalidadeProduto;
	
	/** O atributo idProduto. */
	private Integer idProduto;
	
	/** O atributo idUsuario. */
	private String idUsuario;
	
	/** O atributo idTipoHistorico. */
	private Integer idTipoHistorico;
	
	/** O atributo bolContabilizaCoop. */
	private Short bolContabilizaCoop;
	
	/** O atributo numCoopRecebedora. */
	private Integer numCoopRecebedora;
	
	/** O atributo numCoopDepositaria. */
	private Integer numCoopDepositaria;
	
	/** O atributo numCoopCentral. */
	private Integer numCoopCentral;
	
	/** O atributo numCoopDepositariaAnt. */
	private Integer numCoopDepositariaAnt;
	
	/** O atributo bolOpProcessada. */
	private Short bolOpProcessada;
	
	/** O atributo numPac. */
	private Integer numPac;
	
	/**
	 * Referentes a tabela ValorOpFin da OperacaoFinanceira
	 */
	private BigDecimal valor;
	
	/** O atributo idTipoValorCont. */
	private Integer idTipoValorCont;
	
	/**
	 * Data da operacao financeira
	 * {@link ContaCapitalUtil#formatarDataUS(java.util.Date)}
	 * @return
	 */
	public String getDataOpFinanceiraFormatado() {
		return ContaCapitalUtil.formatarDataUS(this.dataOpFinanceira);
	}
	
	/**
	 * Data de processamento
	 * {@link ContaCapitalUtil#formatarDataUS(java.util.Date)}
	 * @return
	 */
	public String getDataProcessamentoFormatado() {
		return ContaCapitalUtil.formatarDataUS(this.dataProcessamento);
	}
	
	/**
	 * Identifica cooperativa da operacao financeira em questao 
	 */
	private Integer numCoopSingular;


	/**
	 * @return the idOperacaoFinanceira
	 */
	public Integer getIdOperacaoFinanceira() {
		return idOperacaoFinanceira;
	}

	/**
	 * @param idOperacaoFinanceira the idOperacaoFinanceira to set
	 */
	public void setIdOperacaoFinanceira(Integer idOperacaoFinanceira) {
		this.idOperacaoFinanceira = idOperacaoFinanceira;
	}

	/**
	 * @return the dataOpFinanceira
	 */
	public DateTime getDataOpFinanceira() {
		return dataOpFinanceira;
	}

	/**
	 * @param dataOpFinanceira the dataOpFinanceira to set
	 */
	public void setDataOpFinanceira(DateTime dataOpFinanceira) {
		this.dataOpFinanceira = dataOpFinanceira;
	}

	/**
	 * @return the dataProcessamento
	 */
	public DateTime getDataProcessamento() {
		return dataProcessamento;
	}

	/**
	 * @param dataProcessamento the dataProcessamento to set
	 */
	public void setDataProcessamento(DateTime dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	/**
	 * @return the codStatusOpFin
	 */
	public Integer getCodStatusOpFin() {
		return codStatusOpFin;
	}

	/**
	 * @param codStatusOpFin the codStatusOpFin to set
	 */
	public void setCodStatusOpFin(Integer codStatusOpFin) {
		this.codStatusOpFin = codStatusOpFin;
	}

	/**
	 * @return the codTipoMovimento
	 */
	public Integer getCodTipoMovimento() {
		return codTipoMovimento;
	}

	/**
	 * @param codTipoMovimento the codTipoMovimento to set
	 */
	public void setCodTipoMovimento(Integer codTipoMovimento) {
		this.codTipoMovimento = codTipoMovimento;
	}

	/**
	 * @return the codSubRelCont
	 */
	public Integer getCodSubRelCont() {
		return codSubRelCont;
	}

	/**
	 * @param codSubRelCont the codSubRelCont to set
	 */
	public void setCodSubRelCont(Integer codSubRelCont) {
		this.codSubRelCont = codSubRelCont;
	}

	/**
	 * @return the codTipoContabilizacao
	 */
	public Integer getCodTipoContabilizacao() {
		return codTipoContabilizacao;
	}

	/**
	 * @param codTipoContabilizacao the codTipoContabilizacao to set
	 */
	public void setCodTipoContabilizacao(Integer codTipoContabilizacao) {
		this.codTipoContabilizacao = codTipoContabilizacao;
	}

	/**
	 * @return the numReferOpFin
	 */
	public BigDecimal getNumReferOpFin() {
		return numReferOpFin;
	}

	/**
	 * @param numReferOpFin the numReferOpFin to set
	 */
	public void setNumReferOpFin(BigDecimal numReferOpFin) {
		this.numReferOpFin = numReferOpFin;
	}

	/**
	 * @return the numCliente
	 */
	public Integer getNumCliente() {
		return numCliente;
	}

	/**
	 * @param numCliente the numCliente to set
	 */
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}

	/**
	 * @return the idOperacao
	 */
	public Integer getIdOperacao() {
		return idOperacao;
	}

	/**
	 * @param idOperacao the idOperacao to set
	 */
	public void setIdOperacao(Integer idOperacao) {
		this.idOperacao = idOperacao;
	}

	/**
	 * @return the idModalidadeProduto
	 */
	public Integer getIdModalidadeProduto() {
		return idModalidadeProduto;
	}

	/**
	 * @param idModalidadeProduto the idModalidadeProduto to set
	 */
	public void setIdModalidadeProduto(Integer idModalidadeProduto) {
		this.idModalidadeProduto = idModalidadeProduto;
	}

	/**
	 * @return the idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}

	/**
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the idTipoHistorico
	 */
	public Integer getIdTipoHistorico() {
		return idTipoHistorico;
	}

	/**
	 * @param idTipoHistorico the idTipoHistorico to set
	 */
	public void setIdTipoHistorico(Integer idTipoHistorico) {
		this.idTipoHistorico = idTipoHistorico;
	}

	/**
	 * @return the bolContabilizaCoop
	 */
	public Short getBolContabilizaCoop() {
		return bolContabilizaCoop;
	}

	/**
	 * @param bolContabilizaCoop the bolContabilizaCoop to set
	 */
	public void setBolContabilizaCoop(Short bolContabilizaCoop) {
		this.bolContabilizaCoop = bolContabilizaCoop;
	}

	/**
	 * @return the numCoopRecebedora
	 */
	public Integer getNumCoopRecebedora() {
		return numCoopRecebedora;
	}

	/**
	 * @param numCoopRecebedora the numCoopRecebedora to set
	 */
	public void setNumCoopRecebedora(Integer numCoopRecebedora) {
		this.numCoopRecebedora = numCoopRecebedora;
	}

	/**
	 * @return the numCoopDepositaria
	 */
	public Integer getNumCoopDepositaria() {
		return numCoopDepositaria;
	}

	/**
	 * @param numCoopDepositaria the numCoopDepositaria to set
	 */
	public void setNumCoopDepositaria(Integer numCoopDepositaria) {
		this.numCoopDepositaria = numCoopDepositaria;
	}

	/**
	 * @return the numCoopCentral
	 */
	public Integer getNumCoopCentral() {
		return numCoopCentral;
	}

	/**
	 * @param numCoopCentral the numCoopCentral to set
	 */
	public void setNumCoopCentral(Integer numCoopCentral) {
		this.numCoopCentral = numCoopCentral;
	}

	/**
	 * @return the numCoopDepositariaAnt
	 */
	public Integer getNumCoopDepositariaAnt() {
		return numCoopDepositariaAnt;
	}

	/**
	 * @param numCoopDepositariaAnt the numCoopDepositariaAnt to set
	 */
	public void setNumCoopDepositariaAnt(Integer numCoopDepositariaAnt) {
		this.numCoopDepositariaAnt = numCoopDepositariaAnt;
	}

	/**
	 * @return the bolOpProcessada
	 */
	public Short getBolOpProcessada() {
		return bolOpProcessada;
	}

	/**
	 * @param bolOpProcessada the bolOpProcessada to set
	 */
	public void setBolOpProcessada(Short bolOpProcessada) {
		this.bolOpProcessada = bolOpProcessada;
	}

	/**
	 * @return the numPac
	 */
	public Integer getNumPac() {
		return numPac;
	}

	/**
	 * @param numPac the numPac to set
	 */
	public void setNumPac(Integer numPac) {
		this.numPac = numPac;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the idTipoValorCont
	 */
	public Integer getIdTipoValorCont() {
		return idTipoValorCont;
	}

	/**
	 * @param idTipoValorCont the idTipoValorCont to set
	 */
	public void setIdTipoValorCont(Integer idTipoValorCont) {
		this.idTipoValorCont = idTipoValorCont;
	}

	/**
	 * @return the numCoopSingular
	 */
	public Integer getNumCoopSingular() {
		return numCoopSingular;
	}

	/**
	 * @param numCoopSingular the numCoopSingular to set
	 */
	public void setNumCoopSingular(Integer numCoopSingular) {
		this.numCoopSingular = numCoopSingular;
	}
}