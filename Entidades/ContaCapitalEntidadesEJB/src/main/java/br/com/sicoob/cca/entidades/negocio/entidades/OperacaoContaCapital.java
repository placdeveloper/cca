package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * OperacaoContaCapital
 * @author Nairon.Silva
 */
@Entity
@Table(name="OPERACAOCONTACAPITAL", schema="CCA")
public class OperacaoContaCapital extends BancoobEntidade {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDOPERACAOCONTACAPITAL")
	private Long id;
	
	@Column(name="DATAHORAINICIOOPERACAO")
	private DateTimeDB dataHoraInicio;
	
	@Column(name="DATAHORAFIMOPERACAO")
	private DateTimeDB dataHoraFim;
	
	@ManyToOne
	@JoinColumn(name="IDMETODOOPERACAO")
	private MetodoOperacaoContaCapital metodo;
	
	@Column(name="DESCPARAMETROOPERACAO")
	private String descParametros;
	
	@ManyToOne
	@JoinColumn(name="IDRESULTADOOPERACAO")
	private ResultadoOperacaoContaCapital resultado;
	
	@Column(name="DESCERROOPERACAO")
	private String descErro;
	
	@Column(name="IDUSUARIO")
	private String idUsuario;
	
	@Column(name="IDINSTITUICAO")
	private Integer idInstituicao;
	
	@Column(name="IDUNIDADEINST")
	private Short idUnidadeInst;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public DateTimeDB getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(DateTimeDB dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public MetodoOperacaoContaCapital getMetodo() {
		return metodo;
	}

	public void setMetodo(MetodoOperacaoContaCapital metodo) {
		this.metodo = metodo;
	}

	public String getDescParametros() {
		return descParametros;
	}

	public void setDescParametros(String descParametros) {
		this.descParametros = descParametros;
	}

	public ResultadoOperacaoContaCapital getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoOperacaoContaCapital resultado) {
		this.resultado = resultado;
	}

	public String getDescErro() {
		return descErro;
	}

	public void setDescErro(String descErro) {
		this.descErro = descErro;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Short getIdUnidadeInst() {
		return idUnidadeInst;
	}

	public void setIdUnidadeInst(Short idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	@Override
	public String toString() {
		return "OperacaoContaCapital [id=" + id + ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim=" + dataHoraFim
				+ ", metodo=" + metodo + ", descParametros=" + descParametros + ", resultado=" + resultado
				+ ", descErro=" + descErro + ", idUsuario=" + idUsuario + ", idInstituicao=" + idInstituicao
				+ ", idUnidadeInst=" + idUnidadeInst + "]";
	}
	
}
