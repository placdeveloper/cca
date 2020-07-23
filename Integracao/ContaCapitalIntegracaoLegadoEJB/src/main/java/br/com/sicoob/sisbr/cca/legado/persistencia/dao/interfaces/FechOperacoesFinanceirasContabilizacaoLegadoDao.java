package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import br.com.bancoob.excecao.BancoobException;

/**
* @author Ricardo.Barcante
*/
public interface FechOperacoesFinanceirasContabilizacaoLegadoDao {
	/**
	 * Realiza o fechamento de Operacoes Financeiras Contabilizacao
	 * @param numCoop
	 * @param idUsuario 
	 * @throws BancoobException
	 */
	void fechamentoOperacoesFinanceirasContabilizacao(Integer numCoop, String idUsuario) throws BancoobException;
}
