package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * DTO utilizado na entrada do serviço de cancelamento de integralização agendada.
 * @author Nairon.Silva
 */
public class SaidaCancelamentoIntegralizacaoAgendadaDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;

	@AtributoRetorno(posicao=1)
	private Integer indice;
	
	@AtributoRetorno(posicao=2)
	private String valor;
	
	@AtributoRetorno(posicao=3)
	private String dataVencimento;

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the dataVencimento
	 */
	public String getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	
}
