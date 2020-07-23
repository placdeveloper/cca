/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO que segura os dados para montar o relatório de ficha proposta de matricula
 * @author Guilherme.Nunes
 *
 */
public class RelFichaPropostaMatriculaDTO extends BancoobDto{
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -120565210505268621L;
	
	/**
	 * Dados comum a todas as pessoas
	 */
	private Integer idContaCapital;
	
	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/**
	 * tipo da pessoa
	 */
	private short tipoPessoa;
	
	/**
	 * Dados de pessoa juridica
	 */
	private String razaoSocialEmpresa;
	
	/** O atributo cnpjEmpresa. */
	private String cnpjEmpresa;
	
	/** O atributo nomeFantasia. */
	private String nomeFantasia;
	
	/** O atributo inscricaoEstadual. */
	private String inscricaoEstadual;
	
	/** O atributo codCNAE. */
	private String codCNAE;
	
	/** O atributo naturezaJuridica. */
	private String naturezaJuridica;
	
	/** O atributo numRegistroOrgaoCompetente. */
	private String numRegistroOrgaoCompetente;
	
	/** O atributo dataRegistroOrgaoCompetente. */
	private Date dataRegistroOrgaoCompetente;
	
	/** O atributo capitalSocial. */
	private BigDecimal capitalSocial;
	
	/** O atributo faturamentoMensal. */
	private BigDecimal faturamentoMensal;
	
	/**
	 * Dados de pessoa física
	 */
	private String nomeCompleto;
	
	/** O atributo cpf. */
	private String cpf;
	
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
	
	/** O atributo nacionalidade. */
	private String nacionalidade;
	
	/** O atributo naturalidade. */
	private String naturalidade;
	
	/** O atributo nascimento. */
	private Date nascimento;
	
	/** O atributo descSexo. */
	private Character descSexo;
	
	/** O atributo descProfissao. */
	private String descProfissao;
	
	/** O atributo estadoCivil. */
	private String estadoCivil;
	
	/** O atributo nomeCompanheiro. */
	private String nomeCompanheiro;
	
	/** O atributo cpfCompanheiro. */
	private String cpfCompanheiro;
	
	/** O atributo nomeResponsavelLegal. */
	private String nomeResponsavelLegal;
	
	/** O atributo cpfResponsavelLegal. */
	private String cpfResponsavelLegal;
	
	/**
	 * Dados do endereço residencial
	 */
	private String descEnderecoResidencial;
	
	/** O atributo numEnderecoResidencial. */
	private String numEnderecoResidencial;
	
	/** O atributo complementoEnderecoResidencial. */
	private String complementoEnderecoResidencial;
	
	/** O atributo bairroEnderecoResidencial. */
	private String bairroEnderecoResidencial;
	
	/** O atributo municipioEnderecoResidencial. */
	private String municipioEnderecoResidencial;
	
	/** O atributo ufEnderecoResidencial. */
	private String ufEnderecoResidencial;
	
	/** O atributo cepEnderecoResidencial. */
	private String cepEnderecoResidencial;
	
	/** O atributo telefoneEnderecoResidencial. */
	private String telefoneEnderecoResidencial;
	
	/** O atributo ramalEnderecoResidencial. */
	private String ramalEnderecoResidencial;
	
	/**
	 * Dados do endereço comercial
	 */
	private String descEnderecoComercial;
	
	/** O atributo numEnderecoComercial. */
	private String numEnderecoComercial;
	
	/** O atributo complementoEnderecoComercial. */
	private String complementoEnderecoComercial;
	
	/** O atributo bairroEnderecoComercial. */
	private String bairroEnderecoComercial;
	
	/** O atributo municipioEnderecoComercial. */
	private String municipioEnderecoComercial;
	
	/** O atributo ufEnderecoComercial. */
	private String ufEnderecoComercial;
	
	/** O atributo cepEnderecoComercial. */
	private String cepEnderecoComercial;
	
	/** O atributo telefoneEnderecoComercial. */
	private String telefoneEnderecoComercial;
	
	/** O atributo ramalEnderecoComercial. */
	private String ramalEnderecoComercial;
	
	/** O atributo rendaMensal. */
	private BigDecimal rendaMensal;
	
	/** O atributo valorPatrimonio. */
	private BigDecimal valorPatrimonio;
	
