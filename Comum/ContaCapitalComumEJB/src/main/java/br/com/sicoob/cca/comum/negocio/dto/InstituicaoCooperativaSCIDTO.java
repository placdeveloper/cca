package br.com.sicoob.cca.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * InstituicaoCooperativaSCIDTO
 * @author Nairon.Silva
 */
public class InstituicaoCooperativaSCIDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;

	private Integer idInstituicao;
	private Integer numCooperativa;
	private String nome;
	private Integer numPAC;
	
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the numPAC
	 */
	public Integer getNumPAC() {
		return numPAC;
	}
	/**
	 * @param numPAC the numPAC to set
	 */
	public void setNumPAC(Integer numPAC) {
		this.numPAC = numPAC;
	}
}