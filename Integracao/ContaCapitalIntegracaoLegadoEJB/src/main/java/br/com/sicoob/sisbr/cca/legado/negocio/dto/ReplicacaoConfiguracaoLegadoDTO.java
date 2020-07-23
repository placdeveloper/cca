/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO de configuracao da replicacao.
 * @author Nairon.Silva
 */
public class ReplicacaoConfiguracaoLegadoDTO extends BancoobDto {

	private static final long serialVersionUID = -8564329995482522440L;
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
