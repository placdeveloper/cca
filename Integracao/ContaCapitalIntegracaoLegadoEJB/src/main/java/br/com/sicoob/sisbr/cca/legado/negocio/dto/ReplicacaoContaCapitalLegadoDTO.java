/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * DTO ReplicacaoContaCapitalLegadoDTO
 */
public class ReplicacaoContaCapitalLegadoDTO extends BancoobDto {

	private static final long serialVersionUID = -4464963097456240090L;

	private Long idReplicacaoCCA;
	
	private Integer idTabelaReplicadaCCA;
	
	private Integer idSituacaoReplicacaoCCA;
	
	private String codAcao;
	
	private String descChaveReplicacaoSQL; 
	
	private String descChaveReplicacaoDB2;

	private DateTime dataHoraCadastro;	
	
	private DateTime dataHoraReplicacao;

	private Integer numCooperativa;
	
	private Integer idInstituicao;
	
	private String descMensagemReplicacao;
	
	private Long countMonitoracao;
	
	private String siglaTabela;
	
	public Long getIdReplicacaoCCA() {
		return idReplicacaoCCA;
	}

	public void setIdReplicacaoCCA(Long idReplicacaoCCA) {
		this.idReplicacaoCCA = idReplicacaoCCA;
	}

	public Integer getIdTabelaReplicadaCCA() {
		return idTabelaReplicadaCCA;
	}

	public void setIdTabelaReplicadaCCA(Integer idTabelaReplicadaCCA) {
		this.idTabelaReplicadaCCA = idTabelaReplicadaCCA;
	}

	public Integer getIdSituacaoReplicacaoCCA() {
		return idSituacaoReplicacaoCCA;
	}

	public void setIdSituacaoReplicacaoCCA(Integer idSituacaoReplicacaoCCA) {
		this.idSituacaoReplicacaoCCA = idSituacaoReplicacaoCCA;
	}

	public String getCodAcao() {
		return codAcao;
	}

	public void setCodAcao(String codAcao) {
		this.codAcao = codAcao;
	}

	public String getDescChaveReplicacaoSQL() {
		return descChaveReplicacaoSQL;
	}

	public void setDescChaveReplicacaoSQL(String descChaveReplicacaoSQL) {
		this.descChaveReplicacaoSQL = descChaveReplicacaoSQL;
	}

	public String getDescChaveReplicacaoDB2() {
		return descChaveReplicacaoDB2;
	}

	public void setDescChaveReplicacaoDB2(String descChaveReplicacaoDB2) {
		this.descChaveReplicacaoDB2 = descChaveReplicacaoDB2;
	}
	
	public DateTime getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(DateTime dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public DateTime getDataHoraReplicacao() {
		return dataHoraReplicacao;
	}

	public void setDataHoraReplicacao(DateTime dataHoraReplicacao) {
		this.dataHoraReplicacao = dataHoraReplicacao;
	}

	public Integer getNumCooperativa() {
		return numCooperativa;
	}

	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getDescMensagemReplicacao() {
		return descMensagemReplicacao;
	}

	public void setDescMensagemReplicacao(String descMensagemReplicacao) {
		this.descMensagemReplicacao = descMensagemReplicacao;
	}

	public Long getCountMonitoracao() {
		return countMonitoracao;
	}

	public void setCountMonitoracao(Long countMonitoracao) {
		this.countMonitoracao = countMonitoracao;
	}

	/**
	 * NÃO APAGAR 
	 * Seta as chaves Integer e Long das tabelas na estrutura de replicação
	 * @param descChaveReplicacaoDB2
	 */
	public void setDescChaveReplicacaoDB2(Object descChaveReplicacaoDB2){
		if (descChaveReplicacaoDB2 !=null){
			this.descChaveReplicacaoDB2 = descChaveReplicacaoDB2.toString();
		}else{
			this.descChaveReplicacaoDB2 = null;
		}
	}

	/**
	 * Retorna a sigla da tabela
	 * @return
	 */
	public String getSiglaTabela() {
		final int cca = 1;
		final int parc = 2;
		final int lanc = 3;
		if(idTabelaReplicadaCCA != null) {
			switch (idTabelaReplicadaCCA) {
				case cca: 
					return "CCA";
					
				case parc:
					return "PARC";
					
				case lanc:
					return "LANC";
			}
		}
		
		return siglaTabela;
	}

	public void setSiglaTabela(String siglaTabela) {
		this.siglaTabela = siglaTabela;
	}
}