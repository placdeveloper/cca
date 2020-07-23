/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema ContaCapitalIntegracao.
 * 
 */
public final class ContaCapitalIntegracaoFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static ContaCapitalIntegracaoFabricaDelegates fabrica;

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static ContaCapitalIntegracaoFabricaDelegates getInstance() {
		if (fabrica == null) {
			synchronized (ContaCapitalIntegracaoFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new ContaCapitalIntegracaoFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private ContaCapitalIntegracaoFabricaDelegates() {
	}
	
	/**
	 * Cria instancia de InstituicaoIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see InstituicaoIntegracaoDelegate
	 */
	public InstituicaoIntegracaoDelegate criarInstituicaoIntegracaoDelegate(){
		return InstituicaoIntegracaoDelegate.getInstance();
	}	
	
	/**
	 * Cria instancia de CapesIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CapesIntegracaoDelegate
	 */
	public CapesIntegracaoDelegate criarCapesIntegracaoDelegate(){
		return CapesIntegracaoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de GenIntIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see GenIntIntegracaoDelegate
	 */
	public GenIntIntegracaoDelegate criarGenIntIntegracaoDelegate(){
		return GenIntIntegracaoDelegate.getInstance();
	}		

	/**
	 * Cria instancia de ContaCorrenteIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ContaCorrenteIntegracaoDelegate
	 */
	public ContaCorrenteIntegracaoDelegate criarContaCorrenteIntegracaoDelegate(){
		return ContaCorrenteIntegracaoDelegate.getInstance();
	}		
	
	/**
	 * Cria instancia de ContabilidadeIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ContabilidadeIntegracaoDelegate
	 */
	public ContabilidadeIntegracaoDelegate criarContabilidadeIntegracaoDelegate(){
		return ContabilidadeIntegracaoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de LocalizacaoIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see LocalizacaoIntegracaoDelegate
	 */
	public LocalizacaoIntegracaoDelegate criarLocalizacaoIntegracaoDelegate(){
		return LocalizacaoIntegracaoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de DocumentoIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see DocumentoIntegracaoDelegate
	 */
	public DocumentoIntegracaoDelegate criarDocumentoIntegracaoDelegate() {
		return DocumentoIntegracaoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de GftIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see GftIntegracaoDelegate
	 */
	public GftIntegracaoDelegate criarGftIntegracaoDelegate() {
		return GftIntegracaoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de GftIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CaptacaoRemuneradaIntegracaoDelegate
	 */
	public CaptacaoRemuneradaIntegracaoDelegate criarCaptacaoRemuneradaIntegracaoDelegate() {
		return CaptacaoRemuneradaIntegracaoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de CreditoIntegracaoDelegate
	 * @return
	 */
	public CreditoIntegracaoDelegate criarCreditoIntegracaoDelegate() {
		return CreditoIntegracaoDelegate.getInstance();
	}
	
}