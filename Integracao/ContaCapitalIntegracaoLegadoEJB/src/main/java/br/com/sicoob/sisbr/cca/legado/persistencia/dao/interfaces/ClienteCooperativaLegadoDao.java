/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author Marco.Nascimento
 */
public interface ClienteCooperativaLegadoDao {
	
	/**
	 * Cliente cooperativa por cooperativa
	 * @return
	 * @throws BancoobException
	 */
	Integer consultarClienteCooperativa(Integer numCoop, Integer numPac) throws BancoobException;

}