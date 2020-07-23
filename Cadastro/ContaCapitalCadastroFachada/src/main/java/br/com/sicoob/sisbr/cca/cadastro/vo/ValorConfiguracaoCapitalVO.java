/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.vo;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe ValorConfiguracaoCapitalVO.
 */
public class ValorConfiguracaoCapitalVO extends BancoobVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4736685292033035350L;
	
	/** O atributo idValorConfiguracaoCapital. */
	private Integer idValorConfiguracaoCapital;	
	
	/** O atributo idConfiguracaoCapital. */
	private Integer idConfiguracaoCapital;	
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo valorConfiguracao. */
	private String valorConfiguracao;
	
	/** O atributo idUsuario. */
	private String idUsuario;
	
	/** O atributo dataHoraAtualizacao. */
	private DateTimeDB dataHoraAtualizacao;
	
	/** O atributo xmlString. */
	private String xmlString;
	
	/**
	 * Recupera o valor de idValorConfiguracaoCapital.
	 *
	 * @return o valor de idValorConfiguracaoCapital
	 */
	public Integer getIdValorConfiguracaoCapital() {
		return idValorConfiguracaoCapital;
	}
	
	/**
	 * Define o valor de idValorConfiguracaoCapital.
	 *
	 * @param idValorConfiguracaoCapital o novo valor de idValorConfiguracaoCapital
	 */
	public void setIdValorConfiguracaoCapital(Integer idValorConfiguracaoCapital) {
		this.idValorConfiguracaoCapital = idValorConfiguracaoCapital;
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
	 * Recupera o valor de xmlString.
	 *
	 * @return o valor de xmlString
	 */
	public String getXmlString() {
		return xmlString;
	}
	
	/**
	 * Define o valor de xmlString.
	 *
	 * @param xmlString o novo valor de xmlString
	 */
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}
		
}
