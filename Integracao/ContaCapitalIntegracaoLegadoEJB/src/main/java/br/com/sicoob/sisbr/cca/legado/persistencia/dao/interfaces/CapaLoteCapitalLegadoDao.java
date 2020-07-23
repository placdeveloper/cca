/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;

/**
 * A Interface CapaLoteCapitalLegadoDao.
 */
public interface CapaLoteCapitalLegadoDao extends ContaCapitalIntegracaoLegadoCrudDaoIF<CapaLoteCapitalLegado> {

	/**
	 * Atualiza a capa lote de acordo com o total de lançamentos do dia
	 * @param numCooperativa
	 * @param dataLote
	 * @param numLote
	 * @throws BancoobException
	 */
	void atualizarCapaLote(Integer numCooperativa, DateTimeDB dataLote, Integer numLote) throws BancoobException;
	
}
