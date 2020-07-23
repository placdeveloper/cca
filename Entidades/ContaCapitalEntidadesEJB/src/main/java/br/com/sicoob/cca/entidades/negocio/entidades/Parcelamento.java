/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * Parcelamento Conta Capital
 * @author marco.nascimento
 * @since 22/05/2014
 */
@Entity
@Table(name = "PARCELAMENTOCONTACAPITAL", schema = "cca")
public class Parcelamento extends ContaCapitalEntidade<Long> {

	/**
	 * Identificado do parcelemento de conta capital
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPARCELAMENTOCONTACAPITAL")
	private Long id;
	
	/**
	 * Identificador da Conta Capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDCONTACAPITAL")
	private ContaCapital contaCapital;
	
	/**
	 * Número do parcelamento.
	 */
	@Column(name = "NUMPARCELAMENTO")
	private Short numParcelamento;
	
	/**
	 * Número da parcela.
	 */
	@Column(name = "NUMPARCELA")
	private Short numParcela;
	
	/**
	 * Identificador do tipo de parcelamento.
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOPARCELAMENTO")
	private TipoParcelamento tipoParcelamento;
	
	/**
	 * identificador do motivo da devolução.
	 */
	@ManyToOne
	@JoinColumn(name = "IDMOTIVODEVOLUCAO")
	private MotivoDevolucao motivoDevolucao;
	
	/**
	 * Identificador da situação do parcelamento.
	 */
	@ManyToOne
	@JoinColumn(name = "IDSITUACAOPARCELAMENTO")
	private SituacaoParcelamento situacaoParcelamento;
	
	/**
	 * Identificador do tipo de integralização.
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOINTEGRALIZACAO")
	private TipoIntegralizacao tipoIntegralizacao;
	
	/**
	 * Data de vencimento da parcela.
	 */
	@Column(name = "DATAVENCPARCELA", nullable = false)
	private DateTimeDB dataVencimento;
	
	/**
	 * Data da situação da parcela.
	 */
	@Column(name = "DATASITUACAOPARCELA", nullable = false)
	private DateTimeDB dataSituacao;
	
	/**
	 * Valor da parcela.
	 */
	@Column(name = "VALORPARCELA", nullable = false)
	private BigDecimal valor;
	
	/**
	 * Número da conta corrente.
	 */
	@Column(name = "NUMCONTACORRENTE")
	private Long numContaCorrente;
	
	/**
	 * Matrícula do funcionário.
	 */
	@Column(name = "DESCMATRICULAFUNC", length = 40)
	private String matriculaFuncionario;
	
	/**
	 * Observação
	 */
	@Column(name = "DESCOBSERVACAO", length = 200)
	private String observacao;
	
	/**
	 * Código do canal de comunicação
	 */
	@Column(name = "IDCANAL")
	private Integer codCanal;
	
	
	/**
	 * Identificador da instituição no SCI.
	 */
	@Column(name = "IDINSTITUICAO")
	private Integer idInstituicao;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the contaCapital
	 */
	public ContaCapital getContaCapital() {
		return contaCapital;
	}

	/**
	 * @param contaCapital the contaCapital to set
	 */
	public void setContaCapital(ContaCapital contaCapital) {
		this.contaCapital = contaCapital;
	}

	/**
	 * @return the numParcelamento
	 */
	public Short getNumParcelamento() {
		return numParcelamento;
	}

	/**
	 * @param numParcelamento the numParcelamento to set
	 */
	public void setNumParcelamento(Short numParcelamento) {
		this.numParcelamento = numParcelamento;
	}

	/**
	 * @return the numParcela
	 */
	public Short getNumParcela() {
		return numParcela;
	}

	/**
	 * @param numParcela the numParcela to set
	 */
	public void setNumParcela(Short numParcela) {
		this.numParcela = numParcela;
	}

	/**
	 * @return the tipoParcelamento
	 */
	public TipoParcelamento getTipoParcelamento() {
		return tipoParcelamento;
	}

	/**
	 * @param tipoParcelamento the tipoParcelamento to set
	 */
	public void setTipoParcelamento(TipoParcelamento tipoParcelamento) {
		this.tipoParcelamento = tipoParcelamento;
	}

	/**
	 * @return the motivoDevolucao
	 */
	public MotivoDevolucao getMotivoDevolucao() {
		return motivoDevolucao;
	}

	/**
	 * @param motivoDevolucao the motivoDevolucao to set
	 */
	public void setMotivoDevolucao(MotivoDevolucao motivoDevolucao) {
		this.motivoDevolucao = motivoDevolucao;
	}

	/**
	 * @return the situacaoParcelamento
	 */
	public SituacaoParcelamento getSituacaoParcelamento() {
		return situacaoParcelamento;
	}

	/**
	 * @param situacaoParcelamento the situacaoParcelamento to set
	 */
	public void setSituacaoParcelamento(SituacaoParcelamento situacaoParcelamento) {
		this.situacaoParcelamento = situacaoParcelamento;
	}

	/**
	 * @return the tipoIntegralizacao
	 */
	public TipoIntegralizacao getTipoIntegralizacao() {
		return tipoIntegralizacao;
	}

	/**
	 * @param tipoIntegralizacao the tipoIntegralizacao to set
	 */
	public void setTipoIntegralizacao(TipoIntegralizacao tipoIntegralizacao) {
		this.tipoIntegralizacao = tipoIntegralizacao;
	}

	/**
	 * @return the dataVencimento
	 */
	public DateTimeDB getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento(DateTimeDB dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	/**
	 * @return the dataSituacao
	 */
	public DateTimeDB getDataSituacao() {
		return dataSituacao;
	}

	/**
	 * @param dataSituacao the dataSituacao to set
	 */
	public void setDataSituacao(DateTimeDB dataSituacao) {
		this.dataSituacao = dataSituacao;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the matriculaFuncionario
	 */
	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	/**
	 * @param matriculaFuncionario the matriculaFuncionario to set
	 */
	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
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
	 * @return the numContaCorrente
	 */
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}

	/**
	 * @param numContaCorrente the numContaCorrente to set
	 */
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	/**
	 * @return the codCanal
	 */
	public Integer getCodCanal() {
		return codCanal;
	}

	/**
	 * @param codCanal the codCanal to set
	 */
	public void setCodCanal(Integer codCanal) {
		this.codCanal = codCanal;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
}