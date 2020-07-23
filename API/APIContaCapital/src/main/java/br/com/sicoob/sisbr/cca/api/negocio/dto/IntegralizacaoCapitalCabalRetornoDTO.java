/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * DTO IntegralizacaoCapitalCabalRetornoDTO
 */
public class IntegralizacaoCapitalCabalRetornoDTO extends BancoobDto {

	private static final long serialVersionUID = 4660663319468159815L;
	
	private Boolean statusRetornoCapital;
	private String descRetornoCapital;
	private String idOperacaoCabal;
	private String idOperacaoContaCapital;
	private DateTimeDB dataHoraOperacaoContaCapital;
	private String numCpfCnpj;		
	private Integer numCooperativa;	
	
	public Boolean getStatusRetornoCapital() {
		return statusRetornoCapital;
	}
	public void setStatusRetornoCapital(Boolean statusRetornoCapital) {
		this.statusRetornoCapital = statusRetornoCapital;
	}
	public String getDescRetornoCapital() {
		return descRetornoCapital;
	}
	public void setDescRetornoCapital(String descRetornoCapital) {
		this.descRetornoCapital = descRetornoCapital;
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
	public DateTimeDB getDataHoraOperacaoContaCapital() {
		return dataHoraOperacaoContaCapital;
	}
	public void setDataHoraOperacaoContaCapital(DateTimeDB dataHoraOperacaoContaCapital) {
		this.dataHoraOperacaoContaCapital = dataHoraOperacaoContaCapital;
	}
	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}
	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	
	@Override
	public String toString() {
		return "IntegralizacaoCapitalCabalRetornoDTO [statusRetornoCapital=" + statusRetornoCapital
				+ ", descRetornoCapital=" + descRetornoCapital + ", idOperacaoCabal=" + idOperacaoCabal
				+ ", idOperacaoContaCapital=" + idOperacaoContaCapital + ", dataHoraOperacaoContaCapital="
				+ dataHoraOperacaoContaCapital + ", numCpfCnpj=" + numCpfCnpj + ", numCooperativa=" + numCooperativa
				+ "]";
	}
	
}
