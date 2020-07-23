/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema ContaCapitalRelatorios.
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalRelatoriosFabricaDelegates extends BancoobFabricaDelegates {

	/** Instancia do Fabrica de Delegates. */
	private static ContaCapitalRelatoriosFabricaDelegates fabrica = new ContaCapitalRelatoriosFabricaDelegates();

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static ContaCapitalRelatoriosFabricaDelegates getInstance() {
		return fabrica;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	protected ContaCapitalRelatoriosFabricaDelegates() {
		
	}
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�os de relatorios (Conta Capital)
	 */
	public RelContaCapitalDelegate criarRelContaCapitalDelegate() {
		return RelContaCapitalDelegate.getInstance();
	}
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�o de relatorio de Participacao Indireta de Singular
	 */
	public RelParticipacaoIndiretaSingularDelegate criarRelParticipacaoIndiretaSingularDelegate(){
		return RelParticipacaoIndiretaSingularDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�o de relatorio de Ficha Proposta Matricula
	 */
	public RelFichaPropostaMatriculaDelegate criarRelFichaPropostaMatriculaDelegate() {
		return RelFichaPropostaMatriculaDelegate.getInstance();
	}	
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�o de relatorio de Aprova��o de Quadro Pendencia
	 */
	public RelAprovacaoQuadroPendenciaDelegate criarRelAprovacaoQuadroPendenciaDelegate() {
		return RelAprovacaoQuadroPendenciaDelegate.getInstance();
	}	
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�o RelDesligamentoAssociadoServico
	 */
	public RelDesligamentoAssociadoDelegate criarRelDesligamentoAssociadoDelegate() {
		return RelDesligamentoAssociadoDelegate.getInstance();
	}	
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�o RelImpedimentosDesligamentoServico
	 */
	public RelImpedimentosDesligamentoDelegate criarRelImpedimentosDesligamentoDelegate() {
		return RelImpedimentosDesligamentoDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�o RelValorParametroServico
	 */
	public RelValorParametroDelegate criarRelValorParametroDelegate() {
		return RelValorParametroDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�o RelBloqueioDelegate
	 */
	public RelBloqueioDelegate criarRelBloqueioDelegate() {
		return RelBloqueioDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�o RelDebitoIndeterminadoServico
	 */
	public RelDebitoIndeterminadoDelegate criarRelDebitoIndeterminadoDelegate() {
		return RelDebitoIndeterminadoDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�o RelIntegralizacaoOutrosBancosServico
	 */
	public RelIntegralizacaoOutrosBancosDelegate criarRelIntegralizacaoOutrosBancosDelegate() {
		return RelIntegralizacaoOutrosBancosDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate respons�vel por disponibilizar servi�o FechRelatoriosDelegate
	 */
	public FechRelatoriosDelegate criarFechRelatoriosDelegate () {
		return FechRelatoriosDelegate.getInstance();
	}
	
}