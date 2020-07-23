package br.com.sicoob.cca.relatorios.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
* @author Ricardo.Barcante
*/
public class FechRelPosicaoDiariaCarteiraDTO extends BancoobDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1205652105052686215L;

	private Integer idInstituicao;
	private Date dataAtualProduto;
	
	private String carteira;
	
	private Double saldoAnteriorSubscrito;
	private Double saldoAnteriorIntegralizado;
	private Double saldoAnteriorPagar;
	private Double saldoAnteriorRealizar;
	
	private Double creditosSubscrito;
	private Double creditosIntegralizado;
	private Double creditosPagar;
	private Double creditosRealizar;
	
	private Double debitosSubscrito;
	private Double debitosIntegralizado;
	private Double debitosPagar;
	private Double debitosRealizar;
	
	private Double saldoAtualSubscrito;
	private Double saldoAtualIntegralizado;
	private Double saldoAtualPagar;
	private Double saldoAtualRealizar;
	
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Date getDataAtualProduto() {
		return dataAtualProduto;
	}
	public void setDataAtualProduto(Date dataAtualProduto) {
		this.dataAtualProduto = dataAtualProduto;
	}
	public String getCarteira() {
		return carteira;
	}
	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}
	public Double getSaldoAnteriorSubscrito() {
		return saldoAnteriorSubscrito;
	}
	public void setSaldoAnteriorSubscrito(Double saldoAnteriorSubscrito) {
		this.saldoAnteriorSubscrito = saldoAnteriorSubscrito;
	}
	public Double getSaldoAnteriorIntegralizado() {
		return saldoAnteriorIntegralizado;
	}
	public void setSaldoAnteriorIntegralizado(Double saldoAnteriorIntegralizado) {
		this.saldoAnteriorIntegralizado = saldoAnteriorIntegralizado;
	}
	public Double getSaldoAnteriorPagar() {
		return saldoAnteriorPagar;
	}
	public void setSaldoAnteriorPagar(Double saldoAnteriorPagar) {
		this.saldoAnteriorPagar = saldoAnteriorPagar;
	}
	public Double getSaldoAnteriorRealizar() {
		return saldoAnteriorRealizar;
	}
	public void setSaldoAnteriorRealizar(Double saldoAnteriorRealizar) {
		this.saldoAnteriorRealizar = saldoAnteriorRealizar;
	}
	public Double getCreditosSubscrito() {
		return creditosSubscrito;
	}
	public void setCreditosSubscrito(Double creditosSubscrito) {
		this.creditosSubscrito = creditosSubscrito;
	}
	public Double getCreditosIntegralizado() {
		return creditosIntegralizado;
	}
	public void setCreditosIntegralizado(Double creditosIntegralizado) {
		this.creditosIntegralizado = creditosIntegralizado;
	}
	public Double getCreditosPagar() {
		return creditosPagar;
	}
	public void setCreditosPagar(Double creditosPagar) {
		this.creditosPagar = creditosPagar;
	}
	public Double getCreditosRealizar() {
		return creditosRealizar;
	}
	public void setCreditosRealizar(Double creditosRealizar) {
		this.creditosRealizar = creditosRealizar;
	}
	public Double getDebitosSubscrito() {
		return debitosSubscrito;
	}
	public void setDebitosSubscrito(Double debitosSubscrito) {
		this.debitosSubscrito = debitosSubscrito;
	}
	public Double getDebitosIntegralizado() {
		return debitosIntegralizado;
	}
	public void setDebitosIntegralizado(Double debitosIntegralizado) {
		this.debitosIntegralizado = debitosIntegralizado;
	}
	public Double getDebitosPagar() {
		return debitosPagar;
	}
	public void setDebitosPagar(Double debitosPagar) {
		this.debitosPagar = debitosPagar;
	}
	public Double getDebitosRealizar() {
		return debitosRealizar;
	}
	public void setDebitosRealizar(Double debitosRealizar) {
		this.debitosRealizar = debitosRealizar;
	}
	public Double getSaldoAtualSubscrito() {
		return saldoAtualSubscrito;
	}
	public void setSaldoAtualSubscrito(Double saldoAtualSubscrito) {
		this.saldoAtualSubscrito = saldoAtualSubscrito;
	}
	public Double getSaldoAtualIntegralizado() {
		return saldoAtualIntegralizado;
	}
	public void setSaldoAtualIntegralizado(Double saldoAtualIntegralizado) {
		this.saldoAtualIntegralizado = saldoAtualIntegralizado;
	}
	public Double getSaldoAtualPagar() {
		return saldoAtualPagar;
	}
	public void setSaldoAtualPagar(Double saldoAtualPagar) {
		this.saldoAtualPagar = saldoAtualPagar;
	}
	public Double getSaldoAtualRealizar() {
		return saldoAtualRealizar;
	}
	public void setSaldoAtualRealizar(Double saldoAtualRealizar) {
		this.saldoAtualRealizar = saldoAtualRealizar;
	}
}
