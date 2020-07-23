/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.servicos;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.negocio.servicos.BancoobCrudServico;

/**
 * Interface que define um contrato padrao para as operacoes de 
 * CRUD 
 *
 * @author Balbi
 */

public interface ContaCapitalReplicacaoCrudServico<T extends BancoobEntidade> extends ContaCapitalReplicacaoServico,
		BancoobCrudServico<T> {

}
