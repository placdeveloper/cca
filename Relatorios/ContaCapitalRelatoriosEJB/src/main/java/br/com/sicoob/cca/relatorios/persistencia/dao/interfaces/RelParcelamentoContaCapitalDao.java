/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParcelamentoContaCapitalDTO;

/**
 * Servico RelParcelamentoContaCapitalDao
 */
public interface RelParcelamentoContaCapitalDao {

	/**
	 * Pequisa parcelamentos do relatorio de conta capital
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	List<RelParcelamentoContaCapitalDTO> pesquisarParcelamentos(RelParcelamentoContaCapitalDTO filtro) throws BancoobException;
}