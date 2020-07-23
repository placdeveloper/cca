package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapital;
import br.com.sicoob.cca.movimentacao.negocio.servicos.HistBloqueioContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

/**
 * Delegate HistBloqueioContaCapitalDelegate
 */
public class HistBloqueioContaCapitalDelegate extends ContaCapitalMovimentacaoCrudDelegate<HistBloqueioCapital, HistBloqueioContaCapitalServico> {

	@Override
	protected HistBloqueioContaCapitalServico localizarServico() {
		return ContaCapitalMovimentacaoServiceLocator.getInstance().localizarHistBloqueioContaCapitalServico();
	}

}
