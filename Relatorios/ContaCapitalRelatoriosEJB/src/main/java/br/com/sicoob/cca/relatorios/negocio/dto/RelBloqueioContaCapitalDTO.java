package br.com.sicoob.cca.relatorios.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;

/**
 * DTO com as informacoes para o relatorio de bloqueios.
 * @author Nairon.Silva
 */
public class RelBloqueioContaCapitalDTO extends BancoobDto {

	private static final long serialVersionUID = -5710316788925947107L;
	
	private Integer numContaCapital;
	
	private String nomePessoa;

	private String cpfCnpj;
	
	private List<BloqueioContaCapitalDTO> bloqueios;

	/**
	 * @return the numContaCapital
	 */
	public Integer getNumContaCapital() {
		return numContaCapital;
	}

	/**
	 * @param numContaCapital the numContaCapital to set
	 */
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}

	/**
	 * @return the nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * @param nomePessoa the nomePessoa to set
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return the bloqueios
	 */
	public List<BloqueioContaCapitalDTO> getBloqueios() {
		return bloqueios;
	}

	/**
	 * @param bloqueios the bloqueios to set
	 */
	public void setBloqueios(List<BloqueioContaCapitalDTO> bloqueios) {
		this.bloqueios = bloqueios;
	}

}
