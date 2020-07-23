package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO de entrada para integralizacao e devolucao de capital via caixa.
 * @author Nairon.Silva
 */
public class EntradaIntegDevolCapitalViaCaixaDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer idInstituicao;
	private String identificador;
	private String usuario;
	
	// construidos
	private Integer numContaCapital;
	private Integer tipoParcelamento;
	private Integer idSituacaoContaCapital;
	private Integer numCooperativa;
	private String nomeCooperativa;
	private String nomePessoa;
	private String cpfCnpj;
	
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Integer getTipoParcelamento() {
		return tipoParcelamento;
	}
	public void setTipoParcelamento(Integer tipoParcelamento) {
		this.tipoParcelamento = tipoParcelamento;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Verifica se o tipo de parcelamento eh integralizacao.
	 * @return
	 */
	public boolean isTipoParcelamentoIntegralizacao() {
		return EntradaConsultaParcelasViaCaixaDTO.TIPO_PARCELAMENTO_INTEGRALIZACAO.equals(tipoParcelamento);
	}
	
	/**
	 * Verifica se o tipo de parcelamento eh devolucao.
	 * @return
	 */
	public boolean isTipoParcelamentoDevolucao() {
		return EntradaConsultaParcelasViaCaixaDTO.TIPO_PARCELAMENTO_DEVOLUCAO.equals(tipoParcelamento);
	}
	
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	public String getNomeCooperativa() {
		return nomeCooperativa;
	}
	public void setNomeCooperativa(String nomeCooperativa) {
		this.nomeCooperativa = nomeCooperativa;
	}
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public Integer getIdSituacaoContaCapital() {
		return idSituacaoContaCapital;
	}
	public void setIdSituacaoContaCapital(Integer idSituacaoContaCapital) {
		this.idSituacaoContaCapital = idSituacaoContaCapital;
	}

}
