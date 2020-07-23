/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.LancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;

/**
 * A Interface LancamentosCCapitalLegadoDao.
 */
public interface LancamentosCCapitalLegadoDao extends ContaCapitalIntegracaoLegadoCrudDaoIF<LancamentosCCapitalLegado> {
	
	/**
	 * Obter ultimo num seq lanc.
	 *
	 * @param capaLoteCapitalLegado o valor de capa lote capital legado
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer obterUltimoNumSeqLanc(CapaLoteCapitalLegado capaLoteCapitalLegado) throws BancoobException;

	/**
	 * Realiza a inclusao em lote dos lancamentos legados.
	 * @param lancamentosLegado
	 * @return
	 * @throws BancoobException
	 */
	List<LancamentosCCapitalLegado> incluirEmLote(List<LancamentosCCapitalLegado> lancamentosLegado) throws BancoobException;

	/**
	 * Verifica se existe um lancamento pelo numMatricula e descOperacaoExterna
	 * @param numMatricula
	 * @param descOperacaoExterna
	 * @return TRUE se existir, FALSE se nao existir
	 * @throws BancoobException
	 */
	Boolean verificarLancamentoExistente(Integer numCooperativa, Integer numMatricula, String descOperacaoExterna) throws BancoobException;

	/**
	 * Realiza a pesquisa dos lancamentos legados via cco.
	 * 
	 * @param DateTimeDB
	 * @return lista LancamentosCCapitalLegado
	 * @throws BancoobException
	 */
	List<LancamentosCCapitalLegadoDTO> listarLancViaCCO(DateTimeDB dataAtualProd) throws BancoobException;

	/**
	 * Realiza a pesquisa dos lancamentos legados deb indet.
	 * 
	 * @param DateTimeDB
	 * @return lista LancamentosCCapitalLegado
	 * @throws BancoobException
	 */
	List<LancamentosCCapitalLegadoDTO> listarLancViaDebIndet(DateTimeDB dataAtualProd) throws BancoobException;
	
	/**
	 * Realiza a pesquisa dos lancamentos legados destinação juros..
	 * 
	 * @param DateTimeDB
	 * @return lista LancamentosCCapitalLegado
	 * @throws BancoobException
	 */
	List<LancamentosCCapitalLegadoDTO> listarLancamentosDestinacaoJuros(DateTimeDB dataAtualProd) throws BancoobException;
	
	/**
	 * Atualiza movimentos de lançamentos.
	 * 
	 * @param numCoop
	 * @throws BancoobException
	 */
	void atualizaMovimentoLancamentos(Integer numCoop) throws BancoobException;	
}
