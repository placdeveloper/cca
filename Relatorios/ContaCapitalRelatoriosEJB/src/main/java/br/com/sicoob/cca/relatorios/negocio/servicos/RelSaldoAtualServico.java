/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSaldoAtualDTO;

/**
 * The Interface RelSaldoAtualServico.
 */
public interface RelSaldoAtualServico extends ContaCapitalRelatoriosServico {

	/**
	 * Gerar relatorio saldo atual.
	 *
	 * @param dtoEntrada the dto entrada
	 * @return the object
	 * @throws BancoobException the bancoob exception
	 */
	Object gerarRelatorioSaldoAtual(RelSaldoAtualDTO dtoEntrada) throws BancoobException;
}