package br.com.sicoob.cca.replicacao.negocio.servicos.ejb;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.sicoob.cca.replicacao.persistencia.dao.ContaCapitalReplicacaoCrudDaoIF;

/**
 *
 * @author Balbi
 */
public abstract class ContaCapitalReplicacaoCrudServicoEJB<T extends BancoobEntidade> extends
		BancoobCrudServicoEJB<T> {

	/**
	 * @return
	 */
	protected abstract ContaCapitalReplicacaoCrudDaoIF<T> getDAO();
}
