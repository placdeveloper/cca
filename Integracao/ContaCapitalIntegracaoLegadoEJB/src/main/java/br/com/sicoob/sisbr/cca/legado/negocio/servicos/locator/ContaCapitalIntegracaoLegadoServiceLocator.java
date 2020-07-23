/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.CapaLoteCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ClienteCooperativaLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ConciliacaoContabilLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.EmprestimoIntegracaoLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.GestaoEmpresarialLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.HistContaCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.InformacaoAcumuladaLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.LancamentosCCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.OperacaoFinanceiraLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.PLDContaCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ParcelamentoCCALegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.PesquisaEmpresaLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ProdutoLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.TabelaIRRFLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.TrabalhaLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ValorCotasLegadoServico;

/**
 * Service Locator usado pelo sistema ContaCapitalIntegracaoLegado.
 * 
 */
public final class ContaCapitalIntegracaoLegadoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static ContaCapitalIntegracaoLegadoServiceLocator locator = new ContaCapitalIntegracaoLegadoServiceLocator();

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static ContaCapitalIntegracaoLegadoServiceLocator getInstance() {
		
		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private ContaCapitalIntegracaoLegadoServiceLocator() {
		super("legado_cca_integracao");
	}
	
	
	/**
	 * Localiza o EJB que implementa a interface {@code ContaCapitalLegadoServico}.
	 *
	 * @return O EJB solicitado
	 * @see ContaCapitalLegadoServico
	 */
	public ContaCapitalLegadoServico localizarContaCapitalLegadoServico() {
		return (ContaCapitalLegadoServico) localizar("locator.servico.ContaCapitalLegadoServico");
	}	

	/**
	 * Localiza o EJB que implementa a interface {@code HistContaCapitalLegadoServico}.
	 *
	 * @return O EJB solicitado
	 * @see HistContaCapitalLegadoServico
	 */
	public HistContaCapitalLegadoServico localizarHistContaCapitalLegadoServico(){
		return (HistContaCapitalLegadoServico) localizar("locator.servico.HistContaCapitalLegadoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code LancamentosCCapitalLegadoServico}.
	 *
	 * @return O EJB solicitado
	 * @see LancamentosCCapitalLegadoServico
	 */
	public LancamentosCCapitalLegadoServico localizarLancamentosCCapitalLegadoServico(){
		return (LancamentosCCapitalLegadoServico) localizar("locator.servico.LancamentosCCapitalLegadoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code ParcelamentoCCALegadoServico}.
	 *
	 * @return O EJB solicitado
	 * @see ParcelamentoCCALegadoServico
	 */
	public ParcelamentoCCALegadoServico localizarParcelamentoCCALegadoServico(){
		return (ParcelamentoCCALegadoServico) localizar("locator.servico.ParcelamentoCCALegadoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code CapaLoteCapitalLegadoServico}.
	 *
	 * @return O EJB solicitado
	 * @see CapaLoteCapitalLegadoServico
	 */
	public CapaLoteCapitalLegadoServico localizarCapaLoteCapitalLegadoServico(){
		return (CapaLoteCapitalLegadoServico) localizar("locator.servico.CapaLoteCapitalLegadoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code TrabalhaLegadoServico}.
	 *
	 * @return O EJB solicitado
	 * @see TrabalhaLegadoServico
	 */
	public TrabalhaLegadoServico localizarTrabalhaLegadoServico(){
		return (TrabalhaLegadoServico) localizar("locator.servico.TrabalhaLegadoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code ValorCotasLegadoServico}.
	 *
	 * @return O EJB solicitado
	 * @see ValorCotasLegadoServico
	 */
	public ValorCotasLegadoServico localizarValorCotasLegadoServico(){
		return (ValorCotasLegadoServico) localizar("locator.servico.ValorCotasLegadoServico");
	}	
	
	/**
	 * Localiza o EJB que implementa a interface {@code ConciliacaoContabilLegadoServico}.
	 *
	 * @return O EJB solicitado
	 * @see ConciliacaoContabilLegadoServico
	 */
	public ConciliacaoContabilLegadoServico localizarConciliacaoContabilLegadoServico(){
		return (ConciliacaoContabilLegadoServico) localizar("locator.servico.ConciliacaoContabilLegadoServico");
	}
	
	/**
	 * Locator informacao acumulada legado
	 * @return InformacaoAcumuladaLegadoServico
	 */
	public InformacaoAcumuladaLegadoServico localizarInformacaoAcumuladaLegadoServico() {
		return (InformacaoAcumuladaLegadoServico) localizar("locator.servico.InformacaoAcumuladaLegadoServico");
	}
	
	/**
	 * Locator OperacaoFinanceiraLegadoServico
	 */
	public OperacaoFinanceiraLegadoServico localizarOperacaoFinanceiraLegadoServico() {
		return (OperacaoFinanceiraLegadoServico) localizar("locator.servico.OperacaoFinanceiraLegadoServico");
	}
	
	/**
	 * Locator ClienteCooperativaLegadoServico
	 */
	public ClienteCooperativaLegadoServico localizarClienteCooperativaLegadoServico() {
		return (ClienteCooperativaLegadoServico) localizar("locator.servico.ClienteCooperativaLegadoServico");
	}
	
	/**
	 * Locator EmprestimoIntegracaoLegadoServico
	 * @return 
	 */
	public EmprestimoIntegracaoLegadoServico localizarEmprestimoIntegracaoServico() {
		return (EmprestimoIntegracaoLegadoServico) localizar("locator.servico.EmprestimoIntegracaoLegadoServico");
	}

	/**
	 * Locator ReplicacaoContaCapitalLegadoDelegate
	 * @return 
	 */
	public ReplicacaoContaCapitalLegadoServico localizarReplicacaoContaCapitalLegadoServico() {
		return (ReplicacaoContaCapitalLegadoServico) localizar("locator.servico.ReplicacaoContaCapitalLegadoServico");
	}	
	
	/**
	 * Locator GestaoEmpresarialLegadoServico
	 * @return 
	 */
	public GestaoEmpresarialLegadoServico localizarGestaoEmpresarialLegadoServico() {
		return (GestaoEmpresarialLegadoServico) localizar("locator.servico.GestaoEmpresarialLegadoServico");
	}
	
	/**
	 * Locator ProdutoLegadoServico
	 * @return 
	 */
	public ProdutoLegadoServico localizarProdutoLegadoServico() {
		return (ProdutoLegadoServico) localizar("locator.servico.ProdutoLegadoServico");
	}
	
	/**
	 * Locator PLDContaCapitalLegadoServico
	 * @return 
	 */
	public PLDContaCapitalLegadoServico localizarPLDContaCapitalLegadoServico() {
		return (PLDContaCapitalLegadoServico) localizar("locator.servico.PLDContaCapitalLegadoServico");
	}
	
	/**
	 * Locator TabelaIRRFLegadoServico
	 * @return 
	 */
	public TabelaIRRFLegadoServico localizarTabelaIRRFLegadoServico() {
		return (TabelaIRRFLegadoServico) localizar("locator.servico.TabelaIRRFLegadoServico");
	}
	
	/**
	 * Locator PesquisaEmpresaServico
	 * @return 
	 */
	public PesquisaEmpresaLegadoServico localizarPesquisaEmpresaServico() {
		return (PesquisaEmpresaLegadoServico) localizar("locator.servico.PesquisaEmpresaLegadoServico");
	}

	/**
	 * Locator IntegralizacaoOutrosBancosLegadoServico
	 * @return
	 */
	public IntegralizacaoOutrosBancosLegadoServico localizarIntegralizacaoOutrosBancosLegadoServico() {
		return (IntegralizacaoOutrosBancosLegadoServico) localizar("locator.servico.IntegralizacaoOutrosBancosLegadoServico");
	}
}