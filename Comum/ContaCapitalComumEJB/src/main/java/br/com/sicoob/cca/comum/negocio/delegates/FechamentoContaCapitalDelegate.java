/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.FechamentoContaCapitalServico;
import br.com.sicoob.cca.comum.negocio.servicos.locator.ContaCapitalComumServiceLocator;

/**
 * @author Marco.Nascimento
 */
public class FechamentoContaCapitalDelegate extends ContaCapitalComumDelegate<FechamentoContaCapitalServico> {
	
	/**
	 * Utilizar a fabrica para criar o delegate
	 * @see ContaCapitalComumFabricaDelegates#criarFechamentoContaCapitalDelegate()
	 */
	FechamentoContaCapitalDelegate() {
		
	}

	/**
	 * {@link FechamentoContaCapitalServico}
	 */
	@Override
	protected FechamentoContaCapitalServico localizarServico() {
		return ContaCapitalComumServiceLocator.getInstance().localizarFechamentoContaCapitalServico();
	}
	
	/**
	 * {@link FechamentoContaCapitalServico#isFechamentoIniciado()}
	 */
	public boolean isFechamentoIniciado(Integer numCoop) throws BancoobException {
		return getServico().isFechamentoIniciado(numCoop);
	}
}