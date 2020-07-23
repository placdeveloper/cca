package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * ContratoLiquidacaoSimplesDTO
 */
public class ContratoLiquidacaoSimplesDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private String contrato;
	private Date dataOperacao;
	private String modalidade;
	private BigDecimal valorPrincipal;
	private BigDecimal valorQuitacao;
	private String descErro;		
    private boolean bolErro;
	
	public BigDecimal getValorQuitacao() {
		return valorQuitacao;
	}
	public void setValorQuitacao(BigDecimal valorQuitacao) {
		this.valorQuitacao = valorQuitacao;
	}
	public String getDescErro() {
		return descErro;
	}
	public void setDescErro(String descErro) {
		this.descErro = descErro;
	}
	public boolean isBolErro() {
		return bolErro;
	}
	public void setBolErro(boolean bolErro) {
		this.bolErro = bolErro;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public Date getDataOperacao() {
		return dataOperacao;
	}
	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}
	public void setValorPrincipal(BigDecimal bigDecimal) {
		this.valorPrincipal = bigDecimal;
	}

	@Override
	public String toString() {
		String retorno;
		retorno = "ContratoLiquidacaoSimplesDTO [contrato=" + contrato + ", dataOperacao=" + dataOperacao + ", modalidade="
			+ modalidade + ", valorPrincipal=" + valorPrincipal  + ", valorQuitacao=" + valorQuitacao;
		if(bolErro) {
			retorno = retorno + ", bolErro=" + bolErro + ", descErro=" + descErro + "]";					
		}else {
			retorno = retorno + "]";										
		}
		
		return retorno;		
	}

}
