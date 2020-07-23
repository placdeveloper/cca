package br.com.sicoob.cca.frontoffice.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;

/**
 * DTO utilizado na entrada do serviço de cancelamento de integralização agendada.
 * @author Nairon.Silva
 */
public class EntradaCancelamentoIntegralizacaoAgendadaDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private String numCpfCnpj;
	
	private Double numContaCorrente;
	
	private Integer idInstituicao;
	
	private Integer numMatricula;
	
	/** Construidos */
	
	private List<ParcelamentoCCALegadoPK> parcelamentoPKs;
	
	private Integer numCooperativa;
	
	private String nomeCooperativa;
	
	private String nomePessoa;

	/**
	 * @return the numCpfCnpj
	 */
	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}

	/**
	 * @param numCpfCnpj the numCpfCnpj to set
	 */
	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * @return the numMatricula
	 */
	public Integer getNumMatricula() {
		return numMatricula;
	}

	/**
	 * @param numMatricula the numMatricula to set
	 */
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}

	/**
	 * @return the parcelamentoPKs
	 */
	public List<ParcelamentoCCALegadoPK> getParcelamentoPKs() {
		return parcelamentoPKs;
	}

	/**
	 * @param parcelamentoPKs the parcelamentoPKs to set
	 */
	public void setParcelamentoPKs(List<ParcelamentoCCALegadoPK> parcelamentoPKs) {
		this.parcelamentoPKs = parcelamentoPKs;
	}

	public Double getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(Double numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	public Integer getNumCooperativa() {
		return numCooperativa;
	}

	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	public String getNomeCooperativa() {
		return nomeCooperativa;
	}

	public void setNomeCooperativa(String nomeCooperativa) {
		this.nomeCooperativa = nomeCooperativa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	
}
