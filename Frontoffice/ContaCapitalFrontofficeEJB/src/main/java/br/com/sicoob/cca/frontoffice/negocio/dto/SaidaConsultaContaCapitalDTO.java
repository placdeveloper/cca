package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * DTO de saida da consulta de conta capital.
 * @author Nairon.Silva
 */
public class SaidaConsultaContaCapitalDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	@AtributoRetorno(posicao=1)
	private String cpfCnpj;
	
	@AtributoRetorno(posicao=2)
	private String nome;
	
	@AtributoRetorno(posicao=3)
	private Integer numContaCapital;
	
	@AtributoRetorno(posicao=4)
	private String descSituacaoContaCapital;

	public Integer getNumContaCapital() {
		return numContaCapital;
	}

	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescSituacaoContaCapital() {
		return descSituacaoContaCapital;
	}

	public void setDescSituacaoContaCapital(String descSituacaoContaCapital) {
		this.descSituacaoContaCapital = descSituacaoContaCapital;
	}
	
}
