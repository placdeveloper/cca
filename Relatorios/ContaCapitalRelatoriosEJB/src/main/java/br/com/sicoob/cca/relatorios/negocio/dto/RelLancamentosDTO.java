package br.com.sicoob.cca.relatorios.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Kleber Alves
 */
public class RelLancamentosDTO extends BancoobDto {

	private static final long serialVersionUID = 850788905249195796L;

	private Date dataLancamento;
	private Integer tipoHistoricoLancamento;
	private Integer tipoHistoricoEstorno;
	private BigDecimal valorTotalDebito;
	private BigDecimal valorTotalCredito;

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Integer getTipoHistoricoLancamento() {
		return tipoHistoricoLancamento;
	}

	public void setTipoHistoricoLancamento(Integer tipoHistoricoLancamento) {
		this.tipoHistoricoLancamento = tipoHistoricoLancamento;
	}

	public Integer getTipoHistoricoEstorno() {
		return tipoHistoricoEstorno;
	}

	public void setTipoHistoricoEstorno(Integer tipoHistoricoEstorno) {
		this.tipoHistoricoEstorno = tipoHistoricoEstorno;
	}

	public BigDecimal getValorTotalDebito() {
		return valorTotalDebito;
	}

	public void setValorTotalDebito(BigDecimal valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}

	public BigDecimal getValorTotalCredito() {
		return valorTotalCredito;
	}

	public void setValorTotalCredito(BigDecimal valorTotalCredito) {
		this.valorTotalCredito = valorTotalCredito;
	}

}
