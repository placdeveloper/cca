package br.com.sicoob.cca.replicacao.negocio.batimento;

/**
 * Representa o resumo das divergencias de batimentos
 * @author Nairon.Silva
 */
public class RelBatimentoResumo {

	private Integer cooperativa;
	private Integer idInstituicao;
	private String nomeCooperativa;
	
	public Integer getCooperativa() {
		return cooperativa;
	}
	public void setCooperativa(Integer cooperativa) {
		this.cooperativa = cooperativa;
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
	
	@Override
	public String toString() {
		return "RelBatimentoResumo [cooperativa=" + cooperativa
				+ ", idInstituicao=" + idInstituicao + ", nomeCooperativa="
				+ nomeCooperativa + "]";
	}
	
}
