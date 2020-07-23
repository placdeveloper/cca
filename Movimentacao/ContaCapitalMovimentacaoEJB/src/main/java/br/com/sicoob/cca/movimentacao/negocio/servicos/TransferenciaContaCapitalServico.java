/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.TransferenciaRenDTO;

// TODO: Auto-generated Javadoc
/**
 * A Interface TransferenciaContaCapitalServico.
 *
 * @author Antonio.Genaro
 */
public interface TransferenciaContaCapitalServico extends ContaCapitalMovimentacaoServico {
	
	/**
	 * incluir.
	 *
	 * @param transferenciaRenDTO o valor de transferencia ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void incluir(TransferenciaRenDTO transferenciaRenDTO) throws BancoobException;	

}
