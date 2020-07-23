package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

public class DadosFechBaixarParcelasLegadoCCODTO extends BancoobDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo numMatricula. */
	private Integer numMatricula;

	/** O atributo numParcelamento. */
	private Integer numParcelamento;
	
	/** O atributo numParcela. */
	private Integer numParcela;

	/** O atributo codTipoParcelamento. */
	private Integer codTipoParcelamento;	

	/** O atributo dataVencParcela. */
	private DateTime dataVencParcela;
	
	/** O atributo dataSituacaoParcela. */
	private DateTime dataSituacaoParcela;
	
	/** O atributo valorParcela. */
	private BigDecimal valorParcela;
	
	/** O atributo codModoLanc. */
	private Integer	codModoLanc;
	
	/** O atributo codSituacaoParcela. */
	private Integer codSituacaoParcela;	

	/** O atributo numContaCorrente. */
	private Long numContaCorrente;
	
	/** O atributo descOcorrencia. */
	private String descOcorrencia;
	
	/** O atributo tipoHistoricoCCO. */
	private Integer tipoHistoricoCCO;
	
	/** O atributo descNumDocumento. */
	private String descNumDocumento;
	
	/** O atributo numOcorrencia. */
	private Integer numOcorrencia;
	
	/** O atributo codErroRetorno. */
	private String codErroRetorno;

	public String getCodErroRetorno() {
		return codErroRetorno;
	}

	public void setCodErroRetorno(String codErroRetorno) {
		this.codErroRetorno = codErroRetorno;
	}

	public Integer getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Integer getNumParcelamento() {
		return numParcelamento;
	}

	public void setNumParcelamento(Integer numParcelamento) {
		this.numParcelamento = numParcelamento;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}

	public Integer getCodTipoParcelamento() {
		return codTipoParcelamento;
	}

	public void setCodTipoParcelamento(Integer codTipoParcelamento) {
		this.codTipoParcelamento = codTipoParcelamento;
	}

	public DateTime getDataVencParcela() {
		return dataVencParcela;
	}

	public void setDataVencParcela(DateTime dataVencParcela) {
		this.dataVencParcela = dataVencParcela;
	}

	public DateTime getDataSituacaoParcela() {
		return dataSituacaoParcela;
	}

	public void setDataSituacaoParcela(DateTime dataSituacaoParcela) {
		this.dataSituacaoParcela = dataSituacaoParcela;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Integer getCodModoLanc() {
		return codModoLanc;
	}

	public void setCodModoLanc(Integer codModoLanc) {
		this.codModoLanc = codModoLanc;
	}

	public Integer getCodSituacaoParcela() {
		return codSituacaoParcela;
	}

	public void setCodSituacaoParcela(Integer codSituacaoParcela) {
		this.codSituacaoParcela = codSituacaoParcela;
	}

	public Long getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	public String getDescOcorrencia() {
		return descOcorrencia;
	}

	public void setDescOcorrencia(String descOcorrencia) {
		this.descOcorrencia = descOcorrencia;
	}

	public Integer getTipoHistoricoCCO() {
		return tipoHistoricoCCO;
	}

	public void setTipoHistoricoCCO(Integer tipoHistoricoCCO) {
		this.tipoHistoricoCCO = tipoHistoricoCCO;
	}

	public String getDescNumDocumento() {
		return descNumDocumento;
	}

	public void setDescNumDocumento(String descNumDocumento) {
		this.descNumDocumento = descNumDocumento;
	}

	public Integer getNumOcorrencia() {
		return numOcorrencia;
	}

	public void setNumOcorrencia(Integer numOcorrencia) {
		this.numOcorrencia = numOcorrencia;
	}

	@Override
	public String toString() {
		return "DadosFechBaixarParcelasLegadoCCODTO [numMatricula=" + numMatricula + ", numParcelamento=" + numParcelamento
				+ ", numParcela=" + numParcela + ", codTipoParcelamento=" + codTipoParcelamento + ", dataVencParcela=" + dataVencParcela
				+ ", dataSituacaoParcela=" + dataSituacaoParcela + ", valorParcela=" + valorParcela + ", codModoLanc=" + codModoLanc
				+ ", codSituacaoParcela=" + codSituacaoParcela + ", numContaCorrente=" + numContaCorrente + ", descOcorrencia="
				+ descOcorrencia + ", tipoHistoricoCCO=" + tipoHistoricoCCO + ", descNumDocumento="
				+ descNumDocumento + ", numOcorrencia=" + numOcorrencia + "]";
	}
	
}
