/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCorrenteView;

/**
 * A Interface ContaCorrenteViewServico
 * @author Nairon.Silva
 */
public interface ContaCorrenteViewServico extends ContaCapitalMovimentacaoCrudServico<ContaCorrenteView> {

	/**
	 * Verifica se a conta corrente esta bloqueada ou encerrada.
	 * @param idInstituicao
	 * @param numContaCorrente
	 * @return
	 * @throws BancoobException
	 */
	boolean verificarContaCorrenteBloqueadaEncerrada(Integer idInstituicao, Integer numContaCorrente) throws BancoobException;
	
	/**
	 * VErifica se a conta corrente e daquela pessoa ou instituição
	 * @param idInstituicao
	 * @param idPessoa
	 * @param numContaCorrente
	 * @return
	 * @throws BancoobException
	 */
	boolean verificarContaCorrentePessoa(Integer idInstituicao,Integer idPessoa, Integer numContaCorrente) throws BancoobException;
}
