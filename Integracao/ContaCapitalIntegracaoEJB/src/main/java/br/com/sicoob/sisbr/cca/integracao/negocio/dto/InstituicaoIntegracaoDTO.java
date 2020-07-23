/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;


/**
 * A Classe InstituicaoIntegracaoDTO.
 */
public class InstituicaoIntegracaoDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4033217694616865078L;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo nomeInstituicao. */
	private String nomeInstituicao;
	
	/** O atributo siglaInstituicao. */
	private String siglaInstituicao;
	
	/** O atributo descInstituicao. */
	private String descInstituicao;
	
	/** O atributo situacaoInst. */
	private Integer situacaoInst;
	
	/** O atributo numero. */
	private String numero;
	
	/** O atributo dataSituacaoEspecial. */
	private DateTime dataSituacaoEspecial;
	
	/** O atributo codigoSituacaoEspecial. */
	private Integer codigoSituacaoEspecial;
	
	/** O atributo numCNPJ. */
	private String numCNPJ;
	
	/** O atributo descEndereco. */
	private String descEndereco;
	
	/** O atributo nomeCidade. */
	private String nomeCidade;
	
	/** O atributo idLocalidade. */
	private Integer idLocalidade;
	
	/** O atributo uf */
	private String uf;
	
	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	
	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	/**
	 * Recupera o valor de nomeInstituicao.
	 *
	 * @return o valor de nomeInstituicao
	 */
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}
	
	/**
	 * Define o valor de nomeInstituicao.
	 *
	 * @param nomeInstituicao o novo valor de nomeInstituicao
	 */
	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}
	
	/**
	 * Recupera o valor de siglaInstituicao.
	 *
	 * @return o valor de siglaInstituicao
	 */
	public String getSiglaInstituicao() {
		return siglaInstituicao;
	}
	
	/**
	 * Define o valor de siglaInstituicao.
	 *
	 * @param siglaInstituicao o novo valor de siglaInstituicao
	 */
	public void setSiglaInstituicao(String siglaInstituicao) {
		this.siglaInstituicao = siglaInstituicao;
	}
	
	/**
	 * Recupera o valor de descInstituicao.
	 *
	 * @return o valor de descInstituicao
	 */
	public String getDescInstituicao() {
		return descInstituicao;
	}
	
	/**
	 * Define o valor de descInstituicao.
	 *
	 * @param descInstituicao o novo valor de descInstituicao
	 */
	public void setDescInstituicao(String descInstituicao) {
		this.descInstituicao = descInstituicao;
	}
	
	/**
	 * Recupera o valor de situacaoInst.
	 *
	 * @return o valor de situacaoInst
	 */
	public Integer getSituacaoInst() {
		return situacaoInst;
	}
	
	/**
	 * Define o valor de situacaoInst.
	 *
	 * @param situacaoInst o novo valor de situacaoInst
	 */
	public void setSituacaoInst(Integer situacaoInst) {
		this.situacaoInst = situacaoInst;
	}
	
	/**
	 * Recupera o valor de numero.
	 *
	 * @return o valor de numero
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Define o valor de numero.
	 *
	 * @param numero o novo valor de numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return the dataSituacaoEspecial
	 */
	public DateTime getDataSituacaoEspecial() {
		return dataSituacaoEspecial;
	}
	/**
	 * @param dataSituacaoEspecial the dataSituacaoEspecial to set
	 */
	public void setDataSituacaoEspecial(DateTime dataSituacaoEspecial) {
		this.dataSituacaoEspecial = dataSituacaoEspecial;
	}
	/**
	 * @return the codigoSituacaoEspecial
	 */
	public Integer getCodigoSituacaoEspecial() {
		return codigoSituacaoEspecial;
	}
	/**
	 * @param codigoSituacaoEspecial the codigoSituacaoEspecial to set
	 */
	public void setCodigoSituacaoEspecial(Integer codigoSituacaoEspecial) {
		this.codigoSituacaoEspecial = codigoSituacaoEspecial;
	}
	
	/**
	 * Recupera o valor de numCNPJ.
	 *
	 * @return o valor de numCNPJ
	 */
	public String getNumCNPJ() {
		return numCNPJ;
	}
	
	/**
	 * Define o valor de numCNPJ.
	 *
	 * @param numCNPJ o novo valor de numCNPJ
	 */
	public void setNumCNPJ(String numCNPJ) {
		this.numCNPJ = numCNPJ;
	}

	public String getDescEndereco() {
		return descEndereco;
	}

	public void setDescEndereco(String descEndereco) {
		this.descEndereco = descEndereco;
	}

	/**
	 * @return the idLocalidade
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade the idLocalidade to set
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return the nomeCidade
	 */
	public String getNomeCidade() {
		return nomeCidade;
	}

	/**
	 * @param nomeCidade the nomeCidade to set
	 */
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	/**
	 * Retorna o nome da cidade e uf
	 * @return
	 */
	public String getNomeCidadeUf() {
		return getNomeCidade() + " - " + getUf();
	}
	
}
