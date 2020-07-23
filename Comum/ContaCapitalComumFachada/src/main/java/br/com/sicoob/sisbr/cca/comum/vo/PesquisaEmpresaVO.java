/*
 * 
 */
package br.com.sicoob.sisbr.cca.comum.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * @author Marco.Nascimento
 */
public class PesquisaEmpresaVO extends BancoobVo {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 6332415147412747747L;
	
	private Integer numPessoaJuridica;
	private String descNomePessoa;
	private Integer diaFolha;
	private Integer qtdeDiasGeraInf;
	private String numCGC_CPF;

	/**
	 * @return the numPessoaJuridica
	 */
	public Integer getNumPessoaJuridica() {
		return numPessoaJuridica;
	}
	/**
	 * @param numPessoaJuridica the numPessoaJuridica to set
	 */
	public void setNumPessoaJuridica(Integer numPessoaJuridica) {
		this.numPessoaJuridica = numPessoaJuridica;
	}
	/**
	 * @return the descNomePessoa
	 */
	public String getDescNomePessoa() {
		return descNomePessoa;
	}
	/**
	 * @param descNomePessoa the descNomePessoa to set
	 */
	public void setDescNomePessoa(String descNomePessoa) {
		this.descNomePessoa = descNomePessoa;
	}
	/**
	 * @return the diaFolha
	 */
	public Integer getDiaFolha() {
		return diaFolha;
	}
	/**
	 * @param diaFolha the diaFolha to set
	 */
	public void setDiaFolha(Integer diaFolha) {
		this.diaFolha = diaFolha;
	}
	/**
	 * @return the qtdeDiasGeraInf
	 */
	public Integer getQtdeDiasGeraInf() {
		return qtdeDiasGeraInf;
	}
	/**
	 * @param qtdeDiasGeraInf the qtdeDiasGeraInf to set
	 */
	public void setQtdeDiasGeraInf(Integer qtdeDiasGeraInf) {
		this.qtdeDiasGeraInf = qtdeDiasGeraInf;
	}
	/**
	 * @return the numCGC_CPF
	 */
	public String getNumCGC_CPF() {
		return numCGC_CPF;
	}
	/**
	 * @param numCGC_CPF the numCGC_CPF to set
	 */
	public void setNumCGC_CPF(String numCGC_CPF) {
		this.numCGC_CPF = numCGC_CPF;
	}
}