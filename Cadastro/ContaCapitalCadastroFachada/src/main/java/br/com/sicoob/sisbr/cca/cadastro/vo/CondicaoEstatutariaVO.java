/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe CondicaoEstatutariaVO.
 */
public class CondicaoEstatutariaVO extends BancoobVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5450656031824760673L;
	
	private Integer idConfiguracaoCapital;
	private String nomeAgrupadorConfiguracaoCapital;
	private String nomeConfiguracaoCapital;
	private String valorConfiguracao;
	
	public Integer getIdConfiguracaoCapital() {
		return idConfiguracaoCapital;
	}
	
	public void setIdConfiguracaoCapital(Integer idConfiguracaoCapital) {
		this.idConfiguracaoCapital = idConfiguracaoCapital;
	}
	
	public String getNomeAgrupadorConfiguracaoCapital() {
		return nomeAgrupadorConfiguracaoCapital;
	}
	
	public void setNomeAgrupadorConfiguracaoCapital(String nomeAgrupadorConfiguracaoCapital) {
		this.nomeAgrupadorConfiguracaoCapital = nomeAgrupadorConfiguracaoCapital;
	}
	
	public String getNomeConfiguracaoCapital() {
		return nomeConfiguracaoCapital;
	}
	
	public void setNomeConfiguracaoCapital(String nomeConfiguracaoCapital) {
		this.nomeConfiguracaoCapital = nomeConfiguracaoCapital;
	}
	
	public String getValorConfiguracao() {
		return valorConfiguracao;
	}
	
	public void setValorConfiguracao(String valorConfiguracao) {
		this.valorConfiguracao = valorConfiguracao;
	}
}