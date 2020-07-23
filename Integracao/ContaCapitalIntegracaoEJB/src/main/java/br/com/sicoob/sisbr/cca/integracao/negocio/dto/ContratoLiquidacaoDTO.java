package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * ContratoLiquidacaoDTO
 * @author Nairon.Silva
 */
public class ContratoLiquidacaoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer idProduto;
	private Integer idModalidadeProduto;
	private Integer idOrigemOperacaoCredito;
	private Integer numDiasAtraso;
	private Integer numOperacaoCredito;
	private Integer qtdParcelasOperacao;
	private Integer qtdParcelasAtraso;
	private Integer qtdParcelasAberto;

	private String descOperacaoCredito;
	private String descProduto;
	private String descLinha;
	private String descTaxaJuros;
	private String idNivelRisco;
	private String descErro;

	private Date dataVencimento;
	private Date dataUltimaLiquidacao;
	private Date dataEntradaOperacao;

	private Long idOperacaoSISBR20;

	private Boolean bolLegado;
	private Boolean bolErro;
	private Boolean bolRotativo;

	private BigDecimal valorPrincipal;
	private BigDecimal valorRendas;
	private BigDecimal valorMulta;
	private BigDecimal valorMora;
	private BigDecimal valorOutrosEncargos;
	private BigDecimal valorTotalEncargosAtraso;
	private BigDecimal valorAtrasoRenegociado;
	private BigDecimal valorContratado;
	private BigDecimal valorSaldoContabil;
	private BigDecimal valorSaldoGanhoAAuferir;
	private BigDecimal valorCreditoIOF;
	private BigDecimal valorQuitacao;
	private BigDecimal percAliquotaDiarioIOF;
	private BigDecimal percAliquotaRelativaIOF;
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getIdModalidadeProduto() {
		return idModalidadeProduto;
	}
	public void setIdModalidadeProduto(Integer idModalidadeProduto) {
		this.idModalidadeProduto = idModalidadeProduto;
	}
	public Integer getIdOrigemOperacaoCredito() {
		return idOrigemOperacaoCredito;
	}
	public void setIdOrigemOperacaoCredito(Integer idOrigemOperacaoCredito) {
		this.idOrigemOperacaoCredito = idOrigemOperacaoCredito;
	}
	public Integer getNumDiasAtraso() {
		return numDiasAtraso;
	}
	public void setNumDiasAtraso(Integer numDiasAtraso) {
		this.numDiasAtraso = numDiasAtraso;
	}
	public Integer getNumOperacaoCredito() {
		return numOperacaoCredito;
	}
	public void setNumOperacaoCredito(Integer numOperacaoCredito) {
		this.numOperacaoCredito = numOperacaoCredito;
	}
	public Integer getQtdParcelasOperacao() {
		return qtdParcelasOperacao;
	}
	public void setQtdParcelasOperacao(Integer qtdParcelasOperacao) {
		this.qtdParcelasOperacao = qtdParcelasOperacao;
	}
	public Integer getQtdParcelasAtraso() {
		return qtdParcelasAtraso;
	}
	public void setQtdParcelasAtraso(Integer qtdParcelasAtraso) {
		this.qtdParcelasAtraso = qtdParcelasAtraso;
	}
	public Integer getQtdParcelasAberto() {
		return qtdParcelasAberto;
	}
	public void setQtdParcelasAberto(Integer qtdParcelasAberto) {
		this.qtdParcelasAberto = qtdParcelasAberto;
	}
	public String getDescOperacaoCredito() {
		return descOperacaoCredito;
	}
	public void setDescOperacaoCredito(String descOperacaoCredito) {
		this.descOperacaoCredito = descOperacaoCredito;
	}
	public String getDescProduto() {
		return descProduto;
	}
	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}
	public String getDescLinha() {
		return descLinha;
	}
	public void setDescLinha(String descLinha) {
		this.descLinha = descLinha;
	}
	public String getDescTaxaJuros() {
		return descTaxaJuros;
	}
	public void setDescTaxaJuros(String descTaxaJuros) {
		this.descTaxaJuros = descTaxaJuros;
	}
	public String getIdNivelRisco() {
		return idNivelRisco;
	}
	public void setIdNivelRisco(String idNivelRisco) {
		this.idNivelRisco = idNivelRisco;
	}
	public String getDescErro() {
		return descErro;
	}
	public void setDescErro(String descErro) {
		this.descErro = descErro;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataUltimaLiquidacao() {
		return dataUltimaLiquidacao;
	}
	public void setDataUltimaLiquidacao(Date dataUltimaLiquidacao) {
		this.dataUltimaLiquidacao = dataUltimaLiquidacao;
	}
	public Date getDataEntradaOperacao() {
		return dataEntradaOperacao;
	}
	public void setDataEntradaOperacao(Date dataEntradaOperacao) {
		this.dataEntradaOperacao = dataEntradaOperacao;
	}
	public Long getIdOperacaoSISBR20() {
		return idOperacaoSISBR20;
	}
	public void setIdOperacaoSISBR20(Long idOperacaoSISBR20) {
		this.idOperacaoSISBR20 = idOperacaoSISBR20;
	}
	public Boolean getBolLegado() {
		return bolLegado;
	}
	public void setBolLegado(Boolean bolLegado) {
		this.bolLegado = bolLegado;
	}
	public Boolean getBolErro() {
		return bolErro;
	}
	public void setBolErro(Boolean bolErro) {
		this.bolErro = bolErro;
	}
	public Boolean getBolRotativo() {
		return bolRotativo;
	}
	public void setBolRotativo(Boolean bolRotativo) {
		this.bolRotativo = bolRotativo;
	}
	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}
	public void setValorPrincipal(BigDecimal valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}
	public BigDecimal getValorRendas() {
		return valorRendas;
	}
	public void setValorRendas(BigDecimal valorRendas) {
		this.valorRendas = valorRendas;
	}
	public BigDecimal getValorMulta() {
		return valorMulta;
	}
	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}
	public BigDecimal getValorMora() {
		return valorMora;
	}
	public void setValorMora(BigDecimal valorMora) {
		this.valorMora = valorMora;
	}
	public BigDecimal getValorOutrosEncargos() {
		return valorOutrosEncargos;
	}
	public void setValorOutrosEncargos(BigDecimal valorOutrosEncargos) {
		this.valorOutrosEncargos = valorOutrosEncargos;
	}
	public BigDecimal getValorTotalEncargosAtraso() {
		return valorTotalEncargosAtraso;
	}
	public void setValorTotalEncargosAtraso(BigDecimal valorTotalEncargosAtraso) {
		this.valorTotalEncargosAtraso = valorTotalEncargosAtraso;
	}
	public BigDecimal getValorAtrasoRenegociado() {
		return valorAtrasoRenegociado;
	}
	public void setValorAtrasoRenegociado(BigDecimal valorAtrasoRenegociado) {
		this.valorAtrasoRenegociado = valorAtrasoRenegociado;
	}
	public BigDecimal getValorContratado() {
		return valorContratado;
	}
	public void setValorContratado(BigDecimal valorContratado) {
		this.valorContratado = valorContratado;
	}
	public BigDecimal getValorSaldoContabil() {
		return valorSaldoContabil;
	}
	public void setValorSaldoContabil(BigDecimal valorSaldoContabil) {
		this.valorSaldoContabil = valorSaldoContabil;
	}
	public BigDecimal getValorSaldoGanhoAAuferir() {
		return valorSaldoGanhoAAuferir;
	}
	public void setValorSaldoGanhoAAuferir(BigDecimal valorSaldoGanhoAAuferir) {
		this.valorSaldoGanhoAAuferir = valorSaldoGanhoAAuferir;
	}
	public BigDecimal getValorCreditoIOF() {
		return valorCreditoIOF;
	}
	public void setValorCreditoIOF(BigDecimal valorCreditoIOF) {
		this.valorCreditoIOF = valorCreditoIOF;
	}
	public BigDecimal getPercAliquotaDiarioIOF() {
		return percAliquotaDiarioIOF;
	}
	public void setPercAliquotaDiarioIOF(BigDecimal percAliquotaDiarioIOF) {
		this.percAliquotaDiarioIOF = percAliquotaDiarioIOF;
	}
	public BigDecimal getPercAliquotaRelativaIOF() {
		return percAliquotaRelativaIOF;
	}
	public void setPercAliquotaRelativaIOF(BigDecimal percAliquotaRelativaIOF) {
		this.percAliquotaRelativaIOF = percAliquotaRelativaIOF;
	}
	public BigDecimal getValorQuitacao() {
		return valorQuitacao;
	}
	public void setValorQuitacao(BigDecimal valorQuitacao) {
		this.valorQuitacao = valorQuitacao;
	}
	
	@Override
	public String toString() {
		return "ContratoLiquidacaoDTO [descOperacaoCredito=" + descOperacaoCredito + ", descLinha=" + descLinha
				+ ", dataEntradaOperacao=" + dataEntradaOperacao + ", valorQuitacao=" + valorQuitacao + "]";
	}
	
}
