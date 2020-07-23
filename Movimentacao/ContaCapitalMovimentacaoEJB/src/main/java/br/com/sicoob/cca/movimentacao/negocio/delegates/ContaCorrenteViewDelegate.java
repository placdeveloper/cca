/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCorrenteView;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ContaCorrenteViewServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

/**
 * Classe Delegate ContaCorrenteViewDelegate
 * @author Nairon.Silva
 */
public class ContaCorrenteViewDelegate extends ContaCapitalMovimentacaoCrudDelegate<ContaCorrenteView, ContaCorrenteViewServico> {

	/**
	 * Instancia um novo ContaCorrenteViewDelegate.
	 */
	ContaCorrenteViewDelegate() {
		
	}

	/**
	 * Locator ContaCorrenteViewDelegate.
	 *
	 * @return ContaCorrenteViewServico
	 */
	@Override
	protected ContaCorrenteViewServico localizarServico() {
		return (ContaCorrenteViewServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarContaCorrenteViewServico();
	}

	/**
	 * Verifica se a conta corrente esta bloqueada ou encerrada
	 * @param idInstituicao
	 * @param numContaCorrente
	 * @return
	 * @throws BancoobException
	 */
	public boolean verificarContaCorrenteBloqueadaEncerrada(Integer idInstituicao, Integer numContaCorrente) throws BancoobException {
		return getServico().verificarContaCorrenteBloqueadaEncerrada(idInstituicao, numContaCorrente);
	}	
	
}
