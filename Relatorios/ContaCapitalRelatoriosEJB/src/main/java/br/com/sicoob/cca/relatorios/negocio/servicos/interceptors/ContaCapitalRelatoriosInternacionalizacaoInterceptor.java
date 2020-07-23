/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos.interceptors;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.cca.relatorios.infraestrutura.mensagens.ContaCapitalRelatoriosResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema ContaCapitalRelatorios
 * 
 * @author Stefanini IT Solutions
 */
public class ContaCapitalRelatoriosInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

	/**
	 * @return ContaCapitalRelatoriosResourceBundle
	 */
	@Override
	protected BancoobResourceBundle getResourceBundle() {
		return ContaCapitalRelatoriosResourceBundle.getInstance();
	}
}
