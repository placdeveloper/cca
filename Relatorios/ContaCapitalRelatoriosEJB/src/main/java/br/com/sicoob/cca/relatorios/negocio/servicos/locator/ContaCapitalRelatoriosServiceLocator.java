/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.cca.relatorios.negocio.servicos.FechRelatoriosServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelAprovacaoQuadroPendenciaServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelBloqueioServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelContaCapitalServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelDebitoIndeterminadoServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelDesligamentoAssociadoServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelFichaPropostaMatriculaServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelImpedimentosDesligamentoServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelIntegralizacaoOutrosBancosServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelParticipacaoIndiretaSingularServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelValorParametroServico;

/**
 * Service Locator usado pelo sistema ContaCapitalCadastro.
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalRelatoriosServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static ContaCapitalRelatoriosServiceLocator locator = new ContaCapitalRelatoriosServiceLocator();
	
	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static ContaCapitalRelatoriosServiceLocator getInstance() {
		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private ContaCapitalRelatoriosServiceLocator() {
		super("cca_relatorios");
	}
	
	/**
	 * Locator ContaCapitalRelatoriosServico
	 */
	public RelContaCapitalServico localizarRelContaCapitalServico() {
		return (RelContaCapitalServico) localizar("locator.servico.RelContaCapitalServico");
	}
	/**
	 * Locator RelParticipacaoIndiretaSingularServico
	 */
	public RelParticipacaoIndiretaSingularServico localizarRelParticipacaoIndiretaSingularServico(){
		return (RelParticipacaoIndiretaSingularServico) localizar("locator.servico.RelParticipacaoIndiretaSingularServico");
	}
	
	/**
	 * Locator RelFichaPropostaMatriculaServico
	 */
	public RelFichaPropostaMatriculaServico localizarRelFichaPropostaMatriculaServico(){
		return (RelFichaPropostaMatriculaServico) localizar("locator.servico.RelFichaPropostaMatriculaServico");
	}
	
	/**
	 * Locator RelAprovacaoQuadroPendenciaServico
	 */
	public RelAprovacaoQuadroPendenciaServico localizarRelAprovacaoQuadroPendenciaServico(){
		return (RelAprovacaoQuadroPendenciaServico) localizar("locator.servico.RelAprovacaoQuadroPendenciaServico");
	}
	
	/**
	 * Locator RelDesligamentoAssociado
	 */
	public RelDesligamentoAssociadoServico localizarRelDesligamentoAssociadoServico(){
		return (RelDesligamentoAssociadoServico) localizar("locator.servico.RelDesligamentoAssociadoServico");
	}
	
	/**
	 * Locator RelImpedimentosDesligamento
	 */
	public RelImpedimentosDesligamentoServico localizarRelImpedimentosDesligamentoServico(){
		return (RelImpedimentosDesligamentoServico) localizar("locator.servico.RelImpedimentosDesligamentoServico");
	}
	
	/**
	 * Locator RelValorParametroServico
	 */
	public RelValorParametroServico localizarRelValorParametroServico(){
		return (RelValorParametroServico) localizar("locator.servico.RelValorParametroServico");
	}
	
	/**
	 * Locator RelBloqueioServico
	 */
	public RelBloqueioServico localizarRelBloqueioServico(){
		return (RelBloqueioServico) localizar("locator.servico.RelBloqueioServico");
	}
	
	/**
	 * Locator RelDebitoIndeterminadoServico
	 */
	public RelDebitoIndeterminadoServico localizarRelDebitoIndeterminadoServico(){
		return (RelDebitoIndeterminadoServico) localizar("locator.servico.RelDebitoIndeterminadoServico");
	}
	
	/**
	 * Locator RelIntegralizacaoOutrosBancosServico
	 */
	public RelIntegralizacaoOutrosBancosServico localizarRelIntegralizacaoOutrosBancosServico(){
		return (RelIntegralizacaoOutrosBancosServico) localizar("locator.servico.RelIntegralizacaoOutrosBancosServico");
	}
	
	/**
	 * Locator RelIntegralizacaoOutrosBancosServico
	 */
	public FechRelatoriosServico localizarFechRelatoriosServico(){
		return (FechRelatoriosServico) localizar("locator.servico.FechRelatoriosServico");
	}
	
	
}
