/*
 * 
 */
package br.com.sicoob.sisbr.cca.movimentacao.vo;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;

// TODO: Auto-generated Javadoc
/**
 * A Classe DesligarContaCapitalRenVO.
 *
 * @author Antonio.Genaro
 */
public class DesligarContaCapitalRenVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -3676042376475239355L;
	
	/** O atributo idContaCapital. */
	private Integer idContaCapital;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** O atributo idPessoaLegado. */
	private Integer idPessoaLegado;
	
	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo vlrSubs. */
	private BigDecimal vlrSubs;
	
	/** O atributo vlrInteg. */
	private BigDecimal vlrInteg;
	
	/** O atributo vlrAInteg. */
	private BigDecimal vlrAInteg;
	
	/** O atributo vlrBloq. */
	private BigDecimal vlrBloq;
	
	/** O atributo vlrDevol. */
	private BigDecimal vlrDevol;
	
	private BigDecimal vlrEmprestimos;
	
	/** O atributo qtdCotas. */
	private Integer qtdCotas;
	
	/** O atributo tipoOperacao. */
	private Integer tipoOperacao;
	
	/** O atributo dataDesligamento. */
	private Date dataDesligamento;
	
	/**
	 * Recupera o valor de idContaCapital.
	 *
	 * @return the idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	
	/**
	 * Define o valor de idContaCapital.
	 *
	 * @param idContaCapital the idContaCapital to set
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}
	
	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	
	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	/**
	 * Recupera o valor de numContaCapital.
	 *
	 * @return the numContaCapital
	 */
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	
	/**
	 * Define o valor de numContaCapital.
	 *
	 * @param numContaCapital the numContaCapital to set
	 */
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	
	/**
	 * Recupera o valor de idPessoaLegado.
	 *
	 * @return the idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}
	
	/**
	 * Define o valor de idPessoaLegado.
	 *
	 * @param idPessoaLegado the idPessoaLegado to set
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}
	
	/**
	 * Recupera o valor de idPessoa.
	 *
	 * @return the idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}
	
	/**
	 * Define o valor de idPessoa.
	 *
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	/**
	 * Recupera o valor de vlrSubs.
	 *
	 * @return the vlrSubs
	 */
	public BigDecimal getVlrSubs() {
		return vlrSubs;
	}
	
	/**
	 * Define o valor de vlrSubs.
	 *
	 * @param vlrSubs the vlrSubs to set
	 */
	public void setVlrSubs(BigDecimal vlrSubs) {
		this.vlrSubs = vlrSubs;
	}
	
	/**
	 * Recupera o valor de vlrInteg.
	 *
	 * @return the vlrInteg
	 */
	public BigDecimal getVlrInteg() {
		return vlrInteg;
	}
	
	/**
	 * Define o valor de vlrInteg.
	 *
	 * @param vlrInteg the vlrInteg to set
	 */
	public void setVlrInteg(BigDecimal vlrInteg) {
		this.vlrInteg = vlrInteg;
	}
	
	/**
	 * Recupera o valor de vlrAInteg.
	 *
	 * @return the vlrAInteg
	 */
	public BigDecimal getVlrAInteg() {
		return vlrAInteg;
	}
	
	/**
	 * Define o valor de vlrAInteg.
	 *
	 * @param vlrAInteg the vlrAInteg to set
	 */
	public void setVlrAInteg(BigDecimal vlrAInteg) {
		this.vlrAInteg = vlrAInteg;
	}
	
	/**
	 * Recupera o valor de vlrBloq.
	 *
	 * @return the vlrBloq
	 */
	public BigDecimal getVlrBloq() {
		return vlrBloq;
	}
	
	/**
	 * Define o valor de vlrBloq.
	 *
	 * @param vlrBloq the vlrBloq to set
	 */
	public void setVlrBloq(BigDecimal vlrBloq) {
		this.vlrBloq = vlrBloq;
	}
	
	/**
	 * Recupera o valor de qtdCotas.
	 *
	 * @return the qtdCotas
	 */
	public Integer getQtdCotas() {
		return qtdCotas;
	}
	
	/**
	 * Define o valor de qtdCotas.
	 *
	 * @param qtdCotas the qtdCotas to set
	 */
	public void setQtdCotas(Integer qtdCotas) {
		this.qtdCotas = qtdCotas;
	}
	
	/**
	 * Recupera o valor de vlrDevol.
	 *
	 * @return the vlrDevol
	 */
	public BigDecimal getVlrDevol() {
		return vlrDevol;
	}
	
	/**
	 * Define o valor de vlrDevol.
	 *
	 * @param vlrDevol the vlrDevol to set
	 */
	public void setVlrDevol(BigDecimal vlrDevol) {
		this.vlrDevol = vlrDevol;
	}
	
	/**
	 * Recupera o valor de tipoOperacao.
	 *
	 * @return the tipoOperacao
	 */
	public Integer getTipoOperacao() {
		return tipoOperacao;
	}
	
	/**
	 * Define o valor de tipoOperacao.
	 *
	 * @param tipoOperacao the tipoOperacao to set
	 */
	public void setTipoOperacao(Integer tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	
	/**
	 * Recupera o valor de dataDesligamento.
	 *
	 * @return the dataDesligamento
	 */
	public Date getDataDesligamento() {
		if(dataDesligamento != null){
			return new Date(dataDesligamento.getTime());
		}
		return null;
	}
	
	/**
	 * Define o valor de dataDesligamento.
	 *
	 * @param dataDesligamento the dataDesligamento to set
	 */
	public void setDataDesligamento(Date dataDesligamento) {
		if(dataDesligamento != null){
			this.dataDesligamento = new Date(dataDesligamento.getTime());
		} else{
			this.dataDesligamento = null;
		}
	}

	public BigDecimal getVlrEmprestimos() {
		return vlrEmprestimos;
	}

	public void setVlrEmprestimos(BigDecimal vlrEmprestimos) {
		this.vlrEmprestimos = vlrEmprestimos;
	}
}