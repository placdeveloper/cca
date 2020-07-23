/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe TrabalhaLegadoDTO.
 */
public class TrabalhaLegadoDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 7648638964575470148L;
	
	/** O atributo uIDTrabalha. */
	private String uIDTrabalha;
	
	/** O atributo descMatriculaFunc. */
	private String descMatriculaFunc;
	
	/** O atributo descEmpresaTrabalha. */
	private String descEmpresaTrabalha;
	
	/** O atributo descOcupacaoProfissional. */
	private String descOcupacaoProfissional;
	
	/** O atributo dataAdmissao. */
	private DateTimeDB dataAdmissao;
	
	/** O atributo numPessoaFisica. */
	private Integer numPessoaFisica;
	
	/** O atributo numPessoaJuridica. */
	private Integer numPessoaJuridica;
	
	/**
	 * Recupera o valor de UIDTrabalha.
	 *
	 * @return o valor de UIDTrabalha
	 */
	public String getUIDTrabalha() {
		return uIDTrabalha;
	}
	
	/**
	 * Define o valor de UIDTrabalha.
	 *
	 * @param uIDTrabalha o novo valor de UIDTrabalha
	 */
	public void setUIDTrabalha(String uIDTrabalha) {
		this.uIDTrabalha = uIDTrabalha;
	}
	
	/**
	 * Recupera o valor de descMatriculaFunc.
	 *
	 * @return o valor de descMatriculaFunc
	 */
	public String getDescMatriculaFunc() {
		return descMatriculaFunc;
	}
	
	/**
	 * Define o valor de descMatriculaFunc.
	 *
	 * @param descMatriculaFunc o novo valor de descMatriculaFunc
	 */
	public void setDescMatriculaFunc(String descMatriculaFunc) {
		this.descMatriculaFunc = descMatriculaFunc;
	}
	
	/**
	 * Recupera o valor de descEmpresaTrabalha.
	 *
	 * @return o valor de descEmpresaTrabalha
	 */
	public String getDescEmpresaTrabalha() {
		return descEmpresaTrabalha;
	}
	
	/**
	 * Define o valor de descEmpresaTrabalha.
	 *
	 * @param descEmpresaTrabalha o novo valor de descEmpresaTrabalha
	 */
	public void setDescEmpresaTrabalha(String descEmpresaTrabalha) {
		this.descEmpresaTrabalha = descEmpresaTrabalha;
	}
	
	/**
	 * Recupera o valor de descOcupacaoProfissional.
	 *
	 * @return o valor de descOcupacaoProfissional
	 */
	public String getDescOcupacaoProfissional() {
		return descOcupacaoProfissional;
	}
	
	/**
	 * Define o valor de descOcupacaoProfissional.
	 *
	 * @param descOcupacaoProfissional o novo valor de descOcupacaoProfissional
	 */
	public void setDescOcupacaoProfissional(String descOcupacaoProfissional) {
		this.descOcupacaoProfissional = descOcupacaoProfissional;
	}
	
	/**
	 * Recupera o valor de dataAdmissao.
	 *
	 * @return o valor de dataAdmissao
	 */
	public DateTimeDB getDataAdmissao() {
		return dataAdmissao;
	}
	
	/**
	 * Define o valor de dataAdmissao.
	 *
	 * @param dataAdmissao o novo valor de dataAdmissao
	 */
	public void setDataAdmissao(DateTimeDB dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	/**
	 * Recupera o valor de numPessoaFisica.
	 *
	 * @return o valor de numPessoaFisica
	 */
	public Integer getNumPessoaFisica() {
		return numPessoaFisica;
	}
	
	/**
	 * Define o valor de numPessoaFisica.
	 *
	 * @param numPessoaFisica o novo valor de numPessoaFisica
	 */
	public void setNumPessoaFisica(Integer numPessoaFisica) {
		this.numPessoaFisica = numPessoaFisica;
	}
	
	/**
	 * Recupera o valor de numPessoaJuridica.
	 *
	 * @return o valor de numPessoaJuridica
	 */
	public Integer getNumPessoaJuridica() {
		return numPessoaJuridica;
	}
	
	/**
	 * Define o valor de numPessoaJuridica.
	 *
	 * @param numPessoaJuridica o novo valor de numPessoaJuridica
	 */
	public void setNumPessoaJuridica(Integer numPessoaJuridica) {
		this.numPessoaJuridica = numPessoaJuridica;
	}
	
	/**
	 * Recupera o valor de serialversionuid.
	 *
	 * @return o valor de serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}