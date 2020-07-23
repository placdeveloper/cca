/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;
import br.com.sicoob.cca.entidades.negocio.entidades.HistParticipacaoCentralBancoob;
import br.com.sicoob.cca.movimentacao.negocio.servicos.HistParticipacaoCentralBancoobServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

/**
 * @author Marco.Nascimento
 */
public class HistParticipacaoCentralBancoobDelegate extends ContaCapitalMovimentacaoCrudDelegate<HistParticipacaoCentralBancoob, HistParticipacaoCentralBancoobServico> {

	/**
	 * Instancia um novo HistParticipacaoCentralBancoobDelegate.
	 */
	HistParticipacaoCentralBancoobDelegate() {
		
	}			
	
	/**
	 * Locator HistParticipacaoCentralBancoobServico
	 */
	@Override
	protected HistParticipacaoCentralBancoobServico localizarServico() {
		return (HistParticipacaoCentralBancoobServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarHistParticipacaoCentralBancoobServico();
	}	
}
