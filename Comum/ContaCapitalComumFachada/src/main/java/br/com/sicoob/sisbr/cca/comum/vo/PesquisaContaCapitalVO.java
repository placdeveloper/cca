/*
 * 
 */
package br.com.sicoob.sisbr.cca.comum.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * @author Marco.Nascimento
 */
public class PesquisaContaCapitalVO extends BancoobVo {

	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo idPessoaLegado. */
	private Integer idPessoaLegado;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo nome. */
	private String nome;
	
	/** O atributo idSituacaoContaCapital. */
	private Integer idSituacaoContaCapital;
	
	/** O atributo idSituacaoCadastro. */
	private Integer idSituacaoCadastro;
	
	/** O atributo descSituacaoContaCapital. */
	private String descSituacaoContaCapital;
	
	/** O atributo idContaCapital. */
	private Integer idContaCapital;
	
	/** O atributo codTipoPessoa. */
	private Integer codTipoPessoa;
	
	/**
	 * @return the numContaCapital
	 */
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	/**
	 * @param numContaCapital the numContaCapital to set
	 */
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	/**
	 * @return the idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}
	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	/**
	 * @return the idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}
	/**
	 * @param idPessoaLegado the idPessoaLegado to set
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}
	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the idSituacaoContaCapital
	 */
	public Integer getIdSituacaoContaCapital() {
		return idSituacaoContaCapital;
	}
	/**
	 * @param idSituacaoContaCapital the idSituacaoContaCapital to set
	 */
	public void setIdSituacaoContaCapital(Integer idSituacaoContaCapital) {
		this.idSituacaoContaCapital = idSituacaoContaCapital;
	}
	/**
	 * @return the descSituacaoContaCapital
	 */
	public String getDescSituacaoContaCapital() {
		return descSituacaoContaCapital;
	}
	/**
	 * @param descSituacaoContaCapital the descSituacaoContaCapital to set
	 */
	public void setDescSituacaoContaCapital(String descSituacaoContaCapital) {
		this.descSituacaoContaCapital = descSituacaoContaCapital;
	}
	/**
	 * @return the idSituacaoCadastro
	 */
	public Integer getIdSituacaoCadastro() {
		return idSituacaoCadastro;
	}
	/**
	 * @param idSituacaoCadastro the idSituacaoCadastro to set
	 */
	public void setIdSituacaoCadastro(Integer idSituacaoCadastro) {
		this.idSituacaoCadastro = idSituacaoCadastro;
	}
	
	/**
	 * Recupera o valor de idContaCapital.
	 *
	 * @return o valor de idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	
	/**
	 * Define o valor de idContaCapital.
	 *
	 * @param idContaCapital o novo valor de idContaCapital
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}
	/**
	 * @return the codTipoPessoa
	 */
	public Integer getCodTipoPessoa() {
		return codTipoPessoa;
	}
	/**
	 * @param codTipoPessoa the codTipoPessoa to set
	 */
	public void setCodTipoPessoa(Integer codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}
}