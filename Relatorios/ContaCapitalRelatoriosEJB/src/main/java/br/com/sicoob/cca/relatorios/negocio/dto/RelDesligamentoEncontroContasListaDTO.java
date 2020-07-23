package br.com.sicoob.cca.relatorios.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * RelDesligamentoEncontroContasListaDTO
 */
public class RelDesligamentoEncontroContasListaDTO extends BancoobDto {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idInstituicao;
	private Integer numContaCapitalInicial;
	private Integer numContaCapitalFinal;
	
	public Integer getNumContaCapitalInicial() {
		return numContaCapitalInicial;
	}
	public void setNumContaCapitalInicial(Integer numContaCapitalInicial) {
		this.numContaCapitalInicial = numContaCapitalInicial;
	}
	public Integer getNumContaCapitalFinal() {
		return numContaCapitalFinal;
	}
	public void setNumContaCapitalFinal(Integer numContaCapitalFinal) {
		this.numContaCapitalFinal = numContaCapitalFinal;
	}
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
		
}
