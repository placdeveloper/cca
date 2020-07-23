/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;


/**
 * A Classe PessoaIntegracaoDTO.
 */
public class PessoaIntegracaoDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5245217606534884853L;

	// PESSOA
	/** O atributo idPessoa. */
	private Integer idPessoa;  
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo codTipoPessoa. */
	private Short codTipoPessoa;

	// PESSOA COMPARTILHAMENTO
	/** O atributo nomePessoa. */
	private String nomePessoa;
	
	/** O atributo nomeCompleto. */
	private String nomeCompleto;
	
	/** O atributo nomeApelido. */
	private String nomeApelido;

	//TRANSICAO
	/** O atributo idPessoaLegado. */
	private Integer idPessoaLegado;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo codCNAE. */
	private String codCNAE;
	
	/** O atributo naturezaJuridica. */
	private String naturezaJuridica;
	
	/** O atributo numRegistroOrgaoCompetente. */
	private String numRegistroOrgaoCompetente;
	
	/** O atributo dataRegistroOrgaoCompetente. */
	private Date dataRegistroOrgaoCompetente;
	
	/** O atributo dataConstituicao. */
	private Date dataConstituicao;
	
	/**
	 * Natureza juridica
	 */
	private Short codigoTipoFormaConstituicao;
	
	/** O atributo codigoCnaeFiscal. */
	private String codigoCnaeFiscal;
	
	/** O atributo codigoAtividadeEconomica. */
	private Short codigoAtividadeEconomica;
	
	/** O atributo idUnidadeInst */
	private Integer idUnidadeInst;
	
	/**
	 * Recupera o valor de idPessoa.
	 *
	 * @return o valor de idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Define o valor de idPessoa.
	 *
	 * @param idPessoa o novo valor de idPessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera o valor de cpfCnpj.
	 *
	 * @return o valor de cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Define o valor de cpfCnpj.
	 *
	 * @param cpfCnpj o novo valor de cpfCnpj
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera o valor de codTipoPessoa.
	 *
	 * @return o valor de codTipoPessoa
	 */
	public Short getCodTipoPessoa() {
		return codTipoPessoa;
	}

	/**
	 * Define o valor de codTipoPessoa.
	 *
	 * @param codTipoPessoa o novo valor de codTipoPessoa
	 */
	public void setCodTipoPessoa(Short codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}

	/**
	 * Recupera o valor de nomePessoa.
	 *
	 * @return o valor de nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * Define o valor de nomePessoa.
	 *
	 * @param nomePessoa o novo valor de nomePessoa
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * Recupera o valor de nomeCompleto.
	 *
	 * @return o valor de nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * Define o valor de nomeCompleto.
	 *
	 * @param nomeCompleto o novo valor de nomeCompleto
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * Recupera o valor de nomeApelido.
	 *
	 * @return o valor de nomeApelido
	 */
	public String getNomeApelido() {
		return nomeApelido;
	}

	/**
	 * Define o valor de nomeApelido.
	 *
	 * @param nomeApelido o novo valor de nomeApelido
	 */
	public void setNomeApelido(String nomeApelido) {
		this.nomeApelido = nomeApelido;
	}

	/**
	 * Recupera o valor de idPessoaLegado.
	 *
	 * @return o valor de idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}

	/**
	 * Define o valor de idPessoaLegado.
	 *
	 * @param idPessoaLegado o novo valor de idPessoaLegado
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}

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
	 * @return the codigoTipoFormaConstituicao
	 */
	public Short getCodigoTipoFormaConstituicao() {
		return codigoTipoFormaConstituicao;
	}

	/**
	 * @param codigoTipoFormaConstituicao the codigoTipoFormaConstituicao to set
	 */
	public void setCodigoTipoFormaConstituicao(Short codigoTipoFormaConstituicao) {
		this.codigoTipoFormaConstituicao = codigoTipoFormaConstituicao;
	}

	/**
	 * @return the codigoCnaeFiscal
	 */
	public String getCodigoCnaeFiscal() {
		return codigoCnaeFiscal;
	}

	/**
	 * @param codigoCnaeFiscal the codigoCnaeFiscal to set
	 */
	public void setCodigoCnaeFiscal(String codigoCnaeFiscal) {
		this.codigoCnaeFiscal = codigoCnaeFiscal;
	}

	/**
	 * @return the codigoAtividadeEconomica
	 */
	public Short getCodigoAtividadeEconomica() {
		return codigoAtividadeEconomica;
	}

	/**
	 * @param codigoAtividadeEconomica the codigoAtividadeEconomica to set
	 */
	public void setCodigoAtividadeEconomica(Short codigoAtividadeEconomica) {
		this.codigoAtividadeEconomica = codigoAtividadeEconomica;
	}

	/**
	 * @return the codCNAE
	 */
	public String getCodCNAE() {
		return codCNAE;
	}

	/**
	 * @param codCNAE the codCNAE to set
	 */
	public void setCodCNAE(String codCNAE) {
		this.codCNAE = codCNAE;
	}

	/**
	 * Recupera o valor de naturezaJuridica.
	 *
	 * @return o valor de naturezaJuridica
	 */
	public String getNaturezaJuridica() {
		return naturezaJuridica;
	}

	/**
	 * Define o valor de naturezaJuridica.
	 *
	 * @param naturezaJuridica o novo valor de naturezaJuridica
	 */
	public void setNaturezaJuridica(String naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}

	/**
	 * Recupera o valor de numRegistroOrgaoCompetente.
	 *
	 * @return o valor de numRegistroOrgaoCompetente
	 */
	public String getNumRegistroOrgaoCompetente() {
		return numRegistroOrgaoCompetente;
	}

	/**
	 * Define o valor de numRegistroOrgaoCompetente.
	 *
	 * @param numRegistroOrgaoCompetente o novo valor de numRegistroOrgaoCompetente
	 */
	public void setNumRegistroOrgaoCompetente(String numRegistroOrgaoCompetente) {
		this.numRegistroOrgaoCompetente = numRegistroOrgaoCompetente;
	}

	/**
	 * Recupera o valor de dataRegistroOrgaoCompetente.
	 *
	 * @return o valor de dataRegistroOrgaoCompetente
	 */
	public Date getDataRegistroOrgaoCompetente() {
		if(dataRegistroOrgaoCompetente != null){
			return new Date(dataRegistroOrgaoCompetente.getTime());
		}
		return null;
	}

	/**
	 * Define o valor de dataRegistroOrgaoCompetente.
	 *
	 * @param dataRegistroOrgaoCompetente o novo valor de dataRegistroOrgaoCompetente
	 */
	public void setDataRegistroOrgaoCompetente(Date dataRegistroOrgaoCompetente) {
		if(dataRegistroOrgaoCompetente != null){
			this.dataRegistroOrgaoCompetente = new Date(dataRegistroOrgaoCompetente.getTime());
		} else{
			this.dataRegistroOrgaoCompetente = null;
		}
	}

	/**
	 * Recupera o valor de dataConstituicao.
	 *
	 * @return o valor de dataConstituicao
	 */
	public Date getDataConstituicao() {
		if(dataConstituicao != null){
			return new Date(dataConstituicao.getTime());
		}
		return null;
	}

	/**
	 * Define o valor de dataConstituicao.
	 *
	 * @param dataConstituicao o novo valor de dataConstituicao
	 */
	public void setDataConstituicao(Date dataConstituicao) {
		if(dataConstituicao != null){
			this.dataConstituicao = new Date(dataConstituicao.getTime());
		} else{
			this.dataConstituicao = null;
		}
	}

	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}
}