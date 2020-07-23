/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.cca.integracao.infraestrutura.mensagens.ContaCapitalIntegracaoResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema
 * ContaCapitalIntegracao
*/
public class ContaCapitalIntegracaoInternacionalizacaoInterceptor extends
		InternacionalizacaoInterceptor {

	/**
	 * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return ContaCapitalIntegracaoResourceBundle.getInstance();
	}
}
