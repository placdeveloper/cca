package br.com.sicoob.cca.replicacao.negocio.batimento;

import java.util.List;

/**
 * Representa o agrupamentos dos diversos batimentos
 * @author Nairon.Silva
 */
public class RelBatimentoCooperativa {

	private Integer cooperativa;
	private List<RegistroBatimentoContaCapital> batimentosContaCapital;
	private List<RegistroBatimentoLancamento> batimentosLancamento;
	private List<RegistroBatimentoParcelamento> batimentosParcelamento;
	
	public List<RegistroBatimentoContaCapital> getBatimentosContaCapital() {
		return batimentosContaCapital;
	}
	public void setBatimentosContaCapital(
			List<RegistroBatimentoContaCapital> batimentosContaCapital) {
		this.batimentosContaCapital = batimentosContaCapital;
	}
	public List<RegistroBatimentoLancamento> getBatimentosLancamento() {
		return batimentosLancamento;
	}
	public void setBatimentosLancamento(
			List<RegistroBatimentoLancamento> batimentosLancamento) {
		this.batimentosLancamento = batimentosLancamento;
	}
	public List<RegistroBatimentoParcelamento> getBatimentosParcelamento() {
		return batimentosParcelamento;
	}
	public void setBatimentosParcelamento(
			List<RegistroBatimentoParcelamento> batimentosParcelamento) {
		this.batimentosParcelamento = batimentosParcelamento;
	}
	/**
	 * @return the cooperativa
	 */
	public Integer getCooperativa() {
		return cooperativa;
	}
	/**
	 * @param cooperativa the cooperativa to set
	 */
	public void setCooperativa(Integer cooperativa) {
		this.cooperativa = cooperativa;
	}
	
}
