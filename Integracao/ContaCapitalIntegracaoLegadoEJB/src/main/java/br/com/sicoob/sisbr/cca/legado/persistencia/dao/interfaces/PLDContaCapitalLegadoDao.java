package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.Date;

/**
 * A Interface PLDContaCapitalLegadoDao.
 */
public interface PLDContaCapitalLegadoDao {

	/**
	 * Gera PLD para cooperativa
	 * @param numCoop
	 * @param dataLancamento
	 * @return true se gerado com sucesso
	 */
	Boolean gerarPLD(Integer numCoop, Date dataLancamento);
	
}