	/**
	 * Dados de referencia da pessoa.
	 */
	private String tipoReferenciaUm;
	
	/** O atributo descReferenciaUm. */
	private String descReferenciaUm;
	
	/** O atributo numReferenciaUm. */
	private String numReferenciaUm;
	
	/** O atributo tipoReferenciaDois. */
	private String tipoReferenciaDois;
	
	/** O atributo descReferenciaDois. */
	private String descReferenciaDois;
	
	/** O atributo numReferenciaDois. */
	private String numReferenciaDois;
	
	/**
	 * Dados da proposta
	 */
	private String numCooperativa;
	
	/** O atributo razaoSocial. */
	private String razaoSocial;
	
	/** O atributo sigla. */
	private String sigla;
	
	/** O atributo postoDeAtendimento. */
	private String postoDeAtendimento;
	
	/** O atributo cnpj. */
	private String cnpj;
	
	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** O atributo capitalSubscrever. */
	private BigDecimal capitalSubscrever;
	
	/** O atributo integralizacaoVista. */
	private BigDecimal integralizacaoVista;
	
	/** O atributo valorTotalParcela. */
	private BigDecimal valorTotalParcela;
	
	/** O atributo qtdParcelasMensais. */
	private Integer qtdParcelasMensais;
	
	/** O atributo valorParcelas. */
	private BigDecimal valorParcelas;
	
	/** O atributo diaDebito. */
	private Integer diaDebito;
	
	/** O atributo formaDebito. */
	private String formaDebito;
	
	/** O atributo descCidadeUf */
	private String descCidadeUf;

