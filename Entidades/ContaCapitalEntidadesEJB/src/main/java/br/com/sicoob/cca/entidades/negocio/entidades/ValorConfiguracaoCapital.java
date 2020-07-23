/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

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

/**
 * Configuração capital
 * @author antonio.genaro
 * @since 06/10/2015
 */
@Entity
@Table(name = "VALORCONFIGURACAOCAPITAL", schema = "cca")
public class ValorConfiguracaoCapital extends ContaCapitalEntidade<Integer> {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3081432514138259566L;

	/**
	 * PK
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDVALORCONFIGURACAOCAPITAL")
	private Integer id;
	
	/**
	 * configuração de capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDCONFIGURACAOCAPITAL", nullable = false)
	private ConfiguracaoCapital configuracaoCapital;
	
	/**
	 * Instituição
	 */
	@Column(name = "IDINSTITUICAO")
	private Integer idInstituicao;
	
	/**
	 * Valor da configuração
	 */
	@Column(name = "VALORCONFIGURACAO", nullable = false)
	private String valorConfiguracao;
	
	/**
	 * Usuário que gravou os dados
	 */
	@Column(name = "IDUSUARIO", nullable = false)
	private String idUsuario;
	
	/**
	 * data e hora da gravação
	 */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;

	
	/** O atributo historico. */
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDVALORCONFIGURACAOCAPITAL", referencedColumnName = "IDVALORCONFIGURACAOCAPITAL")
	@OrderBy("id.dataHoraAtualizacao DESC")
	private List<HistValorConfiguracaoCapital> historico = new ArrayList<HistValorConfiguracaoCapital>(0);
	
	/**
	 * Recupera o valor de historico.
	 *
	 * @return o valor de historico
	 */
	public List<HistValorConfiguracaoCapital> getHistorico() {
		return historico;
	}

	/**
	 * Define o valor de historico.
	 *
	 * @param historico o novo valor de historico
	 */
	public void setHistorico(List<HistValorConfiguracaoCapital> historico) {
		this.historico = historico;
	}

	/**
	 * @see br.com.sicoob.cca.entidades.negocio.entidades.ContaCapitalEntidade#getId()
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Define o valor de id.
	 *
	 * @param id o novo valor de id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Recupera o valor de configuracaoCapital.
	 *
	 * @return o valor de configuracaoCapital
	 */
	public ConfiguracaoCapital getConfiguracaoCapital() {
		return configuracaoCapital;
	}

	/**
	 * Define o valor de configuracaoCapital.
	 *
	 * @param configuracaoCapital o novo valor de configuracaoCapital
	 */
	public void setConfiguracaoCapital(ConfiguracaoCapital configuracaoCapital) {
		this.configuracaoCapital = configuracaoCapital;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera o valor de valorConfiguracao.
	 *
	 * @return o valor de valorConfiguracao
	 */
	public String getValorConfiguracao() {
		return valorConfiguracao;
	}

	/**
	 * Define o valor de valorConfiguracao.
	 *
	 * @param valorConfiguracao o novo valor de valorConfiguracao
	 */
	public void setValorConfiguracao(String valorConfiguracao) {
		this.valorConfiguracao = valorConfiguracao;
	}

	/**
	 * Recupera o valor de idUsuario.
	 *
	 * @return o valor de idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Define o valor de idUsuario.
	 *
	 * @param idUsuario o novo valor de idUsuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Recupera o valor de dataHoraAtualizacao.
	 *
	 * @return o valor de dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}

	/**
	 * Define o valor de dataHoraAtualizacao.
	 *
	 * @param dataHoraAtualizacao o novo valor de dataHoraAtualizacao
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}	
	
	/**
	 * Verifica se o valor da configuracao eh "0"
	 * @return
	 */
	public boolean isValorBooleanoZero() {
		return "0".equals(getValorConfiguracao());
	}
	
	/**
	 * Verifica se o valor da configuracao eh "1"
	 * @return
	 */
	public boolean isValorBooleanoUm() {
		return "1".equals(getValorConfiguracao());
	}
	
}