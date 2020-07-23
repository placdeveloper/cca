/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO MonitorReplicacaoCapitalLegadoDTO
 */
public class MonitorReplicacaoCapitalLegadoDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 318794481709942774L;
	
	private String tabela;
	private Integer qtdAguardando;
	private Integer qtdInvalido;
	private Integer qtdReplicado;
	private Integer qtdErro;
	
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	public Integer getQtdAguardando() {
		return qtdAguardando;
	}
	public void setQtdAguardando(Integer qtdAguardando) {
		this.qtdAguardando = qtdAguardando;
	}
	public Integer getQtdInvalido() {
		return qtdInvalido;
	}
	public void setQtdInvalido(Integer qtdInvalido) {
		this.qtdInvalido = qtdInvalido;
	}
	public Integer getQtdReplicado() {
		return qtdReplicado;
	}
	public void setQtdReplicado(Integer qtdReplicado) {
		this.qtdReplicado = qtdReplicado;
	}
	public Integer getQtdErro() {
		return qtdErro;
	}
	public void setQtdErro(Integer qtdErro) {
		this.qtdErro = qtdErro;
	}
}