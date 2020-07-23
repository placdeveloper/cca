/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.cca.processamento.infraestrutura.mensagens.ProcessamentoResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema
 * Processamento
*/
public class ProcessamentoInternacionalizacaoInterceptor extends
		InternacionalizacaoInterceptor {

	/**
	 * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return ProcessamentoResourceBundle.getInstance();
	}
}
