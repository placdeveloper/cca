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
 * Configuração capital
 * @author antonio.genaro
 * @since 10/07/2017
 */
@Entity
@Table(name = "BLOQUEIOCAPITAL ", schema = "CCA")
public class BloqueioCapital extends ContaCapitalEntidade<Integer> {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6496697984080236548L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDBLOQUEIOCAPITAL")
	private Integer id;		

	
	/**
	 * Origem do bloqueio de capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDORIGEMBLOQUEIOCAPITAL", nullable = false)
	private OrigemBloqueioCapital origemBloqueioCapital;	
	
	/**
	 * Identificador da Conta Capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDCONTACAPITAL", nullable = false)
	private ContaCapital contaCapital;	
	
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
	
	@Column(name = "DATAPROTOCOLO")
	private DateTimeDB dataProtocolo;
	
	@Column(name = "NUMPROTOCOLO")
	private String numProtocolo;
	
	@Column(name = "NUMPROCESSO")
	private String numProcesso;
	
	/**
	 * Historico
	 */
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDBLOQUEIOCAPITAL", referencedColumnName = "IDBLOQUEIOCAPITAL")
	private List<HistBloqueioCapital> historico = new ArrayList<HistBloqueioCapital>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrigemBloqueioCapital getOrigemBloqueioCapital() {
		return origemBloqueioCapital;
	}

	public void setOrigemBloqueioCapital(OrigemBloqueioCapital origemBloqueioCapital) {
		this.origemBloqueioCapital = origemBloqueioCapital;
	}

	public ContaCapital getContaCapital() {
		return contaCapital;
	}

	public void setContaCapital(ContaCapital contaCapital) {
		this.contaCapital = contaCapital;
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

	/**
	 * @return the historico
	 */
	public List<HistBloqueioCapital> getHistorico() {
		return historico;
	}

	/**
	 * @param historico the historico to set
	 */
	public void setHistorico(List<HistBloqueioCapital> historico) {
		this.historico = historico;
	}	
		
}
