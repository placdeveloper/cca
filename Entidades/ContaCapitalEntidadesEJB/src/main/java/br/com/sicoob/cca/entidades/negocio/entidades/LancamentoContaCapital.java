/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * Lancamento Conta Capital
 * @author antonio.genaro
 * @since 07/04/2016
 */
@Entity
@Table(name = "LANCAMENTOCONTACAPITAL", schema = "cca")
public class LancamentoContaCapital extends ContaCapitalEntidade<Long> {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3317031671175313378L;

	/**
	 * Identificador do lançamento.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDLANCAMENTOCONTACAPITAL")
	private Long id;

	/**
	 *  Identificador do lançamento de estorno.
	 */
	@Column(name = "IDLANCAMENTOCONTACAPITALESTORNO")
	private Long idLancamentoContaCapitalEstorno;
	
	/**
	 * Identificador do tipo de histórico.
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOHISTORICO", nullable = false)
	private TipoHistoricoCCA tipoHistoricoCCA;
	
	/**
	 * Identificador do tipo de histórico de estorno.
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOHISTORICOESTORNO")
	private TipoHistoricoCCA tipoHistoricoEstorno;	
	
	/**
	 * Identificador do tipo de lote.
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOLOTE", nullable = false)
	private TipoLote tipoLote;
	
	/**
	 * Identificador da Conta Capital.
	 */
	@ManyToOne
	@JoinColumn(name = "IDCONTACAPITAL", nullable = false)
	private ContaCapital contaCapital;
	
	
	/**
	 * Identificador da instituição no SCI.
	 */
	@Column(name = "IDINSTITUICAO", nullable = false)
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
	 * Valor do lançamento.
	 */
	@Column(name = "VALORLANCAMENTO", nullable = false)
	private BigDecimal valorLancamento;
	
	/**
	 * Data e hora da execução do lançamento.
	 */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;
	
	/**
	 * Controle do processamento do lançamento.
	 */
	@Column(name = "BOLPROCESSADO", nullable = false)
	private Short bolProcessado;
	
	/**
	 * Identificador do tipo de subscrição.
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOSUBSCRICAO")
	private TipoSubscricao tipoSubscricao;		
	
	/**
	 * numSeqLanc - Controle da chave do sql
	 */
	@Column(name = "NUMSEQLANC", nullable = false)	
	private Integer numSeqLanc;

	/** O atributo descOperacaoExterna. */
	@Column(name="DESCOPERACAOEXTERNA")	
	private String descOperacaoExterna;
	
	
	/**
	 * Recupera o valor de tipoSubscricao.
	 *
	 * @return o valor de tipoSubscricao
	 */
	public TipoSubscricao getTipoSubscricao() {
		return tipoSubscricao;
	}

	/**
	 * Define o valor de tipoSubscricao.
	 *
	 * @param tipoSubscricao o novo valor de tipoSubscricao
	 */
	public void setTipoSubscricao(TipoSubscricao tipoSubscricao) {
		this.tipoSubscricao = tipoSubscricao;
	}

	/**
	 * {@inheritDoc}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o valor de id.
	 *
	 * @param id o novo valor de id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Recupera o valor de idLancamentoContaCapitalEstorno.
	 *
	 * @return o valor de idLancamentoContaCapitalEstorno
	 */
	public Long getIdLancamentoContaCapitalEstorno() {
		return idLancamentoContaCapitalEstorno;
	}

	/**
	 * Define o valor de idLancamentoContaCapitalEstorno.
	 *
	 * @param idLancamentoContaCapitalEstorno o novo valor de idLancamentoContaCapitalEstorno
	 */
	public void setIdLancamentoContaCapitalEstorno(
			Long idLancamentoContaCapitalEstorno) {
		this.idLancamentoContaCapitalEstorno = idLancamentoContaCapitalEstorno;
	}

	/**
	 * Recupera o valor de tipoHistoricoCCA.
	 *
	 * @return o valor de tipoHistoricoCCA
	 */
	public TipoHistoricoCCA getTipoHistoricoCCA() {
		return tipoHistoricoCCA;
	}

