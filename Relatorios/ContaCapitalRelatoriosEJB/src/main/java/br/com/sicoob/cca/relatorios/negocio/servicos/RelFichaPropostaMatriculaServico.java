/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelFichaPropostaMatriculaDTO;

/**
 * Interface para trazer os métodos de operações de crud para gerar o 
 * relatório de ficha proposta matricula
 * 
 * @author Guilherme.Nunes
 *
 */
public interface RelFichaPropostaMatriculaServico extends ContaCapitalRelatoriosServico {

	/**
	 * Método responsável para trazer os dados para gerar relatório de ficha proposta de matrícula
	 * @param dtoEntrada
	 * @return
	 * @throws BancoobException
	 */
	 Object gerarRelatorioFichaPropostaMatricula(RelFichaPropostaMatriculaDTO dtoEntrada) throws BancoobException;

}