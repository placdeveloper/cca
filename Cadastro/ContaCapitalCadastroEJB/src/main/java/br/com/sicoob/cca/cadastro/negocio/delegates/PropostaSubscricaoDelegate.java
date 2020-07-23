/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.servicos.PropostaSubscricaoServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;

/**
 * @author Marco.Nascimento
 */
public class PropostaSubscricaoDelegate extends ContaCapitalCadastroCrudDelegate<PropostaSubscricao, PropostaSubscricaoServico> {

	/**
	 * Instancia um novo PropostaSubscricaoDelegate.
	 */
	PropostaSubscricaoDelegate() {
		
	}

	/**
	 * Locator PropostaSubscricaoServico
	 */
	@Override
	protected PropostaSubscricaoServico localizarServico() {
		return (PropostaSubscricaoServico) ContaCapitalCadastroServiceLocator.getInstance().localizarPropostaSubscricaoServico();
	}
	
	/**
	 * {@link PropostaSubscricaoServico#incluirExterno(PropostaSubscricao)}
	 */		
	public PropostaSubscricao incluirExterno(PropostaSubscricao proposta) throws BancoobException {
		return getServico().incluirExterno(proposta);		
	}
	
}