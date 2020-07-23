package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.sicoob.cca.entidades.negocio.entidades.OrigemBloqueioCapital;
import br.com.sicoob.cca.movimentacao.negocio.servicos.OrigemBloqueioCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

/**
 * Delegate para operacoes de OrigemBloqueioCapital
 * @author Nairon.Silva
 */
public class OrigemBloqueioCapitalDelegate extends ContaCapitalMovimentacaoCrudDelegate<OrigemBloqueioCapital, OrigemBloqueioCapitalServico> {

	@Override
	protected OrigemBloqueioCapitalServico localizarServico() {
		return (OrigemBloqueioCapitalServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarOrigemBloqueioCapitalServico();
	}
	
}
