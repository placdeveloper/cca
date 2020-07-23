/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import br.com.sicoob.cca.entidades.negocio.entidades.HistParticipacaoCentralBancoob;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistParticipacaoCentralBancoobDao;

/**
 * A Classe HistParticipacaoCentralBancoobDaoImpl.
 */
public class HistParticipacaoCentralBancoobDaoImpl extends ContaCapitalMovimentacaoCrudDao<HistParticipacaoCentralBancoob> implements HistParticipacaoCentralBancoobDao {

	/**
	 * Instancia um novo HistParticipacaoCentralBancoobDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public HistParticipacaoCentralBancoobDaoImpl(Class<HistParticipacaoCentralBancoob> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
}