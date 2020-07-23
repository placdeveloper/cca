package br.com.sicoob.cca.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * InformacaoProfissionalDTO
 * @author Nairon.Silva
 */
public class InformacaoProfissionalDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer idInformacaoProfissional;
	private String numMatricula;

	public Integer getIdInformacaoProfissional() {
		return idInformacaoProfissional;
	}

	public void setIdInformacaoProfissional(Integer idInformacaoProfissional) {
		this.idInformacaoProfissional = idInformacaoProfissional;
	}
	
	public String getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(String numMatricula) {
		this.numMatricula = numMatricula;
	}

}
