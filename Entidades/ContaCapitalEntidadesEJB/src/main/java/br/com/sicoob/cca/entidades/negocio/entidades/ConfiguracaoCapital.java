/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Configuração capital
 * @author antonio.genaro
 * @since 28/09/2015
 */
@Entity
@Table(name = "CONFIGURACAOCAPITAL", schema = "cca")
public class ConfiguracaoCapital extends ContaCapitalEntidade<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1744120541127434739L;
	/**
	 * PK
	 */
	@Id
	@Column(name = "IDCONFIGURACAOCAPITAL")
	private Integer id;
	
	/**
	 * Nome da configuração de capital
	 */
	@Column(name = "NOMECONFIGURACAOCAPITAL", nullable = false)
	private String nomeConfiguracaoCapital;

	/**
	 * Descrição da configuração de capital
	 */
	@Column(name = "DESCCONFIGURACAOCAPITAL", nullable = false)
	private String descConfiguracaoCapital;
	
	/**
	 * Tipo da configuração de capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDTIPOVALORCONFIGURACAOCAPITAL", nullable = false)
	private TipoValorConfiguracaoCapital tipoValorConfiguracaoCapital;
	
	/**
	 * Perfil da configuração de capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDPERFILCONFIGURACAOCAPITAL", nullable = false)
	private PerfilConfiguracaoCapital perfilConfiguracaoCapital;
	
	@ManyToOne
	@JoinColumn(name = "IDAGRUPADORCONFIGURACAOCAPITAL", nullable = true)
	private AgrupadorConfiguracaoCapital agrupadorConfiguracaoCapital;

	/**
	 * Status da configuração de capital
	 */
	@Column(name = "BOLATIVO", nullable = false)
	private Boolean bolAtivo;
	
	/**
	 * Indica se valor deve ser maior que 0
	 */
	@Column(name = "BOLMAIORZERO", nullable = true)
	private Boolean bolMaiorZero;
	
	/**
	 * Indica se a configuracao deve ser exibida no relatorio
	 */
	@Column(name = "BOLEXIBERELATORIO", nullable = true)
	private Boolean bolMostrarRelatorio;
	

	/**
	 * {@inheritDoc}
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
	 * Recupera o valor de nomeConfiguracaoCapital.
	 *
	 * @return o valor de nomeConfiguracaoCapital
	 */
	public String getNomeConfiguracaoCapital() {
		return nomeConfiguracaoCapital;
	}

	/**
	 * Define o valor de nomeConfiguracaoCapital.
	 *
	 * @param nomeConfiguracaoCapital o novo valor de nomeConfiguracaoCapital
	 */
	public void setNomeConfiguracaoCapital(String nomeConfiguracaoCapital) {
		this.nomeConfiguracaoCapital = nomeConfiguracaoCapital;
	}

	/**
	 * Recupera o valor de descConfiguracaoCapital.
	 *
	 * @return o valor de descConfiguracaoCapital
	 */
	public String getDescConfiguracaoCapital() {
		return descConfiguracaoCapital;
	}

	/**
	 * Define o valor de descConfiguracaoCapital.
	 *
	 * @param descConfiguracaoCapital o novo valor de descConfiguracaoCapital
	 */
	public void setDescConfiguracaoCapital(String descConfiguracaoCapital) {
		this.descConfiguracaoCapital = descConfiguracaoCapital;
	}

	/**
	 * Recupera o valor de tipoValorConfiguracaoCapital.
	 *
	 * @return o valor de tipoValorConfiguracaoCapital
	 */
	public TipoValorConfiguracaoCapital getTipoValorConfiguracaoCapital() {
		return tipoValorConfiguracaoCapital;
	}

	/**
	 * Define o valor de tipoValorConfiguracaoCapital.
	 *
	 * @param tipoValorConfiguracaoCapital o novo valor de tipoValorConfiguracaoCapital
	 */
	public void setTipoValorConfiguracaoCapital(
			TipoValorConfiguracaoCapital tipoValorConfiguracaoCapital) {
		this.tipoValorConfiguracaoCapital = tipoValorConfiguracaoCapital;
	}

	/**
	 * Recupera o valor de perfilConfiguracaoCapital.
	 *
	 * @return o valor de perfilConfiguracaoCapital
	 */
	public PerfilConfiguracaoCapital getPerfilConfiguracaoCapital() {
		return perfilConfiguracaoCapital;
	}

	/**
	 * Define o valor de perfilConfiguracaoCapital.
	 *
	 * @param perfilConfiguracaoCapital o novo valor de perfilConfiguracaoCapital
	 */
	public void setPerfilConfiguracaoCapital(
			PerfilConfiguracaoCapital perfilConfiguracaoCapital) {
		this.perfilConfiguracaoCapital = perfilConfiguracaoCapital;
	}

	/**
	 * Recupera o valor de bolAtivo.
	 *
	 * @return o valor de bolAtivo
	 */
	public Boolean getBolAtivo() {
		return bolAtivo;
	}

	/**
	 * Define o valor de bolAtivo.
	 *
	 * @param bolAtivo o novo valor de bolAtivo
	 */
	public void setBolAtivo(Boolean bolAtivo) {
		this.bolAtivo = bolAtivo;
	}

	public AgrupadorConfiguracaoCapital getAgrupadorConfiguracaoCapital() {
		return agrupadorConfiguracaoCapital;
	}

	public void setAgrupadorConfiguracaoCapital(AgrupadorConfiguracaoCapital agrupadorConfiguracaoCapital) {
		this.agrupadorConfiguracaoCapital = agrupadorConfiguracaoCapital;
	}

	public Boolean getBolMaiorZero() {
		return bolMaiorZero;
	}

	public void setBolMaiorZero(Boolean bolMaiorZero) {
		this.bolMaiorZero = bolMaiorZero;
	}

	public Boolean getBolMostrarRelatorio() {
		return bolMostrarRelatorio;
	}

	public void setBolMostrarRelatorio(Boolean bolMostrarRelatorio) {
		this.bolMostrarRelatorio = bolMostrarRelatorio;
	}
}