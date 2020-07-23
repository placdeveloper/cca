/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO para relatorio de desligamento de associado.
 */
public class RelDesligamentoAssociadoDTO extends BancoobDto {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idContaCapital;
	private Integer idPessoa;
	private Integer idInstituicao;
	
	private short tipoPessoa;
	
	private String nomeCompleto;
	private Integer numContaCapital;
	
	private String cpfCnpj;
	private String numDocumento;
	private Date emissaoDocumento;
	private String orgaoDocumento;
	private String ufDocumento;
	
	private String filiacao;
	private String nacionalidade;
	private String naturalidade;
	private Date nascimento;
	private Character descSexo;
	private String descProfissao;
	private String estadoCivil;
	
	private String descEnderecoResidencial;
	private String numResidencial;
	private String complementoResidencial;
	private String bairroResidencial;
	private String municipioResidencial;
	private String ufResidencial;
	private String cepResidencial;
	private String telefoneResidencial;
	
	private String razaoSocialEmpresa;
	private Date dataConstituicao;
	private String numInscricaoEstadual;
	private String ufInscricaoEstadual;
	
	private String descEnderecoComercial;
	private String numComercial;
	private String complementoComercial;
	private String bairroComercial;
	private String municipioComercial;
	private String ufComercial;
	private String cepComercial;
	private String telefoneComercial;
	
	
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}
	public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public short getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(short tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	/**
	 * getEmissaoDocumento
	 * @return
	 */
	public Date getEmissaoDocumento() {
		if(emissaoDocumento != null){
			return new Date(emissaoDocumento.getTime());
		}
		return null;
	}
	/**
	 * setEmissaoDocumento
	 * @param emissaoDocumento
	 */
	public void setEmissaoDocumento(Date emissaoDocumento) {
		if(emissaoDocumento != null){
			this.emissaoDocumento = new Date(emissaoDocumento.getTime());
		} else{
			this.emissaoDocumento = null;
		}
	}
	public String getOrgaoDocumento() {
		return orgaoDocumento;
	}
	public void setOrgaoDocumento(String orgaoDocumento) {
		this.orgaoDocumento = orgaoDocumento;
	}
	public String getUfDocumento() {
		return ufDocumento;
	}
	public void setUfDocumento(String ufDocumento) {
		this.ufDocumento = ufDocumento;
	}
	public String getFiliacao() {
		return filiacao;
	}
	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	/**
	 * getNascimento
	 * @return
	 */
	public Date getNascimento() {
		if(nascimento != null){
			return new Date(nascimento.getTime());
		}
		return null;
	}
	/**
	 * setNascimento
	 * @param nascimento
	 */
	public void setNascimento(Date nascimento) {
		if(nascimento != null){
			this.nascimento = new Date(nascimento.getTime());
		} else{
			this.nascimento = null;
		}
	}
	public Character getDescSexo() {
		return descSexo;
	}
	public void setDescSexo(Character descSexo) {
		this.descSexo = descSexo;
	}
	public String getDescProfissao() {
		return descProfissao;
	}
	public void setDescProfissao(String descProfissao) {
		this.descProfissao = descProfissao;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getDescEnderecoResidencial() {
		return descEnderecoResidencial;
	}
	public void setDescEnderecoResidencial(String descEnderecoResidencial) {
		this.descEnderecoResidencial = descEnderecoResidencial;
	}
	public String getNumResidencial() {
		return numResidencial;
	}
	public void setNumResidencial(String numResidencial) {
		this.numResidencial = numResidencial;
	}
	public String getComplementoResidencial() {
		return complementoResidencial;
	}
	public void setComplementoResidencial(String complementoResidencial) {
		this.complementoResidencial = complementoResidencial;
	}
	public String getBairroResidencial() {
		return bairroResidencial;
	}
	public void setBairroResidencial(String bairroResidencial) {
		this.bairroResidencial = bairroResidencial;
	}
	public String getMunicipioResidencial() {
		return municipioResidencial;
	}
	public void setMunicipioResidencial(String municipioResidencial) {
		this.municipioResidencial = municipioResidencial;
	}
	public String getUfResidencial() {
		return ufResidencial;
	}
	public void setUfResidencial(String ufResidencial) {
		this.ufResidencial = ufResidencial;
	}
	public String getCepResidencial() {
		return cepResidencial;
	}
	public void setCepResidencial(String cepResidencial) {
		this.cepResidencial = cepResidencial;
	}
	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}
	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	public String getRazaoSocialEmpresa() {
		return razaoSocialEmpresa;
	}
	public void setRazaoSocialEmpresa(String razaoSocialEmpresa) {
		this.razaoSocialEmpresa = razaoSocialEmpresa;
	}
	/**
	 * getDataConstituicao
	 * @return
	 */
	public Date getDataConstituicao() {
		if(dataConstituicao != null){
			return new Date(dataConstituicao.getTime());
		}
		return null;
	}
	/**
	 * setDataConstituicao
	 * @param dataConstituicao
	 */
	public void setDataConstituicao(Date dataConstituicao) {
		if(dataConstituicao != null){
			this.dataConstituicao = new Date(dataConstituicao.getTime());
		} else{
			this.dataConstituicao = null;
		}
	}
	public String getNumInscricaoEstadual() {
		return numInscricaoEstadual;
	}
	public void setNumInscricaoEstadual(String numInscricaoEstadual) {
		this.numInscricaoEstadual = numInscricaoEstadual;
	}
	public String getUfInscricaoEstadual() {
		return ufInscricaoEstadual;
	}
	public void setUfInscricaoEstadual(String ufInscricaoEstadual) {
		this.ufInscricaoEstadual = ufInscricaoEstadual;
	}
	public String getDescEnderecoComercial() {
		return descEnderecoComercial;
	}
	public void setDescEnderecoComercial(String descEnderecoComercial) {
		this.descEnderecoComercial = descEnderecoComercial;
	}
	public String getNumComercial() {
		return numComercial;
	}
	public void setNumComercial(String numComercial) {
		this.numComercial = numComercial;
	}
	public String getComplementoComercial() {
		return complementoComercial;
	}
	public void setComplementoComercial(String complementoComercial) {
		this.complementoComercial = complementoComercial;
	}
	public String getBairroComercial() {
		return bairroComercial;
	}
	public void setBairroComercial(String bairroComercial) {
		this.bairroComercial = bairroComercial;
	}
	public String getMunicipioComercial() {
		return municipioComercial;
	}
	public void setMunicipioComercial(String municipioComercial) {
		this.municipioComercial = municipioComercial;
	}
	public String getUfComercial() {
		return ufComercial;
	}
	public void setUfComercial(String ufComercial) {
		this.ufComercial = ufComercial;
	}
	public String getCepComercial() {
		return cepComercial;
	}
	public void setCepComercial(String cepComercial) {
		this.cepComercial = cepComercial;
	}
	public String getTelefoneComercial() {
		return telefoneComercial;
	}
	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}
		
}
