/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

// TODO: Auto-generated Javadoc
/**
 * Fabrica para criacao de Delegates do modulo ContaCapitalMovimentacao.
 *
 * @author Balbi
 */
public final class ContaCapitalMovimentacaoFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static ContaCapitalMovimentacaoFabricaDelegates fabrica = new ContaCapitalMovimentacaoFabricaDelegates();

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static ContaCapitalMovimentacaoFabricaDelegates getInstance() {
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe.
	 */
	protected ContaCapitalMovimentacaoFabricaDelegates() {
		
	}
	
	/**
	 * Cria instancia de SubscricaoContaCapitalDelegate.
	 *
	 * @return SubscricaoContaCapitalDelegate
	 * @see SubscricaoContaCapitalDelegate
	 */
	public SubscricaoContaCapitalDelegate criarSubscricaoContaCapitalDelegate() {
		return new SubscricaoContaCapitalDelegate();
	}

	/**
	 * Cria instancia de ParcelamentoContaCapitalDelegate.
	 *
	 * @return ParcelamentoContaCapitalDelegate
	 * @see ParcelamentoContaCapitalDelegate
	 */
	public ParcelamentoContaCapitalDelegate criarParcelamentoContaCapitalDelegate() {
		return new ParcelamentoContaCapitalDelegate();
	}

	/**
	 * Cria instancia de LancamentoContaCapitalDelegate.
	 *
	 * @return DocumentoCapitalDelegate
	 * @see LancamentoContaCapitalDelegate
	 */
	public LancamentoContaCapitalDelegate criarLancamentoContaCapitalDelegate() {
		return new LancamentoContaCapitalDelegate();
	}
	
	/**
	 * Cria instancia de DesligarContaCapitalDelegate.
	 *
	 * @return DesligarContaCapitalDelegate
	 * @see DesligarContaCapitalDelegate
	 */
	public DesligarContaCapitalDelegate criarDesligarContaCapitalDelegate() {
		return new DesligarContaCapitalDelegate();
	}
	
	/**
	 * Cria instancia de GestaoEmpresarialDelegate.
	 *
	 * @return GestaoEmpresarialDelegate
	 * @see GestaoEmpresarialDelegate
	 */
	public GestaoEmpresarialDelegate criarGestaoEmpresarialDelegate() {
		return new GestaoEmpresarialDelegate();
	}

	/**
	 * Cria instancia de LancamentoIntegralizacaoExternaDelegate.
	 *
	 * @return LancamentoIntegralizacaoExternaDelegate
	 * @see LancamentoIntegralizacaoExternaDelegate
	 */
	public LancamentoIntegralizacaoExternaDelegate criarLancamentoIntegralizacaoExternaDelegate() {
		return new LancamentoIntegralizacaoExternaDelegate();
	}		

	/**
	 * Cria instancia de DevolucaoContaCapitalDelegate.
	 *
	 * @return DevolucaoContaCapitalDelegate
	 * @see DevolucaoContaCapitalDelegate
	 */
	public DevolucaoContaCapitalDelegate criarDevolucaoContaCapitalDelegate() {
		return new DevolucaoContaCapitalDelegate();
	}		
	
	/**
	 * Cria instancia de ParcelamentoContaCapitalExternoDelegate.
	 *
	 * @return ParcelamentoContaCapitalExternoDelegate
	 * @see ParcelamentoContaCapitalExternoDelegate
	 */
	public ParcelamentoContaCapitalExternoDelegate criarParcelamentoContaCapitalExternoDelegate(){
		return new ParcelamentoContaCapitalExternoDelegate();
	}

	/**
	 * Cria instancia de BloqueioContaCapitalDelegate.
	 *
	 * @return BloqueioContaCapitalDelegate
	 * @see BloqueioContaCapitalDelegate
	 */
	public BloqueioContaCapitalDelegate criarBloqueioContaCapitalDelegate() {
		return new BloqueioContaCapitalDelegate();
	}		

	/**
	 * Cria instancia de TransferenciaContaCapitalDelegate.
	 *
	 * @return TransferenciaContaCapitalDelegate
	 * @see TransferenciaContaCapitalDelegate
	 */
	public TransferenciaContaCapitalDelegate criarTransferenciaContaCapitalDelegate() {
		return new TransferenciaContaCapitalDelegate();
	}
	
	/**
	 * Cria instancia de DebitoIndeterminadoDelegate
	 *
	 * @return DebitoIndeterminadoDelegate
	 * @see DebitoIndeterminadoDelegate
	 */
	public DebitoIndeterminadoDelegate criarDebitoIndeterminadoDelegate() {
		return new DebitoIndeterminadoDelegate();
	}
	
	/**
	 * Cria instancia de OrigemBloqueioCapitalDelegate
	 *
	 * @return OrigemBloqueioCapitalDelegate
	 * @see OrigemBloqueioCapitalDelegate
	 */
	public OrigemBloqueioCapitalDelegate criarOrigemBloqueioCapitalDelegate() {
		return new OrigemBloqueioCapitalDelegate();
	}
	
	/**
	 * Cria instancia de HistBloqueioContaCapitalDelegate
	 *
	 * @return HistBloqueioContaCapitalDelegate
	 * @see HistBloqueioContaCapitalDelegate
	 */
	public HistBloqueioContaCapitalDelegate criarHistBloqueioContaCapitalDelegate() {
		return new HistBloqueioContaCapitalDelegate();
	}
	
	/**
	 * Cria instancia de ContaCorrenteViewDelegate
	 *
	 * @return ContaCorrenteViewDelegate
	 * @see ContaCorrenteViewDelegate
	 */
	public ContaCorrenteViewDelegate criarContaCorrenteViewDelegate() {
		return new ContaCorrenteViewDelegate();
	}
	
	/**
	 * @return ParticipacaoCentralBancoobDelegate
	 */
	public ParticipacaoCentralBancoobDelegate criarParticipacaoCentralBancoobDelegate() {
		return new ParticipacaoCentralBancoobDelegate();
	}		
	
	/**
	 * @return HistParticipacaoCentralBancoobDelegate
	 */
	public HistParticipacaoCentralBancoobDelegate criarHistParticipacaoCentralBancoobDelegate() {
		return new HistParticipacaoCentralBancoobDelegate();
	}
	
	/**
	 * @return ParticipacaoIndiretaBancoobDelegate
	 */
	public ParticipacaoIndiretaBancoobDelegate criarParticipacaoIndiretaBancoobDelegate() {
		return new ParticipacaoIndiretaBancoobDelegate();
	}		
	
	/**
	 * @return IntegralizacaoOutrosBancosDelegate
	 */
	public IntegralizacaoOutrosBancosDelegate criarIntegralizacaoOutrosBancosDelegate() {
		return new IntegralizacaoOutrosBancosDelegate();
	}		
	
	
}