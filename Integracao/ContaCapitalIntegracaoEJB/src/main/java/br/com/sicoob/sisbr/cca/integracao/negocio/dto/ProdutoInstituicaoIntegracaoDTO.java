/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe ProdutoInstituicaoIntegracaoDTO.
 */
public class ProdutoInstituicaoIntegracaoDTO extends BancoobDto {

	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 8028063212864667573L;
	
	/** O atributo dataUltimoMovimento. */
	private DateTimeDB dataUltimoMovimento;
	
	/** O atributo dataAtualMovimento. */
	private DateTimeDB dataAtualMovimento;
	
	/** O atributo dataProximoMovimento. */
	private DateTimeDB dataProximoMovimento;
	
	/** O atributo dataAntigoProduto. */
	private DateTime dataAntigoProduto;
	
	/** O atributo dataProximoProduto. */
	private DateTime dataProximoProduto;
	
	/** O atributo dataAtualProduto. */
	private DateTime dataAtualProduto;
	
	/**
	 * Recupera o valor de dataUltimoMovimento.
	 *
	 * @return o valor de dataUltimoMovimento
	 */
	public DateTimeDB getDataUltimoMovimento() {
		DateTimeDB clone = null;
        if(this.dataUltimoMovimento != null){
              clone = new DateTimeDB(this.dataUltimoMovimento.getTime());
        }
        return clone;
	}
	
	/**
	 * Define o valor de dataUltimoMovimento.
	 *
	 * @param dataUltimoMovimento o novo valor de dataUltimoMovimento
	 */
	public void setDataUltimoMovimento(DateTimeDB dataUltimoMovimento) {
		if(dataUltimoMovimento != null) {
	        this.dataUltimoMovimento = new DateTimeDB(dataUltimoMovimento.getTime());
		} else {
	        this.dataUltimoMovimento = null;
		}
	}
	
	/**
	 * Recupera o valor de dataAtualMovimento.
	 *
	 * @return o valor de dataAtualMovimento
	 */
	public Date getDataAtualMovimento() {
		DateTimeDB clone = null;
        if(this.dataAtualMovimento != null) {
              clone = new DateTimeDB(this.dataAtualMovimento.getTime());
        }
		return clone;
	}
	
	/**
	 * Define o valor de dataAtualMovimento.
	 *
	 * @param dataAtualMovimento o novo valor de dataAtualMovimento
	 */
	public void setDataAtualMovimento(DateTimeDB dataAtualMovimento) {
		if(dataAtualMovimento != null) {
	        this.dataAtualMovimento = new DateTimeDB(dataAtualMovimento.getTime());
		} else {
	        this.dataAtualMovimento = null;
		}
	}
	
	/**
	 * Recupera o valor de dataProximoMovimento.
	 *
	 * @return o valor de dataProximoMovimento
	 */
	public Date getDataProximoMovimento() {
		DateTimeDB clone = null;
        if(this.dataProximoMovimento != null){
              clone = new DateTimeDB(this.dataProximoMovimento.getTime());
        } 
        return clone;   
	}
	
	/**
	 * Define o valor de dataProximoMovimento.
	 *
	 * @param dataProximoMovimento o novo valor de dataProximoMovimento
	 */
	public void setDataProximoMovimento(DateTimeDB dataProximoMovimento) {
		if(dataProximoMovimento != null) {
	        this.dataProximoMovimento = new DateTimeDB(dataProximoMovimento.getTime());
		} else {
	        this.dataProximoMovimento = null;
		}
	}
	
	/**
	 * Recupera o valor de dataAntigoProduto.
	 *
	 * @return o valor de dataAntigoProduto
	 */
	public DateTimeDB getDataAntigoProduto() {
		DateTimeDB clone = null;
        if(this.dataAntigoProduto != null){
              clone = new DateTimeDB(this.dataAntigoProduto.getTime());
        } 
        return clone;   
	}
	
	/**
	 * Define o valor de dataAntigoProduto.
	 *
	 * @param dataAntigoProduto o novo valor de dataAntigoProduto
	 */
	public void setDataAntigoProduto(DateTimeDB dataAntigoProduto) {
		if(dataAntigoProduto != null) {
	        this.dataAntigoProduto = new DateTime(dataAntigoProduto.getTime());
		} else {
	        this.dataAntigoProduto = null;
		}
	}
	
	/**
	 * Recupera o valor de dataProximoProduto.
	 *
	 * @return o valor de dataProximoProduto
	 */
	public DateTimeDB getDataProximoProduto() {
		DateTimeDB clone = null;
        if(this.dataProximoProduto != null){
              clone = new DateTimeDB(this.dataProximoProduto.getTime());
        } 
        return clone;   
	}
	
	/**
	 * Define o valor de dataProximoProduto.
	 *
	 * @param dataProximoProduto o novo valor de dataProximoProduto
	 */
	public void setDataProximoProduto(DateTimeDB dataProximoProduto) {
		if(dataProximoProduto != null) {
	        this.dataProximoProduto = new DateTime(dataProximoProduto.getTime());
		} else {
	        this.dataProximoProduto = null;
		}
	}
	
	/**
	 * Recupera o valor de dataAtualProduto.
	 *
	 * @return o valor de dataAtualProduto
	 */
	public DateTimeDB getDataAtualProduto() {
		DateTimeDB clone = null;
        if(this.dataAtualProduto != null){
              clone = new DateTimeDB(this.dataAtualProduto.getTime());
        } 
        return clone;   
	}
	
	/**
	 * Define o valor de dataAtualProduto.
	 *
	 * @param dataAtualProduto o novo valor de dataAtualProduto
	 */
	public void setDataAtualProduto(DateTimeDB dataAtualProduto) {
		if(dataAtualProduto != null) {
	        this.dataAtualProduto = new DateTimeDB(dataAtualProduto.getTime());
		} else {
	        this.dataAtualProduto = null;
		}
	}
}