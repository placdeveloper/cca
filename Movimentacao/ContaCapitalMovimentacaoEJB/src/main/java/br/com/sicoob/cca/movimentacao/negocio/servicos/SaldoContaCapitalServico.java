package br.com.sicoob.cca.movimentacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author rodrigo.melchior
 */

public interface SaldoContaCapitalServico extends ContaCapitalMovimentacaoServico{
	
	void realizarCargaSWS(Integer numCoop) throws BancoobException;

}
