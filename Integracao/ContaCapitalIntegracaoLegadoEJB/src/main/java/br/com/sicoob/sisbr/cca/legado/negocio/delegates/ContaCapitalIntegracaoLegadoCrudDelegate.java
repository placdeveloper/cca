/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalIntegracaoLegadoEntidade;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ContaCapitalIntegracaoLegadoCrudServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema ContaCapitalIntegracaoLegado
*/
public abstract class ContaCapitalIntegracaoLegadoCrudDelegate<T extends ContaCapitalIntegracaoLegadoEntidade, S extends ContaCapitalIntegracaoLegadoCrudServico<T>>
		extends BancoobCrudDelegate<T, S> {

}