package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * DTO utilizado no cancelamento de parcelas.
 * @author Nairon.Silva
 */
public class CancelamentoParcelamentoDTO extends BancoobDto {

	private static final long serialVersionUID = 8878820695820275291L;
	
	/** Obrigatorios */
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numParcela. */
	private Short numParcela;
	
	/** O atributo numParcelamento. */
	private Short numParcelamento;
	
	/** O atributo idTipoParcelamento. */
	private Short idTipoParcelamento ;
	
	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** Preenchidos ao cancelar */
	/** O atributo dataVencimento. */
	private DateTimeDB dataVencimento;
	
	/** O atributo dataVencimento. */
	private DateTimeDB dataSituacao;
	
	/** O atributo valorParcela */
	private BigDecimal valorParcela;

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
	 * @return the numParcela
	 */
	public Short getNumParcela() {
		return numParcela;
	}

	/**
	 * @param numParcela the numParcela to set
	 */
	public void setNumParcela(Short numParcela) {
		this.numParcela = numParcela;
	}

	/**
	 * @return the numParcelamento
	 */
	public Short getNumParcelamento() {
		return numParcelamento;
	}

	/**
	 * @param numParcelamento the numParcelamento to set
	 */
	public void setNumParcelamento(Short numParcelamento) {
		this.numParcelamento = numParcelamento;
	}

	/**
	 * @return the idTipoParcelamento
	 */
	public Short getIdTipoParcelamento() {
		return idTipoParcelamento;
	}

	/**
	 * @param idTipoParcelamento the idTipoParcelamento to set
	 */
	public void setIdTipoParcelamento(Short idTipoParcelamento) {
		this.idTipoParcelamento = idTipoParcelamento;
	}

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
	 * @return the dataVencimento
	 */
	public DateTimeDB getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento(DateTimeDB dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	/**
	 * @return the dataSituacao
	 */
	public DateTimeDB getDataSituacao() {
		return dataSituacao;
	}

	/**
	 * @param dataSituacao the dataSituacao to set
	 */
	public void setDataSituacao(DateTimeDB dataSituacao) {
		this.dataSituacao = dataSituacao;
	}

	/**
	 * @return the valorParcela
	 */
	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	/**
	 * @param valorParcela the valorParcela to set
	 */
	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	
}
