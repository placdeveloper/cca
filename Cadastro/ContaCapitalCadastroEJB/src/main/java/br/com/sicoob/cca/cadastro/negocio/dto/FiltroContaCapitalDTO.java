package br.com.sicoob.cca.cadastro.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * FiltroContaCapitalDTO
 */
public class FiltroContaCapitalDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private Integer idInstituicao;
	private Integer numContaCapitalInicial;
	private Integer numContaCapitalFinal;
	
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Integer getNumContaCapitalInicial() {
		return numContaCapitalInicial;
	}
	public void setNumContaCapitalInicial(Integer numContaCapitalInicial) {
		this.numContaCapitalInicial = numContaCapitalInicial;
	}
	public Integer getNumContaCapitalFinal() {
		return numContaCapitalFinal;
	}
	public void setNumContaCapitalFinal(Integer numContaCapitalFinal) {
		this.numContaCapitalFinal = numContaCapitalFinal;
	}
	
	/**
	 * Builder para os filtros.
	 */
	public static final class Builder {
		
		private final FiltroContaCapitalDTO dto;
		
		public Builder() {
			dto = new FiltroContaCapitalDTO();
		}
		
		public Builder setIdInstituicao(Integer idInstituicao) {
			dto.setIdInstituicao(idInstituicao);
			return this;
		}
		public Builder setNumContaCapitalInicial(Integer numContaCapitalInicial) {
			dto.setNumContaCapitalInicial(numContaCapitalInicial);
			return this;
		}
		public Builder setNumContaCapitalFinal(Integer numContaCapitalFinal) {
			dto.setNumContaCapitalFinal(numContaCapitalFinal);
			return this;
		}
		public FiltroContaCapitalDTO build() {
			return dto;
		}
		
	}
	
}
