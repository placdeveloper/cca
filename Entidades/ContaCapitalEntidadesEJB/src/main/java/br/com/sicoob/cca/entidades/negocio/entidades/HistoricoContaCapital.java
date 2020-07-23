/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * Representa historico da conta capital
 * @author marco.nascimento
 * @since 16/05/2014
 */
@Entity
@Table(name = "HISTCONTACAPITAL", schema = "cca")
public class HistoricoContaCapital extends ContaCapitalEntidade<HistoricoContaCapitalPK> {

	/**
	 * PK
	 */
	@EmbeddedId
	private HistoricoContaCapitalPK id;
	
	/**
	 * Identificador da instituição no SCI 
	 */
	@Column(name = "IDINSTITUICAO", nullable = false)
	private Integer idInstituicao;
	
	/**
	 * Identificador da situação da Conta Capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDSITUACAOCONTACAPITAL", nullable = false)
	private SituacaoContaCapital situacaoContaCapital;
	
	/**
	 * Identificador da pessoa no Capes
	 */
	@Column(name = "IDPESSOA", nullable = false)
	private Integer idPessoa;
	
	/**
	 * Situacao da proposta
	 */
	@ManyToOne
	@JoinColumn(name = "IDSITUACAOAPROVACAOCAPITAL", nullable = false)
	private SituacaoCadastroProposta  situacaoCadastroProposta;
	
	/**
	 * Observacao na aprovacao da proposta
	 */
	@Column(name = "DESCOBSAPROVACAOCAPITAL", nullable = true, length = 400)
	private String descObsAprovacao;
	
	/**
	 * Número da Conta Capital do associado na Instituição
	 */
	@Column(name = "NUMCONTACAPITAL", nullable = false)
	private Integer numContaCapital;
	
	/**
	 * Data da matrícula do associado na instituição.
	 */
	@Column(name = "DATAMATRICULA", nullable = false)
	private DateTimeDB dataMatricula;
	
	/**
	 * Data de saída do associado na instituição.
	 */
	@Column(name = "DATASAIDA")
	private DateTimeDB dataSaida;
	
	/**
	 * Identifica se a matrícula foi escolhida pelo cliente
	 */
	@Column(name = "BOLMATRICULAESCOLHIDA", nullable = false)
	private Boolean matriculaEscolhida;
	
	/**
	 * Identificador do usuario
	 */
	@Column(name = "IDUSUARIO", length = 40, nullable = false)
	private String idUsuario;

	/**
	 * @return the id
	 */
	public HistoricoContaCapitalPK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(HistoricoContaCapitalPK id) {
		this.id = id;
	}

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
	 * @return the situacaoContaCapital
	 */
	public SituacaoContaCapital getSituacaoContaCapital() {
		return situacaoContaCapital;
	}

	/**
	 * @param situacaoContaCapital the situacaoContaCapital to set
	 */
	public void setSituacaoContaCapital(SituacaoContaCapital situacaoContaCapital) {
		this.situacaoContaCapital = situacaoContaCapital;
	}

	/**
	 * @return the idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * @return the situacaoCadastroProposta
	 */
	public SituacaoCadastroProposta getSituacaoCadastroProposta() {
		return situacaoCadastroProposta;
	}

	/**
	 * @param situacaoCadastroProposta the situacaoCadastroProposta to set
	 */
	public void setSituacaoCadastroProposta(
			SituacaoCadastroProposta situacaoCadastroProposta) {
		this.situacaoCadastroProposta = situacaoCadastroProposta;
	}

	/**
	 * @return the descObsAprovacao
	 */
	public String getDescObsAprovacao() {
		return descObsAprovacao;
	}

	/**
	 * @param descObsAprovacao the descObsAprovacao to set
	 */
	public void setDescObsAprovacao(String descObsAprovacao) {
		this.descObsAprovacao = descObsAprovacao;
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
	 * @return the dataMatricula
	 */
	public DateTimeDB getDataMatricula() {
		return dataMatricula;
	}

	/**
	 * @param dataMatricula the dataMatricula to set
	 */
	public void setDataMatricula(DateTimeDB dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	/**
	 * @return the dataSaida
	 */
	public DateTimeDB getDataSaida() {
		return dataSaida;
	}

	/**
	 * @param dataSaida the dataSaida to set
	 */
	public void setDataSaida(DateTimeDB dataSaida) {
		this.dataSaida = dataSaida;
	}

	/**
	 * @return the matriculaEscolhida
	 */
	public Boolean getMatriculaEscolhida() {
		return matriculaEscolhida;
	}

	/**
	 * @param matriculaEscolhida the matriculaEscolhida to set
	 */
	public void setMatriculaEscolhida(Boolean matriculaEscolhida) {
		this.matriculaEscolhida = matriculaEscolhida;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
}