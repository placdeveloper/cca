package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO de expurgo da configuracao de replicacao.
 * @author Nairon.Silva
 */
public class ExpurgoReplicacaoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer numCooperativa;
	private Integer idInstituicao;
	private String nomeCooperativa;
	private Long quantidade;
	
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public String getNomeCooperativa() {
		return nomeCooperativa;
	}
	public void setNomeCooperativa(String nomeCooperativa) {
		this.nomeCooperativa = nomeCooperativa;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {
		return "ExpurgoReplicacaoDTO [numCooperativa=" + numCooperativa
				+ ", idInstituicao=" + idInstituicao + ", nomeCooperativa="
				+ nomeCooperativa + ", quantidade=" + quantidade + "]";
	}

}
