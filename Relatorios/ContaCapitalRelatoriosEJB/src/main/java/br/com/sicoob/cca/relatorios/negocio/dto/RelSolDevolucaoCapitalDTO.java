/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO RelSolDevolucaoCapitalDTO
 */
public class RelSolDevolucaoCapitalDTO extends BancoobDto {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 677350905413298926L;
	private Integer idContaCapital;
	private Integer idPessoa;
	private Integer idInstituicao;
	
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}
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