package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO de entrada para consulta de parcelas via caixa.
 * @author Nairon.Silva
 */
public class EntradaConsultaParcelasViaCaixaDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	protected static final Integer TIPO_PARCELAMENTO_INTEGRALIZACAO = 1;
	protected static final Integer TIPO_PARCELAMENTO_DEVOLUCAO = 2;
	
	private Integer numContaCapital;
	private Integer idInstituicao;
	private Integer tipoParcelamento;
	
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Integer getTipoParcelamento() {
		return tipoParcelamento;
	}
	public void setTipoParcelamento(Integer tipoParcelamento) {
		this.tipoParcelamento = tipoParcelamento;
	}
	
	/**
	 * Verifica se o tipo de parcelamento eh integralizacao.
	 * @return
	 */
	public boolean isTipoParcelamentoIntegralizacao() {
		return TIPO_PARCELAMENTO_INTEGRALIZACAO.equals(tipoParcelamento);
	}
	
	/**
	 * Verifica se o tipo de parcelamento eh devolucao.
	 * @return
	 */
	public boolean isTipoParcelamentoDevolucao() {
		return TIPO_PARCELAMENTO_DEVOLUCAO.equals(tipoParcelamento);
	}

}
