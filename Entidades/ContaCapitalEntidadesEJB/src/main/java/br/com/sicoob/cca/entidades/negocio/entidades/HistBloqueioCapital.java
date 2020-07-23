/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * Entidade HistBloqueioCapital
 */
@Entity
@Table(name = "HISTBLOQUEIOCAPITAL", schema = "cca")
public class HistBloqueioCapital extends ContaCapitalEntidade<HistBloqueioCapitalPK> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1552365516659983501L;


	/** O atributo id. */
	@EmbeddedId
	private HistBloqueioCapitalPK id;	

	
	/**
	 * Origem do bloqueio de capital
	 */
	@Column(name = "IDORIGEMBLOQUEIOCAPITAL", nullable = false)
	private Short idOrigemBloqueioCapital;	
	
	/**
	 * Identificador da Conta Capital
	 */
	@Column(name = "IDCONTACAPITAL", nullable = false)
	private Integer idContaCapital;	
	
	/**
	 * Valor do bloqueio.
	 */
	@Column(name = "VALORBLOQUEIO", nullable = false)
	private BigDecimal valorBloqueio;	
	
	/**
	 * Data do inicio do bloqueio.
	 */
	@Column(name = "DATAINICIOBLOQUEIO", nullable = false)
	private DateTimeDB dataInicioBloqueio;
	
	/**
	 * Data do fim do bloqueio.
	 */
	@Column(name = "DATAFIMBLOQUEIO")
	private DateTimeDB dataFimBloqueio;
	
	/**
	 * O bloqueio esta ativo ou não.
	 */
	@Column(name = "BOLATIVO", nullable = false)
	private Short bolAtivo;
	
	/**
	 * Identificador do usuario
	 */
	@Column(name = "IDUSUARIO", length = 40, nullable = false)
	private String idUsuario;
	
	@Column(name = "DATAPROTOCOLO")
	private DateTimeDB dataProtocolo;
	
	@Column(name = "NUMPROTOCOLO")
	private String numProtocolo;
	
	@Column(name = "NUMPROCESSO")
	private String numProcesso;

	public HistBloqueioCapitalPK getId() {
		return id;
	}

	public void setId(HistBloqueioCapitalPK id) {
		this.id = id;
	}

	public Short getIdOrigemBloqueioCapital() {
		return idOrigemBloqueioCapital;
	}

	public void setIdOrigemBloqueioCapital(Short idOrigemBloqueioCapital) {
		this.idOrigemBloqueioCapital = idOrigemBloqueioCapital;
	}

	public Integer getIdContaCapital() {
		return idContaCapital;
	}

	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}

	public BigDecimal getValorBloqueio() {
		return valorBloqueio;
	}

	public void setValorBloqueio(BigDecimal valorBloqueio) {
		this.valorBloqueio = valorBloqueio;
	}

	public DateTimeDB getDataInicioBloqueio() {
		return dataInicioBloqueio;
	}

	public void setDataInicioBloqueio(DateTimeDB dataInicioBloqueio) {
		this.dataInicioBloqueio = dataInicioBloqueio;
	}

	public DateTimeDB getDataFimBloqueio() {
		return dataFimBloqueio;
	}

	public void setDataFimBloqueio(DateTimeDB dataFimBloqueio) {
		this.dataFimBloqueio = dataFimBloqueio;
	}

	public Short getBolAtivo() {
		return bolAtivo;
	}

	public void setBolAtivo(Short bolAtivo) {
		this.bolAtivo = bolAtivo;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the dataProtocolo
	 */
	public DateTimeDB getDataProtocolo() {
		return dataProtocolo;
	}

	/**
	 * @param dataProtocolo the dataProtocolo to set
	 */
	public void setDataProtocolo(DateTimeDB dataProtocolo) {
		this.dataProtocolo = dataProtocolo;
	}

	/**
	 * @return the numProtocolo
	 */
	public String getNumProtocolo() {
		return numProtocolo;
	}

	/**
	 * @param numProtocolo the numProtocolo to set
	 */
	public void setNumProtocolo(String numProtocolo) {
		this.numProtocolo = numProtocolo;
	}

	/**
	 * @return the numProcesso
	 */
	public String getNumProcesso() {
		return numProcesso;
	}

	/**
	 * @param numProcesso the numProcesso to set
	 */
	public void setNumProcesso(String numProcesso) {
		this.numProcesso = numProcesso;
	}
	
}
