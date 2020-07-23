package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Kleber Alves
 */
public class RelConciliacaoContabilDTO extends BancoobDto {

	private static final long serialVersionUID = -2914224525409573488L;

	private Date dataLancamento;
	private Integer tipoHistoricoLancamento;
	private Integer tipoHistoricoEstorno;
	private String descricaoHistorico;
	private String grupoHistorico;
	private Integer quantidadeLancamentos;
	private BigDecimal valorTotal;

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

	public String getDescricaoHistorico() {
		return descricaoHistorico;
	}

	public void setDescricaoHistorico(String descricaoHistorico) {
		this.descricaoHistorico = descricaoHistorico;
	}

	public String getGrupoHistorico() {
		return grupoHistorico;
	}

	public void setGrupoHistorico(String grupoHistorico) {
		this.grupoHistorico = grupoHistorico;
	}

	public Integer getQuantidadeLancamentos() {
		return quantidadeLancamentos;
	}

	public void setQuantidadeLancamentos(Integer quantidadeLancamentos) {
		this.quantidadeLancamentos = quantidadeLancamentos;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
