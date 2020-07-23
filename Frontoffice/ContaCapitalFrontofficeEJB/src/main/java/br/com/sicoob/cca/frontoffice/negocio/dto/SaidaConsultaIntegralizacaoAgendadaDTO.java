package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * DTO utilizado na saída do serviço de consulta de integralização agendada.
 * @author Nairon.Silva
 */
public class SaidaConsultaIntegralizacaoAgendadaDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;

	@AtributoRetorno(posicao=1)
	private String dataAgendamento;
	
	@AtributoRetorno(posicao=2)
	private String valor;
	
	@AtributoRetorno(posicao=3)
	private String dataVencimento;
	
	@AtributoRetorno(posicao=4)
	private String codigoCanal;
	
	@AtributoRetorno(posicao=5)
	private String parcelamentoPK;

	/**
	 * @return the dataAgendamento
	 */
	public String getDataAgendamento() {
		return dataAgendamento;
	}

	/**
	 * @param dataAgendamento the dataAgendamento to set
	 */
	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

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

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the parcelamentoPK
	 */
	public String getParcelamentoPK() {
		return parcelamentoPK;
	}

	/**
	 * @param parcelamentoPK the parcelamentoPK to set
	 */
	public void setParcelamentoPK(String parcelamentoPK) {
		this.parcelamentoPK = parcelamentoPK;
	}
	
}
