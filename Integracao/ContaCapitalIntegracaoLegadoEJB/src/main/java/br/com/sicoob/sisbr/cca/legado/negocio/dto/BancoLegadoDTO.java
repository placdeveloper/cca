package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

public class BancoLegadoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer numBanco;
	private String descBanco;
	
	public Integer getNumBanco() {
		return numBanco;
	}
	public void setNumBanco(Integer numBanco) {
		this.numBanco = numBanco;
	}
	public String getDescBanco() {
		return descBanco;
	}
	public void setDescBanco(String descBanco) {
		this.descBanco = descBanco;
	}

}
