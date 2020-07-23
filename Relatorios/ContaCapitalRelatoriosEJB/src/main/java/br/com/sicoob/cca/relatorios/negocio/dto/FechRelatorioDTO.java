package br.com.sicoob.cca.relatorios.negocio.dto;

import java.io.Serializable;
import java.util.Date;

/**
* @author Ricardo.Barcante
*/
public class FechRelatorioDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3411379553318529233L;

	private Integer idInstituicao;
	private Date data;
	
	private String codRelatorio;
	private String descricao;
	private String caminho;
	
	private byte[] reportStream;
	private String reportName;
	
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getCodRelatorio() {
		return codRelatorio;
	}
	public void setCodRelatorio(String codRelatorio) {
		this.codRelatorio = codRelatorio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public byte[] getReportStream() {
		return reportStream;
	}
	public void setReportStream(byte[] reportStream) {
		this.reportStream = reportStream;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}	
}
