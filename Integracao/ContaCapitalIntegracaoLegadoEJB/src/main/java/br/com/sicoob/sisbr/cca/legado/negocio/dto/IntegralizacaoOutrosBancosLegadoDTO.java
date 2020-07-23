package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * IntegralizacaoOutrosBancosLegadoDTO
 */
public class IntegralizacaoOutrosBancosLegadoDTO extends BancoobDto {

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
	private String anoMes;
	private Integer numCoop;
	private Integer idInstituicao; 
	
	
	public IntegralizacaoOutrosBancosLegadoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IntegralizacaoOutrosBancosLegadoDTO(Integer numBanco2, Integer numAgencia2, Integer sequencialArquivo2,
			String nomeArquivo2, Integer sequencialDetalhe2, Integer numMatricula2, BigDecimal valorIntegralizacao2,
			Boolean bolIntegralizadoCapital2, Integer numCoop2, Integer idInstituicao2, Integer numParcela2) {
			
		this.numBanco = numBanco2;
		this.numAgencia = numAgencia2;
		this.sequencialArquivo = sequencialArquivo2;
		this.nomeArquivo = nomeArquivo2;
		this.sequencialDetalhe = sequencialDetalhe2;
		this.numMatricula = numMatricula2;
		this.valorIntegralizacao = valorIntegralizacao2;
		this.bolIntegralizadoCapital = bolIntegralizadoCapital2;
		this.numCoop = numCoop2;
		this.idInstituicao = idInstituicao2;
		this.numParcela = numParcela2;
		
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
	public Integer getNumAgenciaFavorecido() {
		return numAgenciaFavorecido;
	}
	public void setNumAgenciaFavorecido(Integer numAgenciaFavorecido) {
		this.numAgenciaFavorecido = numAgenciaFavorecido;
	}
	public Integer getNumBancoFavorecido() {
		return numBancoFavorecido;
	}
	public void setNumBancoFavorecido(Integer numBancoFavorecido) {
		this.numBancoFavorecido = numBancoFavorecido;
	}
	public String getNumContaFavorecido() {
		return numContaFavorecido;
	}
	public void setNumContaFavorecido(String numContaFavorecido) {
		this.numContaFavorecido = numContaFavorecido;
	}
	public BigDecimal getValorIntegralizacao() {
		return valorIntegralizacao;
	}
	public void setValorIntegralizacao(BigDecimal valorIntegralizacao) {
		this.valorIntegralizacao = valorIntegralizacao;
	}
	public String getDescBanco() {
		return descBanco;
	}
	public void setDescBanco(String descBanco) {
		this.descBanco = descBanco;
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

	public String getAnoMes() {
		return anoMes;
	}
	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public static final class Builder {
		
		private final IntegralizacaoOutrosBancosLegadoDTO dto;
		
		public Builder() {
			dto = new IntegralizacaoOutrosBancosLegadoDTO();
		}
		
		public Builder setNumMatricula(Integer numMatricula) {
			dto.setNumMatricula(numMatricula);
			return this;
		}
		
		public Builder setNumBancoFavorecido(Integer numBancoFavorecido) {
			dto.setNumBancoFavorecido(numBancoFavorecido);
			return this;
		}
		
		public Builder setContaPrincipal(Boolean contaPrincipal) {
			dto.setContaPrincipal(contaPrincipal);
			return this;
		}
		
		public IntegralizacaoOutrosBancosLegadoDTO build() {
			return dto;
		}
		
	}
	
}
