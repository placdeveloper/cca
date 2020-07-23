/*
 * 
 */
package br.com.sicoob.sisbr.cca.replicacao.vo;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.MonitorReplicacaoCapitalLegadoDTO;

/**
 * VO MonitoracaoCapitalVO
 */
public class MonitoracaoCapitalVO extends BancoobVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6763260905391156453L;
	
	/**
	 * Construtor
	 */
	public MonitoracaoCapitalVO() {
		
	}
	
	/**
	 * Construtor
	 * @param dto
	 */
	public MonitoracaoCapitalVO(MonitorReplicacaoCapitalLegadoDTO dto) {
		this.tabela = dto.getTabela();
		this.qtdAguardando = dto.getQtdAguardando();
		this.qtdErro = dto.getQtdErro();
		this.qtdInvalido = dto.getQtdInvalido();
		this.qtdReplicado = dto.getQtdReplicado();
	}
	
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