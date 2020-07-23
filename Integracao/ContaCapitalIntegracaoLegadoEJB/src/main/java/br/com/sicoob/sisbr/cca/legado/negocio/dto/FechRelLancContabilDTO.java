package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

public class FechRelLancContabilDTO  extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5391718058646577329L;
	
	private Integer idInstituicao;
	private Date dataAtualProduto;
	
	private Integer contaReduzida;
	private String numContaBacen;
	
	private String analiticoContabil;
	private String descricaoConta;
	
	private Integer codHistorico;
	private String descricaoHistoricoContabil;
	
	private Double valorDebito;
	private Double valorCredito;

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
	public Integer getContaReduzida() {
		return contaReduzida;
	}
	public void setContaReduzida(Integer contaReduzida) {
		this.contaReduzida = contaReduzida;
	}
	public String getNumContaBacen() {
		return numContaBacen;
	}
	public void setNumContaBacen(String string) {
		this.numContaBacen = string;
	}
	public String getAnaliticoContabil() {
		return analiticoContabil;
	}
	public void setAnaliticoContabil(String analiticoContabil) {
		this.analiticoContabil = analiticoContabil;
	}
	public String getDescricaoConta() {
		return descricaoConta;
	}
	public void setDescricaoConta(String descricaoConta) {
		this.descricaoConta = descricaoConta;
	}
	public Integer getCodHistorico() {
		return codHistorico;
	}
	public void setCodHistorico(Integer codHistorico) {
		this.codHistorico = codHistorico;
	}
	public String getDescricaoHistoricoContabil() {
		return descricaoHistoricoContabil;
	}
	public void setDescricaoHistoricoContabil(String discricaoHistoricoContabil) {
		this.descricaoHistoricoContabil = discricaoHistoricoContabil;
	}
	public Double getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(Double valorDebito) {
		this.valorDebito = valorDebito;
	}
	public Double getValorCredito() {
		return valorCredito;
	}
	public void setValorCredito(Double valorCredito) {
		this.valorCredito = valorCredito;
	}
}