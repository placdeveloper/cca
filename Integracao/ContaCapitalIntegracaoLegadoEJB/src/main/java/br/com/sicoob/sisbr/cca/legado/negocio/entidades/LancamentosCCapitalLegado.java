/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * A Classe LancamentosCCapitalLegado.
 */
@Entity
@Table(name="LancamentosCCapital", schema="dbo")
public class LancamentosCCapitalLegado extends	ContaCapitalIntegracaoLegadoEntidade {


	/**
	 * 
	 */
	private static final long serialVersionUID = 9221992608453104623L;

	/** O atributo lancamentosCCapitalLegadoPK. */
	@EmbeddedId
	private LancamentosCCapitalLegadoPK lancamentosCCapitalLegadoPK;
	
	/** O atributo contaCapitalLegado. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "numMatricula", referencedColumnName = "numMatricula")
	private ContaCapitalLegado contaCapitalLegado;	
	
	/** O atributo descNumDocumento. */
	@Column(name="DescNumDocumento")	
	private String descNumDocumento;
	
	/** O atributo valorLanc. */
	@Column(name="ValorLanc")	
	private BigDecimal valorLanc;
	
	/** O atributo bolAtualizado. */
	@Column(name="BolAtualizado")	
	private Boolean bolAtualizado;
	
	/** O atributo iDProduto. */
	@Column(name="IDProduto")	
	private Integer iDProduto;
	
	/** O atributo iDTipoHistoricoLanc. */
	@Column(name="IDTipoHistoricoLanc")	
	private Integer iDTipoHistoricoLanc;
	
	/** O atributo iDProdutoEst. */
	@Column(name="IDProdutoEst")	
	private Integer iDProdutoEst;
	
	/** O atributo iDTipoHistoricoEstorno. */
	@Column(name="IDTipoHistoricoEstorno")	
	private Integer iDTipoHistoricoEstorno;
	
	/** O atributo iDUsuarioResp. */
	@Column(name="IDUsuarioResp")	
	private String iDUsuarioResp;
	
	/** O atributo dataHoraInclusao. */
	@Column(name="DataHoraInclusao")	
	private DateTimeDB dataHoraInclusao;
	
	/** O atributo codMotivoDevolucao. */
	@Column(name="CodMotivoDevolucao")	
	private Integer codMotivoDevolucao;
	
	/** O atributo descObsDevolucao. */
	@Column(name="DescObsDevolucao")	
	private String descObsDevolucao;

	/** O atributo idTipoSubscricao. */
	@Column(name="IDTIPOSUBSCRICAO")	
	private Short idTipoSubscricao;	
	
	/** O atributo idLancamentoContaCapital. */
	@Column(name="IDLANCAMENTOCONTACAPITAL")	
	private Long idLancamentoContaCapital;	
	
	/** O atributo descOperacaoExterna. */
	@Column(name="descOperacaoExterna")	
	private String descOperacaoExterna;	
	
	/**
	 * Recupera o valor de idLancamentoContaCapital.
	 *
	 * @return o valor de idLancamentoContaCapital
	 */
	public Long getIdLancamentoContaCapital() {
		return idLancamentoContaCapital;
	}
	
	/**
	 * Define o valor de idLancamentoContaCapital.
	 *
	 * @param idLancamentoContaCapital o novo valor de idLancamentoContaCapital
	 */
	public void setIdLancamentoContaCapital(Long idLancamentoContaCapital) {
		this.idLancamentoContaCapital = idLancamentoContaCapital;
	}
	
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
	 * Recupera o valor de lancamentosCCapitalLegadoPK.
	 *
	 * @return o valor de lancamentosCCapitalLegadoPK
	 */
	public LancamentosCCapitalLegadoPK getLancamentosCCapitalLegadoPK() {
		return lancamentosCCapitalLegadoPK;
	}
	
	/**
	 * Define o valor de lancamentosCCapitalLegadoPK.
	 *
	 * @param lancamentosCCapitalLegadoPK o novo valor de lancamentosCCapitalLegadoPK
	 */
	public void setLancamentosCCapitalLegadoPK(LancamentosCCapitalLegadoPK lancamentosCCapitalLegadoPK) {
		this.lancamentosCCapitalLegadoPK = lancamentosCCapitalLegadoPK;
	}
	
	/**
	 * Recupera o valor de contaCapitalLegado.
	 *
	 * @return o valor de contaCapitalLegado
	 */
	public ContaCapitalLegado getContaCapitalLegado() {
		return contaCapitalLegado;
	}
	
