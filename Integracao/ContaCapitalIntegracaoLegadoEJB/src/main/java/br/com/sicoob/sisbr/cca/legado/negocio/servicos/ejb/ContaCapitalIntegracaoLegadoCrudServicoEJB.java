package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalIntegracaoLegadoEntidade;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;

/**
 * Implementacao padrao do contrato de servicos CRUD de todo o sistema  ContaCapitalIntegracaoLegado
 * 
 */
public abstract class ContaCapitalIntegracaoLegadoCrudServicoEJB<T extends ContaCapitalIntegracaoLegadoEntidade>
		extends BancoobCrudServicoEJB<T> {

	/**
	 * @return
	 */
	protected abstract ContaCapitalIntegracaoLegadoCrudDaoIF<T> getDAO();
	
	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public T incluir(T objeto) throws BancoobException {
		try {
			return super.incluir(objeto); 
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} 
	}
}