package br.com.sicoob.sisbr.cca.legado.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

@Entity
@Table(name="Produto", schema="dbo")
public class ProdutoLegado extends ContaCapitalIntegracaoLegadoEntidade {
	
	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDProduto", nullable=false)
	private Integer idProduto;
	
	@Column(name="DescProduto")
	private String descProduto;
	
	@Column(name="DataUltMovimento")
	private DateTimeDB dataUltMovimento;
	
	@Column(name="DataAtualMovimento")
	private DateTimeDB dataAtualMovimento;
	
	@Column(name="DataProxMovimento")
	private DateTimeDB dataProxMovimento;
	
	@Column(name="CodControleSemana")
	private Integer codControleSemana;
	
	@Column(name="CodControlePeriodo")
	private Integer codControlePeriodo;
	
	@Column(name="DataAntProd")
	private DateTimeDB dataAntProd;
	
	@Column(name="DataProxProd")
	private DateTimeDB dataProxProd;
	
	@Column(name="DataAtualProd")
	private DateTimeDB dataAtualProd;
	
	@Column(name="NumLimiteEmbossamento")
	private Integer numLimiteEmbossamento;
	
	@Column(name="CodSituacaoProc")
	private Integer codSituacaoProc;
	
	@Column(name="SiglaProduto")
	private String siglaProduto;
	
	@Column(name="bolRodarFechamento")
	private Short bolRodarFechamento;
	
	@Column(name="bolReplicarProduto")
	private Short bolReplicarProduto;

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescProduto() {
		return descProduto;
	}

	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}

	public DateTimeDB getDataUltMovimento() {
		return dataUltMovimento;
	}

	public void setDataUltMovimento(DateTimeDB dataUltMovimento) {
		this.dataUltMovimento = dataUltMovimento;
	}

	public DateTimeDB getDataAtualMovimento() {
		return dataAtualMovimento;
	}

	public void setDataAtualMovimento(DateTimeDB dataAtualMovimento) {
		this.dataAtualMovimento = dataAtualMovimento;
	}

	public DateTimeDB getDataProxMovimento() {
		return dataProxMovimento;
	}

	public void setDataProxMovimento(DateTimeDB dataProxMovimento) {
		this.dataProxMovimento = dataProxMovimento;
	}

	public Integer getCodControleSemana() {
		return codControleSemana;
	}

	public void setCodControleSemana(Integer codControleSemana) {
		this.codControleSemana = codControleSemana;
	}

	public Integer getCodControlePeriodo() {
		return codControlePeriodo;
	}

	public void setCodControlePeriodo(Integer codControlePeriodo) {
		this.codControlePeriodo = codControlePeriodo;
	}

	public DateTimeDB getDataAntProd() {
		return dataAntProd;
	}

	public void setDataAntProd(DateTimeDB dataAntProd) {
		this.dataAntProd = dataAntProd;
	}

	public DateTimeDB getDataProxProd() {
		return dataProxProd;
	}

	public void setDataProxProd(DateTimeDB dataProxProd) {
		this.dataProxProd = dataProxProd;
	}

	public DateTimeDB getDataAtualProd() {
		return dataAtualProd;
	}

	public void setDataAtualProd(DateTimeDB dataAtualProd) {
		this.dataAtualProd = dataAtualProd;
	}

	public Integer getNumLimiteEmbossamento() {
		return numLimiteEmbossamento;
	}

	public void setNumLimiteEmbossamento(Integer numLimiteEmbossamento) {
		this.numLimiteEmbossamento = numLimiteEmbossamento;
	}

	public Integer getCodSituacaoProc() {
		return codSituacaoProc;
	}

	public void setCodSituacaoProc(Integer codSituacaoProc) {
		this.codSituacaoProc = codSituacaoProc;
	}

	public String getSiglaProduto() {
		return siglaProduto;
	}

	public void setSiglaProduto(String siglaProduto) {
		this.siglaProduto = siglaProduto;
	}

	public Short getBolRodarFechamento() {
		return bolRodarFechamento;
	}

	public void setBolRodarFechamento(Short bolRodarFechamento) {
		this.bolRodarFechamento = bolRodarFechamento;
	}

	public Short getBolReplicarProduto() {
		return bolReplicarProduto;
	}

	public void setBolReplicarProduto(Short bolReplicarProduto) {
		this.bolReplicarProduto = bolReplicarProduto;
	}
	
	
}
