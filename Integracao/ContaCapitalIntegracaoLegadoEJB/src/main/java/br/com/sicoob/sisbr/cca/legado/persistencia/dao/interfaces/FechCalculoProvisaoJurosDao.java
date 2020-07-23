package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import br.com.bancoob.excecao.BancoobException;

/**
* @author Antonio.Genaro
*/
public interface FechCalculoProvisaoJurosDao {

	void gerarCalculoProvisaoJuros(Integer numCoop) throws BancoobException;

}