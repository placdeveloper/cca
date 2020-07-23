/*
 * 
 */
package br.com.sicoob.sisbr.cca.relatorios.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * VO ItemListaVO
 */
public class ItemListaVO extends BancoobVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4907821244313542354L;
	private String codListaItem;
	private String descListaItem;
	
	/**
	 * Construtor para o ItemListaVO
	 * @param codListaItem
	 * @param descListaItem
	 */
	public ItemListaVO(String codListaItem, String descListaItem) {
		super();
		this.codListaItem = codListaItem;
		this.descListaItem = descListaItem;
	}
	
	/**
	 * Setters and Getters
	 * @return
	 */
	public String getCodListaItem() {
		return codListaItem;
	}
	public void setCodListaItem(String codListaItem) {
		this.codListaItem = codListaItem;
	}
	public String getDescListaItem() {
		return descListaItem;
	}
	public void setDescListaItem(String descListaItem) {
		this.descListaItem = descListaItem;
	}
}
