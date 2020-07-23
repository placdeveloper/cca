/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.cca.comum.infraestrutura.mensagens.ContaCapitalComumResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema ContaCapitalComum
 * 
 * @author Stefanini IT Solutions
 */
public class ContaCapitalComumInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return ContaCapitalComumResourceBundle.getInstance();
	}
}
