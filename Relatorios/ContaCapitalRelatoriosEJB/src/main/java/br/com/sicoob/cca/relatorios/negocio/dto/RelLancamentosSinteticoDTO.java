package br.com.sicoob.cca.relatorios.negocio.dto;

public class RelLancamentosSinteticoDTO extends RelLancamentosDTO {

	private static final long serialVersionUID = -9017329173079325873L;

	private String descricaoHistorico;
	private Integer quantidadeLancamentos;

	public String getDescricaoHistorico() {
		return descricaoHistorico;
	}

	public void setDescricaoHistorico(String descricaoHistorico) {
		this.descricaoHistorico = descricaoHistorico;
	}

	public Integer getQuantidadeLancamentos() {
		return quantidadeLancamentos;
	}

	public void setQuantidadeLancamentos(Integer quantidadeLancamentos) {
		this.quantidadeLancamentos = quantidadeLancamentos;
	}

}
