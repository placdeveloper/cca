/*
 * 
 */
package br.com.sicoob.sisbr.cca.replicacao.vo;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * VO MonitoracaoQuadroTotalVO
 */
public class MonitoracaoQuadroTotalVO extends BancoobDto {

	private static final long serialVersionUID = 7301518687942410783L;
	private Long countAguardando;
	private Long countInvalido;
	private Long countReplicado;
	private Long countErro;
	
	public Long getCountAguardando() {
		return countAguardando;
	}
	public void setCountAguardando(Long countAguardando) {
		this.countAguardando = countAguardando;
	}
	public Long getCountInvalido() {
		return countInvalido;
	}
	public void setCountInvalido(Long countInvalido) {
		this.countInvalido = countInvalido;
	}
	public Long getCountReplicado() {
		return countReplicado;
	}
	public void setCountReplicado(Long countReplicado) {
		this.countReplicado = countReplicado;
	}
	public Long getCountErro() {
		return countErro;
	}
	public void setCountErro(Long countErro) {
		this.countErro = countErro;
	}
	
	
	
}
