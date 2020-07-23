/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ProdutoInstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GenIntIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator.ContaCapitalIntegracaoServiceLocator;

/**
 * A Classe GenIntIntegracaoDelegate.
 */
public class GenIntIntegracaoDelegate extends	ContaCapitalIntegracaoDelegate<GenIntIntegracaoServico> {

	/**
	 * Recupera a unica instancia de GenIntIntegracaoDelegate.
	 *
	 * @return uma instancia de GenIntIntegracaoDelegate
	 */
	public static GenIntIntegracaoDelegate getInstance() {
		return new GenIntIntegracaoDelegate();
	}		
		
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected GenIntIntegracaoServico localizarServico() {
		return (GenIntIntegracaoServico) ContaCapitalIntegracaoServiceLocator.getInstance().localizarGenIntIntegracaoServico();
	}

	/**
	 * Obtem um DTO com informações sobre o Produto, base DB2
	 * @return
	 * @throws BancoobException
	 */
	public ProdutoInstituicaoIntegracaoDTO obterProdutoInstituicao(Integer idProduto,Integer idInstituicao) throws BancoobException{
		return getServico().obterProdutoInstituicao(idProduto, idInstituicao);
	}

	/**
	 * Verificar dia util.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param data o valor de data
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean verificarDiaUtil(Integer idInstituicao,Date data) throws BancoobException{
		return getServico().verificarDiaUtil(idInstituicao, data);
	}

	/**
	 * Recuperar proximo dia util.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param data o valor de data
	 * @return Date
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Date recuperarProximoDiaUtil(Integer idInstituicao,Date data) throws BancoobException{
		return getServico().recuperarProximoDiaUtil(idInstituicao, data);
	}
	
}
