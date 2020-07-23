/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoMatriculaCCARenDTO;

/**
 * The Interface RelSituacaoMatriculaCCARenServico.
 */
public interface RelSituacaoMatriculaCCARenServico extends ContaCapitalRelatoriosServico {

	/**
	 * Gerar relatorio.
	 *
	 * @param dtoEntrada the dto entrada
	 * @return the object
	 * @throws BancoobException the bancoob exception
	 */
	Object gerarRelatorio(RelSituacaoMatriculaCCARenDTO dtoEntrada) throws BancoobException;
}