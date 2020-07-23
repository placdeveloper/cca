package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalIntegracaoLegadoServico;

/**
 * Implementacao base de todos os servicos do sistema ContaCapitalIntegracaoLegado
 * 
 */
public abstract class ContaCapitalIntegracaoLegadoServicoEJB extends BancoobServicoEJB implements ContaCapitalIntegracaoLegadoServico {

	/**
	 * Obter data atual produto.
	 *
	 * @return Date
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected Date obterDataAtualProduto() throws BancoobException {
		ProdutoLegadoDelegate prodLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarProdutoLegadoDelegate();
		return prodLegadoDelegate.obterDataAtualProdutoCCALogado();
	}

}
