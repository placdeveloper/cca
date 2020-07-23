/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;


/**
 * A Interface PLDContaCapitalLegadoServico.
 */
public interface PLDContaCapitalLegadoServico extends ContaCapitalIntegracaoLegadoServico {

	/**
	 * Gerar PLD (SQL Server)
	 * @param numCoop
	 * @return true se gerado com sucesso
	 * @throws BancoobException
	 */
	Boolean gerarPLD(Integer numCoop) throws BancoobException;
}