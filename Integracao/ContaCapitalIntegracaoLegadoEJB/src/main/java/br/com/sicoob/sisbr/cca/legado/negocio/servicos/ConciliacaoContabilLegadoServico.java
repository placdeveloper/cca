/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConciliacaoContabilLegadoDTO;

/**
 * A Interface ConciliacaoContabilLegadoServico.
 */
public interface ConciliacaoContabilLegadoServico extends ContaCapitalIntegracaoLegadoServico {
	
	/**
	 * Obter lista dados conciliacao contabil.
	 *
	 * @param numCooperativa o valor de num cooperativa
	 * @param dataLote o valor de data lote
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<ConciliacaoContabilLegadoDTO> obterListaDadosConciliacaoContabil(Integer numCooperativa, DateTimeDB dataLote) throws BancoobException;

	/**
	 * Atualiza tabela de controle (interno do conta capital) de conciliacao contabil
	 * @param numCooperativa
	 * @param dto
	 * @throws BancoobException
	 */
	void atualizarConciliacaoContabil(Integer numCooperativa, ConciliacaoContabilLegadoDTO dto) throws BancoobException;
}