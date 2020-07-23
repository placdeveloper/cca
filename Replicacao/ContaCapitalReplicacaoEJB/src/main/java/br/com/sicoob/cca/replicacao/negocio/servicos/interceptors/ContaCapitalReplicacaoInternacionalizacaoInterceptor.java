/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.cca.replicacao.infraestrutura.mensagens.ContaCapitalReplicacaoResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema 
 * 
 * @author Balbi
 */
public class ContaCapitalReplicacaoInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return ContaCapitalReplicacaoResourceBundle.getInstance();
	}
}
