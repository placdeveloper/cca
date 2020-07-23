/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.vo;

import br.com.bancoob.negocio.vo.BancoobVo;


/**
 * A Classe ConfiguracaoCapitalVO.
 */
public class ConfiguracaoCapitalVO extends BancoobVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4862056151333173113L;
	
	/** O atributo idConfiguracaoCapital. */
	private Integer idConfiguracaoCapital;	
	
	
	/** O atributo nomeConfiguracaoCapital. */
	private String nomeConfiguracaoCapital;
	
	/** O atributo descConfiguracaoCapital. */
	private String descConfiguracaoCapital;	
	
	/** O atributo idTipoValorConfiguracaoCapital. */
	private Integer idTipoValorConfiguracaoCapital;
	
	/** O atributo descTipoValorConfiguracaoCapital. */
	private String descTipoValorConfiguracaoCapital;	
	
	/** O atributo idPerfilConfiguracaoCapital. */
	private Integer idPerfilConfiguracaoCapital;
	
	/** O atributo descPerfilConfiguracaoCapital. */
	private String descPerfilConfiguracaoCapital;
	
	/** O atributo bolAtivo. */
	private Boolean bolAtivo;
	
	/** O atributo bolMaiorZero. */
	private Boolean bolMaiorZero;
	
	/** O atributo bolMostrarRelatorio. */
	private Boolean bolMostrarRelatorio;
	
	/** O atributo idAgrupador. */
	private Integer idAgrupador;
	
	/**
	 * Recupera o valor de idConfiguracaoCapital.
	 *
	 * @return o valor de idConfiguracaoCapital
	 */
	public Integer getIdConfiguracaoCapital() {
		return idConfiguracaoCapital;
	}
	
	/**
	 * Define o valor de idConfiguracaoCapital.
	 *
	 * @param idConfiguracaoCapital o novo valor de idConfiguracaoCapital
	 */
	public void setIdConfiguracaoCapital(Integer idConfiguracaoCapital) {
		this.idConfiguracaoCapital = idConfiguracaoCapital;
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
	 * Recupera o valor de idTipoValorConfiguracaoCapital.
	 *
	 * @return o valor de idTipoValorConfiguracaoCapital
	 */
	public Integer getIdTipoValorConfiguracaoCapital() {
		return idTipoValorConfiguracaoCapital;
	}
	
	/**
	 * Define o valor de idTipoValorConfiguracaoCapital.
	 *
	 * @param idTipoValorConfiguracaoCapital o novo valor de idTipoValorConfiguracaoCapital
	 */
	public void setIdTipoValorConfiguracaoCapital(
			Integer idTipoValorConfiguracaoCapital) {
		this.idTipoValorConfiguracaoCapital = idTipoValorConfiguracaoCapital;
	}
	
	/**
	 * Recupera o valor de descTipoValorConfiguracaoCapital.
	 *
	 * @return o valor de descTipoValorConfiguracaoCapital
	 */
	public String getDescTipoValorConfiguracaoCapital() {
		return descTipoValorConfiguracaoCapital;
	}
	
	/**
	 * Define o valor de descTipoValorConfiguracaoCapital.
	 *
	 * @param descTipoValorConfiguracaoCapital o novo valor de descTipoValorConfiguracaoCapital
	 */
	public void setDescTipoValorConfiguracaoCapital(
			String descTipoValorConfiguracaoCapital) {
		this.descTipoValorConfiguracaoCapital = descTipoValorConfiguracaoCapital;
	}
	
	/**
	 * Recupera o valor de idPerfilConfiguracaoCapital.
	 *
	 * @return o valor de idPerfilConfiguracaoCapital
	 */
	public Integer getIdPerfilConfiguracaoCapital() {
		return idPerfilConfiguracaoCapital;
	}
	
	/**
	 * Define o valor de idPerfilConfiguracaoCapital.
	 *
	 * @param idPerfilConfiguracaoCapital o novo valor de idPerfilConfiguracaoCapital
	 */
	public void setIdPerfilConfiguracaoCapital(Integer idPerfilConfiguracaoCapital) {
		this.idPerfilConfiguracaoCapital = idPerfilConfiguracaoCapital;
	}
	
	/**
	 * Recupera o valor de descPerfilConfiguracaoCapital.
	 *
	 * @return o valor de descPerfilConfiguracaoCapital
	 */
	public String getDescPerfilConfiguracaoCapital() {
		return descPerfilConfiguracaoCapital;
	}
	
	/**
	 * Define o valor de descPerfilConfiguracaoCapital.
	 *
	 * @param descPerfilConfiguracaoCapital o novo valor de descPerfilConfiguracaoCapital
	 */
	public void setDescPerfilConfiguracaoCapital(
			String descPerfilConfiguracaoCapital) {
		this.descPerfilConfiguracaoCapital = descPerfilConfiguracaoCapital;
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

	public Integer getIdAgrupador() {
		return idAgrupador;
	}

	public void setIdAgrupador(Integer idAgrupador) {
		this.idAgrupador = idAgrupador;
	}	
}