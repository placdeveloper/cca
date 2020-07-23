/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema ContaCapitalIntegracaoLegado.
 * 
 */
public final class ContaCapitalIntegracaoLegadoFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static ContaCapitalIntegracaoLegadoFabricaDelegates fabrica;

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static ContaCapitalIntegracaoLegadoFabricaDelegates getInstance() {
		if (fabrica == null) {
			synchronized (ContaCapitalIntegracaoLegadoFabricaDelegates.class) {
				if (fabrica == null) {
					fabrica = new ContaCapitalIntegracaoLegadoFabricaDelegates();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private ContaCapitalIntegracaoLegadoFabricaDelegates() {
	}
	
	/**
	 * Cria instancia de ContaCapitalLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ContaCapitalLegadoDelegate
	 */
	public ContaCapitalLegadoDelegate criarContaCapitalLegadoDelegate(){
		return ContaCapitalLegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de HistContaCapitalDelegate.
	 *
	 * @return o delegate solicitado
	 * @see HistContaCapitalDelegate
	 */
	public HistContaCapitalLegadoDelegate criarHistContaCapitalDelegate(){
		return HistContaCapitalLegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de LancamentosCCapitalLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see LancamentosCCapitalLegadoDelegate
	 */
	public LancamentosCCapitalLegadoDelegate criarLancamentosCCapitalLegadoDelegate(){
		return LancamentosCCapitalLegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de ParcelamentoCCALegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ParcelamentoCCALegadoDelegate
	 */
	public ParcelamentoCCALegadoDelegate criarParcelamentoCCALegadoDelegate(){
		return ParcelamentoCCALegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de CapaLoteCapitalLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see CapaLoteCapitalLegadoDelegate
	 */
	public CapaLoteCapitalLegadoDelegate criarCapaLoteCapitalLegadoDelegate(){
		return CapaLoteCapitalLegadoDelegate.getInstance();
	}	

	/**
	 * Cria instancia de TrabalhaLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TrabalhaLegadoDelegate
	 */
	public TrabalhaLegadoDelegate criarTrabalhaLegadoDelegate(){
		return TrabalhaLegadoDelegate.getInstance();
	}	
	
	/**
	 * Cria instancia de ValorCotasLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ValorCotasLegadoDelegate
	 */
	public ValorCotasLegadoDelegate criarValorCotasLegadoDelegate(){
		return ValorCotasLegadoDelegate.getInstance();
	}		
	
	/**
	 * Cria instancia de ConciliacaoContabilLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ConciliacaoContabilLegadoDelegate
	 */
	public ConciliacaoContabilLegadoDelegate criarConciliacaoContabilLegadoDelegate(){
		return ConciliacaoContabilLegadoDelegate.getInstance();
	}		
	
	/**
	 * {@link InformacaoAcumuladaLegadoDelegate}
	 * @return
	 */
	public InformacaoAcumuladaLegadoDelegate criarInformacaoAcumuladaLegadoDelegate() {
		return InformacaoAcumuladaLegadoDelegate.getInstance();
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoDelegate}
	 */
	public OperacaoFinanceiraLegadoDelegate criarOperacaoFinanceiraLegadoDelegate() {
		return OperacaoFinanceiraLegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de EmprestimoIntegracaoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see EmprestimoIntegracaoDelegate
	 */
	public EmprestimoIntegracaoDelegate criarEmprestimoIntegracaoDelegate() {
		return EmprestimoIntegracaoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de ReplicacaoContaCapitalLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ReplicacaoContaCapitalLegadoDelegate
	 */
	public ReplicacaoContaCapitalLegadoDelegate criarReplicacaoContaCapitalLegadoDelegate() {
		return ReplicacaoContaCapitalLegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de GestaoEmpresarialLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see GestaoEmpresarialLegadoDelegate
	 */
	public GestaoEmpresarialLegadoDelegate criarGestaoEmpresarialLegadoDelegate() {
		return GestaoEmpresarialLegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de ProdutoLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see ProdutoLegadoDelegate
	 */
	public ProdutoLegadoDelegate criarProdutoLegadoDelegate() {
		return ProdutoLegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de PLDContaCapitalLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see PLDContaCapitalLegadoDelegate
	 */
	public PLDContaCapitalLegadoDelegate criarPLDContaCapitalLegadoDelegate() {
		return PLDContaCapitalLegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de TabelaIRRFLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see TabelaIRRFLegadoDelegate
	 */
	public TabelaIRRFLegadoDelegate criarTabelaIRRFLegadoDelegate() {
		return TabelaIRRFLegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de PesquisaEmpresaDelegate.
	 *
	 * @return o delegate solicitado
	 * @see PesquisaEmpresaLegadoDelegate
	 */
	public PesquisaEmpresaLegadoDelegate criarPesquisaEmpresaLegadoDelegate() {
		return PesquisaEmpresaLegadoDelegate.getInstance();
	}
	
	/**
	 * Cria instancia de IntegralizacaoOutrosBancosLegadoDelegate.
	 *
	 * @return o delegate solicitado
	 * @see IntegralizacaoOutrosBancosLegadoDelegate
	 */
	public IntegralizacaoOutrosBancosLegadoDelegate criarIntegralizacaoOutrosBancosLegadoDelegate() {
		return IntegralizacaoOutrosBancosLegadoDelegate.getInstance();
	}
	
}