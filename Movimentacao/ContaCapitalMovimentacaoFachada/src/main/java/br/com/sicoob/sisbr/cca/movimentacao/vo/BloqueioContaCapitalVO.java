package br.com.sicoob.sisbr.cca.movimentacao.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * VO BloqueioContaCapitalVO
 */
public class BloqueioContaCapitalVO extends BancoobVo {

	private static final long serialVersionUID = 459134161564674450L;
	
	private Integer idBloqueio;
	
	private Integer idContaCapital;
	
	private Integer idInstituicao;
	
	private Integer numContaCapital;
	
	private String nomePessoa;

	private String cpfCnpj;
	
	private Integer codTipoBloqueio;
	
	private Integer idTipoBloqueio;
	
	private String nomeTipoBloqueio;
	
	private String numProtocolo;
	
	private String numProcesso;
	
	private Integer codSituacaoBloqueio;
	
	private DateTimeDB dataBloqueio;
	
	private DateTimeDB dataDesbloqueio;
	
	private BigDecimal valorBloqueado;
	
	private BigDecimal valorDesbloqueio;
	
	private DateTimeDB dataProtocolo;
	
	private Boolean ativo;

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
	 * @return the nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * @param nomePessoa the nomePessoa to set
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return the codTipoBloqueio
	 */
	public Integer getCodTipoBloqueio() {
		return codTipoBloqueio;
	}

	/**
	 * @param codTipoBloqueio the codTipoBloqueio to set
	 */
	public void setCodTipoBloqueio(Integer codTipoBloqueio) {
		this.codTipoBloqueio = codTipoBloqueio;
	}

	/**
	 * @return the nomeTipoBloqueio
	 */
	public String getNomeTipoBloqueio() {
		return nomeTipoBloqueio;
	}

	/**
	 * @param nomeTipoBloqueio the nomeTipoBloqueio to set
	 */
	public void setNomeTipoBloqueio(String nomeTipoBloqueio) {
		this.nomeTipoBloqueio = nomeTipoBloqueio;
	}

	/**
	 * @return the numProtocolo
	 */
	public String getNumProtocolo() {
		return numProtocolo;
	}

	/**
	 * @param numProtocolo the numProtocolo to set
	 */
	public void setNumProtocolo(String numProtocolo) {
		this.numProtocolo = numProtocolo;
	}

	/**
	 * @return the codSituacaoBloqueio
	 */
	public Integer getCodSituacaoBloqueio() {
		return codSituacaoBloqueio;
	}

	/**
	 * @param codSituacaoBloqueio the codSituacaoBloqueio to set
	 */
	public void setCodSituacaoBloqueio(Integer codSituacaoBloqueio) {
		this.codSituacaoBloqueio = codSituacaoBloqueio;
	}

	/**
	 * @return the dataBloqueio
	 */
	public DateTimeDB getDataBloqueio() {
		return dataBloqueio;
	}

	/**
	 * @param dataBloqueio the dataBloqueio to set
	 */
	public void setDataBloqueio(DateTimeDB dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}

	/**
	 * @return the valorBloqueado
	 */
	public BigDecimal getValorBloqueado() {
		return valorBloqueado;
	}

	/**
	 * @param valorBloqueado the valorBloqueado to set
	 */
	public void setValorBloqueado(BigDecimal valorBloqueado) {
		this.valorBloqueado = valorBloqueado;
	}

	/**
	 * @return the dataProtocolo
	 */
	public DateTimeDB getDataProtocolo() {
		return dataProtocolo;
	}

	/**
	 * @param dataProtocolo the dataProtocolo to set
	 */
	public void setDataProtocolo(DateTimeDB dataProtocolo) {
		this.dataProtocolo = dataProtocolo;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return the idBloqueio
	 */
	public Integer getIdBloqueio() {
		return idBloqueio;
	}

	/**
	 * @param idBloqueio the idBloqueio to set
	 */
	public void setIdBloqueio(Integer idBloqueio) {
		this.idBloqueio = idBloqueio;
	}

	/**
	 * @return the numProcesso
	 */
	public String getNumProcesso() {
		return numProcesso;
	}

	/**
	 * @param numProcesso the numProcesso to set
	 */
	public void setNumProcesso(String numProcesso) {
		this.numProcesso = numProcesso;
	}

	/**
	 * @return the dataDesbloqueio
	 */
	public DateTimeDB getDataDesbloqueio() {
		return dataDesbloqueio;
	}

	/**
	 * @param dataDesbloqueio the dataDesbloqueio to set
	 */
	public void setDataDesbloqueio(DateTimeDB dataDesbloqueio) {
		this.dataDesbloqueio = dataDesbloqueio;
	}

	/**
	 * @return the valorDesbloqueio
	 */
	public BigDecimal getValorDesbloqueio() {
		return valorDesbloqueio;
	}

	/**
	 * @param valorDesbloqueio the valorDesbloqueio to set
	 */
	public void setValorDesbloqueio(BigDecimal valorDesbloqueio) {
		this.valorDesbloqueio = valorDesbloqueio;
	}

	/**
	 * @return the idTipoBloqueio
	 */
	public Integer getIdTipoBloqueio() {
		return idTipoBloqueio;
	}

	/**
	 * @param idTipoBloqueio the idTipoBloqueio to set
	 */
	public void setIdTipoBloqueio(Integer idTipoBloqueio) {
		this.idTipoBloqueio = idTipoBloqueio;
	}
	
}
