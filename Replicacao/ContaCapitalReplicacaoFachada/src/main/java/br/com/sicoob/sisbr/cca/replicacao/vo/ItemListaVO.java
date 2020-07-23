/*
 * 
 */
package br.com.sicoob.sisbr.cca.replicacao.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe ItemListaVO.
 */
public class ItemListaVO extends BancoobVo implements Comparable<ItemListaVO> {
	/**
	 * 
	 */
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
	
	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ItemListaVO o) {
		return this.getDescListaItem().compareTo(o.getDescListaItem());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descListaItem == null) ? 0 : descListaItem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ItemListaVO other = (ItemListaVO) obj;
		if (descListaItem == null) {
			if (other.descListaItem != null) {
				return false;
			}
		} else if (!descListaItem.equals(other.descListaItem)) {
			return false;
		}
		return true;
	}
	
}
