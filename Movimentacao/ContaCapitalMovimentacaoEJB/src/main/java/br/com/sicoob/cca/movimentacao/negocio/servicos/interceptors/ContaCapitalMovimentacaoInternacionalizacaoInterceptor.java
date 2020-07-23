/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.cca.movimentacao.infraestrutura.mensagens.ContaCapitalMovimentacaoResourceBundle;

// TODO: Auto-generated Javadoc
/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema .
 *
 * @author Balbi
 */
public class ContaCapitalMovimentacaoInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * Recupera o valor de resourceBundle.
	 *
	 * @return o valor de resourceBundle
	 * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return ContaCapitalMovimentacaoResourceBundle.getInstance();
	}
}
