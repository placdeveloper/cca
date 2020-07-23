package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * DTO para saida da consulta de parcelas via caixa.
 * @author Nairon.Silva
 */
public class SaidaIntegDevolCapitalViaCaixaCabecalhoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	@AtributoRetorno(posicao=1)
	private Integer indice;
	
	@AtributoRetorno(posicao=2)
	private Integer numCooperativa;
	
	@AtributoRetorno(posicao=3)
	private String nomeCooperativa;
	
	@AtributoRetorno(posicao=4)
	private String cpfCnpj;
	
	@AtributoRetorno(posicao=5)
	private String nomePessoa;
	
	@AtributoRetorno(posicao=6)
	private Integer numContaCapital;
	
	@AtributoRetorno(posicao=7)
	private String operacao;

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Integer getNumCooperativa() {
		return numCooperativa;
	}

	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	public String getNomeCooperativa() {
		return nomeCooperativa;
	}

	public void setNomeCooperativa(String nomeCooperativa) {
		this.nomeCooperativa = nomeCooperativa;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public Integer getNumContaCapital() {
		return numContaCapital;
	}

	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

}
