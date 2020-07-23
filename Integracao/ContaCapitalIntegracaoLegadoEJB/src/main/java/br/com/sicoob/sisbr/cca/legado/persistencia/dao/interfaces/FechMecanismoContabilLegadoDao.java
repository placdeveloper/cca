package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author ricardo.barcante
 */
public interface FechMecanismoContabilLegadoDao {
	
	/**
	 * Realiza o fechamento de mecanismo contabil
	 * @param idProduto
	 * @param numCoop
	 * @throws BancoobException
	 */
	void fechamentoMecanismoContabil(Integer idProduto, Integer numCoop) throws BancoobException;
}
