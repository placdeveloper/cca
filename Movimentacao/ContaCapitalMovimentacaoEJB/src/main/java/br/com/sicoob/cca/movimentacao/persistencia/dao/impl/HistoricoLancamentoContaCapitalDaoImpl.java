/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoLancamentoCCA;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistoricoLancamentoContaCapitalDao;

// TODO: Auto-generated Javadoc
/**
 * A Classe HistoricoLancamentoContaCapitalDaoImpl.
 *
 * @author Antonio.Genaro
 */
public class HistoricoLancamentoContaCapitalDaoImpl extends ContaCapitalMovimentacaoCrudDao<HistoricoLancamentoCCA> implements HistoricoLancamentoContaCapitalDao {

	/**
	 * Instancia um novo HistoricoLancamentoContaCapitalDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public HistoricoLancamentoContaCapitalDaoImpl(Class<HistoricoLancamentoCCA> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
}
