package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RecolhimentoIrrfDestinacaoJurosLegadoDTO;

public interface RecolhimentoIrrfDestinacaoJurosLegadoDao {
	
	/**
	 * Obtem dados para o relatorio a partir do filtro (SQL Server)
	 * @param filtro
	 * @return
	 */
	List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> pesquisarRecolhimentoIrrfDestinacaoJuros(RecolhimentoIrrfDestinacaoJurosLegadoDTO filtro) throws BancoobException;
}
