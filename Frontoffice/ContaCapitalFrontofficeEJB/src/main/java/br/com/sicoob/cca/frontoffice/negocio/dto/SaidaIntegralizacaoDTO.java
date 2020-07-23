package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * DTO utilizado na saída do serviço de integralização.
 * @author Nairon.Silva
 */
public class SaidaIntegralizacaoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	@AtributoRetorno(posicao=1)
	private String dataTransacao;
	
	@AtributoRetorno(posicao=2)
	private String valorLancamento;	
	
	@AtributoRetorno(posicao=3)
	private String qtdMeses;
	
	@AtributoRetorno(posicao=4)
	private String diaDebito;
	
	@AtributoRetorno(posicao=5)
	private String tipoAgendamento;
	
	@AtributoRetorno(posicao=6)
	private String numCooperativa;
	
	@AtributoRetorno(posicao=7)
	private String nomeCooperativa;
	
	@AtributoRetorno(posicao=8)
	private String numContaCorrente;
	
	@AtributoRetorno(posicao=9)
	private String nomePessoa;

	/**
	 * @return the dataTransacao
	 */
	public String getDataTransacao() {
		return dataTransacao;
	}

	/**
	 * @param dataTransacao the dataTransacao to set
	 */
	public void setDataTransacao(String dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	/**
	 * @return the valorLancamento
	 */
	public String getValorLancamento() {
		return valorLancamento;
	}

	/**
	 * @param valorLancamento the valorLancamento to set
	 */
	public void setValorLancamento(String valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	/**
	 * @return the qtdMeses
	 */
	public String getQtdMeses() {
		return qtdMeses;
	}

	/**
	 * @param qtdMeses the qtdMeses to set
	 */
	public void setQtdMeses(String qtdMeses) {
		this.qtdMeses = qtdMeses;
	}

	/**
	 * @return the diaDebito
	 */
	public String getDiaDebito() {
		return diaDebito;
	}

	/**
	 * @param diaDebito the diaDebito to set
	 */
	public void setDiaDebito(String diaDebito) {
		this.diaDebito = diaDebito;
	}

	/**
	 * @return the tipoAgendamento
	 */
	public String getTipoAgendamento() {
		return tipoAgendamento;
	}

	/**
	 * @param tipoAgendamento the tipoAgendamento to set
	 */
	public void setTipoAgendamento(String tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}

	public String getNumCooperativa() {
		return numCooperativa;
	}

	public void setNumCooperativa(String numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	public String getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(String numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
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
	
}
