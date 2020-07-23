/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.negocio.servicos.BancoobCrudServico;

/**
 * Interface que define um contrato padrao para as operacoes de 
 * CRUD de todo o sistema ContaCapitalRelatorios
 *
 * @author Stefanini IT Solutions
 */
public interface ContaCapitalRelatoriosCrudServico<T extends BancoobEntidade> extends ContaCapitalRelatoriosServico,
		BancoobCrudServico<T> {

}
