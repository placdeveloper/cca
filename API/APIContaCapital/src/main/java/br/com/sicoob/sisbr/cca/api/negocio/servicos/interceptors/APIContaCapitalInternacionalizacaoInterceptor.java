/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.cca.api.infraestrutura.mensagens.APIContaCapitalResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema
 * APIContaCapital
*/
public class APIContaCapitalInternacionalizacaoInterceptor extends
		InternacionalizacaoInterceptor {

	/**
	 * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return APIContaCapitalResourceBundle.getInstance();
	}
}
