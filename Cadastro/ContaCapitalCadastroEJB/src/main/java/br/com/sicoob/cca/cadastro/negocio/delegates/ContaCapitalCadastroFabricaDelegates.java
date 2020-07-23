/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema ContaCapitalCadastro.
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalCadastroFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static ContaCapitalCadastroFabricaDelegates fabrica = new ContaCapitalCadastroFabricaDelegates();

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static ContaCapitalCadastroFabricaDelegates getInstance() {
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	protected ContaCapitalCadastroFabricaDelegates() {
		
	}
	
	/**
	 * @return ValorCotaDelegate
	 */
	public ValorCotaDelegate criarValorCotaDelegate() {
		return new ValorCotaDelegate();
	}
	
	/**
	 * @return ConfiguracaoCapitalDelegate
	 */
	public ConfiguracaoCapitalDelegate criarConfiguracaoCapitalDelegate() {
		return new ConfiguracaoCapitalDelegate();
	}
	
	/**
	 * @return ValorConfiguracaoCapitalDelegate
	 */
	public ValorConfiguracaoCapitalDelegate criarValorConfiguracaoCapitalDelegate() {
		return new ValorConfiguracaoCapitalDelegate();
	}
	
	/**
	 * @return CadastroContaCapitalRenDelegate
	 */
	public CadastroContaCapitalRenDelegate criarCadastroContaCapitalRenDelegate() {
		return new CadastroContaCapitalRenDelegate();
	}
	
	/**
	 * @return SituacaoCadastroPropostaDelegate
	 */
	public SituacaoCadastroPropostaDelegate criarSituacaoCadastroPropostaDelegate() {
		return new SituacaoCadastroPropostaDelegate();
	}
	
	/**
	 * @return PropostaSubscricaoDelegate
	 */
	public PropostaSubscricaoDelegate criarPropostaSubscricaoDelegate() {
		return new PropostaSubscricaoDelegate();
	}
	
	/**
	 * @return ContaCapitalDelegate
	 */
	public ContaCapitalDelegate criarContaCapitalDelegate() {
		return new ContaCapitalDelegate();
	}
	
	/**
	 * @return DocumentoCapitalDelegate
	 */
	public DocumentoCapitalDelegate criarDocumentoCapitalDelegate() {
		return new DocumentoCapitalDelegate();
	}
	
	/**
	 * @return AprovacaoContaCapitalDelegate
	 */
	public AprovacaoContaCapitalDelegate criarAprovacaoContaCapitalDelegate() {
		return new AprovacaoContaCapitalDelegate();
	}
	
	/**
	 * @return AgrupadorConfiguracaoCapitalDelegate
	 */
	public AgrupadorConfiguracaoCapitalDelegate criarAgrupadorConfiguracaoCapitalDelegate() {
		return new AgrupadorConfiguracaoCapitalDelegate();
	}
}