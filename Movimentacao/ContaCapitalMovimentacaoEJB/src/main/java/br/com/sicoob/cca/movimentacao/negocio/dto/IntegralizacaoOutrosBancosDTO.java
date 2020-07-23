package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;

public class IntegralizacaoOutrosBancosDTO extends BancoobDto {

	/**
	 * 
	 */
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
	private Boolean contaPrincipal;
	private Boolean bolIntegralizadoCapital;
	private Integer sequencialArquivo;
	private Integer sequencialDetalhe;
	private Integer numParcela;
	private String nomeArquivo;
	private Integer numCoop;
	private Integer idInstituicao;
	
	
	public IntegralizacaoOutrosBancosDTO() {
		super();
	}

	public IntegralizacaoOutrosBancosDTO(IntegralizacaoOutrosBancosLegadoDTO dto, Integer idInstituicao2, Integer numCoop2) {
		this.numBanco = dto.getNumBanco();
		this.numAgencia = dto.getNumAgencia();
		this.sequencialArquivo = dto.getSequencialArquivo();
		this.nomeArquivo = dto.getNomeArquivo();
		this.sequencialDetalhe = dto.getSequencialDetalhe();
		this.numParcela = dto.getNumParcela();
		if(dto.getNumMatricula() != null) {
			this.numMatricula = dto.getNumMatricula();
		}
		this.valorIntegralizacao = dto.getValorIntegralizacao();
		if(dto.getBolIntegralizadoCapital() != null) {
			this.bolIntegralizadoCapital = dto.getBolIntegralizadoCapital();
		}
		this.numCoop = numCoop2;
		this.idInstituicao = idInstituicao2;
	}
	
	
	public Integer getNumCoop() {
		return numCoop;
	}

	public void setNumCoop(Integer numCoop) {
		this.numCoop = numCoop;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getNumDVAgenciaFavorecido() {
		return numDVAgenciaFavorecido;
	}
	public void setNumDVAgenciaFavorecido(String numDVAgenciaFavorecido) {
		this.numDVAgenciaFavorecido = numDVAgenciaFavorecido;
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
	public Boolean getContaPrincipal() {
		return contaPrincipal;
	}
	public void setContaPrincipal(Boolean contaPrincipal) {
		this.contaPrincipal = contaPrincipal;
	}
	public Boolean getBolIntegralizadoCapital() {
		return bolIntegralizadoCapital;
	}
	public void setBolIntegralizadoCapital(Boolean bolIntegralizadoCapital) {
		this.bolIntegralizadoCapital = bolIntegralizadoCapital;
	}
	
}
