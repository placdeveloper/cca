/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * The Class RelSituacaoMatriculaCCARenDTO.
 */
public class RelSituacaoMatriculaCCARenDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -120565210505268621L;
	
	/** The id instituicao. */
	private Integer idInstituicao;
	
	/** The num conta capital final. */
	private Integer numContaCapitalFinal;
	
	/** The num conta capital. */
	private Integer numContaCapitalInicial;
	
	/** The nome cliente. */
	private String nomeCliente;
	
	/** The num matricula. */
	private Integer numMatricula;
	
	/** The data matricula. */
	private DateTimeDB dataMatricula;
	
	/** The data saida. */
	private DateTimeDB dataSaida;
	
	/** The data situacao. */
	private DateTimeDB dataSituacao;
	
	/** The situacao. */
	private String situacao;
	
	/** The id situacao conta. */
	private Integer idSituacaoConta;
	
	/** The ultima situacao. */
	private Boolean ultimaSituacao;
	
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
	 * Gets the num conta capital final.
	 *
	 * @return the num conta capital final
	 */
	public Integer getNumContaCapitalFinal() {
		return numContaCapitalFinal;
	}

	/**
	 * Sets the num conta capital final.
	 *
	 * @param numContaCapitalFinal the new num conta capital final
	 */
	public void setNumContaCapitalFinal(Integer numContaCapitalFinal) {
		this.numContaCapitalFinal = numContaCapitalFinal;
	}

	/**
	 * Gets the num conta capital.
	 *
	 * @return the num conta capital
	 */
	public Integer getNumContaCapitalInicial() {
		return numContaCapitalInicial;
	}

	/**
	 * Sets the num conta capital inicial.
	 *
	 * @param numContaCapitalInicial the new num conta capital inicial
	 */
	public void setNumContaCapitalInicial(Integer numContaCapitalInicial) {
		this.numContaCapitalInicial = numContaCapitalInicial;
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
	public DateTimeDB getDataMatricula() {
		return dataMatricula;
	}

	/**
	 * Sets the data matricula.
	 *
	 * @param dataMatricula the new data matricula
	 */
	public void setDataMatricula(DateTimeDB dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	/**
	 * Gets the data saida.
	 *
	 * @return the data saida
	 */
	public DateTimeDB getDataSaida() {
		return dataSaida;
	}

	/**
	 * Sets the data saida.
	 *
	 * @param dataSaida the new data saida
	 */
	public void setDataSaida(DateTimeDB dataSaida) {
		this.dataSaida = dataSaida;
	}

	/**
	 * Gets the data situacao.
	 *
	 * @return the data situacao
	 */
	public DateTimeDB getDataSituacao() {
		return dataSituacao;
	}

	/**
	 * Sets the data situacao.
	 *
	 * @param dataSituacao the new data situacao
	 */
	public void setDataSituacao(DateTimeDB dataSituacao) {
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
	 * Gets the id situacao conta.
	 *
	 * @return the id situacao conta
	 */
	public Integer getIdSituacaoConta() {
		return idSituacaoConta;
	}

	/**
	 * Sets the id situacao conta.
	 *
	 * @param idSituacaoConta the new id situacao conta
	 */
	public void setIdSituacaoConta(Integer idSituacaoConta) {
		this.idSituacaoConta = idSituacaoConta;
	}

	/**
	 * Gets the ultima situacao.
	 *
	 * @return the ultima situacao
	 */
	public Boolean getUltimaSituacao() {
		return ultimaSituacao;
	}

	/**
	 * Sets the ultima situacao.
	 *
	 * @param ultimaSituacao the new ultima situacao
	 */
	public void setUltimaSituacao(Boolean ultimaSituacao) {
		this.ultimaSituacao = ultimaSituacao;
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