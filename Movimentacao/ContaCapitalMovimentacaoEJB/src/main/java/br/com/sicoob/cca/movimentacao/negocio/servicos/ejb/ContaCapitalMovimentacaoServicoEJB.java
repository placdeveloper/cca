package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ContaCapitalMovimentacaoServico;

/**
 *  Implementacao abstrata de movimentacao da conta capital.
 *
 * @author Antonio.Genaro
 */
public abstract class ContaCapitalMovimentacaoServicoEJB extends BancoobServicoEJB implements ContaCapitalMovimentacaoServico {

	/**
	 * Verifica se a conta capital nao existe
	 * @param contaCapital
	 * @throws ContaCapitalMovimentacaoNegocioException
	 */
	protected void verificarContaCapitalNaoEncontrada(ContaCapital contaCapital) throws ContaCapitalMovimentacaoNegocioException {
		if (contaCapital == null) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_INTEG_CCA_NAO_ENC");			
		}
	}
	
}