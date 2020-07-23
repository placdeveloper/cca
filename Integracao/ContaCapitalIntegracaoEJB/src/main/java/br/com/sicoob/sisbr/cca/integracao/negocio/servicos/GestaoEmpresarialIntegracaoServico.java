/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * A Interface GestaoEmpresarialIntegracaoServico.
 */
public interface GestaoEmpresarialIntegracaoServico extends ContaCapitalIntegracaoServico {
	
	/**
	 * Verifica se e primeira carga para a cooperativa
	 * @return
	 * @throws BancoobException
	 */
	Boolean isPrimeiraCarga(Integer numCoop) throws BancoobException;
}