/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoob;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ParticipacaoIndiretaBancoobServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

/**
 * @author Marco.Nascimento
 */
public final class ParticipacaoIndiretaBancoobDelegate extends ContaCapitalMovimentacaoCrudDelegate<ParticipacaoIndiretaBancoob, ParticipacaoIndiretaBancoobServico> {
	
	/**
	 * Instancia um novo ParticipacaoIndiretaBancoobDelegate.
	 */
	ParticipacaoIndiretaBancoobDelegate() {
	
	}

	/**
	 * Locator ParticipacaoIndiretaBancoobServico
	 */
	@Override
	protected ParticipacaoIndiretaBancoobServico localizarServico() {
		return (ParticipacaoIndiretaBancoobServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarParticipacaoIndiretaBancoobServico();
	}
}