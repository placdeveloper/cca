/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.sicoob.cca.relatorios.negocio.servicos.ContaCapitalRelatoriosCrudServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema ContaCapitalRelatorios
 * 
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalRelatoriosCrudDelegate<T extends BancoobEntidade, S extends ContaCapitalRelatoriosCrudServico<T>>
		extends BancoobCrudDelegate<T, S> {

}