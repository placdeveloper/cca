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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * Proposta de subcricao de conta capital
 * @author marco.nascimento
 * @since 25/11/2015
 */
@Entity
@Table(name = "PROPOSTASUBSCRICAO", schema = "cca")
public class PropostaSubscricao extends ContaCapitalEntidade<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6258503227139799562L;

	/**
	 * PK
	 */
	@Id
	@Column(name = "IDCONTACAPITAL")
	private Integer idContaCapital;
	
	/** O atributo tipoIntegralizacao. */
	@ManyToOne
	@JoinColumn(name = "IDTIPOINTEGRALIZACAO", referencedColumnName = "IDTIPOINTEGRALIZACAO")
	private TipoIntegralizacao tipoIntegralizacao;
	
	/** O atributo valorSubsProposta. */
	@Column(name = "VALORSUBSCPROPOSTA", nullable = false)
	private BigDecimal valorSubsProposta;
	
	/** O atributo valorIntegProposta. */
	@Column(name = "VALORINTEGPROPOSTA", nullable = false)
	private BigDecimal valorIntegProposta;
	
	/** O atributo qtdParcelaProposta. */
	@Column(name = "QTDPARCELAPROPOSTA", nullable = false)
	private Integer qtdParcelaProposta;
	
	/** O atributo valorParcelaProposta. */
	@Column(name = "VALORPARCELAPROPOSTA", nullable = false)
	private BigDecimal valorParcelaProposta;
	
	/** O atributo diaDebitoProposta. */
	@Column(name = "DIADEBITOPROPOSTA", nullable = false)
	private Integer diaDebitoProposta;
	
	/** O atributo numContaCorrente. */
	@Column(name = "NUMCONTACORRENTE", nullable = true)
	private Long numContaCorrente;
	
	/** O atributo idUsuario. */
	@Column(name = "IDUSUARIO", length = 40, nullable = false)
	private String idUsuario;
	
	/** O atributo dataHoraAtualizacao. */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;

	/** O atributo bolSubscricaoProposta. */
	@Column(name = "BOLSUBSCRICAOPROPOSTA", nullable = false)
	private Integer bolSubscricaoProposta;
	
	/** O atributo historico. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn (name = "IDCONTACAPITAL", referencedColumnName = "IDCONTACAPITAL")
	@OrderBy("id.dataHoraAtualizacao DESC")
	private List<HistoricoPropostaSubscricao> historico = new ArrayList<HistoricoPropostaSubscricao>(0);
	
	@Override
	public String toString() {
		return "PropostaSubscricao[id=" + getId() + ", valorSubsProposta=" + valorSubsProposta + ", idUsuario=" + idUsuario + "] " +  super.toString();
	}

	/**
	 * @see br.com.sicoob.cca.entidades.negocio.entidades.ContaCapitalEntidade#getId()
	 */
	@Override
	public Integer getId() {
		return idContaCapital;
	}
	
	/**
	 * Define o valor de id.
	 *
	 * @param idContaCapital o novo valor de id
	 */
	public void setId(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
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
	 * @return the valorSubsProposta
	 */
	public BigDecimal getValorSubsProposta() {
		return valorSubsProposta;
	}

	/**
	 * @param valorSubsProposta the valorSubsProposta to set
	 */
	public void setValorSubsProposta(BigDecimal valorSubsProposta) {
		this.valorSubsProposta = valorSubsProposta;
	}

	/**
	 * @return the valorIntegProposta
	 */
	public BigDecimal getValorIntegProposta() {
		return valorIntegProposta;
	}

	/**
	 * @param valorIntegProposta the valorIntegProposta to set
	 */
	public void setValorIntegProposta(BigDecimal valorIntegProposta) {
		this.valorIntegProposta = valorIntegProposta;
	}

	/**
	 * @return the qtdParcelaProposta
	 */
	public Integer getQtdParcelaProposta() {
		return qtdParcelaProposta;
	}

	/**
	 * @param qtdParcelaProposta the qtdParcelaProposta to set
	 */
	public void setQtdParcelaProposta(Integer qtdParcelaProposta) {
		this.qtdParcelaProposta = qtdParcelaProposta;
	}

	/**
	 * @return the valorParcelaProposta
	 */
	public BigDecimal getValorParcelaProposta() {
		return valorParcelaProposta;
	}

	/**
	 * @param valorParcelaProposta the valorParcelaProposta to set
	 */
	public void setValorParcelaProposta(BigDecimal valorParcelaProposta) {
		this.valorParcelaProposta = valorParcelaProposta;
	}

	/**
	 * @return the diaDebitoProposta
	 */
	public Integer getDiaDebitoProposta() {
		return diaDebitoProposta;
	}

	/**
	 * @param diaDebitoProposta the diaDebitoProposta to set
	 */
	public void setDiaDebitoProposta(Integer diaDebitoProposta) {
		this.diaDebitoProposta = diaDebitoProposta;
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
	public List<HistoricoPropostaSubscricao> getHistorico() {
		return historico;
	}

	/**
	 * @param historico the historico to set
	 */
	public void setHistorico(List<HistoricoPropostaSubscricao> historico) {
		this.historico = historico;
	}

	/**
	 * Recupera o valor de bolSubscricaoProposta.
	 *
	 * @return o valor de bolSubscricaoProposta
	 */
	public Integer getBolSubscricaoProposta() {
		return bolSubscricaoProposta;
	}

	/**
	 * Define o valor de bolSubscricaoProposta.
	 *
	 * @param bolSubscricaoProposta o novo valor de bolSubscricaoProposta
	 */
	public void setBolSubscricaoProposta(Integer bolSubscricaoProposta) {
		this.bolSubscricaoProposta = bolSubscricaoProposta;
	}

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
	
}