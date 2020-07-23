/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe ContaCorrenteIntegracaoRetDTO.
 */
public class ContaCorrenteIntegracaoRetDTO extends BancoobDto {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7421241544789043041L;
	
	/** O atributo idContaCorrente. */
	private Long idContaCorrente;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numeroContaCorrente. */
	private Long numeroContaCorrente;
	
	/** O atributo dataEncerramento. */
	private DateTime dataEncerramento;
	
	/** O atributo dataBloqueio. */
	private DateTime dataBloqueio;
	
	/** O atributo idModalidadeProdutoCCO. */
	private Integer idModalidadeProdutoCCO;
	
	/** O atributo descSiglaModCCO. */
	private String descSiglaModCCO;
	
	/** O atributo descTituloExtrato. */
	private String descTituloExtrato;
	
	/** O atributo idTipoContaCorrente. */
	private Integer idTipoContaCorrente;
	
	/** O atributo descTipoContaCor. */
	private String descTipoContaCor;
	
	/** O atributo idPacoteTarifa. */
	private Integer idPacoteTarifa;
	
	/** O atributo descNomePacote. */
	private String descNomePacote;
	
	/** O atributo idGrupoCCO. */
	private Integer idGrupoCCO;
	
	/** O atributo dataAbertura. */
	private DateTime dataAbertura;
	
	/** O atributo codSituacaoCCO. */
	private Integer codSituacaoCCO;
	
	/** O atributo numeroCooperativa. */
	private Integer numeroCooperativa;
	
	/** O atributo descricaoSituacaoCCO. */
	private String descricaoSituacaoCCO;
	
	/** O atributo codCritCentralizacao. */
	private Integer codCritCentralizacao;
	
	/** O atributo codCategoriaCCO. */
	private Integer codCategoriaCCO;
	
	/** O atributo bolEnvioAutomTED. */
	private Integer bolEnvioAutomTED;
	
	/** O atributo bolUtilSaldoAplicacao. */
	private Integer bolUtilSaldoAplicacao;
	
	/** O atributo idUsuario. */
	private String idUsuario;
	
	/** O atributo lstParticipanteContaCorrenteIntegracaoRetDTO. */
	private List<ParticipanteContaCorrenteIntegracaoRetDTO> lstParticipanteContaCorrenteIntegracaoRetDTO;
	
	/**
	 * Recupera o valor de idContaCorrente.
	 *
	 * @return o valor de idContaCorrente
	 */
	public Long getIdContaCorrente() {
		return idContaCorrente;
	}
	
	/**
	 * Define o valor de idContaCorrente.
	 *
	 * @param idContaCorrente o novo valor de idContaCorrente
	 */
	public void setIdContaCorrente(Long idContaCorrente) {
		this.idContaCorrente = idContaCorrente;
	}
	
	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	
	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	/**
	 * Recupera o valor de numeroContaCorrente.
	 *
	 * @return o valor de numeroContaCorrente
	 */
	public Long getNumeroContaCorrente() {
		return numeroContaCorrente;
	}
	
	/**
	 * Define o valor de numeroContaCorrente.
	 *
	 * @param numeroContaCorrente o novo valor de numeroContaCorrente
	 */
	public void setNumeroContaCorrente(Long numeroContaCorrente) {
		this.numeroContaCorrente = numeroContaCorrente;
	}
	
	/**
	 * Recupera o valor de dataEncerramento.
	 *
	 * @return o valor de dataEncerramento
	 */
	public DateTime getDataEncerramento() {
		return dataEncerramento;
	}
	
