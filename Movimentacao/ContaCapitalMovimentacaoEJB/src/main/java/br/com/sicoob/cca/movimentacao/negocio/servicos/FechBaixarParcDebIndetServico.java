package br.com.sicoob.cca.movimentacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoSQL;

/**
 * @author Antonio.Genaro
 */
public interface FechBaixarParcDebIndetServico extends ContaCapitalMovimentacaoServico, Fechamento, FechamentoSQL {
	/**
	 * Baixa integraliza��o por d�bito indeterminado SQL.
	 *
	 * @param numCooperativa
	 * @return 
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void rodarSQL(Integer numCoop) throws BancoobException;	

	/**
	 * Baixa integraliza��o por d�bito indeterminado DB2.
	 *
	 * @param numCooperativa
	 * @return 
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void rodar(Integer numCoop) throws BancoobException;	
}
