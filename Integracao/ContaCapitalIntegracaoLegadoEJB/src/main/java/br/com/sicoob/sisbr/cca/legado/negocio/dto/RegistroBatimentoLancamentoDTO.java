package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO RegistroBatimentoLancamentoDTO
 */
public class RegistroBatimentoLancamentoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private String tabela;
	private Integer bolAtualizado;
	private Integer total;
	private BigDecimal valorLanc;
	
	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	public Integer getBolAtualizado() {
		return bolAtualizado;
	}

	public void setBolAtualizado(Integer bolAtualizado) {
		this.bolAtualizado = bolAtualizado;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public BigDecimal getValorLanc() {
		return valorLanc;
	}

	public void setValorLanc(BigDecimal valorLanc) {
		this.valorLanc = valorLanc;
	}

}
