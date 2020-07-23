package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * DTO de saida com informacoes padroes (comprovante)
 * @author Nairon.Silva
 */
public class SaidaInformacoesGeraisDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;

	@AtributoRetorno(posicao=1)
	private Integer indice;
	
	@AtributoRetorno(posicao=2)
	private String numCooperativa;
	
	@AtributoRetorno(posicao=3)
	private String nomeCooperativa;
	
	@AtributoRetorno(posicao=4)
	private String numContaCorrente;
	
	@AtributoRetorno(posicao=5)
	private String nomePessoa;

	public String getNumCooperativa() {
		return numCooperativa;
	}

	public void setNumCooperativa(String numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	public String getNomeCooperativa() {
		return nomeCooperativa;
	}

	public void setNomeCooperativa(String nomeCooperativa) {
		this.nomeCooperativa = nomeCooperativa;
	}

	public String getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(String numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	
}
