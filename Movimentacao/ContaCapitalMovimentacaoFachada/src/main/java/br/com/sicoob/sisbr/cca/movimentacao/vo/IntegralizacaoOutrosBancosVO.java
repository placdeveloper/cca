package br.com.sicoob.sisbr.cca.movimentacao.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * IntegralizacaoOutrosBancosVO
 */
public class IntegralizacaoOutrosBancosVO extends BancoobVo {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer numCliente;
	private Integer numMatricula;
	private String descNomePessoa;
	private String numCpfCnpj;
	private Integer numBanco;
	private Integer numAgencia;
	private String numDVAgencia;
	private String numContaCorrente;
	private Integer numDVContaCorrente;
	private Integer numBancoFavorecido;
	private Integer numAgenciaFavorecido;
	private String numContaFavorecido;
	private String numDVAgenciaFavorecido;
	private String descBanco;
	private BigDecimal valorIntegralizacao;
	private String valorIntegralizacaoStr;
	private Boolean contaPrincipal;	
	private Boolean selecionado;
	private Boolean bolIntegralizadoCapital;
	private Integer sequencialArquivo;
	private Integer sequencialDetalhe;
	private Integer numParcela;
	private String nomeArquivo;		
	private String strIntegralizadoCapital;			
	
	public Integer getSequencialArquivo() {
		return sequencialArquivo;
	}
	public void setSequencialArquivo(Integer sequencialArquivo) {
		this.sequencialArquivo = sequencialArquivo;
	}
	public Integer getSequencialDetalhe() {
		return sequencialDetalhe;
	}
	public void setSequencialDetalhe(Integer sequencialDetalhe) {
		this.sequencialDetalhe = sequencialDetalhe;
	}
	public Integer getNumParcela() {
		return numParcela;
	}
	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public Integer getNumCliente() {
		return numCliente;
	}
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
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
	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}
	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}
	public Integer getNumBancoFavorecido() {
		return numBancoFavorecido;
	}
	public void setNumBancoFavorecido(Integer numBancoFavorecido) {
		this.numBancoFavorecido = numBancoFavorecido;
	}
	public Integer getNumAgenciaFavorecido() {
		return numAgenciaFavorecido;
	}
	public void setNumAgenciaFavorecido(Integer numAgenciaFavorecido) {
		this.numAgenciaFavorecido = numAgenciaFavorecido;
	}
	public String getNumContaFavorecido() {
		return numContaFavorecido;
	}
	public void setNumContaFavorecido(String numContaFavorecido) {
		this.numContaFavorecido = numContaFavorecido;
	}
	public String getDescBanco() {
		return descBanco;
	}
	public void setDescBanco(String descBanco) {
		this.descBanco = descBanco;
	}
	public BigDecimal getValorIntegralizacao() {
		return valorIntegralizacao;
	}
	public void setValorIntegralizacao(BigDecimal valorIntegralizacao) {
		this.valorIntegralizacao = valorIntegralizacao;
	}
	public Boolean getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}
	public String getValorIntegralizacaoStr() {
		return valorIntegralizacaoStr;
	}
	public void setValorIntegralizacaoStr(String valorIntegralizacaoStr) {
		this.valorIntegralizacaoStr = valorIntegralizacaoStr;
	}
	public Integer getNumBanco() {
		return numBanco;
	}
	public void setNumBanco(Integer numBanco) {
		this.numBanco = numBanco;
	}
	public Integer getNumAgencia() {
		return numAgencia;
	}
	public void setNumAgencia(Integer numAgencia) {
		this.numAgencia = numAgencia;
	}
	public String getNumDVAgencia() {
		return numDVAgencia;
	}
	public void setNumDVAgencia(String numDVAgencia) {
		this.numDVAgencia = numDVAgencia;
	}
	public String getNumContaCorrente() {
		return numContaCorrente;
	}
	public void setNumContaCorrente(String numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
	public Integer getNumDVContaCorrente() {
		return numDVContaCorrente;
	}
	public void setNumDVContaCorrente(Integer numDVContaCorrente) {
		this.numDVContaCorrente = numDVContaCorrente;
	}
	public String getNumDVAgenciaFavorecido() {
		return numDVAgenciaFavorecido;
	}
	public void setNumDVAgenciaFavorecido(String numDVAgenciaFavorecido) {
		this.numDVAgenciaFavorecido = numDVAgenciaFavorecido;
	}
	public Boolean getContaPrincipal() {
		return contaPrincipal;
	}
	public void setContaPrincipal(Boolean contaPrincipal) {
		this.contaPrincipal = contaPrincipal;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getBolIntegralizadoCapital() {
		return bolIntegralizadoCapital;
	}
	public void setBolIntegralizadoCapital(Boolean bolIntegralizadoCapital) {
		this.bolIntegralizadoCapital = bolIntegralizadoCapital;
	}
	public String getStrIntegralizadoCapital() {
		return strIntegralizadoCapital;
	}
	public void setStrIntegralizadoCapital(String strIntegralizadoCapital) {
		this.strIntegralizadoCapital = strIntegralizadoCapital;
	}

}
