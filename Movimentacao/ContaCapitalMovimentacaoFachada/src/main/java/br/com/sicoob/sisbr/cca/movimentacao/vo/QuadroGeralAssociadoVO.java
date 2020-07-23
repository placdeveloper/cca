package br.com.sicoob.sisbr.cca.movimentacao.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe QuadroGeralAssociadoVO
 *
 * @author marco.nascimento
 */
public class QuadroGeralAssociadoVO extends BancoobVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 725086064965064684L;
	
	private String tipoPessoa;
	private Integer qtdPessoasComDebito;
	private Integer qtdPessoasSemDebito;
	private Integer totalAssociados;
	
	/**
	** Debitos via CCO por DIA FIXO
	*/
	private Integer dia;
	private Integer qtdPorDiaFixo;
	private BigDecimal valorTotalPorDiaFixo;
	
	/**
	 * Debitos via CCO por INTERVALO
	 */
	private Integer intervaloDias;
	private Integer qtdIntervalo;
	private BigDecimal valorTotalIntervalo;
	
	/**
	 ** Debitos via FOLHA/BANCO
	 */
	private Integer formaCalculo;
	private String descFormaCalculo;
	private Integer qtdDebitos;
	
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
	 * @return the qtdPessoasComDebito
	 */
	public Integer getQtdPessoasComDebito() {
		return qtdPessoasComDebito;
	}
	/**
	 * @param qtdPessoasComDebito the qtdPessoasComDebito to set
	 */
	public void setQtdPessoasComDebito(Integer qtdPessoasComDebito) {
		this.qtdPessoasComDebito = qtdPessoasComDebito;
	}
	/**
	 * @return the qtdPessoasSemDebito
	 */
	public Integer getQtdPessoasSemDebito() {
		return qtdPessoasSemDebito;
	}
	/**
	 * @param qtdPessoasSemDebito the qtdPessoasSemDebito to set
	 */
	public void setQtdPessoasSemDebito(Integer qtdPessoasSemDebito) {
		this.qtdPessoasSemDebito = qtdPessoasSemDebito;
	}
	/**
	 * @return the totalAssociados
	 */
	public Integer getTotalAssociados() {
		return totalAssociados;
	}
	/**
	 * @param totalAssociados the totalAssociados to set
	 */
	public void setTotalAssociados(Integer totalAssociados) {
		this.totalAssociados = totalAssociados;
	}
	/**
	 * @return the dia
	 */
	public Integer getDia() {
		return dia;
	}
	/**
	 * @param dia the dia to set
	 */
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	/**
	 * @return the qtdPorDiaFixo
	 */
	public Integer getQtdPorDiaFixo() {
		return qtdPorDiaFixo;
	}
	/**
	 * @param qtdPorDiaFixo the qtdPorDiaFixo to set
	 */
	public void setQtdPorDiaFixo(Integer qtdPorDiaFixo) {
		this.qtdPorDiaFixo = qtdPorDiaFixo;
	}
	/**
	 * @return the intervaloDias
	 */
	public Integer getIntervaloDias() {
		return intervaloDias;
	}
	/**
	 * @param intervaloDias the intervaloDias to set
	 */
	public void setIntervaloDias(Integer intervaloDias) {
		this.intervaloDias = intervaloDias;
	}
	/**
	 * @return the qtdIntervalo
	 */
	public Integer getQtdIntervalo() {
		return qtdIntervalo;
	}
	/**
	 * @param qtdIntervalo the qtdIntervalo to set
	 */
	public void setQtdIntervalo(Integer qtdIntervalo) {
		this.qtdIntervalo = qtdIntervalo;
	}
	/**
	 * @return the formaCalculo
	 */
	public Integer getFormaCalculo() {
		return formaCalculo;
	}
	/**
	 * @param formaCalculo the formaCalculo to set
	 */
	public void setFormaCalculo(Integer formaCalculo) {
		this.formaCalculo = formaCalculo;
	}
	/**
	 * @return the qtdDebitos
	 */
	public Integer getQtdDebitos() {
		return qtdDebitos;
	}
	/**
	 * @param qtdDebitos the qtdDebitos to set
	 */
	public void setQtdDebitos(Integer qtdDebitos) {
		this.qtdDebitos = qtdDebitos;
	}
	/**
	 * @return the valorTotalPorDiaFixo
	 */
	public BigDecimal getValorTotalPorDiaFixo() {
		return valorTotalPorDiaFixo;
	}
	/**
	 * @param valorTotalPorDiaFixo the valorTotalPorDiaFixo to set
	 */
	public void setValorTotalPorDiaFixo(BigDecimal valorTotalPorDiaFixo) {
		this.valorTotalPorDiaFixo = valorTotalPorDiaFixo;
	}
	/**
	 * @return the valorTotalIntervalo
	 */
	public BigDecimal getValorTotalIntervalo() {
		return valorTotalIntervalo;
	}
	/**
	 * @param valorTotalIntervalo the valorTotalIntervalo to set
	 */
	public void setValorTotalIntervalo(BigDecimal valorTotalIntervalo) {
		this.valorTotalIntervalo = valorTotalIntervalo;
	}
	/**
	 * @return the descFormaCalculo
	 */
	public String getDescFormaCalculo() {
		return descFormaCalculo;
	}
	/**
	 * @param descFormaCalculo the descFormaCalculo to set
	 */
	public void setDescFormaCalculo(String descFormaCalculo) {
		this.descFormaCalculo = descFormaCalculo;
	}
}