/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.sisbr.cca.processamento.negocio.servicos.interfaces.ProcessamentoReplicacaoServico;

/**
 * Service Locator usado pelo sistema Processamento.
 * 
 */
public final class ProcessamentoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static ProcessamentoServiceLocator locator = new ProcessamentoServiceLocator();

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static ProcessamentoServiceLocator getInstance() {
		
		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	protected ProcessamentoServiceLocator() {
		super("cca_processamento");
	}
	
	/**
	 * Recupera ProcessamentoReplicacaoServico
	 * @return
	 */
	public ProcessamentoReplicacaoServico localizarProcessamentoReplicacaoServico() {
		return (ProcessamentoReplicacaoServico) localizar("locator.cca_processamento.ProcessamentoReplicacaoServico");
	}	
	
}