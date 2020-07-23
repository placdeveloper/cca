/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * A Classe CapaLoteCapitalLegado.
 */
@Entity
@Table(name="CapaLoteCapital", schema="dbo")
public class CapaLoteCapitalLegado extends ContaCapitalIntegracaoLegadoEntidade {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -8958126977412142205L;

	/** O atributo capaLoteCapitalLegadoPK. */
	@EmbeddedId
	private CapaLoteCapitalLegadoPK	capaLoteCapitalLegadoPK;
	
	/** O atributo qtdLancInf. */
	@Column(name="QtdLancInf")
	private Integer qtdLancInf;
	
	/** O atributo qtdLancApu. */
	@Column(name="QtdLancApu")
	private Integer qtdLancApu;
	
	/** O atributo valorTotalLoteApu. */
	@Column(name="ValorTotalLoteApu")
	private BigDecimal valorTotalLoteApu;
	
	/** O atributo valorTotalLoteInf. */
	@Column(name="ValorTotalLoteInf")
	private BigDecimal valorTotalLoteInf;
	
	/** O atributo bolAtualizado. */
	@Column(name="BolAtualizado")
	private Boolean bolAtualizado;
	
	/** O atributo codOrigemLote. */
	@Column(name="CodOrigemLote")
	private Integer codOrigemLote;
	
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
	
	/** O atributo bolNovo. */
	@Transient
	private Boolean bolNovo;
	
	/**
	 * Recupera o valor de bolNovo.
	 *
	 * @return o valor de bolNovo
	 */
	@Transient
	public Boolean getBolNovo() {
		return bolNovo;
	}
	
	/**
	 * Define o valor de bolNovo.
	 *
	 * @param bolNovo o novo valor de bolNovo
	 */
	@Transient
	public void setBolNovo(Boolean bolNovo) {
		this.bolNovo = bolNovo;
	}	
	
	/**
	 * Recupera o valor de capaLoteCapitalLegadoPK.
	 *
	 * @return o valor de capaLoteCapitalLegadoPK
	 */
	public CapaLoteCapitalLegadoPK getCapaLoteCapitalLegadoPK() {
		return capaLoteCapitalLegadoPK;
	}
	
	/**
	 * Define o valor de capaLoteCapitalLegadoPK.
	 *
	 * @param capaLoteCapitalLegadoPK o novo valor de capaLoteCapitalLegadoPK
	 */
	public void setCapaLoteCapitalLegadoPK(
			CapaLoteCapitalLegadoPK capaLoteCapitalLegadoPK) {
		this.capaLoteCapitalLegadoPK = capaLoteCapitalLegadoPK;
	}
	
	/**
	 * Recupera o valor de qtdLancInf.
	 *
	 * @return o valor de qtdLancInf
	 */
	public Integer getQtdLancInf() {
		return qtdLancInf;
	}
	
	/**
	 * Define o valor de qtdLancInf.
	 *
	 * @param qtdLancInf o novo valor de qtdLancInf
	 */
	public void setQtdLancInf(Integer qtdLancInf) {
		this.qtdLancInf = qtdLancInf;
	}
	
	/**
	 * Recupera o valor de qtdLancApu.
	 *
	 * @return o valor de qtdLancApu
	 */
	public Integer getQtdLancApu() {
		return qtdLancApu;
	}
	
	/**
	 * Define o valor de qtdLancApu.
	 *
	 * @param qtdLancApu o novo valor de qtdLancApu
	 */
	public void setQtdLancApu(Integer qtdLancApu) {
		this.qtdLancApu = qtdLancApu;
	}
	
	/**
	 * Recupera o valor de valorTotalLoteApu.
	 *
	 * @return o valor de valorTotalLoteApu
	 */
	public BigDecimal getValorTotalLoteApu() {
		return valorTotalLoteApu;
	}
	
	/**
	 * Define o valor de valorTotalLoteApu.
	 *
	 * @param valorTotalLoteApu o novo valor de valorTotalLoteApu
	 */
	public void setValorTotalLoteApu(BigDecimal valorTotalLoteApu) {
		this.valorTotalLoteApu = valorTotalLoteApu;
	}
	
	/**
	 * Recupera o valor de valorTotalLoteInf.
	 *
	 * @return o valor de valorTotalLoteInf
	 */
	public BigDecimal getValorTotalLoteInf() {
		return valorTotalLoteInf;
	}
	
	/**
	 * Define o valor de valorTotalLoteInf.
	 *
	 * @param valorTotalLoteInf o novo valor de valorTotalLoteInf
	 */
	public void setValorTotalLoteInf(BigDecimal valorTotalLoteInf) {
		this.valorTotalLoteInf = valorTotalLoteInf;
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
	 * Recupera o valor de codOrigemLote.
	 *
	 * @return o valor de codOrigemLote
	 */
	public Integer getCodOrigemLote() {
		return codOrigemLote;
	}
	
	/**
	 * Define o valor de codOrigemLote.
	 *
	 * @param codOrigemLote o novo valor de codOrigemLote
	 */
	public void setCodOrigemLote(Integer codOrigemLote) {
		this.codOrigemLote = codOrigemLote;
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

}

