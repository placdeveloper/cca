package br.com.sicoob.sisbr.cca.relatorios.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe RelDebitoIndeterminadoRenVO
 *
 * @author marco.nascimento
 */
public class RelDebitoIndeterminadoRenVO extends BancoobVo {
	
	private Integer idContaCapital;
	private Integer idDebitoContaCapital;
	
	private Integer numContaCapital;
	private String nome;
	private String cpfCnpj;
	private String tipoPessoa;
	private String formaDebito;
	private BigDecimal valor;
	private String dataPeriodoDeb;
	private String filtroUtilizado;
	
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
	 * @return the idDebitoContaCapital
	 */
	public Integer getIdDebitoContaCapital() {
		return idDebitoContaCapital;
	}
	/**
	 * @param idDebitoContaCapital the idDebitoContaCapital to set
	 */
	public void setIdDebitoContaCapital(Integer idDebitoContaCapital) {
		this.idDebitoContaCapital = idDebitoContaCapital;
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
	 * @return the tipoPessoa
	 */
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	/**
	 * @param tipoPessoa the tipoPessoa to set
	 */
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	/**
	 * @return the formaDebito
	 */
	public String getFormaDebito() {
		return formaDebito;
	}
	/**
	 * @param formaDebito the formaDebito to set
	 */
	public void setFormaDebito(String formaDebito) {
		this.formaDebito = formaDebito;
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
	 * @return the dataPeriodoDeb
	 */
	public String getDataPeriodoDeb() {
		return dataPeriodoDeb;
	}
	/**
	 * @param dataPeriodoDeb the dataPeriodoDeb to set
	 */
	public void setDataPeriodoDeb(String dataPeriodoDeb) {
		this.dataPeriodoDeb = dataPeriodoDeb;
	}
	/**
	 * @return the filtroUtilizado
	 */
	public String getFiltroUtilizado() {
		return filtroUtilizado;
	}
	/**
	 * @param filtroUtilizado the filtroUtilizado to set
	 */
	public void setFiltroUtilizado(String filtroUtilizado) {
		this.filtroUtilizado = filtroUtilizado;
	}
}