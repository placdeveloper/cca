/*
 * 
 */
package br.com.sicoob.sisbr.cca.replicacao.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * VO ConfiguracaoReplicacaoVO
 */
public class ConfiguracaoReplicacaoVO extends BancoobVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2704494504519459711L;
	private Integer idConfiguracaoReplicacaoCCA;
	private String nomeConfiguracaoReplicacao;
	private String descConfiguracaoReplicacao;
	
	public Integer getIdConfiguracaoReplicacaoCCA() {
		return idConfiguracaoReplicacaoCCA;
	}
	public void setIdConfiguracaoReplicacaoCCA(Integer idConfiguracaoReplicacaoCCA) {
		this.idConfiguracaoReplicacaoCCA = idConfiguracaoReplicacaoCCA;
	}
	public String getNomeConfiguracaoReplicacao() {
		return nomeConfiguracaoReplicacao;
	}
	public void setNomeConfiguracaoReplicacao(String nomeConfiguracaoReplicacao) {
		this.nomeConfiguracaoReplicacao = nomeConfiguracaoReplicacao;
	}
	public String getDescConfiguracaoReplicacao() {
		return descConfiguracaoReplicacao;
	}
	public void setDescConfiguracaoReplicacao(String descConfiguracaoReplicacao) {
		this.descConfiguracaoReplicacao = descConfiguracaoReplicacao;
	}
	
}
