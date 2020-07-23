/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * The Class RelSituacaoMatriculaCCARenDTO.
 */
public class RelSituacaoPeriodoCCARenDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -120565210505268621L;
	
	/** The id instituicao. */
	private Integer idInstituicao;
	
	/** The data Periodo. */
	private Date dataPeriodoInicial;
	
	/** The data Periodo. */
	private Date dataPeriodoFinal;
	
	/** The nome cliente. */
	private String nomeCliente;
	
	/** The num matricula. */
	private Integer numMatricula;
	
	/** The data matricula. */
	private Date dataMatricula;
	
	/** The data saida. */
	private Date dataSaida;
	
	/** The data situacao. */
	private Date dataSituacao;
	
	/** The situacao. */
	private String situacao;
	
	/** The id situacao conta. */
	private Integer idSituacao;
		
	/** The cadastros aprovados. */
	private Boolean cadastrosAprovados;
	
	/** The num PA. */
	private Integer numPA;
	
	/** The ordenacao. */
	private Integer ordenacao;

	/**
	 * Gets the id instituicao.
	 *
	 * @return the id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Sets the id instituicao.
	 *
	 * @param idInstituicao the new id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Gets the data periodo inicial.
	 *
	 * @return the data periodo inicial
	 */
	public Date getDataPeriodoInicial() {
		return dataPeriodoInicial;
	}

	/**
	 * Sets the data periodo inicial.
	 *
	 * @param dataPeriodoInicial the new data periodo inicial
	 */
	public void setDataPeriodoInicial(Date dataPeriodoInicial) {
		this.dataPeriodoInicial = dataPeriodoInicial;
	}

	/**
	 * Gets the data periodo final.
	 *
	 * @return the data periodo final
	 */
	public Date getDataPeriodoFinal() {
		return dataPeriodoFinal;
	}

	/**
	 * Sets the data periodo final.
	 *
	 * @param dataPeriodoFinal the new data periodo final
	 */
	public void setDataPeriodoFinal(Date dataPeriodoFinal) {
		this.dataPeriodoFinal = dataPeriodoFinal;
	}

	/**
	 * Gets the nome cliente.
	 *
	 * @return the nome cliente
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * Sets the nome cliente.
	 *
	 * @param nomeCliente the new nome cliente
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	/**
	 * Gets the num matricula.
	 *
	 * @return the num matricula
	 */
	public Integer getNumMatricula() {
		return numMatricula;
	}

	/**
	 * Sets the num matricula.
	 *
	 * @param numMatricula the new num matricula
	 */
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}

	/**
	 * Gets the data matricula.
	 *
	 * @return the data matricula
	 */
	public Date getDataMatricula() {
		return dataMatricula;
	}

	/**
	 * Sets the data matricula.
	 *
	 * @param dataMatricula the new data matricula
	 */
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	/**
	 * Gets the data saida.
	 *
	 * @return the data saida
	 */
	public Date getDataSaida() {
		return dataSaida;
	}

	/**
	 * Sets the data saida.
	 *
	 * @param dataSaida the new data saida
	 */
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	/**
	 * Gets the data situacao.
	 *
	 * @return the data situacao
	 */
	public Date getDataSituacao() {
		return dataSituacao;
	}

	/**
	 * Sets the data situacao.
	 *
	 * @param dataSituacao the new data situacao
	 */
	public void setDataSituacao(Date dataSituacao) {
		this.dataSituacao = dataSituacao;
	}

	/**
	 * Gets the situacao.
	 *
	 * @return the situacao
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * Sets the situacao.
	 *
	 * @param situacao the new situacao
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	/**
	 * Gets the id situacao 
	 *
	 * @return the id situacao 
	 */
	public Integer getIdSituacao() {
		return idSituacao;
	}

	/**
	 * Sets the id situacao 
	 *
	 * @param idSituacaoConta the new id situacao 
	 */
	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}

	/**
	 * Gets the cadastros aprovados.
	 *
	 * @return the cadastros aprovados
	 */
	public Boolean getCadastrosAprovados() {
		return cadastrosAprovados;
	}

	/**
	 * Sets the cadastros aprovados.
	 *
	 * @param cadastrosAprovados the new cadastros aprovados
	 */
	public void setCadastrosAprovados(Boolean cadastrosAprovados) {
		this.cadastrosAprovados = cadastrosAprovados;
	}

	/**
	 * Gets the num PA.
	 *
	 * @return the num PA
	 */
	public Integer getNumPA() {
		return numPA;
	}

	/**
	 * Sets the num PA.
	 *
	 * @param numPA the new num PA
	 */
	public void setNumPA(Integer numPA) {
		this.numPA = numPA;
	}

	/**
	 * Gets the ordenacao.
	 *
	 * @return the ordenacao
	 */
	public Integer getOrdenacao() {
		return ordenacao;
	}

	/**
	 * Sets the ordenacao.
	 *
	 * @param ordenacao the new ordenacao
	 */
	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}
}