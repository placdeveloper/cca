package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * ResultadoLiquidacaoDTO
 * @author Nairon.Silva
 */
public class ResultadoLiquidacaoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private ContratoLiquidacaoDTO contrato;
    private boolean sucesso;
    private String msgErro;
    private BigDecimal valorPago;
	private String descOperacaoCredito;
    
	public String getDescOperacaoCredito() {
		return descOperacaoCredito;
	}
	public void setDescOperacaoCredito(String descOperacaoCredito) {
		this.descOperacaoCredito = descOperacaoCredito;
	}
	public ContratoLiquidacaoDTO getContrato() {
		return contrato;
	}
	public void setContrato(ContratoLiquidacaoDTO contrato) {
		this.contrato = contrato;
	}
	public boolean isSucesso() {
		return sucesso;
	}
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	public String getMsgErro() {
		return msgErro;
	}
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

}
