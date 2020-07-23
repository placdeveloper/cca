/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.cca.comum.negocio.servicos.FechamentoContaCapitalServico;
import br.com.sicoob.cca.comum.negocio.servicos.PesquisaContaCapitalServico;
import br.com.sicoob.cca.comum.negocio.servicos.ViewInstituicaoServico;

/**
 * Service Locator usado pelo sistema ContaCapitalComum.
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalComumServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static ContaCapitalComumServiceLocator locator = new ContaCapitalComumServiceLocator();

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static ContaCapitalComumServiceLocator getInstance() {
		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	protected ContaCapitalComumServiceLocator() {
		super("cca_comum");
	}
	
	/**
	 * @return FechamentoContaCapitalServico
	 */
	public FechamentoContaCapitalServico localizarFechamentoContaCapitalServico() {
		return (FechamentoContaCapitalServico) localizar("locator.servico.FechamentoContaCapitalServico");
	}
	
	/**
	 * @return PesquisaContaCapitalServico
	 */
	public PesquisaContaCapitalServico localizarPesquisaContaCapitalServico() {
		return (PesquisaContaCapitalServico) localizar("locator.servico.PesquisaContaCapitalServico");
	}
	
	/**
	 * @return ViewInstituicaoServico
	 */
	public ViewInstituicaoServico localizarViewInstituicaoServico() {
		return (ViewInstituicaoServico) localizar("locator.servico.ViewInstituicaoServico");
	}
	
}