/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.CapaLoteCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe CapaLoteCapitalLegadoDelegate.
 */
public class CapaLoteCapitalLegadoDelegate extends ContaCapitalIntegracaoLegadoCrudDelegate<CapaLoteCapitalLegado, CapaLoteCapitalLegadoServico> {

	/**
	 * Recupera a unica instancia de CapaLoteCapitalLegadoDelegate.
	 *
	 * @return uma instancia de CapaLoteCapitalLegadoDelegate
	 */
	public static CapaLoteCapitalLegadoDelegate getInstance(){
		return new CapaLoteCapitalLegadoDelegate();
	}
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected CapaLoteCapitalLegadoServico localizarServico() {
		return (CapaLoteCapitalLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarCapaLoteCapitalLegadoServico();
	}
	
	/**
	 * {@link br.com.sicoob.sisbr.cca.legado.negocio.servicos.CapaLoteCapitalLegadoServico#atualizarCapaLote(Integer, DateTimeDB, Integer)}
	 */
	public void atualizarCapaLote(Integer numCooperativa, DateTimeDB dataLote, Integer numLote) throws BancoobException {
		getServico().atualizarCapaLote(numCooperativa, dataLote, numLote);
	}

}
