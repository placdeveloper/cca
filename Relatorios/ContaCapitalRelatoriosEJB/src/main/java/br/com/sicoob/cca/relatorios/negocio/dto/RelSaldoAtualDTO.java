/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * The Class RelSaldoAtualDTO.
 */
public class RelSaldoAtualDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -120565210505268621L;
	
	/** The id instituicao. */
	private Integer idInstituicao;
	
	/** The num conta capital inicial. */
	private Integer numContaCapitalInicial;
	
	/** The num conta capital final. */
	private Integer numContaCapitalFinal;
	
	private Integer numContaCapital;
	
	private String nomeCliente;
	
	private String matriculaFuncionario;
	
	private String situacaoAprovacao;
	
	private BigDecimal subscricao;
	
	private BigDecimal integralizacao;
	
	private BigDecimal valorADevolver;
	
	private BigDecimal valorARealizar;
	
	private String descPA;
	
	/** The situacao conta. */
	private String situacaoConta;	
	
	private Integer idSituacaoConta;
	
	/** The cadastros aprovados. */
	private Boolean cadastrosAprovados;
	
	/** The num PA. */
	private Integer numPA;
	
	/** The ordenacao. */
	private Integer ordenacao;
	
	/** The cnpj empresa. */
	private String cnpjEmpresa;
	
	/** The desc empresa. */
	private String descEmpresa;
	
	/** The agrupar por PA. */
	private Boolean agruparPorPA;
	
	private Date dataAtualProduto;
	
	private Integer numPessoaJuridica;
	
	private Integer idPessoaJuridica;
	
	/** The saldos. */
	private List<RelSaldoAtualDTO> saldos;
	
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
	
	/**
	 * Gets the desc empresa.
	 *
	 * @return the desc empresa
	 */
	public String getDescEmpresa() {
		return descEmpresa;
	}

	/**
	 * Sets the desc empresa.
	 *
	 * @param descEmpresa the new desc empresa
	 */
	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}

	/**
	 * Gets the agrupar por PA.
	 *
	 * @return the agrupar por PA
	 */
	public Boolean getAgruparPorPA() {
		return agruparPorPA;
	}
	
	/**
	 * Sets the agrupar por PA.
	 *
	 * @param agruparPorPA the new agrupar por PA
	 */
	public void setAgruparPorPA(Boolean agruparPorPA) {
		this.agruparPorPA = agruparPorPA;
	}

	/**
	 * @return the saldos
	 */
	public List<RelSaldoAtualDTO> getSaldos() {
		return saldos;
	}

	/**
	 * @param saldos the saldos to set
	 */
	public void setSaldos(List<RelSaldoAtualDTO> saldos) {
		this.saldos = saldos;
	}

	/**
	 * @return the cnpjEmpresa
	 */
	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	/**
	 * @param cnpjEmpresa the cnpjEmpresa to set
	 */
	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
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
	 * @return the nomeCliente
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente the nomeCliente to set
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return the matriculaFuncionario
	 */
	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	/**
	 * @param matriculaFuncionario the matriculaFuncionario to set
	 */
	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	/**
	 * @return the situacaoAprovacao
	 */
	public String getSituacaoAprovacao() {
		return situacaoAprovacao;
	}

	/**
	 * @param situacaoAprovacao the situacaoAprovacao to set
	 */
	public void setSituacaoAprovacao(String situacaoAprovacao) {
		this.situacaoAprovacao = situacaoAprovacao;
	}

	/**
	 * @return the subscricao
	 */
	public BigDecimal getSubscricao() {
		return subscricao;
	}

	/**
	 * @param subscricao the subscricao to set
	 */
	public void setSubscricao(BigDecimal subscricao) {
		this.subscricao = subscricao;
	}

	/**
	 * @return the integralizacao
	 */
	public BigDecimal getIntegralizacao() {
		return integralizacao;
	}

	/**
	 * @param integralizacao the integralizacao to set
	 */
	public void setIntegralizacao(BigDecimal integralizacao) {
		this.integralizacao = integralizacao;
	}

	/**
	 * @return the valorADevolver
	 */
	public BigDecimal getValorADevolver() {
		return valorADevolver;
	}

	/**
	 * @param valorADevolver the valorADevolver to set
	 */
	public void setValorADevolver(BigDecimal valorADevolver) {
		this.valorADevolver = valorADevolver;
	}

	/**
	 * @return the valorARealizar
	 */
	public BigDecimal getValorARealizar() {
		return valorARealizar;
	}

	/**
	 * @param valorARealizar the valorARealizar to set
	 */
	public void setValorARealizar(BigDecimal valorARealizar) {
		this.valorARealizar = valorARealizar;
	}

	/**
	 * @return the descPA
	 */
	public String getDescPA() {
		return descPA;
	}

	/**
	 * @param descPA the descPA to set
	 */
	public void setDescPA(String descPA) {
		this.descPA = descPA;
	}

	/**
	 * @return the situacaoConta
	 */
	public String getSituacaoConta() {
		return situacaoConta;
	}

	/**
	 * @param situacaoConta the situacaoConta to set
	 */
	public void setSituacaoConta(String situacaoConta) {
		this.situacaoConta = situacaoConta;
	}

	/**
	 * @return the idSituacaoConta
	 */
	public Integer getIdSituacaoConta() {
		return idSituacaoConta;
	}

	/**
	 * @param idSituacaoConta the idSituacaoConta to set
	 */
	public void setIdSituacaoConta(Integer idSituacaoConta) {
		this.idSituacaoConta = idSituacaoConta;
	}

	/**
	 * @return the dataAtualProduto
	 */
	public Date getDataAtualProduto() {
		return dataAtualProduto;
	}

	/**
	 * @param dataAtualProduto the dataAtualProduto to set
	 */
	public void setDataAtualProduto(Date dataAtualProduto) {
		this.dataAtualProduto = dataAtualProduto;
	}

	/**
	 * @return the numPessoaJuridica
	 */
	public Integer getNumPessoaJuridica() {
		return numPessoaJuridica;
	}

	/**
	 * @param numPessoaJuridica the numPessoaJuridica to set
	 */
	public void setNumPessoaJuridica(Integer numPessoaJuridica) {
		this.numPessoaJuridica = numPessoaJuridica;
	}

	/**
	 * @return the idPessoaJuridica
	 */
	public Integer getIdPessoaJuridica() {
		return idPessoaJuridica;
	}

	/**
	 * @param idPessoaJuridica the idPessoaJuridica to set
	 */
	public void setIdPessoaJuridica(Integer idPessoaJuridica) {
		this.idPessoaJuridica = idPessoaJuridica;
	}
}