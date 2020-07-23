package br.com.sicoob.sisbr.cca.relatorios.vo;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * The Class RelSituacaoMatriculaCCARenVO.
 */
public class RelSituacaoMatriculaCCARenVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -120565210505268621L;
	
	/** The id instituicao. */
	private Integer idInstituicao;
	
	/** The num conta capital inicial. */
	private Integer numContaCapitalInicial;
	
	/** The num conta capital final. */
	private Integer numContaCapitalFinal;
	
	/** The nome cliente. */
	private String nomeCliente;
	
	/** The data matricula. */
	private DateTimeDB dataMatricula;
	
	/** The data saida. */
	private DateTimeDB dataSaida;
	
	/** The data situacao. */
	private DateTimeDB dataSituacao;
	
	/** The situacao. */
	private String situacao;

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
	 * Gets the num conta capital inicial.
	 *
	 * @return the num conta capital inicial
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

	public Integer getNumContaCapitalFinal() {
		return numContaCapitalFinal;
	}

	public void setNumContaCapitalFinal(Integer numContaCapitalFinal) {
		this.numContaCapitalFinal = numContaCapitalFinal;
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
}