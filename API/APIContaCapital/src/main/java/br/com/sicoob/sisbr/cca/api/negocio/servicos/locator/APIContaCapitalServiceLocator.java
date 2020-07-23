/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.CadastroContaCapitalServico;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.ContaCapitalServico;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.DebIndeterminadoContaCapitalServico;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.IntegralizacaoCapitalServico;

/**
 * Service Locator usado pelo sistema APIContaCapital.
 * 
 */
public final class APIContaCapitalServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static APIContaCapitalServiceLocator locator = new APIContaCapitalServiceLocator();

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static APIContaCapitalServiceLocator getInstance() {
		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	protected APIContaCapitalServiceLocator() {
		super("cca_api");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code ContaCapitalServico}.
	 *
	 * @return O EJB solicitado
	 * @see ContaCapitalServico
	 */
	public ContaCapitalServico localizarContaCapitalServico(){
		return (ContaCapitalServico) localizar("locator.apicontacapital.ContaCapitalServico");
	}	
	
	
	/**
	 * Localiza o Servico EJB que implementa a interface IntegralizacaoServico
	 * @return
	 */
	public IntegralizacaoCapitalServico localizarIntegralizacaoCapitalServico(){
		return (IntegralizacaoCapitalServico) localizar("locator.apicontacapital.IntegralizacaoCapitalServico");
	}

	/**
	 * Localiza o Servico EJB que implementa a interface CadastroContaCapitalServico
	 * @return
	 */
	public CadastroContaCapitalServico localizarCadastroContaCapitalServico(){
		return (CadastroContaCapitalServico) localizar("locator.apicontacapital.CadastroContaCapitalServico");
	}
	
	/**
	 * Localiza o Servico EJB que implementa a interface DebIndeterminadoContaCapitalServico
	 * @return
	 */
	public DebIndeterminadoContaCapitalServico localizarDebIndeterminadoContaCapitalServico() {
		return (DebIndeterminadoContaCapitalServico) localizar("locator.apicontacapital.DebIndeterminadoContaCapitalServico");
	}
}