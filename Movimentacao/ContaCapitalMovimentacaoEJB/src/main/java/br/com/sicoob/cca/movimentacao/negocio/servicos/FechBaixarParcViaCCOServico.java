package br.com.sicoob.cca.movimentacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoSQL;

/**
 * @author Antonio.Genaro
 */
public interface FechBaixarParcViaCCOServico extends ContaCapitalMovimentacaoServico, Fechamento, FechamentoSQL {

	/**
	 * Baixa as parcelas em aberto via cco.
	 *
	 * @param numCooperativa
	 * @return 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void rodarSQL(Integer numCoop) throws BancoobException;	

	/**
	 * Baixa as parcelas em aberto via cco DB2.
	 *
	 * @param numCooperativa
	 * @return 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void rodar(Integer numCoop) throws BancoobException;	

}
