/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Identificador do tipo de historico de lancamento
 * @author marco.nascimento
 * @see GrupoHistorico
 * @since 15/05/2014
 */
@Entity
@Table(name = "TIPOHISTORICOCCA", schema = "cca")
public class TipoHistoricoCCA extends ContaCapitalEntidade<Short> {
	
	/**
	 * Construtor Padr�o
	 */
	public TipoHistoricoCCA() {

	}
	
	/**
	 * Construtor com o ID
	 * @param id
	 */
	public TipoHistoricoCCA(Short id) {
		this.id = id;
	}
	
	/**
	 * Identificador do hist�rico que ser� usado no lan�amento
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTIPOHISTORICO")
	private Short id;
	
	/**
	 * Indica com qual produto o hist�rico est� relacionado. 
	 * No caso desta tabela ser� sempre o poupan�a.
	 */
	@Column(name = "IDPRODUTO")
	private Short idProduto;
	
	/**
	 * Descri��o do hist�rico de lan�amento
	 */
	@Column(name = "DESCTIPOHISTORICO", length = 80)
	private String descricao;

	/**
	 * Descri��o reduzida do hist�rico de lan�amento
	 */
	@Column(name = "DESCTIPOHISTORICOREDUZIDO", length = 38)
	private String descricaoReduzida;
	
	/**
	 * Informa qual a natureza do historico. (Exemplo: C cr�dito, D d�bito). 
	 */
	@Column(name = "CODNATUREZAOPERACAO", length = 1)
	private String naturezaOperacao;
	
	/**
	 * TODO: Produto Operacao?
	 */
	@Column(name = "IDPRODUTOOPERACAO")
	private Short produtoOperacao;
	
	/**
	 * Identifica a opera��o financeira do mecanismo cont�bil que o hist�rico est� relacionado.
	 */
	@Column(name = "IDOPERACAO")
	private Short operacao;
	
	/**
	 * 	Identificador do produto vinculado � opera��o cont�bil de estorno
	 */
	@Column(name = "IDPRODUTOOPERACAOESTORNO")
	private Short operacaoProdutoEstorno;
	
	/**
	 * Identificador da opera��o cont�bil vinculado ao hist�rico de estorno
	 */
	@Column(name = "IDOPERACAOESTORNO")
	private Short operacaoEstorno;
	
	/**
	 * Hist�rico utilizado pelo Bancoob
	 */
	@Column(name = "BOLUSOBANCOOB")
	private Boolean usoBancoob;
	
	/**
	 * Hist�rico utilizado pelas Cooperativas
	 */
	@Column(name = "BOLUSOSINGULAR")
	private Boolean usoSingular;
	
	/**
	 * Hist�rico utilizado pelas Centrais
	 */
	@Column(name = "BOLUSOCENTRAL")
	private Boolean usoCentral;
	
	/**
	 * Identificador do grupo hist�rico
	 */
	@ManyToOne
	@JoinColumn(name = "IDGRUPOHISTORICO", nullable = false)
	private GrupoHistorico grupoHistorico;
	
	/**
	 * Auto relacionamento
	 * Identificador do hist�rico que ser� usado no lan�amento
	 */
	@ManyToOne 
	@JoinColumn(name = "IDTIPOHISTORICOESTORNO", insertable = true, updatable = true, nullable = true) 
	private TipoHistoricoCCA tipoHistoricoCCAPai;

	/**
	 * @return the id
	 */
	public Short getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Short id) {
		this.id = id;
	}

	/**
	 * @return the idProduto
	 */
	public Short getIdProduto() {
		return idProduto;
	}

	/**
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Short idProduto) {
		this.idProduto = idProduto;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricaoReduzida
	 */
	public String getDescricaoReduzida() {
		return descricaoReduzida;
	}

	/**
	 * @param descricaoReduzida the descricaoReduzida to set
	 */
	public void setDescricaoReduzida(String descricaoReduzida) {
		this.descricaoReduzida = descricaoReduzida;
	}

	/**
	 * @return the naturezaOperacao
	 */
	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	/**
	 * @param naturezaOperacao the naturezaOperacao to set
	 */
	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}

	/**
	 * @return the produtoOperacao
	 */
	public Short getProdutoOperacao() {
		return produtoOperacao;
	}

	/**
	 * @param produtoOperacao the produtoOperacao to set
	 */
	public void setProdutoOperacao(Short produtoOperacao) {
		this.produtoOperacao = produtoOperacao;
	}

	/**
	 * @return the operacao
	 */
	public Short getOperacao() {
		return operacao;
	}

	/**
	 * @param operacao the operacao to set
	 */
	public void setOperacao(Short operacao) {
		this.operacao = operacao;
	}

	/**
	 * @return the operacaoProdutoEstorno
	 */
	public Short getOperacaoProdutoEstorno() {
		return operacaoProdutoEstorno;
	}

	/**
	 * @param operacaoProdutoEstorno the operacaoProdutoEstorno to set
	 */
	public void setOperacaoProdutoEstorno(Short operacaoProdutoEstorno) {
		this.operacaoProdutoEstorno = operacaoProdutoEstorno;
	}

	/**
	 * @return the operacaoEstorno
	 */
	public Short getOperacaoEstorno() {
		return operacaoEstorno;
	}

	/**
	 * @param operacaoEstorno the operacaoEstorno to set
	 */
	public void setOperacaoEstorno(Short operacaoEstorno) {
		this.operacaoEstorno = operacaoEstorno;
	}

	/**
	 * @return the usoBancoob
	 */
	public Boolean getUsoBancoob() {
		return usoBancoob;
	}

	/**
	 * @param usoBancoob the usoBancoob to set
	 */
	public void setUsoBancoob(Boolean usoBancoob) {
		this.usoBancoob = usoBancoob;
	}

	/**
	 * @return the usoSingular
	 */
	public Boolean getUsoSingular() {
		return usoSingular;
	}

	/**
	 * @param usoSingular the usoSingular to set
	 */
	public void setUsoSingular(Boolean usoSingular) {
		this.usoSingular = usoSingular;
	}

	/**
	 * @return the usoCentral
	 */
	public Boolean getUsoCentral() {
		return usoCentral;
	}

	/**
	 * @param usoCentral the usoCentral to set
	 */
	public void setUsoCentral(Boolean usoCentral) {
		this.usoCentral = usoCentral;
	}

	/**
	 * @return the grupoHistorico
	 */
	public GrupoHistorico getGrupoHistorico() {
		return grupoHistorico;
	}

	/**
	 * @param grupoHistorico the grupoHistorico to set
	 */
	public void setGrupoHistorico(GrupoHistorico grupoHistorico) {
		this.grupoHistorico = grupoHistorico;
	}

	/**
	 * @return the tipoHistoricoCCAPai
	 */
	public TipoHistoricoCCA getTipoHistoricoCCAPai() {
		return tipoHistoricoCCAPai;
	}

	/**
	 * @param tipoHistoricoCCAPai the tipoHistoricoCCAPai to set
	 */
	public void setTipoHistoricoCCAPai(TipoHistoricoCCA tipoHistoricoCCAPai) {
		this.tipoHistoricoCCAPai = tipoHistoricoCCAPai;
	} 
}