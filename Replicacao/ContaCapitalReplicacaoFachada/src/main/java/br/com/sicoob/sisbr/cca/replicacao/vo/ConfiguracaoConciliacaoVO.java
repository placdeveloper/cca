package br.com.sicoob.sisbr.cca.replicacao.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * VO ConfiguracaoConciliacaoVO
 */
public class ConfiguracaoConciliacaoVO extends BancoobVo {

	private static final long serialVersionUID = -667673082373006592L;
	
	private Integer central;
	private Integer cooperativa;
	private DateTimeDB dataLote;
	private Integer numLoteLanc;
	private Integer numSeqLanc;
	private BigDecimal valorLanc;
	private Integer numMatricula;
	private Integer idHistorico;
	private DateTimeDB dataHoraInclusao;
	private Integer numCliente;
	private String dataMatricula;
	private Integer codGrupoHist;
	
	private BigDecimal valorSaldoAtuSubsc;
	private BigDecimal valorLancSubscDoDia;
	private BigDecimal valorSaldoAtuInteg;
	private BigDecimal valorParcEmAberto;
	private BigDecimal valorLancIntegDoDia;
	private BigDecimal valorDiferenca;
	
	private BigDecimal valorSaldoAtuDevol;
	private BigDecimal valorSaldoBloqInt;
	
	private BigDecimal valorCapaLote;
	private Long qtdCapaLote;
	private BigDecimal valorLancamentos;
	private Long qtdLancamentos;
	
	private String script;
	private String auxiliar1;
	private String auxiliar2;
	private String auxiliar3;
	private String auxiliar4;
	private String auxiliar5;
	
	public Integer getCentral() {
		return central;
	}

	public void setCentral(Integer central) {
		this.central = central;
	}

	public Integer getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}

	public String getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(String dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public BigDecimal getValorSaldoAtuDevol() {
		return valorSaldoAtuDevol;
	}

	public void setValorSaldoAtuDevol(BigDecimal valorSaldoAtuDevol) {
		this.valorSaldoAtuDevol = valorSaldoAtuDevol;
	}

	public BigDecimal getValorSaldoBloqInt() {
		return valorSaldoBloqInt;
	}

	public void setValorSaldoBloqInt(BigDecimal valorSaldoBloqInt) {
		this.valorSaldoBloqInt = valorSaldoBloqInt;
	}

	public Integer getCooperativa() {
		return cooperativa;
	}

	public void setCooperativa(Integer cooperativa) {
		this.cooperativa = cooperativa;
	}

	public DateTimeDB getDataLote() {
		return dataLote;
	}

	public void setDataLote(DateTimeDB dataLote) {
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

	public BigDecimal getValorLanc() {
		return valorLanc;
	}

	public void setValorLanc(BigDecimal valorLanc) {
		this.valorLanc = valorLanc;
	}

	public Integer getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Integer getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}

	public DateTimeDB getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	public void setDataHoraInclusao(DateTimeDB dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}

	public BigDecimal getValorSaldoAtuSubsc() {
		return valorSaldoAtuSubsc;
	}

	public void setValorSaldoAtuSubsc(BigDecimal valorSaldoAtuSubsc) {
		this.valorSaldoAtuSubsc = valorSaldoAtuSubsc;
	}

	public BigDecimal getValorLancSubscDoDia() {
		return valorLancSubscDoDia;
	}

	public void setValorLancSubscDoDia(BigDecimal valorLancSubscDoDia) {
		this.valorLancSubscDoDia = valorLancSubscDoDia;
	}

	public BigDecimal getValorSaldoAtuInteg() {
		return valorSaldoAtuInteg;
	}

	public void setValorSaldoAtuInteg(BigDecimal valorSaldoAtuInteg) {
		this.valorSaldoAtuInteg = valorSaldoAtuInteg;
	}

	public BigDecimal getValorParcEmAberto() {
		return valorParcEmAberto;
	}

	public void setValorParcEmAberto(BigDecimal valorParcEmAberto) {
		this.valorParcEmAberto = valorParcEmAberto;
	}

	public BigDecimal getValorLancIntegDoDia() {
		return valorLancIntegDoDia;
	}

	public void setValorLancIntegDoDia(BigDecimal valorLancIntegDoDia) {
		this.valorLancIntegDoDia = valorLancIntegDoDia;
	}

	public BigDecimal getValorDiferenca() {
		return valorDiferenca;
	}

	public void setValorDiferenca(BigDecimal valorDiferenca) {
		this.valorDiferenca = valorDiferenca;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public BigDecimal getValorCapaLote() {
		return valorCapaLote;
	}

	public void setValorCapaLote(BigDecimal valorCapaLote) {
		this.valorCapaLote = valorCapaLote;
	}

	public Long getQtdCapaLote() {
		return qtdCapaLote;
	}

	public void setQtdCapaLote(Long qtdCapaLote) {
		this.qtdCapaLote = qtdCapaLote;
	}

	public BigDecimal getValorLancamentos() {
		return valorLancamentos;
	}

	public void setValorLancamentos(BigDecimal valorLancamentos) {
		this.valorLancamentos = valorLancamentos;
	}

	public Long getQtdLancamentos() {
		return qtdLancamentos;
	}

	public void setQtdLancamentos(Long qtdLancamentos) {
		this.qtdLancamentos = qtdLancamentos;
	}

	public Integer getCodGrupoHist() {
		return codGrupoHist;
	}

	public void setCodGrupoHist(Integer codGrupoHist) {
		this.codGrupoHist = codGrupoHist;
	}

	public String getAuxiliar1() {
		return auxiliar1;
	}

	public void setAuxiliar1(String auxiliar1) {
		this.auxiliar1 = auxiliar1;
	}

	public String getAuxiliar2() {
		return auxiliar2;
	}

	public void setAuxiliar2(String auxiliar2) {
		this.auxiliar2 = auxiliar2;
	}

	public String getAuxiliar3() {
		return auxiliar3;
	}

	public void setAuxiliar3(String auxiliar3) {
		this.auxiliar3 = auxiliar3;
	}

	public String getAuxiliar4() {
		return auxiliar4;
	}

	public void setAuxiliar4(String auxiliar4) {
		this.auxiliar4 = auxiliar4;
	}

	public String getAuxiliar5() {
		return auxiliar5;
	}

	public void setAuxiliar5(String auxiliar5) {
		this.auxiliar5 = auxiliar5;
	}
	
}
