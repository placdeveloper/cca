package br.com.sicoob.sisbr.cca.cadastro.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe TabelaIRRFVO.
 */
public class TabelaIRRFVO extends BancoobVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8715765491183181971L;
	
	private Integer anoBase;
	private BigDecimal percAliquota;
	private BigDecimal valorBaseInicial;
	private BigDecimal valorBaseFinal;
	private BigDecimal valorDeducao;
	private Boolean selecionado;
	
	/**
	 * @return the percAliquota
	 */
	public BigDecimal getPercAliquota() {
		return percAliquota;
	}
	/**
	 * @param percAliquota the percAliquota to set
	 */
	public void setPercAliquota(BigDecimal percAliquota) {
		this.percAliquota = percAliquota;
	}
	/**
	 * @return the valorBaseInicial
	 */
	public BigDecimal getValorBaseInicial() {
		return valorBaseInicial;
	}
	/**
	 * @param valorBaseInicial the valorBaseInicial to set
	 */
	public void setValorBaseInicial(BigDecimal valorBaseInicial) {
		this.valorBaseInicial = valorBaseInicial;
	}
	/**
	 * @return the valorBaseFinal
	 */
	public BigDecimal getValorBaseFinal() {
		return valorBaseFinal;
	}
	/**
	 * @param valorBaseFinal the valorBaseFinal to set
	 */
	public void setValorBaseFinal(BigDecimal valorBaseFinal) {
		this.valorBaseFinal = valorBaseFinal;
	}
	/**
	 * @return the valorDeducao
	 */
	public BigDecimal getValorDeducao() {
		return valorDeducao;
	}
	/**
	 * @param valorDeducao the valorDeducao to set
	 */
	public void setValorDeducao(BigDecimal valorDeducao) {
		this.valorDeducao = valorDeducao;
	}
	/**
	 * @return the anoBase
	 */
	public Integer getAnoBase() {
		return anoBase;
	}
	/**
	 * @param anoBase the anoBase to set
	 */
	public void setAnoBase(Integer anoBase) {
		this.anoBase = anoBase;
	}
	/**
	 * @return the selecionado
	 */
	public Boolean getSelecionado() {
		return selecionado;
	}
	/**
	 * @param selecionado the selecionado to set
	 */
	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}
}