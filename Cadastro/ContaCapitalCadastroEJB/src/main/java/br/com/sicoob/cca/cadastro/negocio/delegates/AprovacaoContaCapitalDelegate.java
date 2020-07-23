/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.servicos.AprovacaoContaCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;

/**
 * Delegate de aprovacao do cadastro conta capital
 */
public class AprovacaoContaCapitalDelegate extends ContaCapitalCadastroDelegate<AprovacaoContaCapitalServico> {
	
	/**
	 * Instancia um novo AprovacaoContaCapitalDelegate.
	 */
	AprovacaoContaCapitalDelegate() {
		
	}
	
	/**
	 * Locator AprovacaoContaCapitalServico
	 */
	@Override
	protected AprovacaoContaCapitalServico localizarServico() {
		return (AprovacaoContaCapitalServico) ContaCapitalCadastroServiceLocator.getInstance().localizarAprovacaoContaCapitalServico();
	}
	
	/**
	 * {@link AprovacaoContaCapitalServico#aprovar(ContaCapital, Integer, Integer, String)}
	 */
	public void aprovar(ContaCapital contaCapital, Integer idAtividade, Integer idOcorrenciaAtividade, Integer idProcedimento, String nomeProcedimento) throws BancoobException {
		getServico().aprovar(contaCapital, idAtividade, idOcorrenciaAtividade, idProcedimento, nomeProcedimento);
	}
}