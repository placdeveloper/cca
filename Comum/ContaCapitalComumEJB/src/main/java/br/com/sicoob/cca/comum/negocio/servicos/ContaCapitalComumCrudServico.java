/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.servicos;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.negocio.servicos.BancoobCrudServico;

/**
 * Interface que define um contrato padrao para as operacoes de 
 * CRUD de todo o sistema ContaCapitalComum
 *
 * @author Stefanini IT Solutions
 */
public interface ContaCapitalComumCrudServico<T extends BancoobEntidade> extends ContaCapitalComumServico,
		BancoobCrudServico<T> {

}
