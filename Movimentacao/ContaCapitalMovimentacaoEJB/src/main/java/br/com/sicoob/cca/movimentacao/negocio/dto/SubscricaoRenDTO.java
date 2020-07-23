/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

// TODO: Auto-generated Javadoc
/**
 * A Classe SubscricaoRenDTO.
 *
 * @author Antonio.Genaro
 */
public class SubscricaoRenDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6452738861246688311L;
	
	/** O atributo idContaCapital. */
	private Integer idContaCapital;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** O atributo vlrSubs. */
	private BigDecimal vlrSubs;
	
	/** O atributo vlrInteg. */
	private BigDecimal vlrInteg;
	
	/** O atributo qtdParcelas. */
	private Integer qtdParcelas;
	
	/** O atributo vlrParcelas. */
	private BigDecimal vlrParcelas;
	
	/** O atributo diaDebito. */
	private Integer diaDebito;
	
	/** O atributo tipoInteg. */
	private Short tipoInteg;
	
	/** O atributo numContaCorrente. */
	private Long numContaCorrente;
	
	/** O atributo dataHoraAtualizacao. */
	private DateTimeDB dataHoraAtualizacao;
	
	/** O atributo descNumMatriculaFunc. */
	private String descNumMatriculaFunc;
	
	/** O atributo bolSubscricaoProposta. */
	private Integer bolSubscricaoProposta;
	
	/** O atributo idTipoSubscricao. */
	private Short idTipoSubscricao;
	
	/** O atributo idPessoaLegado. */
	private Integer idPessoaLegado;	
	
	/** O atributo idPessoa. */
	private Integer idPessoa;	
	
	/**
	 * Recupera o valor de idPessoaLegado.
	 *
	 * @return o valor de idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}
	
	/**
	 * Define o valor de idPessoaLegado.
	 *
	 * @param idPessoaLegado o novo valor de idPessoaLegado
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}
	
	/**
	 * Recupera o valor de idTipoSubscricao.
	 *
	 * @return o valor de idTipoSubscricao
	 */
	public Short getIdTipoSubscricao() {
		return idTipoSubscricao;
	}
	
	/**
	 * Define o valor de idTipoSubscricao.
	 *
	 * @param idTipoSubscricao o novo valor de idTipoSubscricao
	 */
	public void setIdTipoSubscricao(Short idTipoSubscricao) {
		this.idTipoSubscricao = idTipoSubscricao;
	}
	
	/**
	 * Recupera o valor de idContaCapital.
	 *
	 * @return o valor de idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	
	/**
	 * Define o valor de idContaCapital.
	 *
	 * @param idContaCapital o novo valor de idContaCapital
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
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
	 * Recupera o valor de numContaCapital.
	 *
	 * @return o valor de numContaCapital
	 */
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	
	/**
	 * Define o valor de numContaCapital.
	 *
	 * @param numContaCapital o novo valor de numContaCapital
	 */
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	
	/**
	 * Recupera o valor de vlrSubs.
	 *
	 * @return o valor de vlrSubs
	 */
	public BigDecimal getVlrSubs() {
		return vlrSubs;
	}
	
	/**
	 * Define o valor de vlrSubs.
	 *
	 * @param vlrSubs o novo valor de vlrSubs
	 */
	public void setVlrSubs(BigDecimal vlrSubs) {
		this.vlrSubs = vlrSubs;
	}
	
	/**
	 * Recupera o valor de vlrInteg.
	 *
	 * @return o valor de vlrInteg
	 */
	public BigDecimal getVlrInteg() {
		return vlrInteg;
	}
	
	/**
	 * Define o valor de vlrInteg.
	 *
	 * @param vlrInteg o novo valor de vlrInteg
	 */
	public void setVlrInteg(BigDecimal vlrInteg) {
		this.vlrInteg = vlrInteg;
	}
	
	/**
	 * Recupera o valor de qtdParcelas.
	 *
	 * @return o valor de qtdParcelas
	 */
	public Integer getQtdParcelas() {
		return qtdParcelas;
	}
	
	/**
	 * Define o valor de qtdParcelas.
	 *
	 * @param qtdParcelas o novo valor de qtdParcelas
	 */
	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}
	
	/**
	 * Recupera o valor de vlrParcelas.
	 *
	 * @return o valor de vlrParcelas
	 */
	public BigDecimal getVlrParcelas() {
		return vlrParcelas;
	}
	
	/**
	 * Define o valor de vlrParcelas.
	 *
	 * @param vlrParcelas o novo valor de vlrParcelas
	 */
	public void setVlrParcelas(BigDecimal vlrParcelas) {
		this.vlrParcelas = vlrParcelas;
	}
	
	/**
	 * Recupera o valor de diaDebito.
	 *
	 * @return o valor de diaDebito
	 */
	public Integer getDiaDebito() {
		return diaDebito;
	}
	
	/**
	 * Define o valor de diaDebito.
	 *
	 * @param diaDebito o novo valor de diaDebito
	 */
	public void setDiaDebito(Integer diaDebito) {
		this.diaDebito = diaDebito;
	}
	
	/**
	 * Recupera o valor de tipoInteg.
	 *
	 * @return o valor de tipoInteg
	 */
	public Short getTipoInteg() {
		return tipoInteg;
	}
	
	/**
	 * Define o valor de tipoInteg.
	 *
	 * @param tipoInteg o novo valor de tipoInteg
	 */
	public void setTipoInteg(Short tipoInteg) {
		this.tipoInteg = tipoInteg;
	}
	
	/**
	 * Recupera o valor de dataHoraAtualizacao.
	 *
	 * @return o valor de dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}
	
	/**
	 * Define o valor de dataHoraAtualizacao.
	 *
	 * @param dataHoraAtualizacao o novo valor de dataHoraAtualizacao
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
	
	/**
	 * Recupera o valor de descNumMatriculaFunc.
	 *
	 * @return o valor de descNumMatriculaFunc
	 */
	public String getDescNumMatriculaFunc() {
		return descNumMatriculaFunc;
	}
	
	/**
	 * Define o valor de descNumMatriculaFunc.
	 *
	 * @param descNumMatriculaFunc o novo valor de descNumMatriculaFunc
	 */
	public void setDescNumMatriculaFunc(String descNumMatriculaFunc) {
		this.descNumMatriculaFunc = descNumMatriculaFunc;
	}
	
	/**
	 * Recupera o valor de bolSubscricaoProposta.
	 *
	 * @return o valor de bolSubscricaoProposta
	 */
	public Integer getBolSubscricaoProposta() {
		return bolSubscricaoProposta;
	}
	
	/**
	 * Define o valor de bolSubscricaoProposta.
	 *
	 * @param bolSubscricaoProposta o novo valor de bolSubscricaoProposta
	 */
	public void setBolSubscricaoProposta(Integer bolSubscricaoProposta) {
		this.bolSubscricaoProposta = bolSubscricaoProposta;
	}
	
	/**
	 * Recupera o valor de idPessoa.
	 *
	 * @return o valor de idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}
	
	/**
	 * Define o valor de idPessoa.
	 *
	 * @param idPessoa o novo valor de idPessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera o valor de numContaCorrente.
	 *
	 * @return the numContaCorrente
	 */
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}

	/**
	 * Define o valor de numContaCorrente.
	 *
	 * @param numContaCorrente the numContaCorrente to set
	 */
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	@Override
	public String toString() {
		return "SubscricaoRenDTO [idContaCapital=" + idContaCapital + ", idInstituicao=" + idInstituicao
				+ ", numContaCapital=" + numContaCapital + ", vlrSubs=" + vlrSubs + ", vlrInteg=" + vlrInteg
				+ ", qtdParcelas=" + qtdParcelas + ", vlrParcelas=" + vlrParcelas + ", diaDebito=" + diaDebito
				+ ", tipoInteg=" + tipoInteg + ", numContaCorrente=" + numContaCorrente + ", dataHoraAtualizacao="
				+ dataHoraAtualizacao + ", descNumMatriculaFunc=" + descNumMatriculaFunc + ", bolSubscricaoProposta="
				+ bolSubscricaoProposta + ", idTipoSubscricao=" + idTipoSubscricao + ", idPessoaLegado="
				+ idPessoaLegado + ", idPessoa=" + idPessoa + "]";
	}
	
}
