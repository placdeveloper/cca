/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CaptacaoRemuneradaIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContaCorrenteIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContabilidadeIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CreditoIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.DocumentoIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GenIntIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GftIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.InstituicaoIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.LocalizacaoIntegracaoServico;

/**
 * Service Locator usado pelo sistema ContaCapitalIntegracao.
 * 
 */
public final class ContaCapitalIntegracaoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static ContaCapitalIntegracaoServiceLocator locator = new ContaCapitalIntegracaoServiceLocator();

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static ContaCapitalIntegracaoServiceLocator getInstance() {
		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private ContaCapitalIntegracaoServiceLocator() {
		super("cca_integracao");
	}
	
	
	/**
	 * Localiza o EJB que implementa a interface {@code InstituicaoIntegracaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see InstituicaoIntegracaoServico
	 */
	public InstituicaoIntegracaoServico localizarInstituicaoIntegracaoServico() {
		return (InstituicaoIntegracaoServico) localizar("locator.servico.InstituicaoIntegracaoServico");
	}	

	/**
	 * Localiza o EJB que implementa a interface {@code CapesIntegracaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see CapesIntegracaoServico
	 */
	public CapesIntegracaoServico localizarCapesIntegracaoServico() {
		return (CapesIntegracaoServico) localizar("locator.servico.CapesIntegracaoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code GenIntIntegracaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see GenIntIntegracaoServico
	 */
	public GenIntIntegracaoServico localizarGenIntIntegracaoServico() {
		return (GenIntIntegracaoServico) localizar("locator.servico.GenIntIntegracaoServico");
	}	
	
	/**
	 * Localiza o EJB que implementa a interface {@code ContaCorrenteIntegracaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see ContaCorrenteIntegracaoServico
	 */
	public ContaCorrenteIntegracaoServico localizarContaCorrenteIntegracaoServico() {
		return (ContaCorrenteIntegracaoServico) localizar("locator.servico.ContaCorrenteIntegracaoServico");
	}		
	
	/**
	 * Localiza o EJB que implementa a interface {@code ContabilidadeIntegracaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see ContabilidadeIntegracaoServico
	 */
	public ContabilidadeIntegracaoServico localizarContabilidadeIntegracaoServico() {
		return (ContabilidadeIntegracaoServico) localizar("locator.servico.ContabilidadeIntegracaoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code LocalizacaoIntegracaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see LocalizacaoIntegracaoServico
	 */
	public LocalizacaoIntegracaoServico localizarLocalizacaoIntegracaoServico() {
		return (LocalizacaoIntegracaoServico) localizar("locator.servico.LocalizacaoIntegracaoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code DocumentoIntegracaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see DocumentoIntegracaoServico
	 */
	public DocumentoIntegracaoServico localizarDocumentoIntegracaoServico() {
		return (DocumentoIntegracaoServico) localizar("locator.servico.DocumentoIntegracaoServico");
	}
	
	/**
	 * Locator GftIntegracaoServico
	 * @return 
	 */
	public GftIntegracaoServico localizarGftIntegracaoServico() {
		return (GftIntegracaoServico) localizar("locator.servico.GftIntegracaoServico");
	}
	
	/**
	 * Locator CaptacaoRemuneradaIntegracaoServico
	 * @return 
	 */
	public CaptacaoRemuneradaIntegracaoServico localizarCaptacaoRemuneradaIntegracaoServico() {
		return (CaptacaoRemuneradaIntegracaoServico) localizar("locator.servico.CaptacaoRemuneradaIntegracaoServico");
	}
	
	/**
	 * Locator CreditoIntegracaoServico
	 * @return
	 */
	public CreditoIntegracaoServico localizarCreditoIntegracaoServico() {
		return (CreditoIntegracaoServico) localizar("locator.servico.CreditoIntegracaoServico");
	}
	
}