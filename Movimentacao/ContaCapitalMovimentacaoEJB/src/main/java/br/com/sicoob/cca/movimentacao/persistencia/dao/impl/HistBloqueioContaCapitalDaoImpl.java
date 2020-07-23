/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapital;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistBloqueioContaCapitalDao;

// TODO: Auto-generated Javadoc
/**
 * A Classe HistBloqueioContaCapitalDaoImpl.
 *
 * @author Antonio.Genaro
 */
public class HistBloqueioContaCapitalDaoImpl extends ContaCapitalMovimentacaoCrudDao<HistBloqueioCapital> implements HistBloqueioContaCapitalDao {

	/**
	 * Instancia um novo HistBloqueioContaCapitalDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public HistBloqueioContaCapitalDaoImpl(Class<HistBloqueioCapital> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
}
