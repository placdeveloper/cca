/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * Monitor por cooperativa
 * @author Nairon.Silva
 */
public class MonitorCooperativaReplicacaoCapitalLegadoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer numCooperativa;
	private Integer qtdAguardandoCCA;
	private Integer qtdInvalidoCCA;
	private Integer qtdReplicadoCCA;
	private Integer qtdErroCCA;
	private Integer qtdAguardandoParc;
	private Integer qtdInvalidoParc;
	private Integer qtdReplicadoParc;
	private Integer qtdErroParc;
	private Integer qtdAguardandoLanc;
	private Integer qtdInvalidoLanc;
	private Integer qtdReplicadoLanc;
	private Integer qtdErroLanc;
	
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	public Integer getQtdAguardandoCCA() {
		return qtdAguardandoCCA;
	}
	public void setQtdAguardandoCCA(Integer qtdAguardandoCCA) {
		this.qtdAguardandoCCA = qtdAguardandoCCA;
	}
	public Integer getQtdInvalidoCCA() {
		return qtdInvalidoCCA;
	}
	public void setQtdInvalidoCCA(Integer qtdInvalidoCCA) {
		this.qtdInvalidoCCA = qtdInvalidoCCA;
	}
	public Integer getQtdReplicadoCCA() {
		return qtdReplicadoCCA;
	}
	public void setQtdReplicadoCCA(Integer qtdReplicadoCCA) {
		this.qtdReplicadoCCA = qtdReplicadoCCA;
	}
	public Integer getQtdErroCCA() {
		return qtdErroCCA;
	}
	public void setQtdErroCCA(Integer qtdErroCCA) {
		this.qtdErroCCA = qtdErroCCA;
	}
	public Integer getQtdAguardandoParc() {
		return qtdAguardandoParc;
	}
	public void setQtdAguardandoParc(Integer qtdAguardandoParc) {
		this.qtdAguardandoParc = qtdAguardandoParc;
	}
	public Integer getQtdInvalidoParc() {
		return qtdInvalidoParc;
	}
	public void setQtdInvalidoParc(Integer qtdInvalidoParc) {
		this.qtdInvalidoParc = qtdInvalidoParc;
	}
	public Integer getQtdReplicadoParc() {
		return qtdReplicadoParc;
	}
	public void setQtdReplicadoParc(Integer qtdReplicadoParc) {
		this.qtdReplicadoParc = qtdReplicadoParc;
	}
	public Integer getQtdErroParc() {
		return qtdErroParc;
	}
	public void setQtdErroParc(Integer qtdErroParc) {
		this.qtdErroParc = qtdErroParc;
	}
	public Integer getQtdAguardandoLanc() {
		return qtdAguardandoLanc;
	}
	public void setQtdAguardandoLanc(Integer qtdAguardandoLanc) {
		this.qtdAguardandoLanc = qtdAguardandoLanc;
	}
	public Integer getQtdInvalidoLanc() {
		return qtdInvalidoLanc;
	}
	public void setQtdInvalidoLanc(Integer qtdInvalidoLanc) {
		this.qtdInvalidoLanc = qtdInvalidoLanc;
	}
	public Integer getQtdReplicadoLanc() {
		return qtdReplicadoLanc;
	}
	public void setQtdReplicadoLanc(Integer qtdReplicadoLanc) {
		this.qtdReplicadoLanc = qtdReplicadoLanc;
	}
	public Integer getQtdErroLanc() {
		return qtdErroLanc;
	}
	public void setQtdErroLanc(Integer qtdErroLanc) {
		this.qtdErroLanc = qtdErroLanc;
	}
	
}