/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParticipacaoCentralBancoobDao;

/**
 * A Classe ParticipacaoCentralBancoobDaoImpl.
 */
public class ParticipacaoCentralBancoobDaoImpl extends ContaCapitalMovimentacaoCrudDao<ParticipacaoCentralBancoob> implements ParticipacaoCentralBancoobDao {

	/**
	 * Instancia um novo ParticipacaoCentralBancoobDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ParticipacaoCentralBancoobDaoImpl(Class<ParticipacaoCentralBancoob> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
}