/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.sicoob.cca.comum.negocio.entidades.ContaCapitalComumEntidade;
import br.com.sicoob.cca.comum.negocio.servicos.ContaCapitalComumCrudServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema ContaCapitalComum
 * 
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalComumCrudDelegate<T extends ContaCapitalComumEntidade, S extends ContaCapitalComumCrudServico<T>>
		extends BancoobCrudDelegate<T, S> {

}