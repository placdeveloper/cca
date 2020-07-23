/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;

/**
 * A Interface HistContaCapitalLegadoDao.
 */
public interface HistContaCapitalLegadoDao extends	ContaCapitalIntegracaoLegadoCrudDaoIF<HistContaCapitalLegado> {
	
	/**
	 * Quantidade de vezes que a conta capital foi inativada
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	Integer qtdInativacaoCCA(Integer numMatricula) throws BancoobException;
}