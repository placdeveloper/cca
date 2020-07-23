package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO utilizado na entrada do serviço de consulta de integralização agendada.
 * @author Nairon.Silva
 */
public class EntradaConsultaIntegralizacaoAgendadaDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private String numCpfCnpj;
	
	private Integer idInstituicao;
	
	private Integer codigoCooperativa;
	
	private Integer numMatricula;
	
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
	 * @return the codigoCooperativa
	 */
	public Integer getCodigoCooperativa() {
		return codigoCooperativa;
	}

	/**
	 * @param codigoCooperativa the codigoCooperativa to set
	 */
	public void setCodigoCooperativa(Integer codigoCooperativa) {
		this.codigoCooperativa = codigoCooperativa;
	}
	
}
