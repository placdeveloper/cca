/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;

// TODO: Auto-generated Javadoc
/**
 * A Interface LancamentoContaCapitalServico.
 *
 * @author Antonio.Genaro
 */
public interface LancamentoContaCapitalServico extends ContaCapitalMovimentacaoCrudServico<LancamentoContaCapital>, Fechamento {

	/**
	 * Realiza pesquisa de lancamentos do dia por conta capital .
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<LancamentoContaCapital> pesquisarLancamentosDoDiaPorContaCapital(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Realiza pesquisa de lancamentos do dia por e tipo historico de conta capital .
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @param idTipoHistorico o valor de id tipo historico
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<LancamentoContaCapital> pesquisarLancamentosDoDiaTipoHistContaCapital(Integer idContaCapital, Integer idInstituicao, Integer idTipoHistorico) throws BancoobException;
	
	/**
	 * Calcula o valor subscrito da conta capital considerando os lançamentos do dia
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	BigDecimal calcularValorSubscrito(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Calcula o valor integralizado da conta capital considerando os lançamentos do dia
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	BigDecimal calcularValorIntegralizado(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Calcula o valor de devolucao da conta capital considerando os lançamentos do dia
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	BigDecimal calcularValorDevolucao(Integer idContaCapital) throws BancoobException;

	/**
	 * Consulta quantidade de lancamento de subscrição por conta capital 
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	Integer pesquisarCountLancamentosPorContaCapitalSubscricao(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Inclui Lancamentos em lotes 
	 * @param lista LancamentoContaCapital
	 * @return
	 * @throws BancoobException
	 */
	void incluirEmLote(List<LancamentoContaCapital> lancamentos) throws BancoobException;
	
	/**
	 * Atualiza movimento de lançamentos
	 * @param numCoop
	 * @throws BancoobException
	 */
	void rodar(Integer numCoop) throws BancoobException;
		
}