/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.PLDContaCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe PLDContaCapitalLegadoDelegate.
 */
public class PLDContaCapitalLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<PLDContaCapitalLegadoServico> {

	/**
	 * Recupera a unica instancia de PLDContaCapitalLegadoDelegate.
	 *
	 * @return uma instancia de PLDContaCapitalLegadoDelegate
	 */
	public static PLDContaCapitalLegadoDelegate getInstance() {
		return new PLDContaCapitalLegadoDelegate();
	}
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected PLDContaCapitalLegadoServico localizarServico() {
		return (PLDContaCapitalLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarPLDContaCapitalLegadoServico();
	}
	
	/**
	 * {@link PLDContaCapitalLegadoServico#gerarPLD(Integer)}
	 */
	public Boolean gerarPLD(Integer numCoop) throws BancoobException {
		return getServico().gerarPLD(numCoop);
	}
}