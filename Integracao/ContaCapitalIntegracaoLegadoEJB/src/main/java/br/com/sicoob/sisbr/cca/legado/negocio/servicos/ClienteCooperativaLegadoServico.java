/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author Marco.Nascimento
 */
public interface ClienteCooperativaLegadoServico extends ContaCapitalIntegracaoLegadoServico {
	
	/**
	 * Consulta numero do cliente por cooperativa
	 * @param numCoop
	 * @param numPac
	 * @return numCliente
	 * @throws BancoobException
	 */
	Integer consultarClienteCooperativa(Integer numCoop, Integer numPac) throws BancoobException;
}