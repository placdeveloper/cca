package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosCrudDaoIF;

/**
 * Implementacao padrao do contrato de servicos CRUD de todo o sistema ContaCapitalRelatorios
 *
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalRelatoriosCrudServicoEJB<T extends BancoobEntidade> extends
		BancoobCrudServicoEJB<T> {

	/**
	 * @return
	 */
	protected abstract ContaCapitalRelatoriosCrudDaoIF<T> getDAO();
}
