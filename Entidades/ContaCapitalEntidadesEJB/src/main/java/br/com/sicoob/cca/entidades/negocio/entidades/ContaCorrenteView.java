package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * View do conta corrente
 * @author Nairon.Silva
 */
@Entity
@Table(name = "VIW_CONTACORRENTE", schema = "cco")
public class ContaCorrenteView extends ContaCapitalEntidade<Long> {

	private static final long serialVersionUID = -660298388873796252L;

	@Id
	@Column(name = "IDCONTACORRENTE")
	private Long id;
	
	@Column(name = "IDINSTITUICAO")
	private Integer idInstituicao;
	
	@Column(name = "NUMCONTACORRENTE")
	private Integer numContaCorrente;
	
	@Column(name = "CODCATEGORIACCO")
	private Integer codCategoriaCCO;
	
	@Column(name = "IDGRUPOCONTA")
	private Integer idGrupoConta;
	
	@Column(name = "DESCGRUPO")
	private String descGrupo;
	
	@Column(name = "IDFONTEPAGADORA")
	private Integer idFontePagadora;
	
	@Column(name = "IDMODALIDADETIPOCONTA")
	private Integer idModalidadeTipoConta;
	
	@Column(name = "IDTIPOCONTACORRENTE")
	private Integer idTipoContaCorrente;
	
	@Column(name = "DESCTIPOCONTA")
	private String descTipoConta;
	
	@Column(name = "CODTIPOPESSOA")
	private Integer codTipoPessoa;
	
	@Column(name = "IDMODALIDADE")
	private Integer idModalidade;
	
	@Column(name = "DESCMODALIDADE")
	private String descModalidade;

	@Column(name = "DESCSIGLA")
	private String descSigla;
	
	@Column(name = "CODSITUACAOCCO")
	private Integer codSituacaoCCO;
	
	@Column(name = "DATAHORAABERTURA")
	private DateTimeDB dataHoraAbertura;
	
	@Column(name = "DATAHORAENCERRAMENTO")
	private DateTimeDB dataHoraEncerramento;
	
	@Column(name = "CODSITUACAOAGENDAMENTO")
	private Integer codSituacaoAgendamento;
	
	@Column(name = "IDPESSOA")
	private Integer idPessoa;
	
	@Column(name = "NUMORDEMTITULARIDADE")
	private Integer numOrdemTitularidade;
	
	@Column(name = "NOMEEMBOSSAMENTO")
	private String nomeEmbossamento;
	
	@Column(name = "BOLATIVO")
	private Integer bolAtivo;
	
	/**
	 * Verifica se a conta esta ativa
	 * @return
	 */
	public boolean isAtiva() {
		return Integer.valueOf(1).equals(bolAtivo);
	}
	
	/**
	 * Verifica se a conta esta bloqueada ou encerrada
	 * @return
	 */
	public boolean isBloqueadaOuEncerrada() {
		final Integer codBloqueada = 3;
		final Integer codEncerrada = 4;
		return codBloqueada.equals(codSituacaoCCO) || codEncerrada.equals(codSituacaoCCO);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(Integer numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	public Integer getCodCategoriaCCO() {
		return codCategoriaCCO;
	}

	public void setCodCategoriaCCO(Integer codCategoriaCCO) {
		this.codCategoriaCCO = codCategoriaCCO;
	}

	public Integer getIdGrupoConta() {
		return idGrupoConta;
	}

	public void setIdGrupoConta(Integer idGrupoConta) {
		this.idGrupoConta = idGrupoConta;
	}

	public String getDescGrupo() {
		return descGrupo;
	}

	public void setDescGrupo(String descGrupo) {
		this.descGrupo = descGrupo;
	}

	public Integer getIdFontePagadora() {
		return idFontePagadora;
	}

	public void setIdFontePagadora(Integer idFontePagadora) {
		this.idFontePagadora = idFontePagadora;
	}

	public Integer getIdModalidadeTipoConta() {
		return idModalidadeTipoConta;
	}

	public void setIdModalidadeTipoConta(Integer idModalidadeTipoConta) {
		this.idModalidadeTipoConta = idModalidadeTipoConta;
	}

	public Integer getIdTipoContaCorrente() {
		return idTipoContaCorrente;
	}

	public void setIdTipoContaCorrente(Integer idTipoContaCorrente) {
		this.idTipoContaCorrente = idTipoContaCorrente;
	}

	public String getDescTipoConta() {
		return descTipoConta;
	}

	public void setDescTipoConta(String descTipoConta) {
		this.descTipoConta = descTipoConta;
	}

	public Integer getCodTipoPessoa() {
		return codTipoPessoa;
	}

	public void setCodTipoPessoa(Integer codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}

	public Integer getIdModalidade() {
		return idModalidade;
	}

	public void setIdModalidade(Integer idModalidade) {
		this.idModalidade = idModalidade;
	}

	public String getDescModalidade() {
		return descModalidade;
	}

	public void setDescModalidade(String descModalidade) {
		this.descModalidade = descModalidade;
	}

	public String getDescSigla() {
		return descSigla;
	}

	public void setDescSigla(String descSigla) {
		this.descSigla = descSigla;
	}

	public Integer getCodSituacaoCCO() {
		return codSituacaoCCO;
	}

	public void setCodSituacaoCCO(Integer codSituacaoCCO) {
		this.codSituacaoCCO = codSituacaoCCO;
	}

	public DateTimeDB getDataHoraAbertura() {
		return dataHoraAbertura;
	}

	public void setDataHoraAbertura(DateTimeDB dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}

	public DateTimeDB getDataHoraEncerramento() {
		return dataHoraEncerramento;
	}

	public void setDataHoraEncerramento(DateTimeDB dataHoraEncerramento) {
		this.dataHoraEncerramento = dataHoraEncerramento;
	}

	public Integer getCodSituacaoAgendamento() {
		return codSituacaoAgendamento;
	}

	public void setCodSituacaoAgendamento(Integer codSituacaoAgendamento) {
		this.codSituacaoAgendamento = codSituacaoAgendamento;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Integer getNumOrdemTitularidade() {
		return numOrdemTitularidade;
	}

	public void setNumOrdemTitularidade(Integer numOrdemTitularidade) {
		this.numOrdemTitularidade = numOrdemTitularidade;
	}

	public String getNomeEmbossamento() {
		return nomeEmbossamento;
	}

	public void setNomeEmbossamento(String nomeEmbossamento) {
		this.nomeEmbossamento = nomeEmbossamento;
	}

	public Integer getBolAtivo() {
		return bolAtivo;
	}

	public void setBolAtivo(Integer bolAtivo) {
		this.bolAtivo = bolAtivo;
	}
	
}
