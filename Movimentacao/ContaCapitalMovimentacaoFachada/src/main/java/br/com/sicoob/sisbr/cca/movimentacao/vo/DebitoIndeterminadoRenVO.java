package br.com.sicoob.sisbr.cca.movimentacao.vo;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe DebitoIndeterminadoRenVO
 *
 * @author marco.nascimento
 */
public class DebitoIndeterminadoRenVO extends BancoobVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7103041311762821940L;
	
	private Long idDebitoContaCapital;
	private Integer tipoInclusao;
	private Integer idContaCapital;
	private List<Integer> idsContaCapital;
	private List<Integer> idsDebitoContaCapital;
	private List<Integer> idsNumMatricula;
	private List<Long> contasCorrente;
	private Integer idInstituicao;
	private Integer numContaCapital;
	private Integer idPessoaLegado;
	private Integer idPessoa;
	private Integer tipoInteg;
	private Long numContaCorrente;
	private Integer formaDebito;
	private BigDecimal qtdCotas;
	private BigDecimal percentual;
	private BigDecimal vlrDebito;
	private Integer periodoDebito;
	private Integer numPeriodo;
	private DateTime dataInicialDeb;
	private String nome;
	private Integer codTipoPessoa;
	private String descMatriculaFunc;
	
	/**
	 * @return the tipoInclusao
	 */
	public Integer getTipoInclusao() {
		return tipoInclusao;
	}
	/**
	 * @param tipoInclusao the tipoInclusao to set
	 */
	public void setTipoInclusao(Integer tipoInclusao) {
		this.tipoInclusao = tipoInclusao;
	}
	/**
	 * @return the idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	/**
	 * @param idContaCapital the idContaCapital to set
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
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
	 * @return the idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}
	/**
	 * @param idPessoaLegado the idPessoaLegado to set
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
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
	 * @return the tipoInteg
	 */
	public Integer getTipoInteg() {
		return tipoInteg;
	}
	/**
	 * @param tipoInteg the tipoInteg to set
	 */
	public void setTipoInteg(Integer tipoInteg) {
		this.tipoInteg = tipoInteg;
	}
	/**
	 * @return the formaDebito
	 */
	public Integer getFormaDebito() {
		return formaDebito;
	}
	/**
	 * @param formaDebito the formaDebito to set
	 */
	public void setFormaDebito(Integer formaDebito) {
		this.formaDebito = formaDebito;
	}
	/**
	 * @return the qtdCotas
	 */
	public BigDecimal getQtdCotas() {
		return qtdCotas;
	}
	/**
	 * @param qtdCotas the qtdCotas to set
	 */
	public void setQtdCotas(BigDecimal qtdCotas) {
		this.qtdCotas = qtdCotas;
	}
	/**
	 * @return the percentual
	 */
	public BigDecimal getPercentual() {
		return percentual;
	}
	/**
	 * @param percentual the percentual to set
	 */
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}
	/**
	 * @return the vlrDebito
	 */
	public BigDecimal getVlrDebito() {
		return vlrDebito;
	}
	/**
	 * @param vlrDebito the vlrDebito to set
	 */
	public void setVlrDebito(BigDecimal vlrDebito) {
		this.vlrDebito = vlrDebito;
	}
	/**
	 * @return the periodoDebito
	 */
	public Integer getPeriodoDebito() {
		return periodoDebito;
	}
	/**
	 * @param periodoDebito the periodoDebito to set
	 */
	public void setPeriodoDebito(Integer periodoDebito) {
		this.periodoDebito = periodoDebito;
	}
	/**
	 * @return the numContaCorrente
	 */
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}
	/**
	 * @param numContaCorrente the numContaCorrente to set
	 */
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
	/**
	 * @return the numPeriodo
	 */
	public Integer getNumPeriodo() {
		return numPeriodo;
	}
	/**
	 * @param numPeriodo the numPeriodo to set
	 */
	public void setNumPeriodo(Integer numPeriodo) {
		this.numPeriodo = numPeriodo;
	}
	/**
	 * @return the idDebitoContaCapital
	 */
	public Long getIdDebitoContaCapital() {
		return idDebitoContaCapital;
	}
	/**
	 * @param idDebitoContaCapital the idDebitoContaCapital to set
	 */
	public void setIdDebitoContaCapital(Long idDebitoContaCapital) {
		this.idDebitoContaCapital = idDebitoContaCapital;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the codTipoPessoa
	 */
	public Integer getCodTipoPessoa() {
		return codTipoPessoa;
	}
	/**
	 * @param codTipoPessoa the codTipoPessoa to set
	 */
	public void setCodTipoPessoa(Integer codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}
	/**
	 * @return the dataInicialDeb
	 */
	public DateTime getDataInicialDeb() {
		return dataInicialDeb;
	}
	/**
	 * @param dataInicialDeb the dataInicialDeb to set
	 */
	public void setDataInicialDeb(DateTime dataInicialDeb) {
		this.dataInicialDeb = dataInicialDeb;
	}
	/**
	 * @return the idsContaCapital
	 */
	public List<Integer> getIdsContaCapital() {
		return idsContaCapital;
	}
	/**
	 * @param idsContaCapital the idsContaCapital to set
	 */
	public void setIdsContaCapital(List<Integer> idsContaCapital) {
		this.idsContaCapital = idsContaCapital;
	}
	/**
	 * @return the descMatriculaFunc
	 */
	public String getDescMatriculaFunc() {
		return descMatriculaFunc;
	}
	/**
	 * @param descMatriculaFunc the descMatriculaFunc to set
	 */
	public void setDescMatriculaFunc(String descMatriculaFunc) {
		this.descMatriculaFunc = descMatriculaFunc;
	}
	/**
	 * @return the idsNumMatricula
	 */
	public List<Integer> getIdsNumMatricula() {
		return idsNumMatricula;
	}
	/**
	 * @param idsNumMatricula the idsNumMatricula to set
	 */
	public void setIdsNumMatricula(List<Integer> idsNumMatricula) {
		this.idsNumMatricula = idsNumMatricula;
	}
	/**
	 * @return the contasCorrente
	 */
	public List<Long> getContasCorrente() {
		return contasCorrente;
	}
	/**
	 * @param contasCorrente the contasCorrente to set
	 */
	public void setContasCorrente(List<Long> contasCorrente) {
		this.contasCorrente = contasCorrente;
	}
	/**
	 * @return the idsDebitoContaCapital
	 */
	public List<Integer> getIdsDebitoContaCapital() {
		return idsDebitoContaCapital;
	}
	/**
	 * @param idsDebitoContaCapital the idsDebitoContaCapital to set
	 */
	public void setIdsDebitoContaCapital(List<Integer> idsDebitoContaCapital) {
		this.idsDebitoContaCapital = idsDebitoContaCapital;
	}
}