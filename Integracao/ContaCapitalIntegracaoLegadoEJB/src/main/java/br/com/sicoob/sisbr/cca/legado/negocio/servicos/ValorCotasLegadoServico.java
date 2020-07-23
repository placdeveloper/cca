/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ValorCotaLegado;


/**
 * A Interface ValorCotasLegadoServico.
 */
public interface ValorCotasLegadoServico extends ContaCapitalIntegracaoLegadoCrudServico<ValorCotaLegado> {

	/**
	 * Obter numero minimo cota.
	 *
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer obterNumeroMinimoCota() throws BancoobException;
	
	/**
	 * Obter valor cota.
	 *
	 * @return BigDecimal
	 * @throws BancoobException lança a exceção BancoobException
	 */
	BigDecimal obterValorCota() throws BancoobException;
	
	
	/**
	 * Obtem valor cota vigente
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	ValorCotaLegado obterValorCotaVigente(Integer numCoop) throws BancoobException;
	
	/**
	 * Obtem ultimo valor salario base cadastado
	 * @param numCoop
	 * @return 
	 * @throws BancoobException
	 */
	BigDecimal obterValorSalarioBase(Integer numCoop) throws BancoobException;
}