	/**
	 * Define o valor de dataEncerramento.
	 *
	 * @param dataEncerramento o novo valor de dataEncerramento
	 */
	public void setDataEncerramento(DateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	
	/**
	 * Recupera o valor de dataBloqueio.
	 *
	 * @return o valor de dataBloqueio
	 */
	public DateTime getDataBloqueio() {
		return dataBloqueio;
	}
	
	/**
	 * Define o valor de dataBloqueio.
	 *
	 * @param dataBloqueio o novo valor de dataBloqueio
	 */
	public void setDataBloqueio(DateTime dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}
	
	/**
	 * Recupera o valor de idModalidadeProdutoCCO.
	 *
	 * @return o valor de idModalidadeProdutoCCO
	 */
	public Integer getIdModalidadeProdutoCCO() {
		return idModalidadeProdutoCCO;
	}
	
	/**
	 * Define o valor de idModalidadeProdutoCCO.
	 *
	 * @param idModalidadeProdutoCCO o novo valor de idModalidadeProdutoCCO
	 */
	public void setIdModalidadeProdutoCCO(Integer idModalidadeProdutoCCO) {
		this.idModalidadeProdutoCCO = idModalidadeProdutoCCO;
	}
	
	/**
	 * Recupera o valor de descSiglaModCCO.
	 *
	 * @return o valor de descSiglaModCCO
	 */
	public String getDescSiglaModCCO() {
		return descSiglaModCCO;
	}
	
	/**
	 * Define o valor de descSiglaModCCO.
	 *
	 * @param descSiglaModCCO o novo valor de descSiglaModCCO
	 */
	public void setDescSiglaModCCO(String descSiglaModCCO) {
		this.descSiglaModCCO = descSiglaModCCO;
	}
	
	/**
	 * Recupera o valor de descTituloExtrato.
	 *
	 * @return o valor de descTituloExtrato
	 */
	public String getDescTituloExtrato() {
		return descTituloExtrato;
	}
	
	/**
	 * Define o valor de descTituloExtrato.
	 *
	 * @param descTituloExtrato o novo valor de descTituloExtrato
	 */
	public void setDescTituloExtrato(String descTituloExtrato) {
		this.descTituloExtrato = descTituloExtrato;
	}
	
	/**
	 * Recupera o valor de idTipoContaCorrente.
	 *
	 * @return o valor de idTipoContaCorrente
	 */
	public Integer getIdTipoContaCorrente() {
		return idTipoContaCorrente;
	}
	
	/**
	 * Define o valor de idTipoContaCorrente.
	 *
	 * @param idTipoContaCorrente o novo valor de idTipoContaCorrente
	 */
	public void setIdTipoContaCorrente(Integer idTipoContaCorrente) {
		this.idTipoContaCorrente = idTipoContaCorrente;
	}
	
	/**
	 * Recupera o valor de descTipoContaCor.
	 *
	 * @return o valor de descTipoContaCor
	 */
	public String getDescTipoContaCor() {
		return descTipoContaCor;
	}
	
	/**
	 * Define o valor de descTipoContaCor.
	 *
	 * @param descTipoContaCor o novo valor de descTipoContaCor
	 */
	public void setDescTipoContaCor(String descTipoContaCor) {
		this.descTipoContaCor = descTipoContaCor;
	}
	
	/**
	 * Recupera o valor de idPacoteTarifa.
	 *
	 * @return o valor de idPacoteTarifa
	 */
	public Integer getIdPacoteTarifa() {
		return idPacoteTarifa;
	}
	
	/**
	 * Define o valor de idPacoteTarifa.
	 *
	 * @param idPacoteTarifa o novo valor de idPacoteTarifa
	 */
	public void setIdPacoteTarifa(Integer idPacoteTarifa) {
		this.idPacoteTarifa = idPacoteTarifa;
	}
	
	/**
	 * Recupera o valor de descNomePacote.
	 *
	 * @return o valor de descNomePacote
	 */
	public String getDescNomePacote() {
		return descNomePacote;
	}
	
	/**
	 * Define o valor de descNomePacote.
	 *
	 * @param descNomePacote o novo valor de descNomePacote
	 */
	public void setDescNomePacote(String descNomePacote) {
		this.descNomePacote = descNomePacote;
	}
	
	/**
	 * Recupera o valor de idGrupoCCO.
	 *
	 * @return o valor de idGrupoCCO
	 */
	public Integer getIdGrupoCCO() {
		return idGrupoCCO;
	}
	
	/**
	 * Define o valor de idGrupoCCO.
	 *
	 * @param idGrupoCCO o novo valor de idGrupoCCO
	 */
	public void setIdGrupoCCO(Integer idGrupoCCO) {
		this.idGrupoCCO = idGrupoCCO;
	}
	
	/**
	 * Recupera o valor de dataAbertura.
	 *
	 * @return o valor de dataAbertura
	 */
	public DateTime getDataAbertura() {
		return dataAbertura;
	}
	
	/**
	 * Define o valor de dataAbertura.
	 *
	 * @param dataAbertura o novo valor de dataAbertura
	 */
	public void setDataAbertura(DateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	/**
	 * Recupera o valor de codSituacaoCCO.
	 *
	 * @return o valor de codSituacaoCCO
	 */
	public Integer getCodSituacaoCCO() {
		return codSituacaoCCO;
	}
	
	/**
	 * Define o valor de codSituacaoCCO.
	 *
	 * @param codSituacaoCCO o novo valor de codSituacaoCCO
	 */
	public void setCodSituacaoCCO(Integer codSituacaoCCO) {
		this.codSituacaoCCO = codSituacaoCCO;
	}
	
	/**
	 * Recupera o valor de numeroCooperativa.
	 *
	 * @return o valor de numeroCooperativa
	 */
	public Integer getNumeroCooperativa() {
		return numeroCooperativa;
	}
	
	/**
	 * Define o valor de numeroCooperativa.
	 *
	 * @param numeroCooperativa o novo valor de numeroCooperativa
	 */
	public void setNumeroCooperativa(Integer numeroCooperativa) {
		this.numeroCooperativa = numeroCooperativa;
	}
	
	/**
	 * Recupera o valor de descricaoSituacaoCCO.
	 *
	 * @return o valor de descricaoSituacaoCCO
	 */
	public String getDescricaoSituacaoCCO() {
		return descricaoSituacaoCCO;
	}
	
	/**
	 * Define o valor de descricaoSituacaoCCO.
	 *
	 * @param descricaoSituacaoCCO o novo valor de descricaoSituacaoCCO
	 */
	public void setDescricaoSituacaoCCO(String descricaoSituacaoCCO) {
		this.descricaoSituacaoCCO = descricaoSituacaoCCO;
	}
	
	/**
	 * Recupera o valor de codCritCentralizacao.
	 *
	 * @return o valor de codCritCentralizacao
	 */
	public Integer getCodCritCentralizacao() {
		return codCritCentralizacao;
	}
	
	/**
	 * Define o valor de codCritCentralizacao.
	 *
	 * @param codCritCentralizacao o novo valor de codCritCentralizacao
	 */
	public void setCodCritCentralizacao(Integer codCritCentralizacao) {
		this.codCritCentralizacao = codCritCentralizacao;
	}
	
	/**
	 * Recupera o valor de codCategoriaCCO.
	 *
	 * @return o valor de codCategoriaCCO
	 */
	public Integer getCodCategoriaCCO() {
		return codCategoriaCCO;
	}
	
	/**
	 * Define o valor de codCategoriaCCO.
	 *
	 * @param codCategoriaCCO o novo valor de codCategoriaCCO
	 */
	public void setCodCategoriaCCO(Integer codCategoriaCCO) {
		this.codCategoriaCCO = codCategoriaCCO;
	}
	
	/**
	 * Recupera o valor de bolEnvioAutomTED.
	 *
	 * @return o valor de bolEnvioAutomTED
	 */
	public Integer getBolEnvioAutomTED() {
		return bolEnvioAutomTED;
	}
	
	/**
	 * Define o valor de bolEnvioAutomTED.
	 *
	 * @param bolEnvioAutomTED o novo valor de bolEnvioAutomTED
	 */
	public void setBolEnvioAutomTED(Integer bolEnvioAutomTED) {
		this.bolEnvioAutomTED = bolEnvioAutomTED;
	}
	
	/**
	 * Recupera o valor de bolUtilSaldoAplicacao.
	 *
	 * @return o valor de bolUtilSaldoAplicacao
	 */
	public Integer getBolUtilSaldoAplicacao() {
		return bolUtilSaldoAplicacao;
	}
	
	/**
	 * Define o valor de bolUtilSaldoAplicacao.
	 *
	 * @param bolUtilSaldoAplicacao o novo valor de bolUtilSaldoAplicacao
	 */
	public void setBolUtilSaldoAplicacao(Integer bolUtilSaldoAplicacao) {
		this.bolUtilSaldoAplicacao = bolUtilSaldoAplicacao;
	}
	
	/**
	 * Recupera o valor de idUsuario.
	 *
	 * @return o valor de idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Define o valor de idUsuario.
	 *
	 * @param idUsuario o novo valor de idUsuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * Recupera o valor de lstParticipanteContaCorrenteIntegracaoRetDTO.
	 *
	 * @return o valor de lstParticipanteContaCorrenteIntegracaoRetDTO
	 */
	public List<ParticipanteContaCorrenteIntegracaoRetDTO> getLstParticipanteContaCorrenteIntegracaoRetDTO() {
		return lstParticipanteContaCorrenteIntegracaoRetDTO;
	}
	
	/**
	 * Define o valor de lstParticipanteContaCorrenteIntegracaoRetDTO.
	 *
	 * @param lstParticipanteContaCorrenteIntegracaoRetDTO o novo valor de lstParticipanteContaCorrenteIntegracaoRetDTO
	 */
	public void setLstParticipanteContaCorrenteIntegracaoRetDTO(
			List<ParticipanteContaCorrenteIntegracaoRetDTO> lstParticipanteContaCorrenteIntegracaoRetDTO) {
		this.lstParticipanteContaCorrenteIntegracaoRetDTO = lstParticipanteContaCorrenteIntegracaoRetDTO;
	}
	
	
	
}