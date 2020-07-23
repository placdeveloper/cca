/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe CondicaoEstatutariaDTO.
 */
public class CondicaoEstatutariaDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8091917893795806603L;
	
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