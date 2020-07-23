/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ContaCapitalMovimentacaoCrudServico;

/**
 * A Classe ContaCapitalMovimentacaoCrudDelegate.
 *
 * @author Balbi
 * @param <T> o tipo generico
 * @param <S> o tipo generico
 */
public abstract class ContaCapitalMovimentacaoCrudDelegate<T extends BancoobEntidade, S extends ContaCapitalMovimentacaoCrudServico<T>>
		extends BancoobCrudDelegate<T, S> {

}