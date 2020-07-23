/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContaCapitalIntegracaoServico;

/**
 * Business delegate a ser usado pelo Sistema ContaCapitalIntegracao.
 * 
 */
public abstract class ContaCapitalIntegracaoDelegate<T extends ContaCapitalIntegracaoServico>
		extends BancoobDelegate<T> {

}