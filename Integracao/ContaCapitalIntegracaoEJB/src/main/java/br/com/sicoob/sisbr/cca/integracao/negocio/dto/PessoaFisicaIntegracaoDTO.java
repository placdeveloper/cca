/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe PessoaFisicaIntegracaoDTO.
 */
public class PessoaFisicaIntegracaoDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7305044407611610045L;

	/** O atributo nascimento. */
	private Date nascimento;
	
	/** O atributo nomePai. */
	private String nomePai;
	
	/** O atributo nomeMae. */
	private String nomeMae;
	
	/** O atributo nacionalidade. */
	private String nacionalidade;
	
	/** O atributo naturalidade. */
	private String naturalidade;
	
	/** O atributo descSexo. */
	private Character descSexo;
	
	/** O atributo descProfissao. */
	private String descProfissao;
	
	/** O atributo estadoCivil. */
	private String estadoCivil;
	
	/** O atributo descDocumento. */
	private String descDocumento;
	
	/** O atributo numDocumento. */
	private String numDocumento;
	
	/** O atributo emissaoDocumento. */
	private Date emissaoDocumento;
	
	/** O atributo orgaoDocumento. */
	private String orgaoDocumento;
	
	/** O atributo ufDocumento. */
	private String ufDocumento;
	
	/** O atributo filiacao. */
	private String filiacao;
	
	/** O atributo idNaturalidade. */
	private Integer idNaturalidade;
	/**
	 * @return the nascimento
	 */
	public Date getNascimento() {
		if(nascimento != null){
			return new Date(nascimento.getTime());
		}
		return null;
	}
	/**
	 * @param nascimento the nascimento to set
	 */
	public void setNascimento(Date nascimento) {
		if(nascimento != null){
			this.nascimento = new Date(nascimento.getTime());
		} else{
			this.nascimento = null;
		}
	}
	/**
	 * @return the nomePai
	 */
	public String getNomePai() {
		return nomePai;
	}
	/**
	 * @param nomePai the nomePai to set
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	/**
	 * @return the nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}
	/**
	 * @param nomeMae the nomeMae to set
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	/**
	 * @return the nacionalidade
	 */
	public String getNacionalidade() {
		return nacionalidade;
	}
	/**
	 * @param nacionalidade the nacionalidade to set
	 */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	/**
	 * @return the naturalidade
	 */
	public String getNaturalidade() {
		return naturalidade;
	}
	/**
	 * @param naturalidade the naturalidade to set
	 */
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	/**
	 * @return the descSexo
	 */
	public Character getDescSexo() {
		return descSexo;
	}
	/**
	 * @param descSexo the descSexo to set
	 */
	public void setDescSexo(Character descSexo) {
		this.descSexo = descSexo;
	}
	/**
	 * @return the descProfissao
	 */
	public String getDescProfissao() {
		return descProfissao;
	}
	/**
	 * @param descProfissao the descProfissao to set
	 */
	public void setDescProfissao(String descProfissao) {
		this.descProfissao = descProfissao;
	}
	/**
	 * @return the estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}
	/**
	 * @param estadoCivil the estadoCivil to set
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	/**
	 * @return the descDocumento
	 */
	public String getDescDocumento() {
		return descDocumento;
	}
	/**
	 * @param descDocumento the descDocumento to set
	 */
	public void setDescDocumento(String descDocumento) {
		this.descDocumento = descDocumento;
	}
	/**
	 * @return the numDocumento
	 */
	public String getNumDocumento() {
		return numDocumento;
	}
	/**
	 * @param numDocumento the numDocumento to set
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	/**
	 * @return the emissaoDocumento
	 */
	public Date getEmissaoDocumento() {
		if(emissaoDocumento != null){
			return new Date(emissaoDocumento.getTime());
		}
		return null;
	}
	/**
	 * @param emissaoDocumento the emissaoDocumento to set
	 */
	public void setEmissaoDocumento(Date emissaoDocumento) {
		if(emissaoDocumento != null){
			this.emissaoDocumento = new Date(emissaoDocumento.getTime());
		} else{
			this.emissaoDocumento = null;
		}
	}
	/**
	 * @return the orgaoDocumento
	 */
	public String getOrgaoDocumento() {
		return orgaoDocumento;
	}
	/**
	 * @param orgaoDocumento the orgaoDocumento to set
	 */
	public void setOrgaoDocumento(String orgaoDocumento) {
		this.orgaoDocumento = orgaoDocumento;
	}
	/**
	 * @return the ufDocumento
	 */
	public String getUfDocumento() {
		return ufDocumento;
	}
	/**
	 * @param ufDocumento the ufDocumento to set
	 */
	public void setUfDocumento(String ufDocumento) {
		this.ufDocumento = ufDocumento;
	}
	/**
	 * @return the filiacao
	 */
	public String getFiliacao() {
		return filiacao;
	}
	/**
	 * @param filiacao the filiacao to set
	 */
	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}
	
	/**
	 * Recupera o valor de idNaturalidade.
	 *
	 * @return o valor de idNaturalidade
	 */
	public Integer getIdNaturalidade() {
		return idNaturalidade;
	}
	
	/**
	 * Define o valor de idNaturalidade.
	 *
	 * @param idNaturalidade o novo valor de idNaturalidade
	 */
	public void setIdNaturalidade(Integer idNaturalidade) {
		this.idNaturalidade = idNaturalidade;
	}
}
