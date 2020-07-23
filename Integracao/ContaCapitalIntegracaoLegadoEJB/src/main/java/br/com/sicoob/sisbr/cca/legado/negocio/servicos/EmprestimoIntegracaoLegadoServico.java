/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.EmprestimoIntegracaoDTO;

/**
 * A Interface EmprestimoIntegracaoServico.
 */
public interface EmprestimoIntegracaoLegadoServico extends ContaCapitalIntegracaoLegadoServico {
	
	/**
	 * Operacoes de credito do cliente
	 * @param numCliente
	 * @param numContaCorrente 
	 * @return
	 * @throws BancoobException
	 */
	List<EmprestimoIntegracaoDTO> consultarEmprestimos(Integer numCliente, Long numContaCorrente) throws BancoobException;
}