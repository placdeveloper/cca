/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.sicoob.cca.cadastro.negocio.servicos.ContaCapitalCadastroCrudServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema ContaCapitalCadastro
 * 
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalCadastroCrudDelegate<T extends BancoobEntidade, S extends ContaCapitalCadastroCrudServico<T>>
		extends BancoobCrudDelegate<T, S> {

}