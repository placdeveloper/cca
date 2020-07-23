/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoob;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParticipacaoIndiretaBancoobDao;

/**
 * A Classe ParticipacaoIndiretaBancoobDaoImpl.
 */
public class ParticipacaoIndiretaBancoobDaoImpl extends ContaCapitalMovimentacaoCrudDao<ParticipacaoIndiretaBancoob> implements ParticipacaoIndiretaBancoobDao {

	/**
	 * Instancia um novo ParticipacaoIndiretaBancoobDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ParticipacaoIndiretaBancoobDaoImpl(Class<ParticipacaoIndiretaBancoob> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
}