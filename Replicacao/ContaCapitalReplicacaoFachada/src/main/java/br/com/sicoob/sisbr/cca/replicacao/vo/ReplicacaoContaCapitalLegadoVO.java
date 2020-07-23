/*
 * 
 */
package br.com.sicoob.sisbr.cca.replicacao.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.tipos.DateTime;

/**
 * VO ReplicacaoContaCapitalLegadoVO
 */
public class ReplicacaoContaCapitalLegadoVO extends BancoobVo {
	
	
	/**
	 * Replicacao
	 */
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
	private String siglaTabela;
	private Boolean selecionado;
	
	
	/**
	 * JSON -> Capital
	 */
	private Integer idContaCapital;
	private DateTime dataSaida;
	private Integer numMatricula;
	private DateTime dataMatricula;
	private Integer numCliente;
	private Integer codSituacao;
	private BigDecimal valorSaldoAtuInteg;
	private BigDecimal valorSaldoAtuSubsc;
	private BigDecimal valorSaldoAtuDevolver;
	private BigDecimal valorSaldoBloqInt;
	
	/**
	 * JSON -> Parcelamento
	 */
	private Integer numParcelamento;
	private Integer numParcela;
	private Integer codTipoParcelamento;
	private DateTime dataVencParcela;
	private DateTime dataSituacaoParcela;
	private BigDecimal valorParcela;
	private Integer codModoLanc;
	private Integer codSituacaoParcela;
	private Integer codMotivoDevolucao;
	private String numContaCorrente;
	private String uIDTrabalha;
	private String descObservacao;
	private Integer idParcelamentoContaCapital;
	
	/**
	 * JSON -> Lancamento
	 */
	private DateTime dataLote;
	private Integer numLoteLanc;
	private Integer numSeqLanc;
	private String descNumDocumento;
	private BigDecimal valorLanc;
	private Integer bolAtualizado;
	private Integer idTipoHistoricoLanc;
	private String idUsuarioResp;
	private DateTime dataHoraInclusao;
	private Integer idTipoHistoricoEstorno;
	

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
	 * Recupera a sigla da tabela
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

