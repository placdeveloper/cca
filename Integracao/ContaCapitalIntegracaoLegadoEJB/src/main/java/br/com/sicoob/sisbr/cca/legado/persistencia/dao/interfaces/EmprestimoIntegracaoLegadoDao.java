/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.EmprestimoIntegracaoDTO;

/**
 * @author Marco.Nascimento
 */
public interface EmprestimoIntegracaoLegadoDao {

	/**
	 * Consulta operacoes de credito do cliente (SQL Server)
	 * @param numCliente
	 * @return
	 * @throws BancoobException
	 */
	List<EmprestimoIntegracaoDTO> consultarEmprestimos(Integer numCliente, Long numContaCorrente) throws BancoobException;
}