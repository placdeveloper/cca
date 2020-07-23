/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * DTO relativo a ficha matricula 
 * @author Marco.Nascimento
 */
public class DadosRelFichaMatriculaDTO extends BancoobDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7403913108654129151L;
	
	/** O atributo descNomePessoa. */
	private String descNomePessoa;
	
	/** O atributo descNacionalidade. */
	private String descNacionalidade;
	
	/** O atributo descNaturalidade. */
	private String descNaturalidade;
	
	/** O atributo numRG. */
	private String numRG;
	
	/** O atributo descOrgaoExpRG. */
	private String descOrgaoExpRG;
	
	/** O atributo descUfOrgExpRG. */
	private String descUfOrgExpRG;
	
	/** O atributo desclistaitem. */
	private String desclistaitem;
	
	/** O atributo enderecoResidencial. */
	private String enderecoResidencial;
	
	/** O atributo bairroResidencial. */
	private String bairroResidencial;
	
	/** O atributo cidadeResidencial. */
	private String cidadeResidencial;
	
	/** O atributo ufResidencial. */
	private String ufResidencial;
	
	/** O atributo cepResidencial. */
	private String cepResidencial;
	
	/** O atributo dddResidencial. */
	private String dddResidencial;
	
	/** O atributo numTelefoneResidencial. */
	private String numTelefoneResidencial;
	
	/** O atributo ramalResidencial. */
	private String ramalResidencial;
	
	/** O atributo enderecoComercial. */
	private String enderecoComercial;
	
	/** O atributo bairroComercial. */
	private String bairroComercial;
	
	/** O atributo cidadeComercial. */
	private String cidadeComercial;
	
	/** O atributo ufComercial. */
	private String ufComercial;
	
	/** O atributo cepComercial. */
	private String cepComercial;
	
	/** O atributo dddComercial. */
	private String dddComercial;
	
	/** O atributo numTelefoneComercial. */
	private String numTelefoneComercial;
	
	/** O atributo ramalcomercial. */
	private String ramalcomercial;
	
	/** O atributo descApelidoPessoa. */
	private String descApelidoPessoa;
	
	/** O atributo numCgcCpf. */
	private String numCgcCpf;
	
	/** O atributo sNumPessoa. */
	private String sNumPessoa;
	
	/** O atributo numInscEstadual. */
	private String numInscEstadual;
	
	/** O atributo modoLanc. */
	private String modoLanc;
	
	/** O atributo situacao. */
	private String situacao;
	
	/** O atributo descCondicaoAssociacao. */
	private String descCondicaoAssociacao;
	
	/** O atributo numParcelamento. */
	private Integer numParcelamento;
	
	/** O atributo numParcela. */
	private Integer numParcela;
	
	/** O atributo codEstadoCivil. */
	private Integer codEstadoCivil;
	
	/** O atributo codTipoSexo. */
	private Integer codTipoSexo;
	
	/** O atributo codTipoEndereco. */
	private Integer codTipoEndereco;
	
	/** O atributo codPfPj. */
	private Integer codPfPj;
	
	/** O atributo codProfissao. */
	private Long codProfissao;
	
	/** O atributo numPessoa. */
	private Long numPessoa;
	
	/** O atributo dataNascimento. */
	private DateTime dataNascimento;
	
	/** O atributo dataEmissaoRG. */
	private DateTime dataEmissaoRG;
	
	/** O atributo dataConstituicao. */
	private DateTime dataConstituicao;
	
	/** O atributo dataVencParcela. */
	private DateTime dataVencParcela;
	
	/** O atributo valorSaldoAtuInteg. */
	private BigDecimal valorSaldoAtuInteg;
	
	/** O atributo valorLancInteg. */
	private BigDecimal valorLancInteg;
	
	/** O atributo valorSaldoAtuSubsc. */
	private BigDecimal valorSaldoAtuSubsc;
	
	/** O atributo valorLancSubsc. */
	private BigDecimal valorLancSubsc;
	
	/** O atributo valorParcela. */
	private BigDecimal valorParcela;
	
	/** O atributo bolEnvioCorrespondencia. */
	private Boolean bolEnvioCorrespondencia;
	
	/** O atributo descEstadoCivil. */
	private String descEstadoCivil;
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
	 * @return the descNacionalidade
	 */
	public String getDescNacionalidade() {
		return descNacionalidade;
	}
	/**
	 * @param descNacionalidade the descNacionalidade to set
	 */
	public void setDescNacionalidade(String descNacionalidade) {
		this.descNacionalidade = descNacionalidade;
	}
	/**
	 * @return the descNaturalidade
	 */
	public String getDescNaturalidade() {
		return descNaturalidade;
	}
	/**
	 * @param descNaturalidade the descNaturalidade to set
	 */
	public void setDescNaturalidade(String descNaturalidade) {
		this.descNaturalidade = descNaturalidade;
	}
	/**
	 * @return the numRG
	 */
	public String getNumRG() {
		return numRG;
	}
	/**
	 * @param numRG the numRG to set
	 */
	public void setNumRG(String numRG) {
		this.numRG = numRG;
	}
	/**
	 * @return the descOrgaoExpRG
	 */
	public String getDescOrgaoExpRG() {
		return descOrgaoExpRG;
	}
	/**
	 * @param descOrgaoExpRG the descOrgaoExpRG to set
	 */
	public void setDescOrgaoExpRG(String descOrgaoExpRG) {
		this.descOrgaoExpRG = descOrgaoExpRG;
	}
	/**
	 * @return the descUfOrgExpRG
	 */
	public String getDescUfOrgExpRG() {
		return descUfOrgExpRG;
	}
	/**
	 * @param descUfOrgExpRG the descUfOrgExpRG to set
	 */
	public void setDescUfOrgExpRG(String descUfOrgExpRG) {
		this.descUfOrgExpRG = descUfOrgExpRG;
	}
	/**
	 * @return the desclistaitem
	 */
	public String getDesclistaitem() {
		return desclistaitem;
	}
	/**
	 * @param desclistaitem the desclistaitem to set
	 */
	public void setDesclistaitem(String desclistaitem) {
		this.desclistaitem = desclistaitem;
	}
	/**
	 * @return the enderecoResidencial
	 */
	public String getEnderecoResidencial() {
		return enderecoResidencial;
	}
	/**
	 * @param enderecoResidencial the enderecoResidencial to set
	 */
	public void setEnderecoResidencial(String enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}
	/**
	 * @return the bairroResidencial
	 */
	public String getBairroResidencial() {
		return bairroResidencial;
	}
	/**
	 * @param bairroResidencial the bairroResidencial to set
	 */
	public void setBairroResidencial(String bairroResidencial) {
		this.bairroResidencial = bairroResidencial;
	}
	/**
	 * @return the cidadeResidencial
	 */
	public String getCidadeResidencial() {
		return cidadeResidencial;
	}
	/**
	 * @param cidadeResidencial the cidadeResidencial to set
	 */
	public void setCidadeResidencial(String cidadeResidencial) {
		this.cidadeResidencial = cidadeResidencial;
	}
	/**
	 * @return the ufResidencial
	 */
	public String getUfResidencial() {
		return ufResidencial;
	}
	/**
	 * @param ufResidencial the ufResidencial to set
	 */
	public void setUfResidencial(String ufResidencial) {
		this.ufResidencial = ufResidencial;
	}
	/**
	 * @return the cepResidencial
	 */
	public String getCepResidencial() {
		return cepResidencial;
	}
	/**
	 * @param cepResidencial the cepResidencial to set
	 */
	public void setCepResidencial(String cepResidencial) {
		this.cepResidencial = cepResidencial;
	}
	/**
	 * @return the dddResidencial
	 */
	public String getDddResidencial() {
		return dddResidencial;
	}
	/**
	 * @param dddResidencial the dddResidencial to set
	 */
	public void setDddResidencial(String dddResidencial) {
		this.dddResidencial = dddResidencial;
	}
	/**
	 * @return the numTelefoneResidencial
	 */
	public String getNumTelefoneResidencial() {
		return numTelefoneResidencial;
	}
	/**
	 * @param numTelefoneResidencial the numTelefoneResidencial to set
	 */
	public void setNumTelefoneResidencial(String numTelefoneResidencial) {
		this.numTelefoneResidencial = numTelefoneResidencial;
	}
	/**
	 * @return the ramalResidencial
	 */
	public String getRamalResidencial() {
		return ramalResidencial;
	}
	/**
	 * @param ramalResidencial the ramalResidencial to set
	 */
	public void setRamalResidencial(String ramalResidencial) {
		this.ramalResidencial = ramalResidencial;
	}
	/**
	 * @return the enderecoComercial
	 */
	public String getEnderecoComercial() {
		return enderecoComercial;
	}
	/**
	 * @param enderecoComercial the enderecoComercial to set
	 */
	public void setEnderecoComercial(String enderecoComercial) {
		this.enderecoComercial = enderecoComercial;
	}
	/**
	 * @return the bairroComercial
	 */
	public String getBairroComercial() {
		return bairroComercial;
	}
	/**
	 * @param bairroComercial the bairroComercial to set
	 */
	public void setBairroComercial(String bairroComercial) {
		this.bairroComercial = bairroComercial;
	}
	/**
	 * @return the cidadeComercial
	 */
	public String getCidadeComercial() {
		return cidadeComercial;
	}
	/**
	 * @param cidadeComercial the cidadeComercial to set
	 */
	public void setCidadeComercial(String cidadeComercial) {
		this.cidadeComercial = cidadeComercial;
	}
	/**
	 * @return the ufComercial
	 */
	public String getUfComercial() {
		return ufComercial;
	}
	/**
	 * @param ufComercial the ufComercial to set
	 */
	public void setUfComercial(String ufComercial) {
		this.ufComercial = ufComercial;
	}
	/**
	 * @return the cepComercial
	 */
	public String getCepComercial() {
		return cepComercial;
	}
	/**
	 * @param cepComercial the cepComercial to set
	 */
	public void setCepComercial(String cepComercial) {
		this.cepComercial = cepComercial;
	}
	/**
	 * @return the dddComercial
	 */
	public String getDddComercial() {
		return dddComercial;
	}
	/**
	 * @param dddComercial the dddComercial to set
	 */
	public void setDddComercial(String dddComercial) {
		this.dddComercial = dddComercial;
	}
	/**
	 * @return the numTelefoneComercial
	 */
	public String getNumTelefoneComercial() {
		return numTelefoneComercial;
	}
	/**
	 * @param numTelefoneComercial the numTelefoneComercial to set
	 */
	public void setNumTelefoneComercial(String numTelefoneComercial) {
		this.numTelefoneComercial = numTelefoneComercial;
	}
	/**
	 * @return the ramalcomercial
	 */
	public String getRamalcomercial() {
		return ramalcomercial;
	}
	/**
	 * @param ramalcomercial the ramalcomercial to set
	 */
	public void setRamalcomercial(String ramalcomercial) {
		this.ramalcomercial = ramalcomercial;
	}
	/**
	 * @return the descApelidoPessoa
	 */
	public String getDescApelidoPessoa() {
		return descApelidoPessoa;
	}
	/**
	 * @param descApelidoPessoa the descApelidoPessoa to set
	 */
	public void setDescApelidoPessoa(String descApelidoPessoa) {
		this.descApelidoPessoa = descApelidoPessoa;
	}
	/**
	 * @return the numCgcCpf
	 */
	public String getNumCgcCpf() {
		return numCgcCpf;
	}
	/**
	 * @param numCgcCpf the numCgcCpf to set
	 */
	public void setNumCgcCpf(String numCgcCpf) {
		this.numCgcCpf = numCgcCpf;
	}
	/**
	 * @return the sNumPessoa
	 */
	public String getsNumPessoa() {
		return sNumPessoa;
	}
	/**
	 * @param sNumPessoa the sNumPessoa to set
	 */
	public void setsNumPessoa(String sNumPessoa) {
		this.sNumPessoa = sNumPessoa;
	}
	/**
	 * @return the numInscEstadual
	 */
	public String getNumInscEstadual() {
		return numInscEstadual;
	}
	/**
	 * @param numInscEstadual the numInscEstadual to set
	 */
	public void setNumInscEstadual(String numInscEstadual) {
		this.numInscEstadual = numInscEstadual;
	}
	/**
	 * @return the modoLanc
	 */
	public String getModoLanc() {
		return modoLanc;
	}
	/**
	 * @param modoLanc the modoLanc to set
	 */
	public void setModoLanc(String modoLanc) {
		this.modoLanc = modoLanc;
	}
	/**
	 * @return the situacao
	 */
	public String getSituacao() {
		return situacao;
	}
	/**
	 * @param situacao the situacao to set
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	/**
	 * @return the descCondicaoAssociacao
	 */
	public String getDescCondicaoAssociacao() {
		return descCondicaoAssociacao;
	}
	/**
	 * @param descCondicaoAssociacao the descCondicaoAssociacao to set
	 */
	public void setDescCondicaoAssociacao(String descCondicaoAssociacao) {
		this.descCondicaoAssociacao = descCondicaoAssociacao;
	}
	/**
	 * @return the numParcelamento
	 */
	public Integer getNumParcelamento() {
		return numParcelamento;
	}
	/**
	 * @param numParcelamento the numParcelamento to set
	 */
	public void setNumParcelamento(Integer numParcelamento) {
		this.numParcelamento = numParcelamento;
	}
	/**
	 * @return the numParcela
	 */
	public Integer getNumParcela() {
		return numParcela;
	}
	/**
	 * @param numParcela the numParcela to set
	 */
	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}
	/**
	 * @return the codEstadoCivil
	 */
	public Integer getCodEstadoCivil() {
		return codEstadoCivil;
	}
	/**
	 * @param codEstadoCivil the codEstadoCivil to set
	 */
	public void setCodEstadoCivil(Integer codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}
	/**
	 * @return the codTipoSexo
	 */
	public Integer getCodTipoSexo() {
		return codTipoSexo;
	}
	/**
	 * @param codTipoSexo the codTipoSexo to set
	 */
	public void setCodTipoSexo(Integer codTipoSexo) {
		this.codTipoSexo = codTipoSexo;
	}
	/**
	 * @return the codTipoEndereco
	 */
	public Integer getCodTipoEndereco() {
		return codTipoEndereco;
	}
	/**
	 * @param codTipoEndereco the codTipoEndereco to set
	 */
	public void setCodTipoEndereco(Integer codTipoEndereco) {
		this.codTipoEndereco = codTipoEndereco;
	}
	/**
	 * @return the codPfPj
	 */
	public Integer getCodPfPj() {
		return codPfPj;
	}
	/**
	 * @param codPfPj the codPfPj to set
	 */
	public void setCodPfPj(Integer codPfPj) {
		this.codPfPj = codPfPj;
	}
	/**
	 * @return the codProfissao
	 */
	public Long getCodProfissao() {
		return codProfissao;
	}
	/**
	 * @param codProfissao the codProfissao to set
	 */
	public void setCodProfissao(Long codProfissao) {
		this.codProfissao = codProfissao;
	}
	/**
	 * @return the numPessoa
	 */
	public Long getNumPessoa() {
		return numPessoa;
	}
	/**
	 * @param numPessoa the numPessoa to set
	 */
	public void setNumPessoa(Long numPessoa) {
		this.numPessoa = numPessoa;
	}
	/**
	 * @return the dataNascimento
	 */
	public DateTime getDataNascimento() {
		return dataNascimento;
	}
	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento(DateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	/**
	 * @return the dataEmissaoRG
	 */
	public DateTime getDataEmissaoRG() {
		return dataEmissaoRG;
	}
	/**
	 * @param dataEmissaoRG the dataEmissaoRG to set
	 */
	public void setDataEmissaoRG(DateTime dataEmissaoRG) {
		this.dataEmissaoRG = dataEmissaoRG;
	}
	/**
	 * @return the dataConstituicao
	 */
	public DateTime getDataConstituicao() {
		return dataConstituicao;
	}
	/**
	 * @param dataConstituicao the dataConstituicao to set
	 */
	public void setDataConstituicao(DateTime dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}
	/**
	 * @return the dataVencParcela
	 */
	public DateTime getDataVencParcela() {
		return dataVencParcela;
	}
	/**
	 * @param dataVencParcela the dataVencParcela to set
	 */
	public void setDataVencParcela(DateTime dataVencParcela) {
		this.dataVencParcela = dataVencParcela;
	}
	/**
	 * @return the valorSaldoAtuInteg
	 */
	public BigDecimal getValorSaldoAtuInteg() {
		return valorSaldoAtuInteg;
	}
	/**
	 * @param valorSaldoAtuInteg the valorSaldoAtuInteg to set
	 */
	public void setValorSaldoAtuInteg(BigDecimal valorSaldoAtuInteg) {
		this.valorSaldoAtuInteg = valorSaldoAtuInteg;
	}
	/**
	 * @return the valorLancInteg
	 */
	public BigDecimal getValorLancInteg() {
		return valorLancInteg;
	}
	/**
	 * @param valorLancInteg the valorLancInteg to set
	 */
	public void setValorLancInteg(BigDecimal valorLancInteg) {
		this.valorLancInteg = valorLancInteg;
	}
	/**
	 * @return the valorSaldoAtuSubsc
	 */
	public BigDecimal getValorSaldoAtuSubsc() {
		return valorSaldoAtuSubsc;
	}
	/**
	 * @param valorSaldoAtuSubsc the valorSaldoAtuSubsc to set
	 */
	public void setValorSaldoAtuSubsc(BigDecimal valorSaldoAtuSubsc) {
		this.valorSaldoAtuSubsc = valorSaldoAtuSubsc;
	}
	/**
	 * @return the valorLancSubsc
	 */
	public BigDecimal getValorLancSubsc() {
		return valorLancSubsc;
	}
	/**
	 * @param valorLancSubsc the valorLancSubsc to set
	 */
	public void setValorLancSubsc(BigDecimal valorLancSubsc) {
		this.valorLancSubsc = valorLancSubsc;
	}
	/**
	 * @return the valorParcela
	 */
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	/**
	 * @param valorParcela the valorParcela to set
	 */
	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	/**
	 * @return the bolEnvioCorrespondencia
	 */
	public Boolean getBolEnvioCorrespondencia() {
		return bolEnvioCorrespondencia;
	}
	/**
	 * @param bolEnvioCorrespondencia the bolEnvioCorrespondencia to set
	 */
	public void setBolEnvioCorrespondencia(Boolean bolEnvioCorrespondencia) {
		this.bolEnvioCorrespondencia = bolEnvioCorrespondencia;
	}
	/**
	 * @return the descEstadoCivil
	 */
	public String getDescEstadoCivil() {
		return descEstadoCivil;
	}
	/**
	 * @param descEstadoCivil the descEstadoCivil to set
	 */
	public void setDescEstadoCivil(String descEstadoCivil) {
		this.descEstadoCivil = descEstadoCivil;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}