	/**
	 * Define o valor de tipoHistoricoCCA.
	 *
	 * @param tipoHistoricoCCA o novo valor de tipoHistoricoCCA
	 */
	public void setTipoHistoricoCCA(TipoHistoricoCCA tipoHistoricoCCA) {
		this.tipoHistoricoCCA = tipoHistoricoCCA;
	}

	/**
	 * Recupera o valor de tipoLote.
	 *
	 * @return o valor de tipoLote
	 */
	public TipoLote getTipoLote() {
		return tipoLote;
	}

	/**
	 * Define o valor de tipoLote.
	 *
	 * @param tipoLote o novo valor de tipoLote
	 */
	public void setTipoLote(TipoLote tipoLote) {
		this.tipoLote = tipoLote;
	}

	/**
	 * Recupera o valor de contaCapital.
	 *
	 * @return o valor de contaCapital
	 */
	public ContaCapital getContaCapital() {
		return contaCapital;
	}

	/**
	 * Define o valor de contaCapital.
	 *
	 * @param contaCapital o novo valor de contaCapital
	 */
	public void setContaCapital(ContaCapital contaCapital) {
		this.contaCapital = contaCapital;
	}

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
	 * Recupera o valor de idUsuario.
	 *
	 * @return o valor de idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Define o valor de idUsuario.
	 *
	 * @param idUsuario o novo valor de idUsuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Recupera o valor de dataLancamento.
	 *
	 * @return o valor de dataLancamento
	 */
	public DateTimeDB getDataLancamento() {
		return dataLancamento;
	}

	/**
	 * Define o valor de dataLancamento.
	 *
	 * @param dataLancamento o novo valor de dataLancamento
	 */
	public void setDataLancamento(DateTimeDB dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	/**
	 * Recupera o valor de descNumDocumento.
	 *
	 * @return o valor de descNumDocumento
	 */
	public String getDescNumDocumento() {
		return descNumDocumento;
	}

	/**
	 * Define o valor de descNumDocumento.
	 *
	 * @param descNumDocumento o novo valor de descNumDocumento
	 */
	public void setDescNumDocumento(String descNumDocumento) {
		this.descNumDocumento = descNumDocumento;
	}

	/**
	 * Recupera o valor de valorLancamento.
	 *
	 * @return o valor de valorLancamento
	 */
	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	/**
	 * Define o valor de valorLancamento.
	 *
	 * @param valorLancamento o novo valor de valorLancamento
	 */
	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	/**
	 * Recupera o valor de dataHoraAtualizacao.
	 *
	 * @return o valor de dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}

	/**
	 * Define o valor de dataHoraAtualizacao.
	 *
	 * @param dataHoraAtualizacao o novo valor de dataHoraAtualizacao
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}

	/**
	 * Recupera o valor de bolProcessado.
	 *
	 * @return o valor de bolProcessado
	 */
	public Short getBolProcessado() {
		return bolProcessado;
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

	/**
	 * Define o valor de bolProcessado.
	 *
	 * @param bolProcessado o novo valor de bolProcessado
	 */
	public void setBolProcessado(Short bolProcessado) {
		this.bolProcessado = bolProcessado;
	}

	/**Recupera a operacao externa que originou o lancamento*/	
	public String getDescOperacaoExterna() {
		return descOperacaoExterna;
	}

	/**Define a operacao externa que originou o lancamento*/		
	public void setDescOperacaoExterna(String descOperacaoExterna) {
		this.descOperacaoExterna = descOperacaoExterna;
	}

	public TipoHistoricoCCA getTipoHistoricoEstorno() {
		return tipoHistoricoEstorno;
	}

	public void setTipoHistoricoEstorno(TipoHistoricoCCA tipoHistoricoEstorno) {
		this.tipoHistoricoEstorno = tipoHistoricoEstorno;
	}
	
}
