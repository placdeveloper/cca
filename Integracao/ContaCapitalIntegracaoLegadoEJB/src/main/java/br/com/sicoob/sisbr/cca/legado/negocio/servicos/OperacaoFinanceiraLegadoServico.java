/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.OperacaoFinanceiraLegadoDTO;

/**
 * A Interface OperacaoFinanceiraLegadoServico.
 */
public interface OperacaoFinanceiraLegadoServico extends ContaCapitalIntegracaoLegadoServico {
	
	/**
	 * Inclui operacao financeira (LEGADO)
	 * @param OperacaoFinanceiraLegadoDTO
	 * @throws BancoobException
	 */
	void incluir(OperacaoFinanceiraLegadoDTO of) throws BancoobException;
	
	/**
	 * Ultimo ID
	 * @param numCoop
	 * @see OperacaoFinanceiraLegadoDTO
	 * @return
	 * @throws BancoobException
	 */
	Integer getUltimoId(Integer numCoop) throws BancoobException;
	
	/**
	 * Valor estorno da particição indireta
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	BigDecimal consultarValorEstorno(Integer numCoop) throws BancoobException;
	
	/**
	 * Verifica se a operacao financeira esta cadastrada para a cooperativa em questao
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	Boolean existeOperacaoFinanceiraPI(Integer numCoop) throws BancoobException;
	
	/**
	 * Valor de investimento das centrais no Bancoob por ano e mes
	 * @param numCoop
	 * @param anoMes
	 * @return
	 */
	BigDecimal valorParticipacaoCentralPorAnoMes(Integer numCoop, Integer anoMes) throws BancoobException;
}