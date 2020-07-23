/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ValorCotaLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ValorCotasLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe ValorCotasLegadoDelegate.
 */
public class ValorCotasLegadoDelegate extends ContaCapitalIntegracaoLegadoCrudDelegate<ValorCotaLegado, ValorCotasLegadoServico> {

	/**
	 * Recupera a unica instancia de ValorCotasLegadoDelegate.
	 *
	 * @return uma instancia de ValorCotasLegadoDelegate
	 */
	public static ValorCotasLegadoDelegate getInstance(){
		return new ValorCotasLegadoDelegate();
	}	
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected ValorCotasLegadoServico localizarServico() {
		return (ValorCotasLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarValorCotasLegadoServico();
	}
	
	/**
	 * Obter numero minimo cota.
	 *
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer obterNumeroMinimoCota() throws BancoobException{
		return getServico().obterNumeroMinimoCota();
	}
	
	/**
	 * Obter valor cota.
	 *
	 * @return BigDecimal
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public BigDecimal obterValorCota() throws BancoobException{
		return getServico().obterValorCota();
	}
	
	/**
	 * {@link ValorCotasLegadoServico#obterValorCotaVigente()}
	 */
	public ValorCotaLegado obterValorCotaVigente(Integer numCoop) throws BancoobException{
		return getServico().obterValorCotaVigente(numCoop);
	}
	
	/**
	 * {@link ValorCotasLegadoServico#obterValorSalarioBase()}
	 */
	public BigDecimal obterValorSalarioBase(Integer numCoop) throws BancoobException {
		return getServico().obterValorSalarioBase(numCoop);
	}
}