/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe CadastroContaCapitalRenDTO.
 */
public class CadastroContaCapitalRenDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1807796706180891137L;
	
	/** O atributo idContaCapital. */
	private Integer idContaCapital;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo nomePessoa. */
	private String nomePessoa;
	
	/** O atributo nomeCompleto. */
	private String nomeCompleto;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo idPessoaLegado. */
	private Integer idPessoaLegado;
	
	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** O atributo numContaCapitalGerada. */
	private Integer numContaCapitalGerada;
	
	/** O atributo vlrSubs. */
	private BigDecimal vlrSubs;
	
	/** O atributo vlrInteg. */
	private BigDecimal vlrInteg;
	
	/** O atributo qtdParcelas. */
	private Integer qtdParcelas;
	
	/** O atributo vlrParcelas. */
	private BigDecimal vlrParcelas;
	
	/** O atributo diaDebito. */
	private Integer diaDebito;
	
	/** O atributo tipoInteg. */
	private Integer tipoInteg;
	
	/** O atributo numCco. */
	private Long numCco;
	
	/** O atributo idSituacaoCadastro. */
	private Integer idSituacaoCadastro;
	
	/** O atributo idSituacaoContaCapital. */
	private Integer idSituacaoContaCapital;
	
	/** O atributo descSituacaoAprovacaoCapital. */
	private String descSituacaoAprovacaoCapital;
	
	/** O atributo descSituacaoContaCapital. */
	private String descSituacaoContaCapital;
	
	/** O atributo matriculaEscolhida. */
	private Boolean matriculaEscolhida;
	
	/** O atributo dataHoraAtualizacao. */
	private DateTimeDB dataHoraAtualizacao;
	
	/** O atributo observacao. */
	private String observacao;
	
	//GFT
	/** O atributo idAtividade. */
	private Integer idAtividade;
	
	/** O atributo idOcorrenciaAtividade. */
	private Integer idOcorrenciaAtividade;
	
	/** O atributo idsContaCapital. */
	private List<Integer> idsContaCapital;
	
	/** O atributo dataMatricula. */
	private DateTimeDB dataMatricula;
	
	/** O atributo numCoop. */
	private Integer numCoop;
	
	/**
	 * @return the idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	/**
	 * @param idContaCapital the idContaCapital to set
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
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
	 * @return the nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}
	/**
	 * @param nomePessoa the nomePessoa to set
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
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
	 * @return the vlrSubs
	 */
	public BigDecimal getVlrSubs() {
		return vlrSubs;
	}
	/**
	 * @param vlrSubs the vlrSubs to set
	 */
	public void setVlrSubs(BigDecimal vlrSubs) {
		this.vlrSubs = vlrSubs;
	}
	/**
	 * @return the vlrInteg
	 */
	public BigDecimal getVlrInteg() {
		return vlrInteg;
	}
	/**
	 * @param vlrInteg the vlrInteg to set
	 */
	public void setVlrInteg(BigDecimal vlrInteg) {
		this.vlrInteg = vlrInteg;
	}
	/**
	 * @return the qtdParcelas
	 */
	public Integer getQtdParcelas() {
		return qtdParcelas;
	}
	/**
	 * @param qtdParcelas the qtdParcelas to set
	 */
	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}
	/**
	 * @return the vlrParcelas
	 */
	public BigDecimal getVlrParcelas() {
		return vlrParcelas;
	}
	/**
	 * @param vlrParcelas the vlrParcelas to set
	 */
	public void setVlrParcelas(BigDecimal vlrParcelas) {
		this.vlrParcelas = vlrParcelas;
	}
	/**
	 * @return the diaDebito
	 */
	public Integer getDiaDebito() {
		return diaDebito;
	}
	/**
	 * @param diaDebito the diaDebito to set
	 */
	public void setDiaDebito(Integer diaDebito) {
		this.diaDebito = diaDebito;
	}
	/**
	 * @return the tipoInteg
	 */
	public Integer getTipoInteg() {
		return tipoInteg;
	}
	/**
	 * @param tipoInteg the tipoInteg to set
	 */
	public void setTipoInteg(Integer tipoInteg) {
		this.tipoInteg = tipoInteg;
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
	 * @return the descSituacaoAprovacaoCapital
	 */
	public String getDescSituacaoAprovacaoCapital() {
		return descSituacaoAprovacaoCapital;
	}
	/**
	 * @param descSituacaoAprovacaoCapital the descSituacaoAprovacaoCapital to set
	 */
	public void setDescSituacaoAprovacaoCapital(String descSituacaoAprovacaoCapital) {
		this.descSituacaoAprovacaoCapital = descSituacaoAprovacaoCapital;
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
	 * @return the matriculaEscolhida
	 */
	public Boolean getMatriculaEscolhida() {
		return matriculaEscolhida;
	}
	/**
	 * @param matriculaEscolhida the matriculaEscolhida to set
	 */
	public void setMatriculaEscolhida(Boolean matriculaEscolhida) {
		this.matriculaEscolhida = matriculaEscolhida;
	}
	/**
	 * @return the numContaCapitalGerada
	 */
	public Integer getNumContaCapitalGerada() {
		return numContaCapitalGerada;
	}
	/**
	 * @param numContaCapitalGerada the numContaCapitalGerada to set
	 */
	public void setNumContaCapitalGerada(Integer numContaCapitalGerada) {
		this.numContaCapitalGerada = numContaCapitalGerada;
	}
	/**
	 * @return the dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}
	/**
	 * @param dataHoraAtualizacao the dataHoraAtualizacao to set
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
	/**
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	/**
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	/**
	 * @return the idsContaCapital
	 */
	public List<Integer> getIdsContaCapital() {
		return idsContaCapital;
	}
	/**
	 * @param idsContaCapital the idsContaCapital to set
	 */
	public void setIdsContaCapital(List<Integer> idsContaCapital) {
		this.idsContaCapital = idsContaCapital;
	}
	/**
	 * @return the idAtividade
	 */
	public Integer getIdAtividade() {
		return idAtividade;
	}
	/**
	 * @param idAtividade the idAtividade to set
	 */
	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
	}
	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}
	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	/**
	 * @return the numCco
	 */
	public Long getNumCco() {
		return numCco;
	}
	/**
	 * @param numCco the numCco to set
	 */
	public void setNumCco(Long numCco) {
		this.numCco = numCco;
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
	public DateTimeDB getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(DateTimeDB dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	/**
	 * @return the idOcorrenciaAtividade
	 */
	public Integer getIdOcorrenciaAtividade() {
		return idOcorrenciaAtividade;
	}
	/**
	 * @param idOcorrenciaAtividade the idOcorrenciaAtividade to set
	 */
	public void setIdOcorrenciaAtividade(Integer idOcorrenciaAtividade) {
		this.idOcorrenciaAtividade = idOcorrenciaAtividade;
	}
	/**
	 * @return the numCoop
	 */
	public Integer getNumCoop() {
		return numCoop;
	}
	/**
	 * @param numCoop the numCoop to set
	 */
	public void setNumCoop(Integer numCoop) {
		this.numCoop = numCoop;
	}
}