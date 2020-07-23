/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.cca.cadastro.negocio.servicos.ContaCapitalCadastroServico;

/**
 * Business delegate a ser usado pelo Sistema ContaCapitalCadastro.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalCadastroDelegate<T extends ContaCapitalCadastroServico> extends
		BancoobDelegate<T> {

}
