/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe CadastroContaCapitalDTO.
 */
public class CadastroContaCapitalDTO extends BancoobDto{


	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -4158567792088570853L;
	
	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

}
