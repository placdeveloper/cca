package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO para consulta de monitoracao
 * @author Nairon.Silva
 */
public class ConsultaMonitoracaoDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private static final int QTD_REGISTROS_PADRAO = 1000;
	
	private Boolean apenasPilotos;
	private Integer numCooperativa;
	private String visao;
	private String situacao;
	private Date data;
	private Date periodoDe;
	private Date periodoAte;
	private int qtdRegistros = QTD_REGISTROS_PADRAO;
	private String tabela;
	private String dataCadastro;
	private String dataReplicacao;
	private String acao;
	
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getDataReplicacao() {
		return dataReplicacao;
	}
	public void setDataReplicacao(String dataReplicacao) {
		this.dataReplicacao = dataReplicacao;
	}
	public Boolean getApenasPilotos() {
		return apenasPilotos;
	}
	public void setApenasPilotos(Boolean apenasPilotos) {
		this.apenasPilotos = apenasPilotos;
	}
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getPeriodoDe() {
		return periodoDe;
	}
	public void setPeriodoDe(Date periodoDe) {
		this.periodoDe = periodoDe;
	}
	public Date getPeriodoAte() {
		return periodoAte;
	}
	public void setPeriodoAte(Date periodoAte) {
		this.periodoAte = periodoAte;
	}
	public String getVisao() {
		return visao;
	}
	public void setVisao(String visao) {
		this.visao = visao;
	}
	
	/**
	 * Verifica se deve consultar a visao geral
	 * @return
	 */
	public boolean possuiVisaoGeral() {
		return visao != null && visao.contains("1");
	}
	
	/**
	 * Verifica se deve consultar a visao de cooperativas
	 * @return
	 */
	public boolean possuiVisaoCooperativas() {
		return visao != null && visao.contains("2");
	}
	public int getQtdRegistros() {
		return qtdRegistros;
	}
	public void setQtdRegistros(int qtdRegistros) {
		this.qtdRegistros = qtdRegistros;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}

}
