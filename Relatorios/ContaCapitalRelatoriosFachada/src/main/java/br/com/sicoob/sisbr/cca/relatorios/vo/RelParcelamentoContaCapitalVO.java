package br.com.sicoob.sisbr.cca.relatorios.vo;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe RelParcelamentoContaCapitalVO
 *
 * @author marco.nascimento
 */
public class RelParcelamentoContaCapitalVO extends BancoobVo {
	
	private Integer idInstituicao;
	private Integer numContaCapitalInicial;
	private Integer numContaCapitalFinal;
	private Integer tipoParcelamento;	
	private Integer formaParcelamento;
	private Integer situacaoParcela;	
	private DateTimeDB dataInicialVencimento;
	private DateTimeDB dataFinalVencimento;
	private DateTimeDB dataSituacao;
	private Integer numPA;
	private Integer ordenacao;
	
	
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
	 * @return the numContaCapitalInicial
	 */
	public Integer getNumContaCapitalInicial() {
		return numContaCapitalInicial;
	}
	/**
	 * @param numContaCapitalInicial the numContaCapitalInicial to set
	 */
	public void setNumContaCapitalInicial(Integer numContaCapitalInicial) {
		this.numContaCapitalInicial = numContaCapitalInicial;
	}
	/**
	 * @return the numContaCapitalFinal
	 */
	public Integer getNumContaCapitalFinal() {
		return numContaCapitalFinal;
	}
	/**
	 * @param numContaCapitalFinal the numContaCapitalFinal to set
	 */
	public void setNumContaCapitalFinal(Integer numContaCapitalFinal) {
		this.numContaCapitalFinal = numContaCapitalFinal;
	}
	/**
	 * @return the tipoParcelamento
	 */
	public Integer getTipoParcelamento() {
		return tipoParcelamento;
	}
	/**
	 * @param tipoParcelamento the tipoParcelamento to set
	 */
	public void setTipoParcelamento(Integer tipoParcelamento) {
		this.tipoParcelamento = tipoParcelamento;
	}
	/**
	 * @return the formaParcelamento
	 */
	public Integer getFormaParcelamento() {
		return formaParcelamento;
	}
	/**
	 * @param formaParcelamento the formaParcelamento to set
	 */
	public void setFormaParcelamento(Integer formaParcelamento) {
		this.formaParcelamento = formaParcelamento;
	}
	/**
	 * @return the situacaoParcela
	 */
	public Integer getSituacaoParcela() {
		return situacaoParcela;
	}
	/**
	 * @param situacaoParcela the situacaoParcela to set
	 */
	public void setSituacaoParcela(Integer situacaoParcela) {
		this.situacaoParcela = situacaoParcela;
	}
	/**
	 * @return the dataInicialVencimento
	 */
	public DateTimeDB getDataInicialVencimento() {
		return dataInicialVencimento;
	}
	/**
	 * @param dataInicialVencimento the dataInicialVencimento to set
	 */
	public void setDataInicialVencimento(DateTimeDB dataInicialVencimento) {
		this.dataInicialVencimento = dataInicialVencimento;
	}
	/**
	 * @return the dataFinalVencimento
	 */
	public DateTimeDB getDataFinalVencimento() {
		return dataFinalVencimento;
	}
	/**
	 * @param dataFinalVencimento the dataFinalVencimento to set
	 */
	public void setDataFinalVencimento(DateTimeDB dataFinalVencimento) {
		this.dataFinalVencimento = dataFinalVencimento;
	}
	/**
	 * @return the dataSituacao
	 */
	public DateTimeDB getDataSituacao() {
		return dataSituacao;
	}
	/**
	 * @param dataSituacao the dataSituacao to set
	 */
	public void setDataSituacao(DateTimeDB dataSituacao) {
		this.dataSituacao = dataSituacao;
	}
	/**
	 * @return the numPA
	 */
	public Integer getNumPA() {
		return numPA;
	}
	/**
	 * @param numPA the numPA to set
	 */
	public void setNumPA(Integer numPA) {
		this.numPA = numPA;
	}
	/**
	 * @return the ordenacao
	 */
	public Integer getOrdenacao() {
		return ordenacao;
	}
	/**
	 * @param ordenacao the ordenacao to set
	 */
	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}
}