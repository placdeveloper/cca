/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;

/**
 * Conta capital
 * @author marco.nascimento
 * @since 15/05/2014
 */
@Entity
@Table(name = "CONTACAPITAL", schema = "cca")
public class ContaCapital extends ContaCapitalEntidade<Integer> {

	/**
	 * PK
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCONTACAPITAL")
	private Integer id;
	
	/**
	 * Identificador da instituição no SCI 
	 */
	@Column(name = "IDINSTITUICAO", nullable = false)
	private Integer idInstituicao;
	
	/**
	 * Identificador da situação da Conta Capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDSITUACAOCONTACAPITAL", nullable = false)
	private SituacaoContaCapital situacaoContaCapital;
	
	/**
	 * Identificador da pessoa no Capes
	 */
	@Column(name = "IDPESSOA", nullable = false)
	private Integer idPessoa;
	
	/**
	 * Situacao da proposta
	 */
	@ManyToOne
	@JoinColumn(name = "IDSITUACAOAPROVACAOCAPITAL", nullable = false)
	private SituacaoCadastroProposta  situacaoCadastroProposta;
	
	/**
	 * Observacao na aprovacao da proposta
	 */
	@Column(name = "DESCOBSAPROVACAOCAPITAL", nullable = true, length = 400)
	private String descObsAprovacao;
	
	/**
	 * Número da Conta Capital do associado na Instituição
	 */
	@Column(name = "NUMCONTACAPITAL", nullable = false)
	private Integer numContaCapital;
	
	/**
	 * Data da matrícula do associado na instituição.
	 */
	@Column(name = "DATAMATRICULA", nullable = false)
	private DateTimeDB dataMatricula;
	
	/**
	 * Valor de Integralização na Conta Capital
	 */
	@Column(name = "VALORSALDOINTEG", nullable = false)
	private BigDecimal valorInteg;
	
	/**
	 * Valor de subscrição na Conta Capital
	 */
	@Column(name = "VALORSALDOSUBSC", nullable = false)
	private BigDecimal valorSubs;
	
	/**
	 * Valor de devolução na Conta Capital
	 */
	@Column(name = "VALORSALDODEVOL", nullable = false)
	private BigDecimal valorDevol;
	
	/**
	 * Valor de bloqueio na Conta Capital
	 */
	@Column(name = "VALORSALDOBLOQ", nullable = false)
	private BigDecimal valorBloq;
	
	/**
	 * Data de saída do associado na instituição.
	 */
	@Column(name = "DATASAIDA")
	private DateTimeDB dataSaida;
	
	/**
	 * Identifica se a matrícula foi escolhida pelo cliente
	 */
	@Column(name = "BOLMATRICULAESCOLHIDA", nullable = false)
	private Boolean matriculaEscolhida;
	
	/**
	 * Identificador do usuario
	 */
	@Column(name = "IDUSUARIO", length = 40, nullable = false)
	private String idUsuario;
	
	/**
	 * Data e hora de atualização do cadastro
	 */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;
	
	/** O atributo historico. */
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn (name = "IDCONTACAPITAL", referencedColumnName = "IDCONTACAPITAL")
	@OrderBy("id.dataHoraAtualizacao DESC")
	private List<HistoricoContaCapital> historico = new ArrayList<HistoricoContaCapital>(0);
	
	/** O atributo documentos. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contaCapital")
	@OrderBy("dataHoraAtualizacao DESC")
	private List<DocumentoCapital> documentos = new ArrayList<DocumentoCapital>(0);
	
	@Column(name = "DESCDADOSDESLIGAMENTO")
	private String descDadosDesligamento;
	
	@Override
	public String toString() {
		return "ContaCapital[id=" + id + ", idInstituicao=" + idInstituicao + ", idPessoa=" + idPessoa + ", numContaCapital=" + numContaCapital + ", idUsuario" + idUsuario + "] " + super.toString();
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the situacaoContaCapital
	 */
	public SituacaoContaCapital getSituacaoContaCapital() {
		return situacaoContaCapital;
	}