	/**
	 * Define o valor de contaCapitalLegado.
	 *
	 * @param contaCapitalLegado o novo valor de contaCapitalLegado
	 */
	public void setContaCapitalLegado(ContaCapitalLegado contaCapitalLegado) {
		this.contaCapitalLegado = contaCapitalLegado;
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
	 * Recupera o valor de valorLanc.
	 *
	 * @return o valor de valorLanc
	 */
	public BigDecimal getValorLanc() {
		return valorLanc;
	}
	
	/**
	 * Define o valor de valorLanc.
	 *
	 * @param valorLanc o novo valor de valorLanc
	 */
	public void setValorLanc(BigDecimal valorLanc) {
		this.valorLanc = valorLanc;
	}
	
	/**
	 * Recupera o valor de bolAtualizado.
	 *
	 * @return o valor de bolAtualizado
	 */
	public Boolean getBolAtualizado() {
		return bolAtualizado;
	}
	
	/**
	 * Define o valor de bolAtualizado.
	 *
	 * @param bolAtualizado o novo valor de bolAtualizado
	 */
	public void setBolAtualizado(Boolean bolAtualizado) {
		this.bolAtualizado = bolAtualizado;
	}
	
	/**
	 * Recupera o valor de iDProduto.
	 *
	 * @return o valor de iDProduto
	 */
	public Integer getiDProduto() {
		return iDProduto;
	}
	
	/**
	 * Define o valor de iDProduto.
	 *
	 * @param iDProduto o novo valor de iDProduto
	 */
	public void setiDProduto(Integer iDProduto) {
		this.iDProduto = iDProduto;
	}
	
	/**
	 * Recupera o valor de iDTipoHistoricoLanc.
	 *
	 * @return o valor de iDTipoHistoricoLanc
	 */
	public Integer getiDTipoHistoricoLanc() {
		return iDTipoHistoricoLanc;
	}
	
	/**
	 * Define o valor de iDTipoHistoricoLanc.
	 *
	 * @param iDTipoHistoricoLanc o novo valor de iDTipoHistoricoLanc
	 */
	public void setiDTipoHistoricoLanc(Integer iDTipoHistoricoLanc) {
		this.iDTipoHistoricoLanc = iDTipoHistoricoLanc;
	}
	
	/**
	 * Recupera o valor de iDProdutoEst.
	 *
	 * @return o valor de iDProdutoEst
	 */
	public Integer getiDProdutoEst() {
		return iDProdutoEst;
	}
	
	/**
	 * Define o valor de iDProdutoEst.
	 *
	 * @param iDProdutoEst o novo valor de iDProdutoEst
	 */
	public void setiDProdutoEst(Integer iDProdutoEst) {
		this.iDProdutoEst = iDProdutoEst;
	}
	
	/**
	 * Recupera o valor de iDTipoHistoricoEstorno.
	 *
	 * @return o valor de iDTipoHistoricoEstorno
	 */
	public Integer getiDTipoHistoricoEstorno() {
		return iDTipoHistoricoEstorno;
	}
	
	/**
	 * Define o valor de iDTipoHistoricoEstorno.
	 *
	 * @param iDTipoHistoricoEstorno o novo valor de iDTipoHistoricoEstorno
	 */
	public void setiDTipoHistoricoEstorno(Integer iDTipoHistoricoEstorno) {
		this.iDTipoHistoricoEstorno = iDTipoHistoricoEstorno;
	}
	
	/**
	 * Recupera o valor de iDUsuarioResp.
	 *
	 * @return o valor de iDUsuarioResp
	 */
	public String getiDUsuarioResp() {
		return iDUsuarioResp;
	}
	
	/**
	 * Define o valor de iDUsuarioResp.
	 *
	 * @param iDUsuarioResp o novo valor de iDUsuarioResp
	 */
	public void setiDUsuarioResp(String iDUsuarioResp) {
		this.iDUsuarioResp = iDUsuarioResp;
	}
	
	/**
	 * Recupera o valor de dataHoraInclusao.
	 *
	 * @return o valor de dataHoraInclusao
	 */
	public DateTimeDB getDataHoraInclusao() {
		return dataHoraInclusao;
	}
	
	/**
	 * Define o valor de dataHoraInclusao.
	 *
	 * @param dataHoraInclusao o novo valor de dataHoraInclusao
	 */
	public void setDataHoraInclusao(DateTimeDB dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}
	
	/**
	 * Recupera o valor de codMotivoDevolucao.
	 *
	 * @return o valor de codMotivoDevolucao
	 */
	public Integer getCodMotivoDevolucao() {
		return codMotivoDevolucao;
	}
	
	/**
	 * Define o valor de codMotivoDevolucao.
	 *
	 * @param codMotivoDevolucao o novo valor de codMotivoDevolucao
	 */
	public void setCodMotivoDevolucao(Integer codMotivoDevolucao) {
		this.codMotivoDevolucao = codMotivoDevolucao;
	}
	
	/**
	 * Recupera o valor de descObsDevolucao.
	 *
	 * @return o valor de descObsDevolucao
	 */
	public String getDescObsDevolucao() {
		return descObsDevolucao;
	}
	
	/**
	 * Define o valor de descObsDevolucao.
	 *
	 * @param descObsDevolucao o novo valor de descObsDevolucao
	 */
	public void setDescObsDevolucao(String descObsDevolucao) {
		this.descObsDevolucao = descObsDevolucao;
	}

	/**Recupera a operacao externa que originou o lancamento*/
	public String getDescOperacaoExterna() {
		return descOperacaoExterna;
	}

	/**Define a operacao externa que originou o lancamento*/	
	public void setDescOperacaoExterna(String descOperacaoExterna) {
		this.descOperacaoExterna = descOperacaoExterna;
	}
	
}	