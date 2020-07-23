/*
 * 
 */
package br.com.sicoob.sisbr.cca.apirest.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.cca.apirest.infraestrutura.mensagens.APIRestContaCapitalResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema
 * APIRestContaCapital
*/
public class APIRestContaCapitalInternacionalizacaoInterceptor extends
		InternacionalizacaoInterceptor {

	/**
	 * @see br.com.bancoob.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return APIRestContaCapitalResourceBundle.getInstance();
	}
}