	/**
	 * @param situacaoContaCapital the situacaoContaCapital to set
	 */
	public void setSituacaoContaCapital(SituacaoContaCapital situacaoContaCapital) {
		this.situacaoContaCapital = situacaoContaCapital;
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
	 * @return the situacaoCadastroProposta
	 */
	public SituacaoCadastroProposta getSituacaoCadastroProposta() {
		return situacaoCadastroProposta;
	}

	/**
	 * @param situacaoCadastroProposta the situacaoCadastroProposta to set
	 */
	public void setSituacaoCadastroProposta(
			SituacaoCadastroProposta situacaoCadastroProposta) {
		this.situacaoCadastroProposta = situacaoCadastroProposta;
	}

	/**
	 * @return the descObsAprovacao
	 */
	public String getDescObsAprovacao() {
		return descObsAprovacao;
	}

	/**
	 * @param descObsAprovacao the descObsAprovacao to set
	 */
	public void setDescObsAprovacao(String descObsAprovacao) {
		this.descObsAprovacao = descObsAprovacao;
	}

	

	/**
	 * @return the dataMatricula
	 */
	public DateTimeDB getDataMatricula() {
		return dataMatricula;
	}

	/**
	 * @param dataMatricula the dataMatricula to set
	 */
	public void setDataMatricula(DateTimeDB dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	/**
	 * @return the valorInteg
	 */
	public BigDecimal getValorInteg() {
		return valorInteg;
	}

	/**
	 * @param valorInteg the valorInteg to set
	 */
	public void setValorInteg(BigDecimal valorInteg) {
		this.valorInteg = valorInteg;
	}

	/**
	 * @return the valorSubs
	 */
	public BigDecimal getValorSubs() {
		return valorSubs;
	}

	/**
	 * @param valorSubs the valorSubs to set
	 */
	public void setValorSubs(BigDecimal valorSubs) {
		this.valorSubs = valorSubs;
	}

	/**
	 * @return the valorDevol
	 */
	public BigDecimal getValorDevol() {
		return valorDevol;
	}

	/**
	 * @param valorDevol the valorDevol to set
	 */
	public void setValorDevol(BigDecimal valorDevol) {
		this.valorDevol = valorDevol;
	}

	/**
	 * @return the valorBloq
	 */
	public BigDecimal getValorBloq() {
		return valorBloq;
	}

	/**
	 * @param valorBloq the valorBloq to set
	 */
	public void setValorBloq(BigDecimal valorBloq) {
		this.valorBloq = valorBloq;
	}

	/**
	 * @return the dataSaida
	 */
	public DateTimeDB getDataSaida() {
		return dataSaida;
	}

	/**
	 * @param dataSaida the dataSaida to set
	 */
	public void setDataSaida(DateTimeDB dataSaida) {
		this.dataSaida = dataSaida;
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
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
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
	 * @return the historico
	 */
	public List<HistoricoContaCapital> getHistorico() {
		return historico;
	}

	/**
	 * @param historico the historico to set
	 */
	public void setHistorico(List<HistoricoContaCapital> historico) {
		this.historico = historico;
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
	 * @return the documentos
	 */
	public List<DocumentoCapital> getDocumentos() {
		return documentos;
	}

	/**
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(List<DocumentoCapital> documentos) {
		this.documentos = documentos;
	}
	
	/**
	 * Verifica se a conta capital esta com situacao ATIVA
	 * @return
	 */
	public boolean isSituacaoContaCapitalAtiva() {
		return Short.valueOf(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo().shortValue())
					.equals(getSituacaoContaCapital().getId());
	}

	public String getDescDadosDesligamento() {
		return descDadosDesligamento;
	}

	public void setDescDadosDesligamento(String descDadosDesligamento) {
		this.descDadosDesligamento = descDadosDesligamento;
	}
}