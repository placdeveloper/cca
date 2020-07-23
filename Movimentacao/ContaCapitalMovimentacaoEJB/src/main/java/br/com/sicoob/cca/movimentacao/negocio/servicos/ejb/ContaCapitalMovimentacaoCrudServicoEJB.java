package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;

/**
 * EJB contendo servicos relacionados a ContaCapitalMovimentacaoCrud.
 *
 * @author Balbi
 * @param <T> o tipo generico
 */
public abstract class ContaCapitalMovimentacaoCrudServicoEJB<T extends BancoobEntidade> extends
		BancoobCrudServicoEJB<T> {

	/**
	 * {@inheritDoc}
	 */
	protected abstract ContaCapitalMovimentacaoCrudDaoIF<T> getDAO();
}
