/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.cca.legado.infraestrutura.mensagens.ContaCapitalIntegracaoLegadoResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema
 * ContaCapitalIntegracaoLegado
*/
public class ContaCapitalIntegracaoLegadoInternacionalizacaoInterceptor extends
		InternacionalizacaoInterceptor {

	/**
	 * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return ContaCapitalIntegracaoLegadoResourceBundle.getInstance();
	}
}
