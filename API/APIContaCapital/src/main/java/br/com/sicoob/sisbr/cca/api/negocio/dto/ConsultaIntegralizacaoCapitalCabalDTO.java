/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * DTO ConsultaIntegralizacaoCapitalCabalDTO
 */
public class ConsultaIntegralizacaoCapitalCabalDTO extends BancoobDto {
	
	private static final long serialVersionUID = 5058540009742338925L;
	private DateTimeDB dataIntegralizacao;
	private Integer numCooperativa;
	private String idOperacaoCabal;
	
	public DateTimeDB getDataIntegralizacao() {
		return dataIntegralizacao;
	}
	public void setDataIntegralizacao(DateTimeDB dataIntegralizacao) {
		this.dataIntegralizacao = dataIntegralizacao;
	}
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	public String getIdOperacaoCabal() {
		return idOperacaoCabal;
	}
	public void setIdOperacaoCabal(String idOperacaoCabal) {
		this.idOperacaoCabal = idOperacaoCabal;
	}

	
	
}
