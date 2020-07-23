/*
 * 
 */
package br.com.sicoob.sisbr.cca.movimentacao.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

// TODO: Auto-generated Javadoc
/**
 * A Classe ItemListaVO.
 *
 * @author Antonio.Genaro
 */
public class ItemListaVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4907821244313542354L;
	
	/** O atributo codListaItem. */
	private String codListaItem;
	
	/** O atributo descListaItem. */
	private String descListaItem;
	
	/**
	 * Instancia um novo ItemListaVO.
	 *
	 * @param codListaItem o valor de cod lista item
	 * @param descListaItem o valor de desc lista item
	 */
	public ItemListaVO(String codListaItem, String descListaItem) {
		super();
		this.codListaItem = codListaItem;
		this.descListaItem = descListaItem;
	}
	
	/**
	 * Recupera o valor de codListaItem.
	 *
	 * @return o valor de codListaItem
	 */
	public String getCodListaItem() {
		return codListaItem;
	}
	
	/**
	 * Define o valor de codListaItem.
	 *
	 * @param codListaItem o novo valor de codListaItem
	 */
	public void setCodListaItem(String codListaItem) {
		this.codListaItem = codListaItem;
	}
	
	/**
	 * Recupera o valor de descListaItem.
	 *
	 * @return o valor de descListaItem
	 */
	public String getDescListaItem() {
		return descListaItem;
	}
	
	/**
	 * Define o valor de descListaItem.
	 *
	 * @param descListaItem o novo valor de descListaItem
	 */
	public void setDescListaItem(String descListaItem) {
		this.descListaItem = descListaItem;
	}
}
