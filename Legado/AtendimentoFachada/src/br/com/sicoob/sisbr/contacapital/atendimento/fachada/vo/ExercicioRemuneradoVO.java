package br.com.sicoob.sisbr.contacapital.atendimento.fachada.vo;

import java.math.BigDecimal;

import br.com.sicoob.tipos.DateTime;

/**
 * 
 * @author Marco.Afonso	
 *
 */
public class ExercicioRemuneradoVO {
	private Integer anoProvisao;
	private DateTime dataCadastro;
	private Boolean bolConfirmado;
	private String idUsuario;
	private BigDecimal percCCapital;
	private BigDecimal percCCorrente;
	private BigDecimal taxaJurosProvisao;

    public ExercicioRemuneradoVO() {
    	super();
    }

	public Integer getAnoProvisao() {
		return this.anoProvisao;
	}
	public void setAnoProvisao(Integer anoProvisao) {
		this.anoProvisao = anoProvisao;
	}

	public DateTime getDataCadastro() {
		return this.dataCadastro;
	}
	public void setDataCadastro(DateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Boolean getBolConfirmado() {
		return this.bolConfirmado;
	}

	public void setBolConfirmado(Boolean bolConfirmado) {
		this.bolConfirmado = bolConfirmado;
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public BigDecimal getPercCCapital() {
		return this.percCCapital;
	}

	public void setPercCCapital(BigDecimal percCCapital) {
		this.percCCapital = percCCapital;
	}

	public BigDecimal getPercCCorrente() {
		return this.percCCorrente;
	}

	public void setPercCCorrente(BigDecimal percCCorrente) {
		this.percCCorrente = percCCorrente;
	}

	public BigDecimal getTaxaJurosProvisao() {
		return this.taxaJurosProvisao;
	}

	public void setTaxaJurosProvisao(BigDecimal taxaJurosProvisao) {
		this.taxaJurosProvisao = taxaJurosProvisao;
	}
}