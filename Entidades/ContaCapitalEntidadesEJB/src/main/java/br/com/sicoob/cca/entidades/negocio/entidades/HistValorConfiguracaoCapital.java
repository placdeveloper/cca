/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Configuração capital
 * @author antonio.genaro
 * @since 06/10/2015
 */
@Entity
@Table(name = "HISTVALORCONFIGURACAOCAPITAL", schema = "cca")
public class HistValorConfiguracaoCapital extends ContaCapitalEntidade<HistValorConfiguracaoCapitalPK> {	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2627431963199726555L;

	/**
	 * PK
	 */
	@EmbeddedId
	private HistValorConfiguracaoCapitalPK id;
	
	/**
	 * configuração de capital
	 */
	@Column(name = "IDCONFIGURACAOCAPITAL")
	private Integer idConfiguracaoCapital;
	
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
	 * {@inheritDoc}
	 */
	public HistValorConfiguracaoCapitalPK getId() {
		return id;
	}

	/**
	 * Define o valor de id.
	 *
	 * @param id o novo valor de id
	 */
	public void setId(HistValorConfiguracaoCapitalPK id) {
		this.id = id;
	}

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
	
}	