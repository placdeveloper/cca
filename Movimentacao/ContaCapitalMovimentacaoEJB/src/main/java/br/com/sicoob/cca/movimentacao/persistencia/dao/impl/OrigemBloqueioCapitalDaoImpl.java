package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import br.com.sicoob.cca.entidades.negocio.entidades.OrigemBloqueioCapital;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.OrigemBloqueioCapitalDao;

/**
 * Dao para OrigemBloqueioCapital
 * @author Nairon.Silva
 */
public class OrigemBloqueioCapitalDaoImpl extends ContaCapitalMovimentacaoCrudDao<OrigemBloqueioCapital> implements OrigemBloqueioCapitalDao {

	/**
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public OrigemBloqueioCapitalDaoImpl(Class<OrigemBloqueioCapital> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}

}
