/*
 * 
 */
package br.com.sicoob.sisbr.cca.movimentacao.vo;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.types.DateTimeDB;

// TODO: Auto-generated Javadoc
/**
 * A Classe CadastroContaCapitalRenVO.
 *
 * @author Marco.Nascimento
 */
public class CadastroContaCapitalRenVO extends BancoobVo {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4988895202068300064L;
	
	/** O atributo idContaCapital. */
	private Integer idContaCapital;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo nomePessoa. */
	private String nomePessoa;
	
	/** O atributo nomeCompleto. */
	private String nomeCompleto;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo idPessoaLegado. */
	private Integer idPessoaLegado;
	
	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** O atributo numContaCapitalGerada. */
	private Integer numContaCapitalGerada;
	
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
	private Integer tipoInteg;
	
	/** O atributo numCco. */
	private Long numCco;
	
	/** O atributo idSituacaoCadastro. */
	private Integer idSituacaoCadastro;
	
	/** O atributo idSituacaoContaCapital. */
	private Integer idSituacaoContaCapital;
	
	/** O atributo descSituacaoAprovacaoCapital. */
	private String descSituacaoAprovacaoCapital;
	
	/** O atributo descSituacaoContaCapital. */
	private String descSituacaoContaCapital;
	
	/** O atributo matriculaEscolhida. */
	private Boolean matriculaEscolhida;
	
	/** O atributo dataInclusao. */
	private String dataInclusao;
	
	/** O atributo descCentral. */
	private String descCentral;
	
	/** O atributo descSingular. */
	private String descSingular;
	
	/** O atributo dataHoraAtualizacao. */
	private DateTimeDB dataHoraAtualizacao;
	
	/** O atributo observacao. */
	private String observacao;
	
	/** O atributo idAtividade. */
	private Integer idAtividade;
	
	/** O atributo idsContaCapital. */
	private List<Integer> idsContaCapital;
	
