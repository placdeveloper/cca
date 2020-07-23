package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO RegistroBatimentoContaCapitalDTO
 */
public class RegistroBatimentoContaCapitalDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private String tabela;
	private Integer total;
	private BigDecimal valorSaldoAtuSubsc;
	private BigDecimal valorSaldoAtuInteg;
	private BigDecimal valorSaldoAtuDevolver;
	private BigDecimal valorSaldoBloq;
	
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public BigDecimal getValorSaldoAtuSubsc() {
		return valorSaldoAtuSubsc;
	}
	public void setValorSaldoAtuSubsc(BigDecimal valorSaldoAtuSubsc) {
		this.valorSaldoAtuSubsc = valorSaldoAtuSubsc;
	}
	public BigDecimal getValorSaldoAtuInteg() {
		return valorSaldoAtuInteg;
	}
	public void setValorSaldoAtuInteg(BigDecimal valorSaldoAtuInteg) {
		this.valorSaldoAtuInteg = valorSaldoAtuInteg;
	}
	public BigDecimal getValorSaldoAtuDevolver() {
		return valorSaldoAtuDevolver;
	}
	public void setValorSaldoAtuDevolver(BigDecimal valorSaldoAtuDevolver) {
		this.valorSaldoAtuDevolver = valorSaldoAtuDevolver;
	}
	public BigDecimal getValorSaldoBloq() {
		return valorSaldoBloq;
	}
	public void setValorSaldoBloq(BigDecimal valorSaldoBloq) {
		this.valorSaldoBloq = valorSaldoBloq;
	}

}
