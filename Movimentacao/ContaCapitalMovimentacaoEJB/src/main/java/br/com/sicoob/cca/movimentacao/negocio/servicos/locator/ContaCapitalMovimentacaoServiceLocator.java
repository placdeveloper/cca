/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.cca.movimentacao.negocio.servicos.BloqueioContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ContaCorrenteViewServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.DebitoIndeterminadoServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.DesligarContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.DevolucaoContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.FechBaixarParcDebIndetServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.FechBaixarParcViaCCOServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.GestaoEmpresarialServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.HistBloqueioContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.HistParticipacaoCentralBancoobServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.IntegralizacaoOutrosBancosServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoIntegralizacaoExternaServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.OrigemBloqueioCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ParcelamentoContaCapitalExternoServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ParcelamentoContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ParticipacaoCentralBancoobServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ParticipacaoIndiretaBancoobServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.SubscricaoContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.TransferenciaContaCapitalServico;

// TODO: Auto-generated Javadoc
/**
 * Service Locator usado pelo sistema.
 *
 * @author Balbi
 */
public final class ContaCapitalMovimentacaoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static ContaCapitalMovimentacaoServiceLocator locator = new ContaCapitalMovimentacaoServiceLocator();
	
	/**
	 * Singleton da class.
	 *
	 * @return A instancia da classe
	 */
	public static ContaCapitalMovimentacaoServiceLocator getInstance() {
		return locator;
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoServiceLocator.
	 */
	private ContaCapitalMovimentacaoServiceLocator() {
		super("cca_movimentacao");
	}
		
	/**
	 * Localiza o EJB que implementa a interface {@code SubscricaoContaCapitalServico}.
	 *
	 * @return SubscricaoContaCapitalServico
	 * @see SubscricaoContaCapitalServico
	 */
	public SubscricaoContaCapitalServico localizarSubscricaoContaCapitalServico() {
		return (SubscricaoContaCapitalServico) localizar("locator.servico.SubscricaoContaCapitalServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code ParcelamentoContaCapitalServico}.
	 *
	 * @return ParcelamentoContaCapitalServico
	 * @see ParcelamentoContaCapitalServico
	 */
	public ParcelamentoContaCapitalServico localizarParcelamentoContaCapitalServico() {
		return (ParcelamentoContaCapitalServico) localizar("locator.servico.ParcelamentoContaCapitalServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code ParcelamentoContaCapitalExternoServico}.
	 *
	 * @return ParcelamentoContaCapitalExternoServico
	 * @see ParcelamentoContaCapitalExternoServico
	 */
	public ParcelamentoContaCapitalExternoServico localizarParcelamentoContaCapitalExternoServico() {
		return (ParcelamentoContaCapitalExternoServico) localizar("locator.servico.ParcelamentoContaCapitalExternoServico");
	}	
	
	/**
	 * Localiza o EJB que implementa a interface {@code LancamentoContaCapitalServico}.
	 *
	 * @return LancamentoContaCapitalServico
	 * @see LancamentoContaCapitalServico
	 */
	public LancamentoContaCapitalServico localizarLancamentoContaCapitalServico() {
		return (LancamentoContaCapitalServico) localizar("locator.servico.LancamentoContaCapitalServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code DesligarContaCapitalServico}.
	 *
	 * @return DesligarContaCapitalServico
	 * @see DesligarContaCapitalServico
	 */
	public DesligarContaCapitalServico localizarDesligarContaCapitalServico() {
		return (DesligarContaCapitalServico) localizar("locator.servico.DesligarContaCapitalServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code GestaoEmpresarialServico}.
	 *
	 * @return GestaoEmpresarialServico
	 * @see GestaoEmpresarialServico
	 */
	public GestaoEmpresarialServico localizarGestaoEmpresarialServico() {
		return (GestaoEmpresarialServico) localizar("locator.servico.GestaoEmpresarialServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code LancamentoIntegralizacaoExternaServico}.
	 *
	 * @return LancamentoIntegralizacaoExternaServico
	 * @see LancamentoIntegralizacaoExternaServico
	 */
	public LancamentoIntegralizacaoExternaServico localizarLancamentoIntegralizacaoExternaServico() {
		return (LancamentoIntegralizacaoExternaServico) localizar("locator.servico.LancamentoIntegralizacaoExternaServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code DevolucaoContaCapitalServico}.
	 *
	 * @return SubscricaoContaCapitalServico
	 * @see DevolucaoContaCapitalServico
	 */
	public DevolucaoContaCapitalServico localizarDevolucaoContaCapitalServico() {
		return (DevolucaoContaCapitalServico) localizar("locator.servico.DevolucaoContaCapitalServico");
	}		
	
	/**
	 * Localiza o EJB que implementa a interface {@code BloqueioContaCapitalServico}.
	 *
	 * @return BloqueioContaCapitalServico
	 * @see BloqueioContaCapitalServico
	 */
	public BloqueioContaCapitalServico localizarBloqueioContaCapitalServico() {
		return (BloqueioContaCapitalServico) localizar("locator.servico.BloqueioContaCapitalServico");
	}		
	
	/**
	 * Localiza o EJB que implementa a interface {@code TransferenciaContaCapitalServico}.
	 *
	 * @return TransferenciaContaCapitalServico
	 * @see TransferenciaContaCapitalServico
	 */
	public TransferenciaContaCapitalServico localizarTransferenciaContaCapitalServico() {
		return (TransferenciaContaCapitalServico) localizar("locator.servico.TransferenciaContaCapitalServico");
	}		
	
	/**
	 * Localiza o EJB que implementa a interface {@code localizarDebitoIndeterminadoServico}.
	 *
	 * @return localizarDebitoIndeterminadoServico
	 * @see localizarDebitoIndeterminadoServico
	 */
	public DebitoIndeterminadoServico localizarDebitoIndeterminadoServico() {
		return (DebitoIndeterminadoServico) localizar("locator.servico.DebitoIndeterminadoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code OrigemBloqueioCapitalServico}.
	 *
	 * @return OrigemBloqueioCapitalServico
	 * @see OrigemBloqueioCapitalServico
	 */
	public OrigemBloqueioCapitalServico localizarOrigemBloqueioCapitalServico() {
		return (OrigemBloqueioCapitalServico) localizar("locator.servico.OrigemBloqueioCapitalServico");
	}	
	
	/**
	 * Localiza o EJB que implementa a interface {@code HistBloqueioContaCapitalServico}.
	 *
	 * @return HistBloqueioContaCapitalServico
	 * @see HistBloqueioContaCapitalServico
	 */
	public HistBloqueioContaCapitalServico localizarHistBloqueioContaCapitalServico() {
		return (HistBloqueioContaCapitalServico) localizar("locator.servico.HistBloqueioContaCapitalServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code ContaCorrenteViewServico}.
	 *
	 * @return ContaCorrenteViewServico
	 * @see ContaCorrenteViewServico
	 */
	public ContaCorrenteViewServico localizarContaCorrenteViewServico() {
		return (ContaCorrenteViewServico) localizar("locator.servico.ContaCorrenteViewServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code ParticipacaoCentralBancoobServico}.
	 *
	 * @return O EJB solicitado
	 * @see ParticipacaoCentralBancoobServico
	 */
	public ParticipacaoCentralBancoobServico localizarParticipacaoCentralBancoobServico() {
		return (ParticipacaoCentralBancoobServico) localizar("locator.servico.ParticipacaoCentralBancoobServico");
	}		
	
	/**
	 * Localiza o EJB que implementa a interface {@code HistParticipacaoCentralBancoobServico}.
	 *
	 * @return O EJB solicitado
	 * @see HistParticipacaoCentralBancoobServico
	 */
	public HistParticipacaoCentralBancoobServico localizarHistParticipacaoCentralBancoobServico() {
		return (HistParticipacaoCentralBancoobServico) localizar("locator.servico.HistParticipacaoCentralBancoobServico");
	}		
	
	/**
	 * Localiza o EJB que implementa a interface {@code ParticipacaoIndiretaBancoobServico}.
	 *
	 * @return O EJB solicitado
	 * @see ParticipacaoIndiretaBancoobServico
	 */
	public ParticipacaoIndiretaBancoobServico localizarParticipacaoIndiretaBancoobServico() {
		return (ParticipacaoIndiretaBancoobServico) localizar("locator.servico.ParticipacaoIndiretaBancoobServico");
	}		
	
	/**
	 * Localiza o EJB que implementa a interface {@code IntegralizacaoOutrosBancosServico}.
	 *
	 * @return O EJB solicitado
	 * @see IntegralizacaoOutrosBancosServico
	 */
	public IntegralizacaoOutrosBancosServico localizarIntegralizacaoOutrosBancosServico() {
		return (IntegralizacaoOutrosBancosServico) localizar("locator.servico.IntegralizacaoOutrosBancosServico");
	}		
	
	/**
	 * Localiza o EJB que implementa a interface {@code FechBaixarParcViaCCOServico}.
	 *
	 * @return O EJB solicitado
	 * @see FechBaixarParcViaCCOServico
	 */
	public FechBaixarParcViaCCOServico localizarFechBaixarParcViaCCOServico() {
		return (FechBaixarParcViaCCOServico) localizar("locator.servico.FechBaixarParcViaCCOServico");
	}		
		
	/**
	 * Localiza o EJB que implementa a interface {@code FechBaixarParcDebIndetServico}.
	 *
	 * @return O EJB solicitado
	 * @see FechBaixarParcDebIndetServico
	 */
	public FechBaixarParcDebIndetServico localizarFechBaixarParcDebIndetServico() {
		return (FechBaixarParcDebIndetServico) localizar("locator.servico.FechBaixarParcDebIndetServico");
	}		
	
}