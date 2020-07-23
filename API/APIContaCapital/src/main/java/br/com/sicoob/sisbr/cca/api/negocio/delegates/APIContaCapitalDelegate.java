/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.APIContaCapitalServico;

/**
 * Business delegate a ser usado pelo Sistema APIContaCapital.
 * 
 */
public abstract class APIContaCapitalDelegate<T extends APIContaCapitalServico>
		extends BancoobDelegate<T> {

}