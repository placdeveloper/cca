/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import br.com.sicoob.cca.cadastro.negocio.servicos.SituacaoCadastroPropostaServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;

/**
 * @author Marco.Nascimento
 */
public class SituacaoCadastroPropostaDelegate extends ContaCapitalCadastroCrudDelegate<SituacaoCadastroProposta, SituacaoCadastroPropostaServico> {

	/**
	 * Instancia um novo SituacaoCadastroPropostaDelegate.
	 */
	SituacaoCadastroPropostaDelegate() {
		
	}

	/**
	 * Locator SituacaoCadastroPropostaServico
	 */
	@Override
	protected SituacaoCadastroPropostaServico localizarServico() {
		return (SituacaoCadastroPropostaServico) ContaCapitalCadastroServiceLocator.getInstance().localizarSituacaoCadastroPropostaServico();
	}
}