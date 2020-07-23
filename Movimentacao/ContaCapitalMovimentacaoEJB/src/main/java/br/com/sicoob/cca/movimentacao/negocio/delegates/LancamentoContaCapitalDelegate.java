/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.servicos.LancamentoContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * A Classe LancamentoContaCapitalDelegate.
 *
 * @author Antonio.Genaro
 */
public class LancamentoContaCapitalDelegate extends ContaCapitalMovimentacaoCrudDelegate<LancamentoContaCapital, LancamentoContaCapitalServico> {

	/**
	 * Instancia um novo LancamentoContaCapitalDelegate.
	 */
	LancamentoContaCapitalDelegate(){
		
	}

	/**
	 * Locator CadastroContaCapitalServico.
	 *
	 * @return LancamentoContaCapitalServico
	 */
	@Override
	protected LancamentoContaCapitalServico localizarServico() {
		return (LancamentoContaCapitalServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarLancamentoContaCapitalServico();
	}	
	
	/**
	 * {@link LancamentoContaCapitalServico#pesquisarLancamentosDoDiaPorContaCapital(Integer)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<LancamentoContaCapital> pesquisarLancamentosDoDiaPorContaCapital(Integer idContaCapital) throws BancoobException {
		return getServico().pesquisarLancamentosDoDiaPorContaCapital(idContaCapital);
	}
	
	
	/**
	 * {@link LancamentoContaCapitalServico#pesquisarLancamentosDoDiaTipoHistContaCapital()}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @param idTipoHistorico o valor de id tipo historico
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<LancamentoContaCapital> pesquisarLancamentosDoDiaTipoHistContaCapital(Integer idContaCapital, Integer idInstituicao, Integer idTipoHistorico) throws BancoobException {
		return getServico().pesquisarLancamentosDoDiaTipoHistContaCapital(idContaCapital, idInstituicao, idTipoHistorico);		
	}
	
	/**
	 * Calcula o valor subscrito da conta capital considerando os lançamentos do dia
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public BigDecimal calcularValorSubscrito(Integer idContaCapital) throws BancoobException {
		return getServico().calcularValorSubscrito(idContaCapital);
	}
	
	/**
	 * Calcula o valor integralizado da conta capital considerando os lançamentos do dia
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public BigDecimal calcularValorIntegralizado(Integer idContaCapital) throws BancoobException {
		return getServico().calcularValorIntegralizado(idContaCapital);
	}
	
	/**
	 * Calcula o valor de devolucao da conta capital considerando os lançamentos do dia
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public BigDecimal calcularValorDevolucao(Integer idContaCapital) throws BancoobException {
		return getServico().calcularValorDevolucao(idContaCapital);
	}
	
	/**
	 * Consulta quantidade de lancamento de subscrição por conta capital 
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public Integer pesquisarCountLancamentosPorContaCapitalSubscricao(Integer idContaCapital) throws BancoobException {
		return getServico().pesquisarCountLancamentosPorContaCapitalSubscricao(idContaCapital);
	}
	
}