	/**
	 * Setters and Getters
	 */
	
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

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public short getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(short tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getRazaoSocialEmpresa() {
		return razaoSocialEmpresa;
	}

	public void setRazaoSocialEmpresa(String razaoSocialEmpresa) {
		this.razaoSocialEmpresa = razaoSocialEmpresa;
	}

	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getCodCNAE() {
		return codCNAE;
	}

	public void setCodCNAE(String codCNAE) {
		this.codCNAE = codCNAE;
	}

	public String getNaturezaJuridica() {
		return naturezaJuridica;
	}

	public void setNaturezaJuridica(String naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}

	public String getNumRegistroOrgaoCompetente() {
		return numRegistroOrgaoCompetente;
	}

	public void setNumRegistroOrgaoCompetente(String numRegistroOrgaoCompetente) {
		this.numRegistroOrgaoCompetente = numRegistroOrgaoCompetente;
	}

	/**
	 * getDataRegistroOrgaoCompetente
	 * @return
	 */
	public Date getDataRegistroOrgaoCompetente() {
		if(dataRegistroOrgaoCompetente != null){
			return new Date(dataRegistroOrgaoCompetente.getTime());
		}
		return null;
	}

	/**
	 * setDataRegistroOrgaoCompetente
	 * @param dataRegistroOrgaoCompetente
	 */
	public void setDataRegistroOrgaoCompetente(Date dataRegistroOrgaoCompetente) {
		if(dataRegistroOrgaoCompetente != null){
			this.dataRegistroOrgaoCompetente = new Date(dataRegistroOrgaoCompetente.getTime());
		} else{
			this.dataRegistroOrgaoCompetente = null;
		}
	}

	public BigDecimal getCapitalSocial() {
		return capitalSocial;
	}

	public void setCapitalSocial(BigDecimal capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public BigDecimal getFaturamentoMensal() {
		return faturamentoMensal;
	}

	public void setFaturamentoMensal(BigDecimal faturamentoMensal) {
		this.faturamentoMensal = faturamentoMensal;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDescDocumento() {
		return descDocumento;
	}

	public void setDescDocumento(String descDocumento) {
		this.descDocumento = descDocumento;
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
		if(emissaoDocumento != null) {
			this.emissaoDocumento = new Date(emissaoDocumento.getTime());
		}else{
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

	public String getNomeCompanheiro() {
		return nomeCompanheiro;
	}

	public void setNomeCompanheiro(String nomeCompanheiro) {
		this.nomeCompanheiro = nomeCompanheiro;
	}

	public String getCpfCompanheiro() {
		return cpfCompanheiro;
	}

	public void setCpfCompanheiro(String cpfCompanheiro) {
		this.cpfCompanheiro = cpfCompanheiro;
	}

	public String getNomeResponsavelLegal() {
		return nomeResponsavelLegal;
	}

	public void setNomeResponsavelLegal(String nomeResponsavelLegal) {
		this.nomeResponsavelLegal = nomeResponsavelLegal;
	}

	public String getCpfResponsavelLegal() {
		return cpfResponsavelLegal;
	}

	public void setCpfResponsavelLegal(String cpfResponsavelLegal) {
		this.cpfResponsavelLegal = cpfResponsavelLegal;
	}

	public String getDescEnderecoResidencial() {
		return descEnderecoResidencial;
	}

	public void setDescEnderecoResidencial(String descEnderecoResidencial) {
		this.descEnderecoResidencial = descEnderecoResidencial;
	}

	public String getNumEnderecoResidencial() {
		return numEnderecoResidencial;
	}

	public void setNumEnderecoResidencial(String numEnderecoResidencial) {
		this.numEnderecoResidencial = numEnderecoResidencial;
	}

	public String getComplementoEnderecoResidencial() {
		return complementoEnderecoResidencial;
	}

	public void setComplementoEnderecoResidencial(
			String complementoEnderecoResidencial) {
		this.complementoEnderecoResidencial = complementoEnderecoResidencial;
	}

	public String getBairroEnderecoResidencial() {
		return bairroEnderecoResidencial;
	}

	public void setBairroEnderecoResidencial(String bairroEnderecoResidencial) {
		this.bairroEnderecoResidencial = bairroEnderecoResidencial;
	}

	public String getMunicipioEnderecoResidencial() {
		return municipioEnderecoResidencial;
	}

	public void setMunicipioEnderecoResidencial(String municipioEnderecoResidencial) {
		this.municipioEnderecoResidencial = municipioEnderecoResidencial;
	}

	public String getUfEnderecoResidencial() {
		return ufEnderecoResidencial;
	}

	public void setUfEnderecoResidencial(String ufEnderecoResidencial) {
		this.ufEnderecoResidencial = ufEnderecoResidencial;
	}

	public String getCepEnderecoResidencial() {
		return cepEnderecoResidencial;
	}

	public void setCepEnderecoResidencial(String cepEnderecoResidencial) {
		this.cepEnderecoResidencial = cepEnderecoResidencial;
	}

	public String getTelefoneEnderecoResidencial() {
		return telefoneEnderecoResidencial;
	}

	public void setTelefoneEnderecoResidencial(String telefoneEnderecoResidencial) {
		this.telefoneEnderecoResidencial = telefoneEnderecoResidencial;
	}

	public String getRamalEnderecoResidencial() {
		return ramalEnderecoResidencial;
	}

	public void setRamalEnderecoResidencial(String ramalEnderecoResidencial) {
		this.ramalEnderecoResidencial = ramalEnderecoResidencial;
	}

	public String getDescEnderecoComercial() {
		return descEnderecoComercial;
	}

	public void setDescEnderecoComercial(String descEnderecoComercial) {
		this.descEnderecoComercial = descEnderecoComercial;
	}

	public String getNumEnderecoComercial() {
		return numEnderecoComercial;
	}

	public void setNumEnderecoComercial(String numEnderecoComercial) {
		this.numEnderecoComercial = numEnderecoComercial;
	}

	public String getComplementoEnderecoComercial() {
		return complementoEnderecoComercial;
	}

	public void setComplementoEnderecoComercial(String complementoEnderecoComercial) {
		this.complementoEnderecoComercial = complementoEnderecoComercial;
	}

	public String getBairroEnderecoComercial() {
		return bairroEnderecoComercial;
	}

	public void setBairroEnderecoComercial(String bairroEnderecoComercial) {
		this.bairroEnderecoComercial = bairroEnderecoComercial;
	}

	public String getMunicipioEnderecoComercial() {
		return municipioEnderecoComercial;
	}

	public void setMunicipioEnderecoComercial(String municipioEnderecoComercial) {
		this.municipioEnderecoComercial = municipioEnderecoComercial;
	}

	public String getUfEnderecoComercial() {
		return ufEnderecoComercial;
	}

	public void setUfEnderecoComercial(String ufEnderecoComercial) {
		this.ufEnderecoComercial = ufEnderecoComercial;
	}

	public String getCepEnderecoComercial() {
		return cepEnderecoComercial;
	}

	public void setCepEnderecoComercial(String cepEnderecoComercial) {
		this.cepEnderecoComercial = cepEnderecoComercial;
	}

	public String getTelefoneEnderecoComercial() {
		return telefoneEnderecoComercial;
	}

	public void setTelefoneEnderecoComercial(String telefoneEnderecoComercial) {
		this.telefoneEnderecoComercial = telefoneEnderecoComercial;
	}

	public String getRamalEnderecoComercial() {
		return ramalEnderecoComercial;
	}

	public void setRamalEnderecoComercial(String ramalEnderecoComercial) {
		this.ramalEnderecoComercial = ramalEnderecoComercial;
	}

	public BigDecimal getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(BigDecimal rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public BigDecimal getValorPatrimonio() {
		return valorPatrimonio;
	}

	public void setValorPatrimonio(BigDecimal valorPatrimonio) {
		this.valorPatrimonio = valorPatrimonio;
	}

	public String getTipoReferenciaUm() {
		return tipoReferenciaUm;
	}

	public void setTipoReferenciaUm(String tipoReferenciaUm) {
		this.tipoReferenciaUm = tipoReferenciaUm;
	}

	public String getDescReferenciaUm() {
		return descReferenciaUm;
	}

	public void setDescReferenciaUm(String descReferenciaUm) {
		this.descReferenciaUm = descReferenciaUm;
	}

	public String getNumReferenciaUm() {
		return numReferenciaUm;
	}

	public void setNumReferenciaUm(String numReferenciaUm) {
		this.numReferenciaUm = numReferenciaUm;
	}

	public String getTipoReferenciaDois() {
		return tipoReferenciaDois;
	}

	public void setTipoReferenciaDois(String tipoReferenciaDois) {
		this.tipoReferenciaDois = tipoReferenciaDois;
	}

	public String getDescReferenciaDois() {
		return descReferenciaDois;
	}

	public void setDescReferenciaDois(String descReferenciaDois) {
		this.descReferenciaDois = descReferenciaDois;
	}

	public String getNumReferenciaDois() {
		return numReferenciaDois;
	}

	public void setNumReferenciaDois(String numReferenciaDois) {
		this.numReferenciaDois = numReferenciaDois;
	}

	public String getNumCooperativa() {
		return numCooperativa;
	}

	public void setNumCooperativa(String numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getPostoDeAtendimento() {
		return postoDeAtendimento;
	}

	public void setPostoDeAtendimento(String postoDeAtendimento) {
		this.postoDeAtendimento = postoDeAtendimento;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getNumContaCapital() {
		return numContaCapital;
	}

	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}

	public BigDecimal getCapitalSubscrever() {
		return capitalSubscrever;
	}

	public void setCapitalSubscrever(BigDecimal capitalSubscrever) {
		this.capitalSubscrever = capitalSubscrever;
	}

	public BigDecimal getIntegralizacaoVista() {
		return integralizacaoVista;
	}

	public void setIntegralizacaoVista(BigDecimal integralizacaoVista) {
		this.integralizacaoVista = integralizacaoVista;
	}

	public BigDecimal getValorTotalParcela() {
		return valorTotalParcela;
	}

	public void setValorTotalParcela(BigDecimal valorTotalParcela) {
		this.valorTotalParcela = valorTotalParcela;
	}

	public Integer getQtdParcelasMensais() {
		return qtdParcelasMensais;
	}

	public void setQtdParcelasMensais(Integer qtdParcelasMensais) {
		this.qtdParcelasMensais = qtdParcelasMensais;
	}

	public BigDecimal getValorParcelas() {
		return valorParcelas;
	}

	public void setValorParcelas(BigDecimal valorParcelas) {
		this.valorParcelas = valorParcelas;
	}

	public Integer getDiaDebito() {
		return diaDebito;
	}

	public void setDiaDebito(Integer diaDebito) {
		this.diaDebito = diaDebito;
	}

	public String getFormaDebito() {
		return formaDebito;
	}

	public void setFormaDebito(String formaDebito) {
		this.formaDebito = formaDebito;
	}

	/**
	 * @return the descCidadeUf
	 */
	public String getDescCidadeUf() {
		return descCidadeUf;
	}

	/**
	 * @param descCidadeUf the descCidadeUf to set
	 */
	public void setDescCidadeUf(String descCidadeUf) {
		this.descCidadeUf = descCidadeUf;
	}

}