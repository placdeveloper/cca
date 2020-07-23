/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.servicos;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.negocio.servicos.BancoobCrudServico;

/**
 * Interface que define um contrato padrao para as operacoes de 
 * CRUD .
 *
 * @author Balbi
 * @param <T> o tipo generico
 */

public interface ContaCapitalMovimentacaoCrudServico<T extends BancoobEntidade> extends ContaCapitalMovimentacaoServico,
		BancoobCrudServico<T> {

}
