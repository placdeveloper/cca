/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * EmprestimoIntegracaoDTO
 */
public class EmprestimoIntegracaoDTO extends BancoobDto  {

	private Integer numContrato;
	private String nome;
	private Date dataOperacao;
	private Date dataVencimento;
	private BigDecimal valor;
	private BigDecimal saldoDevedor;
	/**
	 * @return the numContrato
	 */
	public Integer getNumContrato() {
		return numContrato;
	}
	/**
	 * @param numContrato the numContrato to set
	 */
	public void setNumContrato(Integer numContrato) {
		this.numContrato = numContrato;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the dataOperacao
	 */
	public Date getDataOperacao() {
		if(dataOperacao != null){
			return new Date(dataOperacao.getTime());
		}
		return null;
	}
	/**
	 * @param dataOperacao the dataOperacao to set
	 */
	public void setDataOperacao(Date dataOperacao) {
		if(dataOperacao != null){
			this.dataOperacao = new Date(dataOperacao.getTime());
		} else{
			this.dataOperacao = null;
		}
	}
	/**
	 * @return the dataVencimento
	 */
	public Date getDataVencimento() {
		if(dataVencimento != null){
			return new Date(dataVencimento.getTime());
		}
		return null;
	}
	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento(Date dataVencimento) {
		if(dataVencimento != null){
			this.dataVencimento = new Date(dataVencimento.getTime());
		} else{
			this.dataVencimento = null;
		}
	}
	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	/**
	 * @return the saldoDevedor
	 */
	public BigDecimal getSaldoDevedor() {
		return saldoDevedor;
	}
	/**
	 * @param saldoDevedor the saldoDevedor to set
	 */
	public void setSaldoDevedor(BigDecimal saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}
}