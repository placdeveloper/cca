/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LocalizacaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.LocalizacaoIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator.ContaCapitalIntegracaoServiceLocator;

/**
 * A Classe LocalizacaoIntegracaoDelegate.
 */
public class LocalizacaoIntegracaoDelegate extends ContaCapitalIntegracaoDelegate<LocalizacaoIntegracaoServico> {

	/**
	 * Recupera a unica instancia de LocalizacaoIntegracaoDelegate.
	 *
	 * @return uma instancia de LocalizacaoIntegracaoDelegate
	 */
	public static LocalizacaoIntegracaoDelegate getInstance() {
		return new LocalizacaoIntegracaoDelegate();
	}		
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected LocalizacaoIntegracaoServico localizarServico() {
		return (LocalizacaoIntegracaoServico) ContaCapitalIntegracaoServiceLocator.getInstance().localizarLocalizacaoIntegracaoServico();
	}
	
	/**
	 * Consultar localidade.
	 *
	 * @param idLoc o valor de id loc
	 * @return LocalizacaoIntegracaoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public LocalizacaoIntegracaoDTO consultarLocalidade (Integer idLoc) throws BancoobException {
		return getServico().consultarLocalidade(idLoc);
	}
}
