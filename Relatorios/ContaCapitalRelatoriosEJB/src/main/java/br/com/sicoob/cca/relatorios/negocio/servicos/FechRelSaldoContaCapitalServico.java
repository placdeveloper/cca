package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;

/**
* @author Ricardo.Barcante
*/
public interface FechRelSaldoContaCapitalServico extends ContaCapitalRelatoriosServico, Fechamento {
	public void rodar(Integer idInstituicao) throws BancoobException;
}