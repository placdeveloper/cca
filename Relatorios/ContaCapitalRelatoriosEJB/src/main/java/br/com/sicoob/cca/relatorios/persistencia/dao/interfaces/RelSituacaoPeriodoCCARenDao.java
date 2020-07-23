package br.com.sicoob.cca.relatorios.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoPeriodoCCARenDTO;

/**
 * The Interface RelSituacaoPeriodoCCARenDao.
 */
public interface RelSituacaoPeriodoCCARenDao {
	

	/**
	 * Pesquisar situacao CCA por Periodo.
	 *
	 * @param filtro the filtro
	 * @return the list
	 * @throws BancoobException the bancoob exception
	 */
	List<RelSituacaoPeriodoCCARenDTO> pesquisarSituacaoCCAPorPeriodo(RelSituacaoPeriodoCCARenDTO filtro) throws BancoobException;
}