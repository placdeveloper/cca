/*
 * 
 */
package br.com.sicoob.sisbr.cca.movimentacao;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.delegates.BloqueioContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.LancamentoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;


/**
 * Classe Auxiliar da camada de integração.
 *
 * @author Marcos.Balbi
 */
public abstract class MovimentacaoContaCapital extends BancoobFachada {

	/**
	 * Calcula o valor subscrito da conta capital considerando os lançamentos do dia.
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	protected BigDecimal calcularValorSubscrito(Integer idContaCapital) throws BancoobException {
		LancamentoContaCapitalDelegate lancamentoDelegate = 
				ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarLancamentoContaCapitalDelegate();
		return lancamentoDelegate.calcularValorSubscrito(idContaCapital);
	}
	
	/**
	 * Calcula o valor integralizado da conta capital considerando os lançamentos do dia.
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	protected BigDecimal calcularValorIntegralizado(Integer idContaCapital) throws BancoobException {
		LancamentoContaCapitalDelegate lancamentoDelegate = 
				ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarLancamentoContaCapitalDelegate();
		return lancamentoDelegate.calcularValorIntegralizado(idContaCapital);
	}
	
	/**
	 * Calcula o valor de devolucao da conta capital considerando os lançamentos do dia.
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	protected BigDecimal calcularValorDevolucao(Integer idContaCapital) throws BancoobException {
		LancamentoContaCapitalDelegate lancamentoDelegate = 
				ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarLancamentoContaCapitalDelegate();
		return lancamentoDelegate.calcularValorDevolucao(idContaCapital);
	}
	
	/**
	 * Calcula o valor bloqueado da conta capital.
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	protected BigDecimal calcularValorBloqueado(Integer idContaCapital) throws BancoobException {
		BloqueioContaCapitalDelegate bloqueioContaCapitalDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarBloqueioContaCapitalDelegate();
		return bloqueioContaCapitalDelegate.calcularValorBloqueado(idContaCapital);			
	}
	
	/**
	 * Verifica se a conta capital nao existe
	 * @param contaCapital
	 * @throws ContaCapitalMovimentacaoNegocioException
	 */
	protected void verificarContaCapitalNaoEncontrada(ContaCapital contaCapital) throws ContaCapitalMovimentacaoNegocioException {
		if (contaCapital == null) {
			throw new ContaCapitalMovimentacaoNegocioException("Conta Capital não encontrada.");			
		}
	}
	
}
