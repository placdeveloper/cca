/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * Histórico de lançamento
 * @author marco.nascimento
 * @since 19/05/2014
 */
@Entity
@Table(name = "HISTLANCAMENTOCONTACAPITAL", schema = "cca")
public class HistoricoLancamentoCCA extends ContaCapitalEntidade<Long> {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3442784866782872877L;

	/**
	 * Identificador do lançamento de historico
	 */
	@Id
	@Column(name = "IDHISTLANCAMENTOCONTACAPITAL")
	private Long id;
	
	/**
	 * Auto relacionamento
	 * Identificador do lançamento de historico de estorno.
	 */
	@Column(name = "IDHISTLANCAMENTOESTORNOCONTACAPITAL")
	private Long idHistLancEstornoContaCapital;
	
	/**
	 * Identificador do tipo de histórico.
	 */
	@Column(name = "IDTIPOHISTORICO")
	private Short idTipoHistorico;
	
	@Column(name = "IDTIPOHISTORICOESTORNO")
	private Short idTipoHistoricoEstorno;
	
	/**
	 * Identificador do tipo de lote.
	 */
	@Column(name = "IDTIPOLOTE")
	private Short idTipoLote;
	
	/**
	 * Identificador da Conta Capital
	 */
	@Column(name = "IDCONTACAPITAL")
	private Integer idContaCapital;
	
	/**
	 * Identificador da instituição no SCI
	 */
	@Column(name = "IDINSTITUICAO")
	private Integer idInstituicao;
	
	/**
	 * Identificador do usuário.
	 */
	@Column(name = "IDUSUARIO", length = 40, nullable = false)
	private String idUsuario;
	
	/**
	 * Data do lançamento.
	 */
	@Column(name = "DATALANCAMENTO", nullable = false)
	private DateTimeDB dataLancamento;
	
	/**
	 * Número do documento.
	 */
	@Column(name = "DESCNUMDOCUMENTO", length = 40, nullable = false)
	private String descNumDocumento;
	
	/**
	 * Valor do lançamento
	 */
	@Column(name = "VALORLANCAMENTO")
	private BigDecimal valorLancamento;
	
	/**
	 * Data e hora da execução do lançamento.
	 */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;
	
	/**
	 * Tipo de subscrição.
	 */
	@Column(name = "IDTIPOSUBSCRICAO")
	private Short idTipoSubscricao;
	
	/**
	 * numSeqLanc - Controle da chave do sql
	 */
	@Column(name = "NUMSEQLANC", nullable = false)	
	private Integer numSeqLanc;	

	/**
	 * Recupera o valor de idTipoSubscricao.
	 *
	 * @return o valor de idTipoSubscricao
	 */
	public Short getIdTipoSubscricao() {
		return idTipoSubscricao;
	}

	/**
	 * Define o valor de idTipoSubscricao.
	 *
	 * @param idTipoSubscricao o novo valor de idTipoSubscricao
	 */
	public void setIdTipoSubscricao(Short idTipoSubscricao) {
		this.idTipoSubscricao = idTipoSubscricao;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idHistLancEstornoContaCapital
	 */
	public Long getIdHistLancEstornoContaCapital() {
		return idHistLancEstornoContaCapital;
	}

	/**
	 * @param idHistLancEstornoContaCapital the idHistLancEstornoContaCapital to set
	 */
	public void setIdHistLancEstornoContaCapital(Long idHistLancEstornoContaCapital) {
		this.idHistLancEstornoContaCapital = idHistLancEstornoContaCapital;
	}

	/**
	 * @return the idTipoHistorico
	 */
	public Short getIdTipoHistorico() {
		return idTipoHistorico;
	}

	/**
	 * @param idTipoHistorico the idTipoHistorico to set
	 */
	public void setIdTipoHistorico(Short idTipoHistorico) {
		this.idTipoHistorico = idTipoHistorico;
	}

	/**
	 * @return the idTipoLote
	 */
	public Short getIdTipoLote() {
		return idTipoLote;
	}

	/**
	 * @param idTipoLote the idTipoLote to set
	 */
	public void setIdTipoLote(Short idTipoLote) {
		this.idTipoLote = idTipoLote;
	}

	/**
	 * @return the idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}

	/**
	 * @param idContaCapital the idContaCapital to set
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
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
	 * @return the dataLancamento
	 */
	public DateTimeDB getDataLancamento() {
		return dataLancamento;
	}

	/**
	 * @param dataLancamento the dataLancamento to set
	 */
	public void setDataLancamento(DateTimeDB dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	/**
	 * @return the descNumDocumento
	 */
	public String getDescNumDocumento() {
		return descNumDocumento;
	}

	/**
	 * @param descNumDocumento the descNumDocumento to set
	 */
	public void setDescNumDocumento(String descNumDocumento) {
		this.descNumDocumento = descNumDocumento;
	}

	/**
	 * @return the valorLancamento
	 */
	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	/**
	 * @param valorLancamento the valorLancamento to set
	 */
	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	/**
	 * @return the dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}

	/**
	 * @param dataHoraAtualizacao the dataHoraAtualizacao to set
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
	
	/**
	 * getNumSeqLanc
	 * @return
	 */
	public Integer getNumSeqLanc() {
		return numSeqLanc;
	}

	/**
	 * setNumSeqLanc
	 * @return
	 */	
	public void setNumSeqLanc(Integer numSeqLanc) {
		this.numSeqLanc = numSeqLanc;
	}

	public Short getIdTipoHistoricoEstorno() {
		return idTipoHistoricoEstorno;
	}

	public void setIdTipoHistoricoEstorno(Short idTipoHistoricoEstorno) {
		this.idTipoHistoricoEstorno = idTipoHistoricoEstorno;
	}
	
}