	/** O atributo permissaoExcluir. */
	private Boolean permissaoExcluir;
	
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
	 * Recupera o valor de nomePessoa.
	 *
	 * @return the nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}
	
	/**
	 * Define o valor de nomePessoa.
	 *
	 * @param nomePessoa the nomePessoa to set
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	
	/**
	 * Recupera o valor de cpfCnpj.
	 *
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	
	/**
	 * Define o valor de cpfCnpj.
	 *
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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
	 * Recupera o valor de qtdParcelas.
	 *
	 * @return the qtdParcelas
	 */
	public Integer getQtdParcelas() {
		return qtdParcelas;
	}
	
	/**
	 * Define o valor de qtdParcelas.
	 *
	 * @param qtdParcelas the qtdParcelas to set
	 */
	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}
	
	/**
	 * Recupera o valor de vlrParcelas.
	 *
	 * @return the vlrParcelas
	 */
	public BigDecimal getVlrParcelas() {
		return vlrParcelas;
	}
	
	/**
	 * Define o valor de vlrParcelas.
	 *
	 * @param vlrParcelas the vlrParcelas to set
	 */
	public void setVlrParcelas(BigDecimal vlrParcelas) {
		this.vlrParcelas = vlrParcelas;
	}
	
	/**
	 * Recupera o valor de diaDebito.
	 *
	 * @return the diaDebito
	 */
	public Integer getDiaDebito() {
		return diaDebito;
	}
	
	/**
	 * Define o valor de diaDebito.
	 *
	 * @param diaDebito the diaDebito to set
	 */
	public void setDiaDebito(Integer diaDebito) {
		this.diaDebito = diaDebito;
	}
	
	/**
	 * Recupera o valor de tipoInteg.
	 *
	 * @return the tipoInteg
	 */
	public Integer getTipoInteg() {
		return tipoInteg;
	}
	
	/**
	 * Define o valor de tipoInteg.
	 *
	 * @param tipoInteg the tipoInteg to set
	 */
	public void setTipoInteg(Integer tipoInteg) {
		this.tipoInteg = tipoInteg;
	}
	
	/**
	 * Recupera o valor de idSituacaoCadastro.
	 *
	 * @return the idSituacaoCadastro
	 */
	public Integer getIdSituacaoCadastro() {
		return idSituacaoCadastro;
	}
	
	/**
	 * Define o valor de idSituacaoCadastro.
	 *
	 * @param idSituacaoCadastro the idSituacaoCadastro to set
	 */
	public void setIdSituacaoCadastro(Integer idSituacaoCadastro) {
		this.idSituacaoCadastro = idSituacaoCadastro;
	}
	
	/**
	 * Recupera o valor de descSituacaoAprovacaoCapital.
	 *
	 * @return the descSituacaoAprovacaoCapital
	 */
	public String getDescSituacaoAprovacaoCapital() {
		return descSituacaoAprovacaoCapital;
	}
	
	/**
	 * Define o valor de descSituacaoAprovacaoCapital.
	 *
	 * @param descSituacaoAprovacaoCapital the descSituacaoAprovacaoCapital to set
	 */
	public void setDescSituacaoAprovacaoCapital(String descSituacaoAprovacaoCapital) {
		this.descSituacaoAprovacaoCapital = descSituacaoAprovacaoCapital;
	}
	
	/**
	 * Recupera o valor de descSituacaoContaCapital.
	 *
	 * @return the descSituacaoContaCapital
	 */
	public String getDescSituacaoContaCapital() {
		return descSituacaoContaCapital;
	}
	
	/**
	 * Define o valor de descSituacaoContaCapital.
	 *
	 * @param descSituacaoContaCapital the descSituacaoContaCapital to set
	 */
	public void setDescSituacaoContaCapital(String descSituacaoContaCapital) {
		this.descSituacaoContaCapital = descSituacaoContaCapital;
	}
	
	/**
	 * Recupera o valor de matriculaEscolhida.
	 *
	 * @return the matriculaEscolhida
	 */
	public Boolean getMatriculaEscolhida() {
		return matriculaEscolhida;
	}
	
	/**
	 * Define o valor de matriculaEscolhida.
	 *
	 * @param matriculaEscolhida the matriculaEscolhida to set
	 */
	public void setMatriculaEscolhida(Boolean matriculaEscolhida) {
		this.matriculaEscolhida = matriculaEscolhida;
	}
	
	/**
	 * Recupera o valor de numContaCapitalGerada.
	 *
	 * @return the numContaCapitalGerada
	 */
	public Integer getNumContaCapitalGerada() {
		return numContaCapitalGerada;
	}
	
	/**
	 * Define o valor de numContaCapitalGerada.
	 *
	 * @param numContaCapitalGerada the numContaCapitalGerada to set
	 */
	public void setNumContaCapitalGerada(Integer numContaCapitalGerada) {
		this.numContaCapitalGerada = numContaCapitalGerada;
	}
	
	/**
	 * Recupera o valor de dataInclusao.
	 *
	 * @return the dataInclusao
	 */
	public String getDataInclusao() {
		return dataInclusao;
	}
	
	/**
	 * Define o valor de dataInclusao.
	 *
	 * @param dataInclusao the dataInclusao to set
	 */
	public void setDataInclusao(String dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	
	/**
	 * Recupera o valor de descCentral.
	 *
	 * @return the descCentral
	 */
	public String getDescCentral() {
		return descCentral;
	}
	
	/**
	 * Define o valor de descCentral.
	 *
	 * @param descCentral the descCentral to set
	 */
	public void setDescCentral(String descCentral) {
		this.descCentral = descCentral;
	}
	
	/**
	 * Recupera o valor de descSingular.
	 *
	 * @return the descSingular
	 */
	public String getDescSingular() {
		return descSingular;
	}
	
	/**
	 * Define o valor de descSingular.
	 *
	 * @param descSingular the descSingular to set
	 */
	public void setDescSingular(String descSingular) {
		this.descSingular = descSingular;
	}

	/**
	 * Recupera o valor de dataHoraAtualizacao.
	 *
	 * @return the dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}
	
	/**
	 * Define o valor de dataHoraAtualizacao.
	 *
	 * @param dataHoraAtualizacao the dataHoraAtualizacao to set
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
	
	/**
	 * Recupera o valor de observacao.
	 *
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}
	
	/**
	 * Define o valor de observacao.
	 *
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	/**
	 * Recupera o valor de nomeCompleto.
	 *
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	/**
	 * Define o valor de nomeCompleto.
	 *
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	/**
	 * Recupera o valor de idAtividade.
	 *
	 * @return the idAtividade
	 */
	public Integer getIdAtividade() {
		return idAtividade;
	}
	
	/**
	 * Define o valor de idAtividade.
	 *
	 * @param idAtividade the idAtividade to set
	 */
	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
	}
	
	/**
	 * Recupera o valor de idsContaCapital.
	 *
	 * @return the idsContaCapital
	 */
	public List<Integer> getIdsContaCapital() {
		return idsContaCapital;
	}
	
	/**
	 * Define o valor de idsContaCapital.
	 *
	 * @param idsContaCapital the idsContaCapital to set
	 */
	public void setIdsContaCapital(List<Integer> idsContaCapital) {
		this.idsContaCapital = idsContaCapital;
	}
	
	/**
	 * Recupera o valor de idSituacaoContaCapital.
	 *
	 * @return the idSituacaoContaCapital
	 */
	public Integer getIdSituacaoContaCapital() {
		return idSituacaoContaCapital;
	}
	
	/**
	 * Define o valor de idSituacaoContaCapital.
	 *
	 * @param idSituacaoContaCapital the idSituacaoContaCapital to set
	 */
	public void setIdSituacaoContaCapital(Integer idSituacaoContaCapital) {
		this.idSituacaoContaCapital = idSituacaoContaCapital;
	}
	
	/**
	 * Recupera o valor de numCco.
	 *
	 * @return the numCco
	 */
	public Long getNumCco() {
		return numCco;
	}
	
	/**
	 * Define o valor de numCco.
	 *
	 * @param numCco the numCco to set
	 */
	public void setNumCco(Long numCco) {
		this.numCco = numCco;
	}
	
	/**
	 * Recupera o valor de permissaoExcluir.
	 *
	 * @return o valor de permissaoExcluir
	 */
	public Boolean getPermissaoExcluir() {
		return permissaoExcluir;
	}
	
	/**
	 * Define o valor de permissaoExcluir.
	 *
	 * @param permissaoExcluir o novo valor de permissaoExcluir
	 */
	public void setPermissaoExcluir(Boolean permissaoExcluir) {
		this.permissaoExcluir = permissaoExcluir;
	}
}