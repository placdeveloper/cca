package br.com.sicoob.cca.frontoffice.negocio.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.cca.frontoffice.infraestrutura.mensagens.ContaCapitalFrontofficeResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema .
 */
public class ContaCapitalFrontofficeInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * Recupera o valor de resourceBundle.
	 *
	 * @return o valor de resourceBundle
	 * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return ContaCapitalFrontofficeResourceBundle.getInstance();
	}
}
