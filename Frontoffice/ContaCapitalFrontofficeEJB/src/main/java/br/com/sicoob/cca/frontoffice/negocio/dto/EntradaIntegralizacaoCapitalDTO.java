package br.com.sicoob.cca.frontoffice.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.cca.frontoffice.negocio.util.EnumTipoAgendamentoCCA;

/**
 * DTO para recebimento das mensagens do serviço de integralização.
 * @author Nairon.Silva
 */
public class EntradaIntegralizacaoCapitalDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;

	private BigDecimal valorLancamento;	
	
	private EnumTipoAgendamentoCCA tipoAgendamento;
	
	private Integer qtdMeses;
	
	private Integer diaDebito;
	
	private String numCpfCnpj;
	
	private Double numContaCorrente;
	
	private Integer idInstituicao;
	
	private Integer codigoCanal;
	
	/** Construidos */
	
	private Integer numCooperativa;
	
	private String nomeCooperativa;
	
	private Integer numMatricula;
	
	private Integer idPessoa;
	
	private String nomePessoa;
	
	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public EnumTipoAgendamentoCCA getTipoAgendamento() {
		return tipoAgendamento;
	}

	public void setTipoAgendamento(EnumTipoAgendamentoCCA tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}

	public Integer getQtdMeses() {
		return qtdMeses;
	}

	public void setQtdMeses(Integer qtdMeses) {
		this.qtdMeses = qtdMeses;
	}

	public Integer getDiaDebito() {
		return diaDebito;
	}

	public void setDiaDebito(Integer diaDebito) {
		this.diaDebito = diaDebito;
	}

	public Double getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(Double numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(Integer codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}

	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}

	public Integer getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Integer getNumCooperativa() {
		return numCooperativa;
	}

	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNomeCooperativa() {
		return nomeCooperativa;
	}

	public void setNomeCooperativa(String nomeCooperativa) {
		this.nomeCooperativa = nomeCooperativa;
	}
	
	/**
	 * Verifica se eh agendamento Nesta Data
	 * @return
	 */
	public boolean isAgendamentoNestaData() {
		return EnumTipoAgendamentoCCA.NESTA_DATA.equals(tipoAgendamento);
	}
	
}
