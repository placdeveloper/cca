package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * DTO para saida de conteudo unico
 * @author Nairon.Silva
 */
public class SaidaConteudoUnicoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor padrao
	 */
	public SaidaConteudoUnicoDTO() {
		
	}
	
	/**
	 * Recebe o indice e conteudo
	 * @param indice
	 * @param conteudo
	 */
	public SaidaConteudoUnicoDTO(Integer indice, String conteudo) {
		this.indice = indice;
		this.conteudo = conteudo;
	}
	
	@AtributoRetorno(posicao=1)
	private Integer indice;
	
	@AtributoRetorno(posicao=2)
	private String conteudo;

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	
}
