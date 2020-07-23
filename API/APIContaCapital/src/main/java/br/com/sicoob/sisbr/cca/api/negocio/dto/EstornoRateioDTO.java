package br.com.sicoob.sisbr.cca.api.negocio.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO de entrada para estorno do rateio
 * @author Nairon.Silva
 */
public class EstornoRateioDTO extends BancoobDto {

	private static final long serialVersionUID = -1286727463380929861L;
	
	private String idUsuario;
	private Set<String> chavesRateio = new HashSet<String>();
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Set<String> getChavesRateio() {
		return chavesRateio;
	}
	
	/**
	 * Adiciona chave de rateio.
	 * @param chaveRateio
	 */
	public void addChaveRateio(String chaveRateio) {
		this.chavesRateio.add(chaveRateio);
	}

}
