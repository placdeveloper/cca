/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ContaCapitalMovimentacaoServico;

/**
 * A Classe ContaCapitalMovimentacaoDelegate.
 *
 * @author Balbi
 * @param <T> o tipo generico
 */
public abstract class ContaCapitalMovimentacaoDelegate<T extends ContaCapitalMovimentacaoServico> extends
		BancoobDelegate<T> {

}
