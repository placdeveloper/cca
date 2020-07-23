package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * Lancamento simplificado
 * @author Nairon.Silva
 */
public class LancamentoCCADTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer idLancamentoContaCapital;
	private Integer idTipoHistorico;
	private Integer idTipoLote;
	private Integer idContaCapital;
	private Integer idInstituicao;
	private BigDecimal valorLancamento;
	private Integer numSeqLanc;
	private String codNaturezaOperacao;
	private Integer idGrupoHistorico;
	private Integer idTipoHistoricoEstorno;
	private String codNaturezaOperacaoEstorno;

	public Integer getIdLancamentoContaCapital() {
		return idLancamentoContaCapital;
	}

	public void setIdLancamentoContaCapital(Integer idLancamentoContaCapital) {
		this.idLancamentoContaCapital = idLancamentoContaCapital;
	}

	public Integer getIdTipoHistorico() {
		return idTipoHistorico;
	}

	public void setIdTipoHistorico(Integer idTipoHistorico) {
		this.idTipoHistorico = idTipoHistorico;
	}

	public Integer getIdTipoLote() {
		return idTipoLote;
	}

	public void setIdTipoLote(Integer idTipoLote) {
		this.idTipoLote = idTipoLote;
	}

	public Integer getIdContaCapital() {
		return idContaCapital;
	}

	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public Integer getNumSeqLanc() {
		return numSeqLanc;
	}

	public void setNumSeqLanc(Integer numSeqLanc) {
		this.numSeqLanc = numSeqLanc;
	}

	public String getCodNaturezaOperacao() {
		return codNaturezaOperacao;
	}

	public void setCodNaturezaOperacao(String codNaturezaOperacao) {
		this.codNaturezaOperacao = codNaturezaOperacao;
	}

	public Integer getIdGrupoHistorico() {
		return idGrupoHistorico;
	}

	public void setIdGrupoHistorico(Integer idGrupoHistorico) {
		this.idGrupoHistorico = idGrupoHistorico;
	}

	public Integer getIdTipoHistoricoEstorno() {
		return idTipoHistoricoEstorno;
	}

	public void setIdTipoHistoricoEstorno(Integer idTipoHistoricoEstorno) {
		this.idTipoHistoricoEstorno = idTipoHistoricoEstorno;
	}

	public String getCodNaturezaOperacaoEstorno() {
		return codNaturezaOperacaoEstorno;
	}

	public void setCodNaturezaOperacaoEstorno(String codNaturezaOperacaoEstorno) {
		this.codNaturezaOperacaoEstorno = codNaturezaOperacaoEstorno;
	}
	
}
