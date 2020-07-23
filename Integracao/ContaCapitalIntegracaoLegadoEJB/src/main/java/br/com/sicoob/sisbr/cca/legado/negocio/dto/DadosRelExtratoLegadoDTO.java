/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe DadosRelExtratoLegadoDTO.
 */
public class DadosRelExtratoLegadoDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3512702867385468185L;
	
	/** O atributo numMatricula. */
	private Long numMatricula;
	
	/** O atributo valorSaldoBloqInt. */
	private BigDecimal valorSaldoBloqInt;
	
	/** O atributo numPessoa. */
	private Long numPessoa;
	
	/** O atributo descNomePessoa. */
	private String descNomePessoa;
	
	/** O atributo codSituacao. */
	private Integer codSituacao;
	
	/** O atributo enderecoResidencial. */
	private String enderecoResidencial;
	
	/** O atributo bairroResidencial. */
	private String bairroResidencial;
	
	/** O atributo cidadeResidencial. */
	private String cidadeResidencial;
	
	/** O atributo ufResidencial. */
	private String ufResidencial;
	
	/** O atributo cepResidencial. */
	private String cepResidencial;
	
	/** O atributo dddResidencial. */
	private String dddResidencial;
	
	/** O atributo numTelefoneResidencial. */
	private String numTelefoneResidencial;
	
	/** O atributo numCGC_CPF. */
	private String numCGC_CPF;
	
	/** O atributo idTipoHistoricoLanc. */
	private Long idTipoHistoricoLanc;
	
	/** O atributo idTipoHistoricoEstorno. */
	private Long idTipoHistoricoEstorno;
	
	/** O atributo descHistorico. */
	private String descHistorico;
	
	/** O atributo codGrupoHist. */
	private Integer codGrupoHist;
	
	/** O atributo valorLanc. */
	private BigDecimal valorLanc;
	
	/** O atributo dataLote. */
	private Date dataLote;
	
	/** O atributo dataHoraInclusao. */
	private Date dataHoraInclusao;
	
	/** O atributo numSeqLanc. */
	private Long numSeqLanc;
	
	/** O atributo valorCredito. */
	private BigDecimal valorCredito;
	
	/** O atributo valorDebito. */
	private BigDecimal valorDebito;
	
	/** O atributo saldoAtual. */
	private BigDecimal saldoAtual;
	
	/** O atributo numPac. */
	private Integer numPac;
	
	/** O atributo descMatriculaFunc. */
	private String descMatriculaFunc;
	
	/** O atributo empresa. */
	private String empresa;
	
	/** O atributo departamento. */
	private String departamento;
	
	/** O atributo numTelefoneFormatado. */
	private String numTelefoneFormatado;
	
	/**
	 * Recupera o valor de numMatricula.
	 *
	 * @return o valor de numMatricula
	 */
	public Long getNumMatricula() {
		return numMatricula;
	}
	
	/**
	 * Define o valor de numMatricula.
	 *
	 * @param numMatricula o novo valor de numMatricula
	 */
	public void setNumMatricula(Long numMatricula) {
		this.numMatricula = numMatricula;
	}
	
	/**
	 * Recupera o valor de valorSaldoBloqInt.
	 *
	 * @return o valor de valorSaldoBloqInt
	 */
	public BigDecimal getValorSaldoBloqInt() {
		return valorSaldoBloqInt;
	}
	
	/**
	 * Define o valor de valorSaldoBloqInt.
	 *
	 * @param valorSaldoBloqInt o novo valor de valorSaldoBloqInt
	 */
	public void setValorSaldoBloqInt(BigDecimal valorSaldoBloqInt) {
		this.valorSaldoBloqInt = valorSaldoBloqInt;
	}
	
	/**
	 * Recupera o valor de numPessoa.
	 *
	 * @return o valor de numPessoa
	 */
	public Long getNumPessoa() {
		return numPessoa;
	}
	
	/**
	 * Define o valor de numPessoa.
	 *
	 * @param numPessoa o novo valor de numPessoa
	 */
	public void setNumPessoa(Long numPessoa) {
		this.numPessoa = numPessoa;
	}
	
	/**
	 * Recupera o valor de descNomePessoa.
	 *
	 * @return o valor de descNomePessoa
	 */
	public String getDescNomePessoa() {
		return descNomePessoa;
	}
	
	/**
	 * Define o valor de descNomePessoa.
	 *
	 * @param descNomePessoa o novo valor de descNomePessoa
	 */
	public void setDescNomePessoa(String descNomePessoa) {
		this.descNomePessoa = descNomePessoa;
	}
	
	/**
	 * Recupera o valor de codSituacao.
	 *
	 * @return o valor de codSituacao
	 */
	public Integer getCodSituacao() {
		return codSituacao;
	}
	
	/**
	 * Define o valor de codSituacao.
	 *
	 * @param codSituacao o novo valor de codSituacao
	 */
	public void setCodSituacao(Integer codSituacao) {
		this.codSituacao = codSituacao;
	}
	
	/**
	 * Recupera o valor de enderecoResidencial.
	 *
	 * @return o valor de enderecoResidencial
	 */
	public String getEnderecoResidencial() {
		return enderecoResidencial;
	}
	
	/**
	 * Define o valor de enderecoResidencial.
	 *
	 * @param enderecoResidencial o novo valor de enderecoResidencial
	 */
	public void setEnderecoResidencial(String enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}
	
	/**
	 * Recupera o valor de bairroResidencial.
	 *
	 * @return o valor de bairroResidencial
	 */
	public String getBairroResidencial() {
		return bairroResidencial;
	}
	
	/**
	 * Define o valor de bairroResidencial.
	 *
	 * @param bairroResidencial o novo valor de bairroResidencial
	 */
	public void setBairroResidencial(String bairroResidencial) {
		this.bairroResidencial = bairroResidencial;
	}
	
	/**
	 * Recupera o valor de cidadeResidencial.
	 *
	 * @return o valor de cidadeResidencial
	 */
	public String getCidadeResidencial() {
		return cidadeResidencial;
	}
	
	/**
	 * Define o valor de cidadeResidencial.
	 *
	 * @param cidadeResidencial o novo valor de cidadeResidencial
	 */
	public void setCidadeResidencial(String cidadeResidencial) {
		this.cidadeResidencial = cidadeResidencial;
	}
	
	/**
	 * Recupera o valor de ufResidencial.
	 *
	 * @return o valor de ufResidencial
	 */
	public String getUfResidencial() {
		return ufResidencial;
	}
	
	/**
	 * Define o valor de ufResidencial.
	 *
	 * @param ufResidencial o novo valor de ufResidencial
	 */
	public void setUfResidencial(String ufResidencial) {
		this.ufResidencial = ufResidencial;
	}
	
	/**
	 * Recupera o valor de cepResidencial.
	 *
	 * @return o valor de cepResidencial
	 */
	public String getCepResidencial() {
		return cepResidencial;
	}
	
	/**
	 * Define o valor de cepResidencial.
	 *
	 * @param cepResidencial o novo valor de cepResidencial
	 */
	public void setCepResidencial(String cepResidencial) {
		this.cepResidencial = cepResidencial;
	}
	
	/**
	 * Recupera o valor de dddResidencial.
	 *
	 * @return o valor de dddResidencial
	 */
	public String getDddResidencial() {
		return dddResidencial;
	}
	
	/**
	 * Define o valor de dddResidencial.
	 *
	 * @param dddResidencial o novo valor de dddResidencial
	 */
	public void setDddResidencial(String dddResidencial) {
		this.dddResidencial = dddResidencial;
	}
	
	/**
	 * Recupera o valor de numTelefoneResidencial.
	 *
	 * @return o valor de numTelefoneResidencial
	 */
	public String getNumTelefoneResidencial() {
		return numTelefoneResidencial;
	}
	
	/**
	 * Define o valor de numTelefoneResidencial.
	 *
	 * @param numTelefoneResidencial o novo valor de numTelefoneResidencial
	 */
	public void setNumTelefoneResidencial(String numTelefoneResidencial) {
		this.numTelefoneResidencial = numTelefoneResidencial;
	}
	
	/**
	 * Recupera o valor de numCGC_CPF.
	 *
	 * @return o valor de numCGC_CPF
	 */
	public String getNumCGC_CPF() {
		return numCGC_CPF;
	}
	
	/**
	 * Define o valor de numCGC_CPF.
	 *
	 * @param numCGC_CPF o novo valor de numCGC_CPF
	 */
	public void setNumCGC_CPF(String numCGC_CPF) {
		this.numCGC_CPF = numCGC_CPF;
	}
	
	/**
	 * Recupera o valor de idTipoHistoricoLanc.
	 *
	 * @return o valor de idTipoHistoricoLanc
	 */
	public Long getIdTipoHistoricoLanc() {
		return idTipoHistoricoLanc;
	}
	
	/**
	 * Define o valor de idTipoHistoricoLanc.
	 *
	 * @param idTipoHistoricoLanc o novo valor de idTipoHistoricoLanc
	 */
	public void setIdTipoHistoricoLanc(Long idTipoHistoricoLanc) {
		this.idTipoHistoricoLanc = idTipoHistoricoLanc;
	}
	
	/**
	 * Recupera o valor de idTipoHistoricoEstorno.
	 *
	 * @return o valor de idTipoHistoricoEstorno
	 */
	public Long getIdTipoHistoricoEstorno() {
		return idTipoHistoricoEstorno;
	}
	
	/**
	 * Define o valor de idTipoHistoricoEstorno.
	 *
	 * @param idTipoHistoricoEstorno o novo valor de idTipoHistoricoEstorno
	 */
	public void setIdTipoHistoricoEstorno(Long idTipoHistoricoEstorno) {
		this.idTipoHistoricoEstorno = idTipoHistoricoEstorno;
	}
	
	/**
	 * Recupera o valor de descHistorico.
	 *
	 * @return o valor de descHistorico
	 */
	public String getDescHistorico() {
		return descHistorico;
	}
	
	/**
	 * Define o valor de descHistorico.
	 *
	 * @param descHistorico o novo valor de descHistorico
	 */
	public void setDescHistorico(String descHistorico) {
		this.descHistorico = descHistorico;
	}
	
	/**
	 * Recupera o valor de codGrupoHist.
	 *
	 * @return o valor de codGrupoHist
	 */
	public Integer getCodGrupoHist() {
		return codGrupoHist;
	}
	
	/**
	 * Define o valor de codGrupoHist.
	 *
	 * @param codGrupoHist o novo valor de codGrupoHist
	 */
	public void setCodGrupoHist(Integer codGrupoHist) {
		this.codGrupoHist = codGrupoHist;
	}
	
	/**
	 * Recupera o valor de valorLanc.
	 *
	 * @return o valor de valorLanc
	 */
	public BigDecimal getValorLanc() {
		return valorLanc;
	}
	
	/**
	 * Define o valor de valorLanc.
	 *
	 * @param valorLanc o novo valor de valorLanc
	 */
	public void setValorLanc(BigDecimal valorLanc) {
		this.valorLanc = valorLanc;
	}
	
	/**
	 * Recupera o valor de dataLote.
	 *
	 * @return o valor de dataLote
	 */
	public Date getDataLote() {
		DateTimeDB clone = null;
		if(this.dataLote != null){
			clone = new DateTimeDB(this.dataLote.getTime());
		}
		return clone;
	}
	
	/**
	 * Define o valor de dataLote.
	 *
	 * @param dataLote o novo valor de dataLote
	 */
	public void setDataLote(Date dataLote) {
		if(dataLote != null){
			this.dataLote = new DateTimeDB(dataLote.getTime());
		}else{
			this.dataLote = null;
		}
	}
	
	/**
	 * Recupera o valor de dataHoraInclusao.
	 *
	 * @return o valor de dataHoraInclusao
	 */
	public Date getDataHoraInclusao() {
		DateTimeDB clone = null;
		if(this.dataHoraInclusao != null){
			clone = new DateTimeDB(this.dataHoraInclusao.getTime());
		}
		return clone;
	}
	
	/**
	 * Define o valor de dataHoraInclusao.
	 *
	 * @param dataHoraInclusao o novo valor de dataHoraInclusao
	 */
	public void setDataHoraInclusao(Date dataHoraInclusao) {
		if(dataHoraInclusao != null){
			this.dataHoraInclusao = new DateTimeDB(dataHoraInclusao.getTime());
		}else{
			this.dataHoraInclusao = null;
		}
	}
	
	/**
	 * Recupera o valor de numSeqLanc.
	 *
	 * @return o valor de numSeqLanc
	 */
	public Long getNumSeqLanc() {
		return numSeqLanc;
	}
	
	/**
	 * Define o valor de numSeqLanc.
	 *
	 * @param numSeqLanc o novo valor de numSeqLanc
	 */
	public void setNumSeqLanc(Long numSeqLanc) {
		this.numSeqLanc = numSeqLanc;
	}
	
	/**
	 * Recupera o valor de valorCredito.
	 *
	 * @return o valor de valorCredito
	 */
	public BigDecimal getValorCredito() {
		return valorCredito;
	}
	
	/**
	 * Define o valor de valorCredito.
	 *
	 * @param valorCredito o novo valor de valorCredito
	 */
	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}
	
	/**
	 * Recupera o valor de valorDebito.
	 *
	 * @return o valor de valorDebito
	 */
	public BigDecimal getValorDebito() {
		return valorDebito;
	}
	
	/**
	 * Define o valor de valorDebito.
	 *
	 * @param valorDebito o novo valor de valorDebito
	 */
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}
	
	/**
	 * Recupera o valor de saldoAtual.
	 *
	 * @return o valor de saldoAtual
	 */
	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}
	
	/**
	 * Define o valor de saldoAtual.
	 *
	 * @param saldoAtual o novo valor de saldoAtual
	 */
	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}
	
	/**
	 * Recupera o valor de numPac.
	 *
	 * @return o valor de numPac
	 */
	public Integer getNumPac() {
		return numPac;
	}
	
	/**
	 * Define o valor de numPac.
	 *
	 * @param numPac o novo valor de numPac
	 */
	public void setNumPac(Integer numPac) {
		this.numPac = numPac;
	}
	
	/**
	 * Recupera o valor de descMatriculaFunc.
	 *
	 * @return o valor de descMatriculaFunc
	 */
	public String getDescMatriculaFunc() {
		return descMatriculaFunc;
	}
	
	/**
	 * Define o valor de descMatriculaFunc.
	 *
	 * @param descMatriculaFunc o novo valor de descMatriculaFunc
	 */
	public void setDescMatriculaFunc(String descMatriculaFunc) {
		this.descMatriculaFunc = descMatriculaFunc;
	}
	
	/**
	 * Recupera o valor de empresa.
	 *
	 * @return o valor de empresa
	 */
	public String getEmpresa() {
		return empresa;
	}
	
	/**
	 * Define o valor de empresa.
	 *
	 * @param empresa o novo valor de empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	/**
	 * Recupera o valor de departamento.
	 *
	 * @return o valor de departamento
	 */
	public String getDepartamento() {
		return departamento;
	}
	
	/**
	 * Define o valor de departamento.
	 *
	 * @param departamento o novo valor de departamento
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	/**
	 * Recupera o valor de numTelefoneFormatado.
	 *
	 * @return o valor de numTelefoneFormatado
	 */
	public String getNumTelefoneFormatado() {
		return numTelefoneFormatado;
	}
	
	/**
	 * Define o valor de numTelefoneFormatado.
	 *
	 * @param numTelefoneFormatado o novo valor de numTelefoneFormatado
	 */
	public void setNumTelefoneFormatado(String numTelefoneFormatado) {
		this.numTelefoneFormatado = numTelefoneFormatado;
	}
	
}
