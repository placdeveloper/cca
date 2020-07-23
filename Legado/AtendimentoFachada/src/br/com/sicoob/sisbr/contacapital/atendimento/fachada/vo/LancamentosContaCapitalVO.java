package br.com.sicoob.sisbr.contacapital.atendimento.fachada.vo;
 
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import br.com.sicoob.tipos.DateTime;

/**
 * Classe migrada do projeto SisbrVO. A classe antiga continua a existir, até a conclusão dos trabalhos.
 * 
 * @author Stefanini IT Sollutions
 *
 */
public class LancamentosContaCapitalVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private DateTime dataLote;
	private Integer numLoteLanc;
	private Integer numSeqLanc;
	private String descNumDocumento;
	private BigDecimal valorLanc;
	private Integer numMatricula;
	private String descNomePessoa;
	private Integer idTipoHistoricoLanc;
	private Integer idTipoHistoricoEstorno;
	private Integer idProduto;
	private Boolean bolAtualizado;
	private Integer idProdutoEst;
	private DateTime dataHoraInclusao;
	private String descHistorico;
	
	private DateTime dataLoteInicial;
	private DateTime dataLoteFinal;
	
	public String getDataLoteBD() {
		if(getDataLote() != null)
			return new SimpleDateFormat("yyyy-MM-dd").format(getDataLote());
		else
			return "";
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

	public Integer getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}

	public String getDescNomePessoa() {
		return descNomePessoa;
	}

	public void setDescNomePessoa(String descNomePessoa) {
		this.descNomePessoa = descNomePessoa;
	}

	public Integer getIdTipoHistoricoLanc() {
		return idTipoHistoricoLanc;
	}

	public void setIdTipoHistoricoLanc(Integer idTipoHistoricoLanc) {
		this.idTipoHistoricoLanc = idTipoHistoricoLanc;
	}

	public Integer getIdTipoHistoricoEstorno() {
		return idTipoHistoricoEstorno;
	}

	public void setIdTipoHistoricoEstorno(Integer idTipoHistoricoEstorno) {
		this.idTipoHistoricoEstorno = idTipoHistoricoEstorno;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Boolean getBolAtualizado() {
		return bolAtualizado;
	}

	public void setBolAtualizado(Boolean bolAtualizado) {
		this.bolAtualizado = bolAtualizado;
	}

	public Integer getIdProdutoEst() {
		return idProdutoEst;
	}

	public void setIdProdutoEst(Integer idProdutoEst) {
		this.idProdutoEst = idProdutoEst;
	}

	public DateTime getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	public void setDataHoraInclusao(DateTime dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}

	public String getDescHistorico() {
		return descHistorico;
	}

	public void setDescHistorico(String descHistorico) {
		this.descHistorico = descHistorico;
	}

	public DateTime getDataLoteInicial() {
		return dataLoteInicial;
	}

	public void setDataLoteInicial(DateTime dataLoteInicial) {
		this.dataLoteInicial = dataLoteInicial;
	}

	public DateTime getDataLoteFinal() {
		return dataLoteFinal;
	}

	public void setDataLoteFinal(DateTime dataLoteFinal) {
		this.dataLoteFinal = dataLoteFinal;
	}
}