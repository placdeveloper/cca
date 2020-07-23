/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.negocio.servicos.BancoobCrudServico;

/**
 * Interface que define um contrato padrao para as operacoes de 
 * CRUD de todo o sistema ContaCapitalCadastro
 *
 * @author Stefanini IT Solutions
 */

public interface ContaCapitalCadastroCrudServico<T extends BancoobEntidade> extends ContaCapitalCadastroServico,
		BancoobCrudServico<T> {

}
