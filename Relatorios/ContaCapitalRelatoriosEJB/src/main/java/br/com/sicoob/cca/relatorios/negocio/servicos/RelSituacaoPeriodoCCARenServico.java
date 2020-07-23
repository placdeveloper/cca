/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoPeriodoCCARenDTO;

/**
 * The Interface RelSituacaoPeriodoCCARenServico.
 */
public interface RelSituacaoPeriodoCCARenServico extends ContaCapitalRelatoriosServico {

	/**
	 * Gerar relatorio.
	 *
	 * @param dtoEntrada the dto entrada
	 * @return the object
	 * @throws BancoobException the bancoob exception
	 */
	Object gerarRelatorio(RelSituacaoPeriodoCCARenDTO dtoEntrada) throws BancoobException;
}