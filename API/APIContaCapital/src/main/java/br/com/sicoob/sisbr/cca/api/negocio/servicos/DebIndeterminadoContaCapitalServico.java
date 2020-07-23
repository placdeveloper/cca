/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ContaCapitalDebIndeterminadoDTO;

/**
 * @author Marco.Nascimento
 */
public interface DebIndeterminadoContaCapitalServico extends APIContaCapitalServico {

	/**
	 * Cria debito indeterminado (mensal) para a conta capital <br>
	 * Para o cenário do faça parte,o débito será parametrizado mensal(TipoPeriodoDeb = 1), via cco(CodFormaDeb = 2) 
	 * e é controlado pelo dia de vencimento do débito(DataVencimentoDeb). 
	 * @param dto
	 * @return true se inclusao realizada com sucesso
	 * @throws BancoobException
	 */
	Boolean incluirDebitoIndeterminado(ContaCapitalDebIndeterminadoDTO dto) throws BancoobException;
}