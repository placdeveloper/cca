package br.com.sicoob.sisbr.cca.replicacao.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * VO de expurgo da replicacao.
 */
public class ExpurgoReplicacaoVO extends BancoobVo {

	private static final long serialVersionUID = 1L;
	
	private Integer numCooperativa;
	private Integer idInstituicao;
	private String nomeCooperativa;
	private Long quantidade;
	
	private Boolean selecionado;
	
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
