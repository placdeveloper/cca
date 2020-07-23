package br.com.sicoob.sisbr.contacapital.atendimento.fachada.vo;
 
import java.io.Serializable;
import java.math.BigDecimal;

import br.com.sicoob.tipos.DateTime;

/**
 * Classe migrada do projeto SisbrVO. A classe antiga continua a existir, até a conclusão dos trabalhos.
 * 
 * @author Stefanini IT Sollutions
 *
 */
public class CapaLoteCapitalVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private DateTime dataLote;
	private Long numLoteLanc;
	private Long qtdLancInf; 
	private Long qtdLancApu; 
	private BigDecimal valorTotalLoteApu; 
	private BigDecimal valorTotalLoteInf; 
	private Boolean bolAtualizado;   
	private Integer codOrigemLote; 
	private Integer idProduto;
	private Integer idTipoHistoricoLanc;
	private Integer idProdutoEst;
	private Integer idTipoHistoricoEstorno;
	
	private String situacao;
	private String origem;
	
	public DateTime getDataLote() {
		return dataLote;
	}
	public void setDataLote(DateTime dataLote) {
		this.dataLote = dataLote;
	}
	public Long getNumLoteLanc() {
		return numLoteLanc;
	}
	public void setNumLoteLanc(Long numLoteLanc) {
		this.numLoteLanc = numLoteLanc;
	}
	public Long getQtdLancInf() {
		return qtdLancInf;
	}
	public void setQtdLancInf(Long qtdLancInf) {
		this.qtdLancInf = qtdLancInf;
	}
	public Long getQtdLancApu() {
		return qtdLancApu;
	}
	public void setQtdLancApu(Long qtdLancApu) {
		this.qtdLancApu = qtdLancApu;
	}
	public BigDecimal getValorTotalLoteApu() {
		return valorTotalLoteApu;
	}
	public void setValorTotalLoteApu(BigDecimal valorTotalLoteApu) {
		this.valorTotalLoteApu = valorTotalLoteApu;
	}
	public BigDecimal getValorTotalLoteInf() {
		return valorTotalLoteInf;
	}
	public void setValorTotalLoteInf(BigDecimal valorTotalLoteInf) {
		this.valorTotalLoteInf = valorTotalLoteInf;
	}
	public Boolean getBolAtualizado() {
		return bolAtualizado;
	}
	public void setBolAtualizado(Boolean bolAtualizado) {
		this.bolAtualizado = bolAtualizado;
	}
	public Integer getCodOrigemLote() {
		return codOrigemLote;
	}
	public void setCodOrigemLote(Integer codOrigemLote) {
		this.codOrigemLote = codOrigemLote;
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
	
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	

}
