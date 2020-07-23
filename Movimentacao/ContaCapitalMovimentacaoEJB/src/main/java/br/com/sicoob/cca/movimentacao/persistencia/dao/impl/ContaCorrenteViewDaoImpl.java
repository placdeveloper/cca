/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import br.com.sicoob.cca.entidades.negocio.entidades.ContaCorrenteView;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ContaCorrenteViewDao;

/**
 * A Classe ContaCorrenteViewDaoImpl
 * @author Nairon.Silva
 */
public class ContaCorrenteViewDaoImpl extends ContaCapitalMovimentacaoCrudDao<ContaCorrenteView> implements ContaCorrenteViewDao {
	
	/**
	 * Instancia um novo ContaCorrenteViewDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ContaCorrenteViewDaoImpl(Class<ContaCorrenteView> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
	
}
