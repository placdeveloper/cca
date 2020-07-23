package br.com.sicoob.cca.comum.negocio.servicos.ejb;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumCrudDaoIF;

/**
 * Implementacao padrao do contrato de servicos CRUD de todo o sistema ContaCapitalComum
 *
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalComumCrudServicoEJB<T extends BancoobEntidade> extends BancoobCrudServicoEJB<T> {

	/**
	 * @return
	 */
	protected abstract ContaCapitalComumCrudDaoIF<T> getDAO();
}
