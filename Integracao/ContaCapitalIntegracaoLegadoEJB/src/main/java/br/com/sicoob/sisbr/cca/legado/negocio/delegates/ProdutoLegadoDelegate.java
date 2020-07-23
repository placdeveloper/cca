/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ProdutoLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe ProdutoLegadoDelegate.
 */
public class ProdutoLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<ProdutoLegadoServico> {

	/**
	 * Recupera a unica instancia de ProdutoLegadoDelegate.
	 *
	 * @return uma instancia de ProdutoLegadoDelegate
	 */
	public static ProdutoLegadoDelegate getInstance() {
		return new ProdutoLegadoDelegate();
	}
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected ProdutoLegadoServico localizarServico() {
		return (ProdutoLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarProdutoLegadoServico();
	}
	
	/**
	 * {@link ProdutoLegadoServico#obterDataAtualProduto(Integer, Integer)}
	 */
	public Date obterDataAtualProduto(Integer idProduto, Integer idInstituicao) throws BancoobException {
		return getServico().obterDataAtualProduto(idProduto, idInstituicao);
	}
	
	/**
	 * Obtem a data atual do produto CCA para o usuario logado.
	 * @return
	 * @throws BancoobException
	 */
	public Date obterDataAtualProdutoCCALogado() throws BancoobException {
		return obterDataAtualProduto(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, 
				Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
	}
	
	/**
	 * {@link ProdutoLegadoServico#obterDataAnteriorProduto(Integer, Integer)}
	 */
	public Date obterDataAnteriorProduto(Integer idProduto, Integer numCoop) throws BancoobException {
		return getServico().obterDataAnteriorProduto(idProduto, numCoop);
	}

	public boolean isTrocouMesProduto() {
		
		
		return false;
	}
}