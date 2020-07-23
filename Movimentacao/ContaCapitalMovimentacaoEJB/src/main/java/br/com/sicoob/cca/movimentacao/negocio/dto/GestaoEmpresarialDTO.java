/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

// TODO: Auto-generated Javadoc
/**
 * A Classe GestaoEmpresarialDTO.
 *
 * @author Antonio.Genaro
 */
public class GestaoEmpresarialDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2599091906489383285L;

	/** O atributo numPac. */
	private Integer numPac;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo codPFPJ. */
	private Integer codPFPJ;
	
	/** O atributo dataInicio. */
	private Date dataInicio;
	
	/** O atributo dataFim. */
	private Date dataFim;
	
	/** O atributo baseIRRF. */
	private BigDecimal baseIRRF;
	
	/** O atributo valorIRRF. */
	private BigDecimal valorIRRF;
	
	/** O atributo idProduto. */
	private Integer idProduto;
	
	/** O atributo nomeProduto. */
	private String nomeProduto;

	/**
	 * Recupera o valor de numPac.
	 *
	 * @return the numPac
	 */
	public Integer getNumPac() {
		return numPac;
	}

	/**
	 * Define o valor de numPac.
	 *
	 * @param numPac the numPac to set
	 */
	public void setNumPac(Integer numPac) {
		this.numPac = numPac;
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
	 * Recupera o valor de codPFPJ.
	 *
	 * @return the codPFPJ
	 */
	public Integer getCodPFPJ() {
		return codPFPJ;
	}

	/**
	 * Define o valor de codPFPJ.
	 *
	 * @param codPFPJ the codPFPJ to set
	 */
	public void setCodPFPJ(Integer codPFPJ) {
		this.codPFPJ = codPFPJ;
	}

	/**
	 * Recupera o valor de baseIRRF.
	 *
	 * @return the baseIRRF
	 */
	public BigDecimal getBaseIRRF() {
		return baseIRRF;
	}

	/**
	 * Define o valor de baseIRRF.
	 *
	 * @param baseIRRF the baseIRRF to set
	 */
	public void setBaseIRRF(BigDecimal baseIRRF) {
		this.baseIRRF = baseIRRF;
	}

	/**
	 * Recupera o valor de valorIRRF.
	 *
	 * @return the valorIRRF
	 */
	public BigDecimal getValorIRRF() {
		return valorIRRF;
	}

	/**
	 * Define o valor de valorIRRF.
	 *
	 * @param valorIRRF the valorIRRF to set
	 */
	public void setValorIRRF(BigDecimal valorIRRF) {
		this.valorIRRF = valorIRRF;
	}

	/**
	 * Recupera o valor de dataInicio.
	 *
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * Define o valor de dataInicio.
	 *
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * Recupera o valor de dataFim.
	 *
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * Define o valor de dataFim.
	 *
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * Recupera o valor de idProduto.
	 *
	 * @return the idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}

	/**
	 * Define o valor de idProduto.
	 *
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	/**
	 * Recupera o valor de nomeProduto.
	 *
	 * @return the nomeProduto
	 */
	public String getNomeProduto() {
		return nomeProduto;
	}

	/**
	 * Define o valor de nomeProduto.
	 *
	 * @param nomeProduto the nomeProduto to set
	 */
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
}