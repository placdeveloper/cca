/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelFichaPropostaMatriculaDTO;

/**
 * Interface para trazer os m�todos de opera��es de crud para gerar o 
 * relat�rio de ficha proposta matricula
 * 
 * @author Guilherme.Nunes
 *
 */
public interface RelFichaPropostaMatriculaServico extends ContaCapitalRelatoriosServico {

	/**
	 * M�todo respons�vel para trazer os dados para gerar relat�rio de ficha proposta de matr�cula
	 * @param dtoEntrada
	 * @return
	 * @throws BancoobException
	 */
	 Object gerarRelatorioFichaPropostaMatricula(RelFichaPropostaMatriculaDTO dtoEntrada) throws BancoobException;

}