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
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * Debito conta capital
 * @author marco.nascimento
 * @since 22/05/2014
 */
@Entity
@Table(name = "DEBITOCONTACAPITAL", schema = "cca")
public class DebitoContaCapital extends ContaCapitalEntidade<Long> {

	/**
	 * Identificador do débito indeterminado da Conta Capital
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDDEBITOCONTACAPITAL")
	private Long id;
	
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
	 * Historico
	 */
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDDEBITOCONTACAPITAL", referencedColumnName = "IDDEBITOCONTACAPITAL")
	private List<HistDebitoContaCapital> historico = new ArrayList<HistDebitoContaCapital>(0);
	
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
	 * Data e hora da efetivação do débito.
	 */
	@Column(name = "DATAHORADEBITO", nullable = false)
	private DateTimeDB horaVencimentoDebito;
	
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
	 * @return the horaVencimentoDebito
	 */
	public DateTimeDB getHoraVencimentoDebito() {
		return horaVencimentoDebito;
	}

	/**
	 * @param horaVencimentoDebito the horaVencimentoDebito to set
	 */
	public void setHoraVencimentoDebito(DateTimeDB horaVencimentoDebito) {
		this.horaVencimentoDebito = horaVencimentoDebito;
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
	 * @return the historico
	 */
	public List<HistDebitoContaCapital> getHistorico() {
		return historico;
	}

	/**
	 * @param historico the historico to set
	 */
	public void setHistorico(List<HistDebitoContaCapital> historico) {
		this.historico = historico;
	}
}