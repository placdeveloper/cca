/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSaldoAtualDTO;

/**
 * The Interface RelSaldoAtualDao.
 */
public interface RelSaldoAtualDao {
	
	/**
	 * Pesquisar parcelamentos.
	 *
	 * @param filtro the filtro
	 * @return the list
	 * @throws BancoobException the bancoob exception
	 */
	List<RelSaldoAtualDTO> pesquisarSaldoAtual(RelSaldoAtualDTO filtro) throws BancoobException;
}