/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.HistContaCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe HistContaCapitalLegadoDelegate.
 */
public class HistContaCapitalLegadoDelegate extends ContaCapitalIntegracaoLegadoCrudDelegate<HistContaCapitalLegado, HistContaCapitalLegadoServico> {
	
	/**
	 * Recupera a unica instancia de HistContaCapitalLegadoDelegate.
	 *
	 * @return uma instancia de HistContaCapitalLegadoDelegate
	 */
	public static HistContaCapitalLegadoDelegate getInstance(){
		return new HistContaCapitalLegadoDelegate();
	}
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected HistContaCapitalLegadoServico localizarServico() {
		return (HistContaCapitalLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarHistContaCapitalLegadoServico();
	}
	
	/**
	 * {@link HistContaCapitalLegadoServico#qtdInativacaoCCA(Integer)}
	 */
	public Integer qtdInativacaoCCA(Integer numMatricula) throws BancoobException {
		return getServico().qtdInativacaoCCA(numMatricula);
	}
}