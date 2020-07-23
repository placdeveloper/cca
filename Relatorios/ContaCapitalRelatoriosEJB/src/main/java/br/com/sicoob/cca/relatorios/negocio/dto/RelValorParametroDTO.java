/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * Classe RelValorParametroDTO
 */
public class RelValorParametroDTO extends BancoobDto {
	
	/**
	 * Construtor
	 */
	public RelValorParametroDTO() {
		
	}
	
	/**
	 * Construtor
	 * @param valor
	 * @param dataAlteracao
	 * @param usuario
	 * @param descCoop
	 */
	public RelValorParametroDTO(String valor, Date dataAlteracao, String usuario, String descCoop) {
		this.valor = valor;
		this.dataAlteracao = dataAlteracao;
		this.usuario = usuario;
		this.descCoop = descCoop;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String valor;
	private Date dataAlteracao;
	private String usuario;
	private String descCoop;
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getDescCoop() {
		return descCoop;
	}
	
	public void setDescCoop(String descCoop) {
		this.descCoop = descCoop;
	}
}