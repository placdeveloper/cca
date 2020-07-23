/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.cca.cadastro.infraestrutura.mensagens.ContaCapitalCadastroResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema ContaCapitalCadastro
 * 
 * @author Stefanini IT Solutions
 */
public class ContaCapitalCadastroInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return ContaCapitalCadastroResourceBundle.getInstance();
	}
}
