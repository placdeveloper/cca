/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.cca.comum.negocio.servicos.ContaCapitalComumServico;

/**
 * Business delegate a ser usado pelo Sistema ContaCapitalComum.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalComumDelegate<T extends ContaCapitalComumServico> extends
		BancoobDelegate<T> {

}