	public Integer getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Integer getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}

	public Integer getCodSituacao() {
		return codSituacao;
	}

	public void setCodSituacao(Integer codSituacao) {
		this.codSituacao = codSituacao;
	}

	public BigDecimal getValorSaldoAtuInteg() {
		return valorSaldoAtuInteg;
	}

	public void setValorSaldoAtuInteg(BigDecimal valorSaldoAtuInteg) {
		this.valorSaldoAtuInteg = valorSaldoAtuInteg;
	}

	public BigDecimal getValorSaldoAtuSubsc() {
		return valorSaldoAtuSubsc;
	}

	public void setValorSaldoAtuSubsc(BigDecimal valorSaldoAtuSubsc) {
		this.valorSaldoAtuSubsc = valorSaldoAtuSubsc;
	}

	public BigDecimal getValorSaldoAtuDevolver() {
		return valorSaldoAtuDevolver;
	}

	public void setValorSaldoAtuDevolver(BigDecimal valorSaldoAtuDevolver) {
		this.valorSaldoAtuDevolver = valorSaldoAtuDevolver;
	}

	public BigDecimal getValorSaldoBloqInt() {
		return valorSaldoBloqInt;
	}

	public void setValorSaldoBloqInt(BigDecimal valorSaldoBloqInt) {
		this.valorSaldoBloqInt = valorSaldoBloqInt;
	}

	public DateTime getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(DateTime dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Boolean getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}

	public Integer getNumParcelamento() {
		return numParcelamento;
	}

	public void setNumParcelamento(Integer numParcelamento) {
		this.numParcelamento = numParcelamento;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}

	public Integer getCodTipoParcelamento() {
		return codTipoParcelamento;
	}

	public void setCodTipoParcelamento(Integer codTipoParcelamento) {
		this.codTipoParcelamento = codTipoParcelamento;
	}

	public DateTime getDataVencParcela() {
		return dataVencParcela;
	}

	public void setDataVencParcela(DateTime dataVencParcela) {
		this.dataVencParcela = dataVencParcela;
	}

	public DateTime getDataSituacaoParcela() {
		return dataSituacaoParcela;
	}

	public void setDataSituacaoParcela(DateTime dataSituacaoParcela) {
		this.dataSituacaoParcela = dataSituacaoParcela;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Integer getCodModoLanc() {
		return codModoLanc;
	}

	public void setCodModoLanc(Integer codModoLanc) {
		this.codModoLanc = codModoLanc;
	}

	public Integer getCodSituacaoParcela() {
		return codSituacaoParcela;
	}

	public void setCodSituacaoParcela(Integer codSituacaoParcela) {
		this.codSituacaoParcela = codSituacaoParcela;
	}

	public Integer getCodMotivoDevolucao() {
		return codMotivoDevolucao;
	}

	public void setCodMotivoDevolucao(Integer codMotivoDevolucao) {
		this.codMotivoDevolucao = codMotivoDevolucao;
	}

	public DateTime getDataLote() {
		return dataLote;
	}

	public void setDataLote(DateTime dataLote) {
		this.dataLote = dataLote;
	}

	public Integer getNumLoteLanc() {
		return numLoteLanc;
	}

	public void setNumLoteLanc(Integer numLoteLanc) {
		this.numLoteLanc = numLoteLanc;
	}

	public Integer getNumSeqLanc() {
		return numSeqLanc;
	}

	public void setNumSeqLanc(Integer numSeqLanc) {
		this.numSeqLanc = numSeqLanc;
	}

	public String getDescNumDocumento() {
		return descNumDocumento;
	}

	public void setDescNumDocumento(String descNumDocumento) {
		this.descNumDocumento = descNumDocumento;
	}

	public BigDecimal getValorLanc() {
		return valorLanc;
	}

	public void setValorLanc(BigDecimal valorLanc) {
		this.valorLanc = valorLanc;
	}

	public Integer getBolAtualizado() {
		return bolAtualizado;
	}

	public void setBolAtualizado(Integer bolAtualizado) {
		this.bolAtualizado = bolAtualizado;
	}

	public Integer getIdTipoHistoricoLanc() {
		return idTipoHistoricoLanc;
	}

	public void setIdTipoHistoricoLanc(Integer idTipoHistoricoLanc) {
		this.idTipoHistoricoLanc = idTipoHistoricoLanc;
	}

	public String getIdUsuarioResp() {
		return idUsuarioResp;
	}

	public void setIdUsuarioResp(String idUsuarioResp) {
		this.idUsuarioResp = idUsuarioResp;
	}

	public DateTime getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	public void setDataHoraInclusao(DateTime dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}

	public Integer getIdContaCapital() {
		return idContaCapital;
	}

	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}

	public DateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(DateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(String numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	public String getuIDTrabalha() {
		return uIDTrabalha;
	}

	public void setuIDTrabalha(String uIDTrabalha) {
		this.uIDTrabalha = uIDTrabalha;
	}

	public String getDescObservacao() {
		return descObservacao;
	}

	public void setDescObservacao(String descObservacao) {
		this.descObservacao = descObservacao;
	}

	public Integer getIdParcelamentoContaCapital() {
		return idParcelamentoContaCapital;
	}

	public void setIdParcelamentoContaCapital(Integer idParcelamentoContaCapital) {
		this.idParcelamentoContaCapital = idParcelamentoContaCapital;
	}

	public Integer getIdTipoHistoricoEstorno() {
		return idTipoHistoricoEstorno;
	}

	public void setIdTipoHistoricoEstorno(Integer idTipoHistoricoEstorno) {
		this.idTipoHistoricoEstorno = idTipoHistoricoEstorno;
	}
}