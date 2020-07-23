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
	 * Classe Delegate responsável por disponibilizar serviços de relatorios (Conta Capital)
	 */
	public RelContaCapitalDelegate criarRelContaCapitalDelegate() {
		return RelContaCapitalDelegate.getInstance();
	}
	/**
	 * Classe Delegate responsável por disponibilizar serviço de relatorio de Participacao Indireta de Singular
	 */
	public RelParticipacaoIndiretaSingularDelegate criarRelParticipacaoIndiretaSingularDelegate(){
		return RelParticipacaoIndiretaSingularDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate responsável por disponibilizar serviço de relatorio de Ficha Proposta Matricula
	 */
	public RelFichaPropostaMatriculaDelegate criarRelFichaPropostaMatriculaDelegate() {
		return RelFichaPropostaMatriculaDelegate.getInstance();
	}	
	
	/**
	 * Classe Delegate responsável por disponibilizar serviço de relatorio de Aprovação de Quadro Pendencia
	 */
	public RelAprovacaoQuadroPendenciaDelegate criarRelAprovacaoQuadroPendenciaDelegate() {
		return RelAprovacaoQuadroPendenciaDelegate.getInstance();
	}	
	
	/**
	 * Classe Delegate responsável por disponibilizar serviço RelDesligamentoAssociadoServico
	 */
	public RelDesligamentoAssociadoDelegate criarRelDesligamentoAssociadoDelegate() {
		return RelDesligamentoAssociadoDelegate.getInstance();
	}	
	
	/**
	 * Classe Delegate responsável por disponibilizar serviço RelImpedimentosDesligamentoServico
	 */
	public RelImpedimentosDesligamentoDelegate criarRelImpedimentosDesligamentoDelegate() {
		return RelImpedimentosDesligamentoDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate responsável por disponibilizar serviço RelValorParametroServico
	 */
	public RelValorParametroDelegate criarRelValorParametroDelegate() {
		return RelValorParametroDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate responsável por disponibilizar serviço RelBloqueioDelegate
	 */
	public RelBloqueioDelegate criarRelBloqueioDelegate() {
		return RelBloqueioDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate responsável por disponibilizar serviço RelDebitoIndeterminadoServico
	 */
	public RelDebitoIndeterminadoDelegate criarRelDebitoIndeterminadoDelegate() {
		return RelDebitoIndeterminadoDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate responsável por disponibilizar serviço RelIntegralizacaoOutrosBancosServico
	 */
	public RelIntegralizacaoOutrosBancosDelegate criarRelIntegralizacaoOutrosBancosDelegate() {
		return RelIntegralizacaoOutrosBancosDelegate.getInstance();
	}
	
	/**
	 * Classe Delegate responsável por disponibilizar serviço FechRelatoriosDelegate
	 */
	public FechRelatoriosDelegate criarFechRelatoriosDelegate () {
		return FechRelatoriosDelegate.getInstance();
	}
	
}