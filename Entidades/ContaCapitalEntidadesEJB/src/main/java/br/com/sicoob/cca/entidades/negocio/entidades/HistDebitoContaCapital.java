/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * Historico de Debito Conta Capital
 * @author marco.nascimento
 * @since 21/09/2017
 */
@Entity
@Table(name = "HISTDEBITOCONTACAPITAL", schema = "cca")
public class HistDebitoContaCapital extends ContaCapitalEntidade<HistDebitoContaCapitalPK> {

	/**
	 * PK
	 */
	@EmbeddedId
	private HistDebitoContaCapitalPK id;
	
	/**
	 * Identificador da Conta Capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDCONTACAPITAL", nullable = false)
	private ContaCapital contaCapital;
	
	/**
	 * Identificador da instituição no SCI
	 */
	@Column(name = "IDINSTITUICAO", nullable = false)
	private Integer idInstituicao;
	
	/**
	 * Identificador do tipo de integralização.
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOINTEGRALIZACAO", nullable = false)
	private TipoIntegralizacao tipoIntegralizacao;
	
	/**
	 * Identificador do tipo de débito.
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOVALORDEBITO", nullable = false)
	private TipoValorDebito tipoValorDebito;
	
	/**
	 * Identificador do período.
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOPERIODODEBITO", nullable = false)
	private TipoPeriodoDebito tipoPeriodoDebito;
	
	/**
	 * Identificador do usuario
	 */
	@Column(name = "IDUSUARIO", length = 40, nullable = false)
	private String idUsuario;
	
	/**
	 * Número para o tipo de período do débito.
	 */
	@Column(name = "NUMPERIODO", nullable = false)
	private Short numPeriodo;
	
	/**
	 * Valor a ser debitado.
	 */
	@Column(name = "VALORDEBITO", nullable = false)
	private BigDecimal valorDebito;
	
	/**
	 * Data de vencimento para o débito.
	 */
	@Column(name = "DATAVENCIMENTODEBITO", nullable = false)
	private DateTimeDB dataVencimentoDebito;
	
	/**
	 * Número da conta corrente.
	 */
	@Column(name = "NUMCONTACORRENTE")
	private Long numContaCorrente;
	
	/**
	 * Matrícula do funcionário.
	 */
	@Column(name = "DESCMATRICULAFUNC", length = 40)
	private String descricaoMatriculaFunc;
	
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
	 * @return the tipoValorDebito
	 */
	public TipoValorDebito getTipoValorDebito() {
		return tipoValorDebito;
	}

	/**
	 * @param tipoValorDebito the tipoValorDebito to set
	 */
	public void setTipoValorDebito(TipoValorDebito tipoValorDebito) {
		this.tipoValorDebito = tipoValorDebito;
	}

	/**
	 * @return the tipoPeriodoDebito
	 */
	public TipoPeriodoDebito getTipoPeriodoDebito() {
		return tipoPeriodoDebito;
	}

	/**
	 * @param tipoPeriodoDebito the tipoPeriodoDebito to set
	 */
	public void setTipoPeriodoDebito(TipoPeriodoDebito tipoPeriodoDebito) {
		this.tipoPeriodoDebito = tipoPeriodoDebito;
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
	 * @return the numPeriodo
	 */
	public Short getNumPeriodo() {
		return numPeriodo;
	}

	/**
	 * @param numPeriodo the numPeriodo to set
	 */
	public void setNumPeriodo(Short numPeriodo) {
		this.numPeriodo = numPeriodo;
	}

	/**
	 * @return the valorDebito
	 */
	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito the valorDebito to set
	 */
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	/**
	 * @return the dataVencimentoDebito
	 */
	public DateTimeDB getDataVencimentoDebito() {
		return dataVencimentoDebito;
	}

	/**
	 * @param dataVencimentoDebito the dataVencimentoDebito to set
	 */
	public void setDataVencimentoDebito(DateTimeDB dataVencimentoDebito) {
		this.dataVencimentoDebito = dataVencimentoDebito;
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
	 * @return the descricaoMatriculaFunc
	 */
	public String getDescricaoMatriculaFunc() {
		return descricaoMatriculaFunc;
	}

	/**
	 * @param descricaoMatriculaFunc the descricaoMatriculaFunc to set
	 */
	public void setDescricaoMatriculaFunc(String descricaoMatriculaFunc) {
		this.descricaoMatriculaFunc = descricaoMatriculaFunc;
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
	 * @param id the id to set
	 */
	public void setId(HistDebitoContaCapitalPK id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public HistDebitoContaCapitalPK getId() {
		return id;
	}
}