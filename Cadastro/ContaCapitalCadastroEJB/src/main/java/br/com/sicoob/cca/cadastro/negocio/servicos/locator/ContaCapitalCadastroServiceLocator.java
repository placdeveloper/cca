/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.cca.cadastro.negocio.servicos.AgrupadorConfiguracaoCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.AprovacaoContaCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.CadastroContaCapitalRenServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.ConfiguracaoCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.ContaCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.DocumentoCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.PropostaSubscricaoServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.SituacaoCadastroPropostaServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.ValorConfiguracaoCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.ValorCotaServico;

/**
 * Service Locator usado pelo sistema ContaCapitalCadastro.
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalCadastroServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static ContaCapitalCadastroServiceLocator locator = new ContaCapitalCadastroServiceLocator();

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static ContaCapitalCadastroServiceLocator getInstance() {
		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	protected ContaCapitalCadastroServiceLocator() {
		super("cca.cadastro");
	}

	/**
	 * @return ValorCotaServico
	 */
	public ValorCotaServico localizarValorCotaServico() {
		return (ValorCotaServico) localizar("locator.servico.ValorCotaServico");
	}
	
	/**
	 * @return ConfiguracaoCapitalServico
	 */
	public ConfiguracaoCapitalServico localizarConfiguracaoCapitalServico() {
		return (ConfiguracaoCapitalServico) localizar("locator.servico.ConfiguracaoCapitalServico");
	}

	/**
	 * @return ValorConfiguracaoCapitalServico
	 */
	public ValorConfiguracaoCapitalServico localizarValorConfiguracaoCapitalServico() {
		return (ValorConfiguracaoCapitalServico) localizar("locator.servico.ValorConfiguracaoCapitalServico");
	}
	
	/**
	 * @return CadastroContaCapitalRenServico
	 */
	public CadastroContaCapitalRenServico localizarCadastroContaCapitalRenServico() {
		return (CadastroContaCapitalRenServico) localizar("locator.servico.CadastroContaCapitalRenServico");
	}
	
	/**
	 * @return SituacaoCadastroPropostaServico
	 */
	public SituacaoCadastroPropostaServico localizarSituacaoCadastroPropostaServico() {
		return (SituacaoCadastroPropostaServico) localizar("locator.servico.SituacaoCadastroPropostaServico");
	}
	
	/**
	 * @return PropostaSubscricaoServico
	 */
	public PropostaSubscricaoServico localizarPropostaSubscricaoServico() {
		return (PropostaSubscricaoServico) localizar("locator.servico.PropostaSubscricaoServico");
	}
	
	/**
	 * @return ContaCapitalServico
	 */
	public ContaCapitalServico localizarContaCapitalServico() {
		return (ContaCapitalServico) localizar("locator.servico.ContaCapitalServico");
	}
	
	/**
	 * @return DocumentoCapitalServico
	 */
	public DocumentoCapitalServico localizarDocumentoCapitalServico() {
		return (DocumentoCapitalServico) localizar("locator.servico.DocumentoCapitalServico");
	}
	
	/**
	 * @return AprovacaoContaCapitalServico
	 */
	public AprovacaoContaCapitalServico localizarAprovacaoContaCapitalServico() {
		return (AprovacaoContaCapitalServico) localizar("locator.servico.AprovacaoContaCapitalServico");
	}
	
	/**
	 * @return AgrupadorConfiguracaoCapitalServico
	 */
	public AgrupadorConfiguracaoCapitalServico localizarAgrupadorConfiguracaoCapitalServico() {
		return (AgrupadorConfiguracaoCapitalServico) localizar("locator.servico.AgrupadorConfiguracaoCapitalServico");
	}
}