/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO RelImpedimentosDesligamentoDTO
 */
public class RelImpedimentosDesligamentoDTO extends BancoobDto {
	
	private Integer idContaCapital;
	private Integer idPessoa;
	private Integer idInstituicao;
	
	private Integer numContaCapital;
	private String nomeCompleto;
	private String cpfCnpj;
	private Date dataAbertura;
	
	private Long numContaCorrente;
	private String titularidadeContaCorrente;
	
	private Integer numContrato;
	private String nomeContrato;
	private Date dataOperacao;
	private Date dataVencimento;
	private BigDecimal valorEmprestimo;
	private BigDecimal saldoDevedor;
	
	private BigDecimal valorBloqueado;
	
	private boolean esconderEmprestimos;

	/**
	 * @return the idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}

	/**
	 * @param idContaCapital the idContaCapital to set
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}

	/**
	 * @return the idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * @return the numContaCapital
	 */
	public Integer getNumContaCapital() {
		return numContaCapital;
	}

	/**
	 * @param numContaCapital the numContaCapital to set
	 */
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}

	/**
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return the dataAbertura
	 */
	public Date getDataAbertura() {
		if(dataAbertura != null){
			return new Date(dataAbertura.getTime());
		}
		return null;
	}

	/**
	 * @param dataAbertura the dataAbertura to set
	 */
	public void setDataAbertura(Date dataAbertura) {
		if(dataAbertura != null){
			this.dataAbertura = new Date(dataAbertura.getTime());
		} else{
			this.dataAbertura = null;
		}
	}

	/**
	 * @return the numContaCorrente
	 */
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}

	/**
	 * @param numContaCorrente the numContaCorrente to set
	 */
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	/**
	 * @return the titularidadeContaCorrente
	 */
	public String getTitularidadeContaCorrente() {
		return titularidadeContaCorrente;
	}

	/**
	 * @param titularidadeContaCorrente the titularidadeContaCorrente to set
	 */
	public void setTitularidadeContaCorrente(String titularidadeContaCorrente) {
		this.titularidadeContaCorrente = titularidadeContaCorrente;
	}

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
	 * @return the nomeContrato
	 */
	public String getNomeContrato() {
		return nomeContrato;
	}

	/**
	 * @param nomeContrato the nomeContrato to set
	 */
	public void setNomeContrato(String nomeContrato) {
		this.nomeContrato = nomeContrato;
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
	 * @return the valorEmprestimo
	 */
	public BigDecimal getValorEmprestimo() {
		return valorEmprestimo;
	}

	/**
	 * @param valorEmprestimo the valorEmprestimo to set
	 */
	public void setValorEmprestimo(BigDecimal valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
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

	/**
	 * @return the valorBloqueado
	 */
	public BigDecimal getValorBloqueado() {
		return valorBloqueado;
	}

	/**
	 * @param valorBloqueado the valorBloqueado to set
	 */
	public void setValorBloqueado(BigDecimal valorBloqueado) {
		this.valorBloqueado = valorBloqueado;
	}

	public boolean isEsconderEmprestimos() {
		return esconderEmprestimos;
	}

	public void setEsconderEmprestimos(boolean esconderEmprestimos) {
		this.esconderEmprestimos = esconderEmprestimos;
	}
}