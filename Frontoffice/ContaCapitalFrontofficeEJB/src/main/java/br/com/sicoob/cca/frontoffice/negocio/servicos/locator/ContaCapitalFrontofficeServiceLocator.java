package br.com.sicoob.cca.frontoffice.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.cca.frontoffice.negocio.servicos.IntegralizacaoCapitalServico;

/**
 * Service Locator usado pelo sistema.
 */
public final class ContaCapitalFrontofficeServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static ContaCapitalFrontofficeServiceLocator locator = new ContaCapitalFrontofficeServiceLocator();
	
	/**
	 * Singleton da class.
	 *
	 * @return A instancia da classe
	 */
	public static ContaCapitalFrontofficeServiceLocator getInstance() {
		return locator;
	}

	/**
	 * Instancia um novo ContaCapitalFrontofficeServiceLocator.
	 */
	private ContaCapitalFrontofficeServiceLocator() {
		super("cca_frontoffice");
	}
	
	/**
	 * Localiza o serviço para integralização de capital.
	 * @return O serviço para integralização de capital.
	 */
	public IntegralizacaoCapitalServico localizarIntegralizacaoCapitalServico(){
		return (IntegralizacaoCapitalServico) localizar("locator.cca.frontoffice.IntegralizacaoCapitalServico");
	}
		
}
