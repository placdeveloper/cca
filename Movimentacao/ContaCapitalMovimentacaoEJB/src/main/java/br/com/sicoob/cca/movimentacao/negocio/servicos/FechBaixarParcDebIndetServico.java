package br.com.sicoob.cca.movimentacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoSQL;

/**
 * @author Antonio.Genaro
 */
public interface FechBaixarParcDebIndetServico extends ContaCapitalMovimentacaoServico, Fechamento, FechamentoSQL {
	/**
	 * Baixa integralização por débito indeterminado SQL.
	 *
	 * @param numCooperativa
	 * @return 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void rodarSQL(Integer numCoop) throws BancoobException;	

	/**
	 * Baixa integralização por débito indeterminado DB2.
	 *
	 * @param numCooperativa
	 * @return 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void rodar(Integer numCoop) throws BancoobException;	
}
