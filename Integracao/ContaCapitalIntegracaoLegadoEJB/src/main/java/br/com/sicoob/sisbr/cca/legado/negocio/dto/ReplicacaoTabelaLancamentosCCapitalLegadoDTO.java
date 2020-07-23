/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * DTO ReplicacaoTabelaLancamentosCCapitalLegadoDTO
 */
public class ReplicacaoTabelaLancamentosCCapitalLegadoDTO extends BancoobDto {

	private static final long serialVersionUID = 2027768670731667369L;
	
	private DateTime dataLote;
	private Integer numLoteLanc;
	private Integer numSeqLanc;
	private Integer numMatricula;
	private String descNumDocumento;
	private BigDecimal valorLanc;
	private Boolean bolAtualizado;
	private Integer idProduto;
	private Integer idTipoHistoricoLanc;
	private Integer idProdutoEst;
	private Integer idTipoHistoricoEstorno;
	private String idUsuarioResp;
	private DateTime dataHoraInclusao;
	private Integer codMotivoDevolucao;
	private String descObsDevolucao;
	private Long idLancamentoContaCapital;
	private Integer idContaCapital;
	private Integer numCooperativa;
	private Integer idInstituicao;
	
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
	public Integer getNumMatricula() {
		return numMatricula;
	}
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
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
	public Boolean getBolAtualizado() {
		return bolAtualizado;
	}
	public void setBolAtualizado(Boolean bolAtualizado) {
		this.bolAtualizado = bolAtualizado;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getIdTipoHistoricoLanc() {
		return idTipoHistoricoLanc;
	}
	public void setIdTipoHistoricoLanc(Integer idTipoHistoricoLanc) {
		this.idTipoHistoricoLanc = idTipoHistoricoLanc;
	}
	public Integer getIdProdutoEst() {
		return idProdutoEst;
	}
	public void setIdProdutoEst(Integer idProdutoEst) {
		this.idProdutoEst = idProdutoEst;
	}
	public Integer getIdTipoHistoricoEstorno() {
		return idTipoHistoricoEstorno;
	}
	public void setIdTipoHistoricoEstorno(Integer idTipoHistoricoEstorno) {
		this.idTipoHistoricoEstorno = idTipoHistoricoEstorno;
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
	public Integer getCodMotivoDevolucao() {
		return codMotivoDevolucao;
	}
	public void setCodMotivoDevolucao(Integer codMotivoDevolucao) {
		this.codMotivoDevolucao = codMotivoDevolucao;
	}
	public String getDescObsDevolucao() {
		return descObsDevolucao;
	}
	public void setDescObsDevolucao(String descObsDevolucao) {
		this.descObsDevolucao = descObsDevolucao;
	}
	public Long getIdLancamentoContaCapital() {
		return idLancamentoContaCapital;
	}
	public void setIdLancamentoContaCapital(Long idLancamentoContaCapital) {
		this.idLancamentoContaCapital = idLancamentoContaCapital;
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
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	
}
