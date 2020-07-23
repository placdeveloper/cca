/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * DTO ConsultaIntegralizacaoCapitalCabalRetornoDTO
 */
public class ConsultaIntegralizacaoCapitalCabalRetornoDTO extends BancoobDto {

	private static final long serialVersionUID = -4434005381893088336L;
	
	private DateTimeDB dataHoraOperacaoContaCapital;
	private Integer numCooperativa;
	private String numCpfCnpj;	
	private String idOperacaoCabal;
	private String idOperacaoContaCapital;
	
	public DateTimeDB getDataHoraOperacaoContaCapital() {
		return dataHoraOperacaoContaCapital;
	}
	public void setDataHoraOperacaoContaCapital(
			DateTimeDB dataHoraOperacaoContaCapital) {
		this.dataHoraOperacaoContaCapital = dataHoraOperacaoContaCapital;
	}
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}
	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}
	public String getIdOperacaoCabal() {
		return idOperacaoCabal;
	}
	public void setIdOperacaoCabal(String idOperacaoCabal) {
		this.idOperacaoCabal = idOperacaoCabal;
	}
	public String getIdOperacaoContaCapital() {
		return idOperacaoContaCapital;
	}
	public void setIdOperacaoContaCapital(String idOperacaoContaCapital) {
		this.idOperacaoContaCapital = idOperacaoContaCapital;
	}

	
	
}
